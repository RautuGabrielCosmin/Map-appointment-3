package test;

import domain.Dentist;
import repo.ExceptionRepository;
import repo.IRepository;
import service.ServiceDentists;
import repo.FakeRepository;


public class ServiceTests {

    public void testGetAll_correctSize_NoError() {

        IRepository<Integer, Dentist> fakeDentistsRepo = new FakeRepository<Integer, Dentist>();
        ServiceDentists dentistsService = new ServiceDentists(fakeDentistsRepo);

        Iterable<Dentist> dentists = dentistsService.getAll();
        int size = 0;
        for (Dentist dentist : dentists) {
            size++;
        }

        assert size == 2;


    }
}