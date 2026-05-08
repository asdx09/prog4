package hu.pte.mik.prog4.model;

import java.util.Objects;

public class CompanyUpdateRequest {

    private Long id;
    private String name;
    private String address;
    private String taxNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
        CompanyUpdateRequest that = (CompanyUpdateRequest) object;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(address, that.address) && Objects.equals(taxNumber, that.taxNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, taxNumber);
    }
}
