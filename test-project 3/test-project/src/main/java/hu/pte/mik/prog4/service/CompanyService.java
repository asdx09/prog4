package hu.pte.mik.prog4.service;

import hu.pte.mik.prog4.model.Company;
import org.apache.log4j.Logger;

import java.util.Objects;

public class CompanyService implements ClientService<Company> {

    private static final Logger LOGGER = Logger.getLogger(CompanyService.class.toString());


    @Override
    public void pay(Company client) {
        LOGGER.info(Objects.toString(client));
    }

    @Override
    public void receiveService(Company client) {
        LOGGER.info("Receive service as a company: " + client);
    }
}
