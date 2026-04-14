package hu.pte.mik;

import hu.pte.mik.model.Client;
import hu.pte.mik.model.Company;
import hu.pte.mik.model.Person;
import hu.pte.mik.service.CompanyService;
import hu.pte.mik.service.IdProvider;
import hu.pte.mik.service.PersonService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Controll {

    private static final Logger LOGGER = Logger.getLogger(Controll.class.toString());

    private final CompanyService companyService = new CompanyService();
    private final PersonService personService = new PersonService();
    private final IdProvider id = IdProvider.getInstance();

    public void start() {
        LOGGER.info("Hello World!");

//        List<String[]> list = this.createDummyList();
//
//        for (String[] strings : list) {
//            Client client = this.convertFromString(strings);
//
////            this.callService(client);
//
////            Runnable runnable = () -> this.callService(client);
////            new Thread(runnable).start();
//
////            new Thread(() -> this.callService(client)).start();
//        }

//        this.createDummyList()
//                .forEach(strings -> {
//                    Client client = this.convertFromString(strings);
//                    new Thread(() -> this.callService(client)).start();
//                });

//        this.createDummyList()
//                .stream()
//                .map(strings -> this.convertFromString(strings))
//                .map(client -> (Runnable) () -> this.callService(client))
//                .map(runnable -> new Thread(runnable))
//                .forEach(thread -> thread.start());

//        this.createDummyList()
//                .stream()
//                .map(this::convertFromString)
//                .map(client -> (Runnable) () -> this.callService(client))
//                .map(Thread::new)
//                .forEach(Thread::start);

        this.createDummyList()
                .stream()
                .map(this::convertFromString)
                .forEach(client -> new Thread(() -> this.callService(client)).start());
    }

    private List<String[]> createDummyList() {
        List<String[]> dummyList = new ArrayList<>();
        dummyList.add(new String[]{"P", "Pista", "address01", "22234TA"});
        dummyList.add(new String[]{"C", "Google", "Google's address", "2434234234234"});
        dummyList.add(new String[]{"P", "Linus", "address02", "432440TA"});
        dummyList.add(new String[]{"C", "Oracle", "Oracle's address", "1231232142342"});
        dummyList.add(new String[]{"P", "Tom", "address03", "534554TA"});
        dummyList.add(new String[]{"C", "Suit", "Suit's address", "4564352353"});
        dummyList.add(new String[]{"P", "Jerry", "address04", "534543TA"});
        dummyList.add(new String[]{"C", "OTP", "OTP's address", "53453453453534"});


        return dummyList;
    }


    private Client convertFromString(String[] array) {
//        switch (array[0]) {
//            case "P":
//                return new Person(this.id.getAndIncrement(), array[1], array[2], array[3]);
//            case "C":
//                return new Company(this.id.getAndIncrement(), array[1], array[2], array[3]);
//            default:
//                throw new RuntimeException("Unknown client type: " + array);
//        }

        return switch (array[0]) {
            case "P" -> new Person(this.id.nextId(), array[1], array[2], array[3]);
            case "C" -> new Company(this.id.nextId(), array[1], array[2], array[3]);
            default -> throw new RuntimeException("Unknown client type: " + array);
        };
    }

    private void callService(Client client) {
//        if (client instanceof Person) {
//            this.personService.pay((Person) client);
//        } else if (client instanceof Company) {
//            this.companyService.pay((Company) client);
//        } else {
//            throw new RuntimeException("Unknown client type: " + client);
//        }

        LOGGER.info("Thread name: " + Thread.currentThread().getName());

        try {
            Thread.sleep((long) (Math.random() * 5000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (client instanceof Person person) {
            this.personService.pay(person);
        } else if (client instanceof Company company) {
            this.companyService.pay(company);
        } else {
            throw new RuntimeException("Unknown clien tpye: " + client);
        }
    }

}
