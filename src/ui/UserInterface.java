package ui;

import domain.Appointment;
import domain.Dentist;
import filter.FilterAppointmentsByHour;
import filter.FilterDentistsByAge;
import repo.*;
import repo.BinaryFileDentistsRepository;
import service.ServiceAppointments;
import service.ServiceDentists;
import test.ServiceTests;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;

public class UserInterface {
    // constants
    static final int DISPLAY_ALL_OPTION_APPOINTMENTS = 1;
    static final int ADD_APPOINTMENT_OPTION = 2;
    static final int MODIFY_APPOINTMENT_AT_ID_OPTION = 3;
    static final int REMOVE_APPOINTMENT__AT_ID_OPTION = 4;
    static final int FIND_APPOINTMENT_BY_ID=5;
    static final int GET_IDS_OF_APPOINTMENTS_OF_DOCTOR_OPTION = 6;
    static final int GET_DOCTOR_NAME_MOST_APPOINTMENTS_OPTION = 7;

    static final int DISPLAY_ALL_OPTION_FILTER_APPOINTMENTS = 8;
    static final int ADD_APPOINTMENT_OPTION_FILTER = 9;
    static final int MODIFY_APPOINTMENT_AT_ID_OPTION_FILTER = 10;
    static final int REMOVE_APPOINTMENT_AT_ID_OPTION_FILTER = 11;


    static final int EXIT_OPTION = 0;

    static final int DISPLAY_ALL_OPTION_DENTISTS = 12;
    static final int ADD_DENTIST_OPTION = 13;
    static final int MODIFY_DENTIST_AT_ID_OPTION = 14;
    static final int REMOVE_DENTIST_AT_ID_OPTION = 15;
    static final int FIND_DENTIST_BY_ID=16;

    static final int DISPLAY_ALL_OPTION_FILTER_DENTISTS = 17;
    static final int ADD_DENTIST_OPTION_FILTER = 18;
    static final int MODIFY_DENTIST_AT_ID_OPTION_FILTER = 19;
    static final int REMOVE_DENTIST_AT_ID_OPTION_FILTER = 20;



    //methods
    public static String printMenuAppointments(){
        return "\nMENU APPOINTMENTS\n"+
                DISPLAY_ALL_OPTION_APPOINTMENTS +".Print appointments\n"+
                ADD_APPOINTMENT_OPTION+".Add appointment\n"+ MODIFY_APPOINTMENT_AT_ID_OPTION +".Modify at id " +
                "\n"+REMOVE_APPOINTMENT__AT_ID_OPTION+".Remove appointment\n"+
                FIND_APPOINTMENT_BY_ID+".Find appointment by id\n"+
                GET_IDS_OF_APPOINTMENTS_OF_DOCTOR_OPTION +".Get the ids of the appointments of a certain doctor" +
                "\n"+ GET_DOCTOR_NAME_MOST_APPOINTMENTS_OPTION +".Get the name of the doctor with the most appointments" +
                "\n"+"\nMENU FILTERED APPOINTMENTS\n"+ DISPLAY_ALL_OPTION_FILTER_APPOINTMENTS +".Print appointments\n"+ADD_APPOINTMENT_OPTION_FILTER+".Add appointment\n"+ MODIFY_APPOINTMENT_AT_ID_OPTION_FILTER +".Modify at id"
                + "\n"+ REMOVE_APPOINTMENT_AT_ID_OPTION_FILTER +".Remove appointment\n"+"\n";
    }
    public static String printMenuDentists(){
        return "MENU DENTISTS\n"+ DISPLAY_ALL_OPTION_DENTISTS +".Print dentists\n"+ADD_DENTIST_OPTION+".Add dentist\n"+ MODIFY_DENTIST_AT_ID_OPTION +".Modify at id " +
                "\n"+REMOVE_DENTIST_AT_ID_OPTION+".Remove dentist\n"+
                FIND_DENTIST_BY_ID+".Find dentist by id\n"+
                "\n"+"MENU FILTERED DENTISTS\n"+ DISPLAY_ALL_OPTION_FILTER_DENTISTS +".Print dentists\n"+ADD_DENTIST_OPTION_FILTER+".Add dentist\n"+ MODIFY_DENTIST_AT_ID_OPTION_FILTER +".Modify at id"
                + "\n"+ REMOVE_DENTIST_AT_ID_OPTION_FILTER +".Remove dentist\n"+ EXIT_OPTION +".Exit all";
    }

