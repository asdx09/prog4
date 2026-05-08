package hu.pte.mik.prog4.ws;

import hu.pte.mik.prog4.model.Company;
import hu.pte.mik.prog4.model.Person;
import hu.pte.mik.prog4.service.CompanyService;
import hu.pte.mik.prog4.service.PersonService;
import jakarta.jws.WebService;
import org.apache.log4j.Logger;

@WebService(endpointInterface = "hu.pte.mik.prog4.ws.Payment", serviceName = "payment", targetNamespace = "hu.pte.mik.prog4.ws.soap")
public class SoapPayment implements Payment {

    private static final Logger LOGGER = Logger.getLogger(SoapPayment.class);

    private final PersonService personService;
    private final CompanyService companyService;

    public SoapPayment() {
        this.personService = new PersonService();
        this.companyService = new CompanyService();
    }

    @Override
    public Boolean companyPay(Company company) {
        try {
            this.companyService.pay(company);
            return true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean personPay(Person person) {
        try {
            this.personService.pay(person);
            return true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }
}
