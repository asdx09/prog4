package hu.pte.mik.service;

import hu.pte.mik.model.Person;
import hu.pte.mik.xml.XmlGenerator;

import java.util.logging.Logger;

public class PersonService implements ClientService<Person> {

    private static final Logger LOGGER = Logger.getLogger(PersonService.class.toString());

    private final XmlGenerator xmlGenerator = new XmlGenerator();

    @Override
    public void pay(Person client) {
        LOGGER.info(xmlGenerator.convertToXml(client));
    }

    @Override
    public void receiveService(Person client) {
        LOGGER.info("Receive service as a person: " + client);
    }
}