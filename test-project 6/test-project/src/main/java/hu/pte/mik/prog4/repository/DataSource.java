package hu.pte.mik.prog4.repository;

import hu.pte.mik.prog4.model.Client;
import hu.pte.mik.prog4.model.Company;
import hu.pte.mik.prog4.model.Person;
import hu.pte.mik.prog4.service.IdProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataSource {

    private static final DataSource DATA_SOURCE = new DataSource();

    private final IdProvider idProvider = IdProvider.getInstance();
    private final List<Client> dataList;

    private DataSource() {
        this.dataList = this.createDummyList()
                .stream()
                .map(this::convertToClient)
                .collect(Collectors.toList());
    }

    public static DataSource getInstance() {
        return DATA_SOURCE;
    }

    private List<String[]> createDummyList() {
        List<String[]> dummyList = new ArrayList<>();
        dummyList.add(new String[]{"P", "Pista", "address01", "324243TA"});
        dummyList.add(new String[]{"C", "Google", "Google's address", "234345345345"});
        dummyList.add(new String[]{"P", "Linus", "address02", "543453TA"});
        dummyList.add(new String[]{"C", "Oracle", "Oracle's address", "567745665464"});
        dummyList.add(new String[]{"P", "Tom", "address03", "5345345TA"});
        dummyList.add(new String[]{"C", "Suit", "Suit's address", "3453453456"});
        dummyList.add(new String[]{"P", "Jerry", "address04", "534534TA"});
        dummyList.add(new String[]{"C", "OTP", "OTP's address", "4534554645654657"});

        return dummyList;
    }

    private Client convertToClient(String[] array) {
        return switch (array[0]) {
            case "P" -> new Person(this.idProvider.nextId(), array[1], array[2], array[3]);
            case "C" -> new Company(this.idProvider.nextId(), array[1], array[2], array[3]);
            default -> throw new RuntimeException("Unkonown client tpye: " + array);
        };
    }

    public List<Person> getAllPerson() {
        return this.dataList.stream()
                .filter(client -> client instanceof Person)
                .map(client -> (Person) client)
                .collect(Collectors.toList());
    }

    public List<Company> getAllCompany() {
        return this.dataList.stream()
                .filter(client -> client instanceof Company)
                .map(client -> (Company) client)
                .collect(Collectors.toList());
    }

    public void save(Client client) {
        this.dataList.add(client);
    }


}
