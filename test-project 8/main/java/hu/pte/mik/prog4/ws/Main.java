package hu.pte.mik.prog4.ws;

import hu.pte.mik.prog4.generated.ws.Company;
import hu.pte.mik.prog4.generated.ws.Payment;
import hu.pte.mik.prog4.generated.ws.Payment_Service;
import jakarta.xml.ws.BindingProvider;


public class Main {

    public static void main(String[] args) {
        Company company = new Company();
        company.setName("MBH");
        company.setAddress("Valami_address");
        company.setTaxNumber("1234345345");
        Payment_Service paymentService = new Payment_Service();
        Payment port = paymentService.getSoapPaymentPort();
//        ((BindingProvider) port).getRequestContext()
//                .put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://localhost:8080/test_project_war_exploded/ws/pay2");
        Boolean result = port.companyPay(company);
        System.err.println(result);
    }
}