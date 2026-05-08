package hu.pte.mik.prog4.repository;

import hu.pte.mik.prog4.model.Company;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CompanyRepository extends Repository implements ClientRepository<Company> {

    private static final Logger LOGGER = Logger.getLogger(CompanyRepository.class);

    @Override
    public List<Company> listAll() {
        try (Connection connection = this.getConnection();
             Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT id, name, address, tax_number FROM company");

            List<Company> companies = new ArrayList<>();
            while (rs.next()) {
                companies.add(this.mapCompany(rs));
            }

            return companies;

        } catch (SQLException e) {
            LOGGER.error("Lekérdezés közben hiba történt!" + e.getMessage(), e);
            throw new RuntimeException(e);
        } catch (NamingException e) {
            LOGGER.error("Hiba történt!" + e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    private Company mapCompany(ResultSet rs) throws SQLException {
        Company company = new Company();
        company.setId(rs.getLong("id"));
        company.setName(rs.getString("name"));
        company.setAddress(rs.getString("address"));
        company.setTaxNumber(rs.getString("tax_number"));
        return company;
    }

    @Override
    public Company save(Company client) {
        if(client.getId() == null) {
            return this.create(client);
        } else {
            return this.update(client);
        }
    }

    @Override
    public Company findById(Long id) {
        try(Connection connection = this.getConnection();
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT id, name, address, tax_number FROM company WHERE id = ?");) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            return rs.next() ? this.mapCompany(rs) : null;

        } catch (SQLException e) {
            LOGGER.error("Lekérdezés közben hiba történt!" + e.getMessage(), e);
            throw new RuntimeException(e);
        } catch (NamingException e) {
            LOGGER.error("Hiba történt!" + e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public Company update(Company company) {
        try (Connection connection = this.getConnection();
        PreparedStatement stmt = connection.prepareStatement(
                "UPDATE company SET name = ?, address = ?, tax_number = ? WHERE id = ?");) {
            stmt.setString(1, company.getName());
            stmt.setString(2, company.getAddress());
            stmt.setString(3, company.getTaxNumber());
            stmt.setLong(4, company.getId());

            stmt.executeUpdate();

            return this.findById(company.getId());
        } catch (SQLException e) {
            LOGGER.error("Lekérdezés közben hiba történt!" + e.getMessage(), e);
            throw new RuntimeException(e);
        } catch (NamingException e) {
            LOGGER.error("Hiba történt!" + e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public Company create(Company company) {
        try (Connection connection = this.getConnection();
        PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO company (name, address, tax_number) VALUES (?,?,?)",
                Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, company.getName());
            stmt.setString(2, company.getAddress());
            stmt.setString(3, company.getTaxNumber());

            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();

            return generatedKeys.next() ?
                    this.findById(generatedKeys.getLong(1)) : null;



        } catch (SQLException e) {
            LOGGER.error("Lekérdezés közben hiba történt!" + e.getMessage(), e);
            throw new RuntimeException(e);
        } catch (NamingException e) {
            LOGGER.error("Hiba történt!" + e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

}
