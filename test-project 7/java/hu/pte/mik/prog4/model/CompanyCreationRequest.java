package hu.pte.mik.prog4.model;

import java.util.Objects;

public class CompanyCreationRequest {

    private String name;
    private String address;
    private String taxNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        CompanyCreationRequest that = (CompanyCreationRequest) object;
        return Objects.equals(name, that.name) && Objects.equals(address, that.address) && Objects.equals(taxNumber, that.taxNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, taxNumber);
    }
}
