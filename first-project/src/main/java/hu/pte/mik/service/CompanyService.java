package hu.pte.mik.service;

import hu.pte.mik.model.Company;

import java.util.logging.Logger;

public class CompanyService implements ClientService<Company> {

    private static final Logger LOGGER = Logger.getLogger(CompanyService.class.toString());

    @Override
    public void pay(Company client) {
        LOGGER.info("Pay as a company: " + client);
    }

    @Override
    public void receiveService(Company client) {
        LOGGER.info("Receive service as a company: " + client);
    }
}
