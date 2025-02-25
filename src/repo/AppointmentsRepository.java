package repo;

import domain.Appointment;

import java.util.ArrayList;

// extend the memory repository and make a special repository for handling appointments
public class AppointmentsRepository extends MemoryRepository<Integer, Appointment>{
      public AppointmentsRepository(){
       super();
   }


    //Get the name of the doctor with the most appointments
    public Integer getNrOfAppointmentsOfDoctor(String doctorName) {
        int nrAppointments = 0;

        Iterable<Appointment> allAppointments = this.getAll();
        for (Appointment appointment: allAppointments)
            if (appointment.getDoctorName().equals(doctorName)) {
                nrAppointments++;
            }
        return nrAppointments;

    }
    public String getDoctorNameWithMostAppointments(){
        int maximNrAppointments=-1;

        Iterable<Appointment> allAppointments = this.getAll();
        String doctorNameWithMostAppointments= new String();
        for(Appointment appointment: allAppointments){
            if(getNrOfAppointmentsOfDoctor(appointment.getDoctorName())>maximNrAppointments) {
                maximNrAppointments = getNrOfAppointmentsOfDoctor(appointment.getDoctorName());
                doctorNameWithMostAppointments=appointment.getDoctorName();
            }
        }
        return doctorNameWithMostAppointments;
    }

}
