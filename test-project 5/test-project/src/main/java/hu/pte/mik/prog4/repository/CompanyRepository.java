package hu.pte.mik.prog4.repository;

import hu.pte.mik.prog4.model.Company;

import java.util.List;

public class CompanyRepository implements ClientRepository<Company> {

    private final DataSource dataSource = DataSource.getInstance();

    @Override
    public List<Company> listAll() {
        return this.dataSource.getAllCompany();
    }

    @Override
    public void save(Company client) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Company findById(Long id) {
        throw new UnsupportedOperationException();
    }
}
