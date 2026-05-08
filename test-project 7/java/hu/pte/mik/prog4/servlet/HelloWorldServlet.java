package hu.pte.mik.prog4.servlet;

import hu.pte.mik.prog4.model.Client;
import hu.pte.mik.prog4.service.CompanyService;
import hu.pte.mik.prog4.service.PersonService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HelloWorldServlet extends HttpServlet {

    private final PersonService personService = new PersonService();
    private final CompanyService companyService = new CompanyService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        List<Client> clients = new ArrayList<>();
        clients.addAll(this.personService.listAll());
        clients.addAll(this.companyService.listAll());

        writer.println("<html><head><meta charset=\"UTF-8\"></head><body>");
        writer.println("<table border=\"1\">");
        clients.stream().sorted(Comparator.comparing(Client::getId))
                .forEach(client -> {
            writer.println("<tbody>");
            writer.println("<tr>");
            writer.println("<td>" + client.getId() + "</td>");
            writer.println("<td>" + client.getName() + "</td>");
            writer.println("<td>" + client.getAddress() + "</td>");
            writer.println("</tr>");
            writer.println("</tbody>");
        });
        writer.println("</table>");
        writer.println("</body>");
        writer.println("</html>");

    }


}
