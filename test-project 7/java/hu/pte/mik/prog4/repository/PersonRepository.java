package hu.pte.mik.prog4.repository;

import hu.pte.mik.prog4.model.Person;

import java.util.List;

public class PersonRepository extends Repository implements ClientRepository<Person> {

    @Override
    public List<Person> listAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Person save(Person client) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Person findById(Long id) {
        throw new UnsupportedOperationException();
    }
}
