package hu.pte.mik.prog4.service;

import hu.pte.mik.prog4.model.Company;
import hu.pte.mik.prog4.repository.CompanyRepository;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Objects;

public class CompanyService implements ClientService<Company> {

    private static final Logger LOGGER = Logger.getLogger(CompanyService.class.toString());

    private final CompanyRepository companyRepository = new CompanyRepository();

    @Override
    public void pay(Company client) {
        LOGGER.info(Objects.toString(client));
    }

    @Override
    public void receiveService(Company client) {
        LOGGER.info("Receive service as a company: " + client);
    }

    @Override
    public List<Company> listAll() {
        return this.companyRepository.listAll();
    }

    @Override
    public void save(Company client) {
        this.companyRepository.save(client);
    }
}