    public static int readInteger(){
        Scanner scanner = new Scanner(System.in);
        if(scanner.hasNextInt())
            return scanner.nextInt();
        return 0;
    }
    public static String readString(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void testFakeRepository_correctTests_NoError(){
        ServiceTests testFakeRepoCustom = new ServiceTests();
        testFakeRepoCustom.testGetAll_correctSize_NoError();
        System.out.println("Custom fakeRepository tests executed!\n");
        //testFakeRepoCustom.testGetAll_EmptyCollection_ThrowsException();
    }

    public static void main(String []Args) {



            testFakeRepository_correctTests_NoError();

            // FROM FILE !!!!
            IRepository<Integer, Dentist> dentistsRepository = null;
            IRepository<Integer, Appointment> appointmentsRepository = null;

            FileReader fileReader = null; // settings.properties file
            try {
                fileReader = new FileReader("D:\\MAP\\a3-DragosTrandafir\\src\\ui\\settings.properties");

                Properties properties = new Properties();
                properties.load(fileReader);

                String type = (String) properties.getProperty("type");
                System.out.println(type);

                String isAppointmentsIncluded = (String) properties.getProperty("appointments");
                System.out.println(isAppointmentsIncluded);

                String isDentistsIncluded = (String) properties.getProperty("dentists");
                System.out.println(isDentistsIncluded);


                if (type.equals("text")) {
                    String appointmentsPathText = (String) properties.getProperty("appointmentsPathText");
                    System.out.println(appointmentsPathText);

                    String dentistsPathText = properties.getProperty("dentistsPathText");
                    System.out.println(dentistsPathText);

                    if (isAppointmentsIncluded.equals("yes"))
                        appointmentsRepository = new AppointmentsTextFileRepository(appointmentsPathText);
                    else
                        appointmentsRepository = new AppointmentsRepository();

                    if (isDentistsIncluded.equals("yes")) {
                        dentistsRepository = new DentistsTextFileRepository(dentistsPathText);
                    } else
                        dentistsRepository = new DentistsRepository();
                } else if (type.equals("binary")) {

//                    ObjectOutputStream outDentistsStream = new ObjectOutputStream(new FileOutputStream(dentistsPath));
//                    Dentist dentist1 = new Dentist(1, "Dr. Smith", 30);
//                    Dentist dentist2 = new Dentist(2, "Dr. Johnson", 40);
//                    HashMap<Integer, Dentist> initialDentists = new HashMap<>();
//                    initialDentists.put(dentist1.getId(), dentist1);
//                    initialDentists.put(dentist2.getId(), dentist2);
//                    outDentistsStream.writeObject(initialDentists);


//                    ObjectOutputStream outAppointmentsStream = new ObjectOutputStream(new FileOutputStream(appointmentsPath));
//                    Appointment appointment1 = new Appointment(123, "Dragos Trandafir", "Andrei Ion", "07:00", "12.12.2004");
//                    Appointment appointment2 = new Appointment(341, "Patient0", "Doctor7", "10:00", "10.10.2010");
//                    HashMap<Integer, Appointment> initialAppointments = new HashMap<>();
//                    initialAppointments.put(appointment1.getId(), appointment1);
//                    initialAppointments.put(appointment2.getId(), appointment2);

//                    outAppointmentsStream.writeObject(initialAppointments);


                    String appointmentsPathBinary = (String) properties.getProperty("appointmentsPathBinary");
                    System.out.println(appointmentsPathBinary);

                    String dentistsPathBinary = properties.getProperty("dentistsPathBinary");
                    System.out.println(dentistsPathBinary);

                    if (isAppointmentsIncluded.equals("yes"))
                        appointmentsRepository = new BinaryFileAppointmentsRepository(appointmentsPathBinary);
                    else
                        appointmentsRepository = new AppointmentsRepository();

                    if (isDentistsIncluded.equals("yes")) {
                        dentistsRepository = new BinaryFileDentistsRepository(dentistsPathBinary);
                    } else
                        dentistsRepository = new DentistsRepository();
                }else{
                    appointmentsRepository = new AppointmentsRepository();
                    dentistsRepository = new DentistsRepository();
                }


                ServiceAppointments serviceAppointments = new ServiceAppointments(appointmentsRepository);
                ServiceDentists serviceDentists = new ServiceDentists(dentistsRepository);

                 // filtered appointments
                //FilteredRepository<Integer,Appointment> filteredAppointmentsRepository= new FilteredRepository<Integer,Appointment>(new FilterByDoctorName("Doctor0"));// decide here what filter with value you want to impose
                FilteredRepository<Integer, Appointment> filteredAppointmentsRepository
                        = new FilteredRepository<Integer, Appointment>(new FilterAppointmentsByHour("08:00", "16:00"));// decide here what filter with value you want to impose

                ServiceAppointments serviceFilteredAppointments = new ServiceAppointments(filteredAppointmentsRepository);

                serviceFilteredAppointments.add(124,  "Dragos Trandafir", "Andrei Ionescu", "07:00", "12.12.2004");
                serviceFilteredAppointments.add(340, "Patient0", "Doctor0", "10:00", "10.10.2010");
                serviceFilteredAppointments.add(342, "Patient1", "Doctor1", "10:00", "13.10.2010");
                serviceFilteredAppointments.add(343, "Patient2", "Doctor2", "11:00", "10.1.2010");
                serviceFilteredAppointments.add(344, "Patient3", "Doctor0", "17:00", "8.7.2010");


                // filtered dentists
                FilteredRepository<Integer, Dentist> filteredDentistsRepository = new FilteredRepository<Integer, Dentist>(new FilterDentistsByAge(40));
                //FilteredRepository<Integer,Dentist> filteredDentistsRepository = new FilteredRepository<Integer,Dentist>(new FilterDentistsByDentistName("Doctor0"));

                ServiceDentists serviceFilteredDentists = new ServiceDentists(filteredDentistsRepository);

                serviceFilteredDentists.add(6, "Dragos", 34);
                serviceFilteredDentists.add(7, "Doctor0", 36);
                serviceFilteredDentists.add(3, "Doctor1", 40);
                serviceFilteredDentists.add(4, "Andrei Ionescu", 59);
                serviceFilteredDentists.add(5, "Dragos2", 44);


                boolean exit = false;
                while (!exit) {
                    System.out.println(printMenuAppointments());
                    System.out.println(printMenuDentists());
                    System.out.print("\nOption chosen: ");
                    int option = readInteger();

                    switch (option) {
                        //normal options APPOINTMENTS
                        case DISPLAY_ALL_OPTION_APPOINTMENTS:
                            System.out.println(serviceAppointments.toStringRepo());
                            break;
                        case ADD_APPOINTMENT_OPTION:
                            System.out.println("Id:");
                            int id_add_appointment = readInteger();

                            System.out.println("Patient name:");
                            String patientName_add_appointment = readString();

                            System.out.println("Doctor name:");
                            String doctorName_add_appointment = readString();

                            System.out.println("Hour:");
                            String hour_add_appointment = readString();

                            System.out.println("Date:");
                            String date_add_appointment = readString();

                            //Appointment readAppointment_add_appointment = new Appointment(id_add_appointment,patientName_add_appointment,doctorName_add_appointment,hour_add_appointment,date_add_appointment);
                            try {
                            serviceAppointments.add(id_add_appointment, patientName_add_appointment, doctorName_add_appointment, hour_add_appointment, date_add_appointment);
                            }catch(RuntimeException e){
                                System.out.println(e.getMessage());
                            }
                            break;
                        case MODIFY_APPOINTMENT_AT_ID_OPTION:
                            System.out.println("Modify at this id:");
                            int idWhereToModify = readInteger();

                            //System.out.println("Id:");
                            //int id_modify_appointment = readInteger();

                            System.out.println("Patient name:");
                            String patientName_modify_appointment = readString();

                            System.out.println("Doctor name:");
                            String doctorName_modify_appointment = readString();

                            System.out.println("Hour:");
                            String hour_modify_appointment = readString();

                            System.out.println("Date:");
                            String date_modify_appointment = readString();

                            //Appointment readAppointment_modify_appointment = new Appointment(id_modify_appointment,patientName_modify_appointment,doctorName_modify_appointment,hour_modify_appointment,date_modify_appointment);
                            try{
                            serviceAppointments.modify(idWhereToModify, patientName_modify_appointment, doctorName_modify_appointment, hour_modify_appointment, date_modify_appointment);
                            }catch(RuntimeException e){
                                System.out.println(e.getMessage());
                            }
                            break;
                        case REMOVE_APPOINTMENT__AT_ID_OPTION:
                            System.out.println("Remove at this id:");
                            int idWhereToRemove = readInteger();

                            try{
                            serviceAppointments.delete(idWhereToRemove);
                            }catch(RuntimeException e){
                                System.out.println(e.getMessage());
                            }
                            break;
                        case FIND_APPOINTMENT_BY_ID:
                            System.out.println("Id:");
                            int id_find_appointment = readInteger();

                            try{
                            System.out.println(serviceAppointments.findById(id_find_appointment));
                            }catch(RuntimeException e){
                                System.out.println(e.getMessage());
                            }
                            break;
                        case GET_IDS_OF_APPOINTMENTS_OF_DOCTOR_OPTION:
                            System.out.println("Doctor name:");
                            String doctorName_get_ids_of_appointments_of_doctor = readString();

                            System.out.println(serviceAppointments.getIdsOfAppointmentsOfDoctor(doctorName_get_ids_of_appointments_of_doctor));
                            break;
                        case GET_DOCTOR_NAME_MOST_APPOINTMENTS_OPTION:
                            System.out.println(serviceAppointments.getDoctorNameWithMostAppointments());
                            break;
                        case EXIT_OPTION:
                            exit = true;
                            break;

                        // Filter options APPOINTMENTS
                        case DISPLAY_ALL_OPTION_FILTER_APPOINTMENTS:
                            System.out.println(serviceFilteredAppointments.toStringRepo());
                            break;
                        case ADD_APPOINTMENT_OPTION_FILTER:
                            System.out.println("Id:");
                            int id_add_appointment_filtered = readInteger();

                            System.out.println("Patient name:");
                            String patientName_add_appointment_filtered = readString();

                            System.out.println("Doctor name:");
                            String doctorName_add_appointment_filtered = readString();

                            System.out.println("Hour:");
                            String hour_add_appointment_filtered = readString();

                            System.out.println("Date:");
                            String date_add_appointment_filtered = readString();

                            try {
                                serviceFilteredAppointments.add(id_add_appointment_filtered, patientName_add_appointment_filtered, doctorName_add_appointment_filtered
                                        , hour_add_appointment_filtered, date_add_appointment_filtered);
                            } catch (RuntimeException e) {
                                System.out.println(e.getMessage());
                            }

                            break;
                        case MODIFY_APPOINTMENT_AT_ID_OPTION_FILTER:
                            System.out.println("Modify at this id:");
                            int idWhereToModify_filtered = readInteger();


                            System.out.println("Patient name:");
                            String patientName_modify_appointment_filtered = readString();

                            System.out.println("Doctor name:");
                            String doctorName_modify_appointment_filtered = readString();

                            System.out.println("Hour:");
                            String hour_modify_appointment_filtered = readString();

                            System.out.println("Date:");
                            String date_modify_appointment_filtered = readString();

                            try {
                                serviceFilteredAppointments.modify(idWhereToModify_filtered, patientName_modify_appointment_filtered,
                                        doctorName_modify_appointment_filtered, hour_modify_appointment_filtered, date_modify_appointment_filtered);
                            } catch (RuntimeException e) {
                                System.out.println(e.getMessage());
                            }


                            break;
                        case REMOVE_APPOINTMENT_AT_ID_OPTION_FILTER:
                            System.out.println("Remove at this id:");
                            int idWhereToRemove_filtered = readInteger();

                            try {
                                serviceFilteredAppointments.delete(idWhereToRemove_filtered);
                            } catch (RuntimeException e) {
                                System.out.println(e.getMessage());
                            }
                            break;






                        //normal options DENTISTS
                        case DISPLAY_ALL_OPTION_DENTISTS:
                            System.out.println(serviceDentists.toStringRepo());
                            break;
                        case ADD_DENTIST_OPTION:
                            System.out.println("Id:");
                            int id_add_dentist = readInteger();

                            System.out.println("Name:");
                            String name_add_dentists = readString();

                            System.out.println("Age:");
                            int age_add_dentist = readInteger();


                            try{
                            serviceDentists.add(id_add_dentist, name_add_dentists, age_add_dentist);
                            } catch (RuntimeException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case MODIFY_DENTIST_AT_ID_OPTION:
                            System.out.println("Modify at this id:");
                            int idWhereToModify_dentists = readInteger();

                            System.out.println("Name:");
                            String name_modify_dentists = readString();

                            System.out.println("Age:");
                            int age_modify_dentist = readInteger();


                            try{
                            serviceDentists.modify(idWhereToModify_dentists, name_modify_dentists, age_modify_dentist);
                            } catch (RuntimeException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case REMOVE_DENTIST_AT_ID_OPTION:
                            System.out.println("Remove at this id:");
                            int idWhereToRemove_dentists = readInteger();

                            try{
                            serviceDentists.delete(idWhereToRemove_dentists);
                            } catch (RuntimeException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case FIND_DENTIST_BY_ID:
                            System.out.println("Id:");
                            int id_find_dentist = readInteger();

                            try{
                            System.out.println(serviceDentists.findById(id_find_dentist));
                            } catch (RuntimeException e) {
                                System.out.println(e.getMessage());
                            }
                            break;

                        // Filter options DENTISTS
                        case DISPLAY_ALL_OPTION_FILTER_DENTISTS:
                            System.out.println(serviceFilteredDentists.toStringRepo());
                            break;
                        case ADD_DENTIST_OPTION_FILTER:
                            System.out.println("Id:");
                            int id_add_dentist_filtered = readInteger();

                            System.out.println("Name:");
                            String name_add_dentists_filtered = readString();

                            System.out.println("Age:");
                            int age_add_dentist_filtered = readInteger();

                            try {
                                serviceFilteredDentists.add(id_add_dentist_filtered, name_add_dentists_filtered, age_add_dentist_filtered);
                            } catch (RuntimeException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case MODIFY_DENTIST_AT_ID_OPTION_FILTER:
                            System.out.println("Modify at this id:");
                            int idWhereToModify_dentist_filtered = readInteger();

                            System.out.println("Name:");
                            String name_modify_dentists_filtered = readString();

                            System.out.println("Age:");
                            int age_modify_dentist_filtered = readInteger();

                            try {
                                serviceFilteredDentists.modify(idWhereToModify_dentist_filtered, name_modify_dentists_filtered, age_modify_dentist_filtered);
                            } catch (RuntimeException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case REMOVE_DENTIST_AT_ID_OPTION_FILTER:
                            System.out.println("Remove at this id:");
                            int idWhereToRemove_dentist_filtered = readInteger();

                            try {
                                serviceFilteredDentists.delete(idWhereToRemove_dentist_filtered);
                            } catch (RuntimeException e) {
                                System.out.println(e.getMessage());
                            }
                            break;


                    }

                }
            } catch (ExceptionRepository e) {
                System.out.println(e.getMessage());
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

    }
}
