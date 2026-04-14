package hu.pte.mik.prog4.service;


import hu.pte.mik.prog4.model.Person;
import hu.pte.mik.prog4.repository.PersonRepository;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Objects;

public class PersonService implements ClientService<Person> {

    private static final Logger LOGGER = Logger.getLogger(PersonService.class.toString());

    private final PersonRepository personRepository = new PersonRepository();

    @Override
    public void pay(Person client) {
        LOGGER.info(Objects.toString(client));
    }

    @Override
    public void receiveService(Person client) {
        LOGGER.info("Receive service as a person: " + client);
    }

    @Override
    public List<Person> listAll() {
        return this.personRepository.listAll();
    }

    @Override
    public void save(Person client) {
        this.personRepository.save(client);
    }
}
