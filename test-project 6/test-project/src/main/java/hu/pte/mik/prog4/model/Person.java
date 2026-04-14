package hu.pte.mik.prog4.model;

import java.util.Objects;

public class Person extends Client {

    private String idNumber;

    public Person(Long id, String name, String address, String idNumber) {
        super(id, name, address);
        this.idNumber = idNumber;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Person person = (Person) o;
        return Objects.equals(idNumber, person.idNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idNumber);
    }

//    @Override
//    public String toString() {
//        return "Person{" +
//                "idNumber='" + idNumber + '\'' +
//                ", " + super.toString() +
//                '}';
//    }


    @Override
    public String toString() {
        return "Person{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", idNumber='" + idNumber + '\'' +
                '}';
    }
}
