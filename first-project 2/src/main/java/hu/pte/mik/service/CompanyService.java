package hu.pte.mik.service;

import hu.pte.mik.model.Company;
import hu.pte.mik.xml.XmlGenerator;

import java.util.logging.Logger;

public class CompanyService implements ClientService<Company> {

    private static final Logger LOGGER = Logger.getLogger(CompanyService.class.toString());

    private final XmlGenerator xmlGenerator = new XmlGenerator();

    @Override
    public void pay(Company client) {
        LOGGER.info(xmlGenerator.convertToXml(client));
    }

    @Override
    public void receiveService(Company client) {
        LOGGER.info("Receive service as a company: " + client);
    }
}
