package repo;

import domain.Dentist;

import java.io.*;
import java.util.ArrayList;

public class DentistsTextFileRepository extends FileRepository<Integer, Dentist> {
    public DentistsTextFileRepository(String filename) throws ExceptionRepository {
        super(filename);
    }

    @Override
    protected void readFromFile() throws ExceptionRepository  {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))){
            String line;
            while((line = bufferedReader.readLine())!= null){
                String[] tokens = line.split(",");
                if(tokens.length != 3)
                    continue;
                Integer id = Integer.parseInt(tokens[0]);
                String name = tokens[1];
                Integer age = Integer.parseInt(tokens[2]);
                Dentist dentist = new Dentist(id, name, age);
                try {
                    super.add(id, dentist);
                } catch (ExceptionRepository e) {
                    throw new ExceptionRepository(e.getMessage());
                }
            }
        }catch(FileNotFoundException e){
            throw new RuntimeException(e);
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void writeToFile() throws ExceptionRepository {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.fileName))){
            for(Dentist dentist: super.getAll()){
                bufferedWriter.write(dentist.getId() + ","+ dentist.getName() + ","+ dentist.getAge()+"\n");
            }
        }catch(IOException e){
            throw new ExceptionRepository(e.getMessage());
        }
    }

//    @Override
//    public String getDoctorMostAppointments() {
//        return "";
//    }
//
//    @Override
//    public ArrayList<Integer> getAppointmentsOfDoctor(String doctorName) {
//        return null;
//    }
}
