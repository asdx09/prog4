package hu.pte.mik.prog4.repository;

import hu.pte.mik.prog4.model.Person;

import java.util.List;

public class PersonRepository implements ClientRepository<Person> {

    private final DataSource dataSource = DataSource.getInstance();

    @Override
    public List<Person> listAll() {
        return this.dataSource.getAllPerson();
    }

    @Override
    public void save(Person client) {
        this.dataSource.save(client);
    }

    @Override
    public Person findById(Long id) {
        throw new UnsupportedOperationException();
    }
}
