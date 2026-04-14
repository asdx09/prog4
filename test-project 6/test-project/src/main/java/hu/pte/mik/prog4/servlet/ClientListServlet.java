package hu.pte.mik.prog4.servlet;

import hu.pte.mik.prog4.model.Client;
import hu.pte.mik.prog4.service.CompanyService;
import hu.pte.mik.prog4.service.PersonService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientListServlet extends HttpServlet {

    private final PersonService personService = new PersonService();
    private final CompanyService companyService = new CompanyService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Client> clients = new ArrayList<>();
        clients.addAll(this.personService.listAll());
        clients.addAll(this.companyService.listAll());

        req.setAttribute("clients", clients);
        req.getRequestDispatcher("/clientList.jsp").forward(req, resp);
    }
}
