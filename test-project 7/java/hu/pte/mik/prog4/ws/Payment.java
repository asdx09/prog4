package hu.pte.mik.prog4.ws;

import hu.pte.mik.prog4.model.Company;
import hu.pte.mik.prog4.model.Person;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public interface Payment {

    @WebMethod
    Boolean companyPay(Company company);

    @WebMethod
    Boolean personPay(Person person);

}
