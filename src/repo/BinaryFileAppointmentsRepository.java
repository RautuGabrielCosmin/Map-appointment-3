package repo;

import domain.Appointment;

import java.io.*;

public class BinaryFileAppointmentsRepository extends FileRepository<Integer,Appointment> {


    public BinaryFileAppointmentsRepository(String fileName) throws ExceptionRepository {
        super(fileName);
    }

    @Override
    protected void writeToFile() throws ExceptionRepository {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(this.fileName))) {
            objectOutputStream.writeObject(this.objects);

        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            throw new ExceptionRepository(e.getMessage());
        } catch (IOException e) {
            //e.printStackTrace();
            throw new ExceptionRepository(e.getMessage());
        }
    }
    @Override
    protected void readFromFile() throws ExceptionRepository {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(this.fileName))) {
            this.objects = (java.util.HashMap<Integer, Appointment>) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            throw new ExceptionRepository(e.getMessage());
        } catch (IOException e) {
            throw new ExceptionRepository(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new ExceptionRepository(e.getMessage());
        }
    }
}
