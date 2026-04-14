package hu.pte.mik.prog4.model;

import java.util.Objects;

public class Company extends Client {

    private String taxNumber;

    public Company(Long id, String name, String address, String taxNumber) {
        super(id, name, address);
        this.taxNumber = taxNumber;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Company company = (Company) o;
        return Objects.equals(taxNumber, company.taxNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), taxNumber);
    }
}
