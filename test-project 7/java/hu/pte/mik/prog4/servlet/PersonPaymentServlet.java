package hu.pte.mik.prog4.servlet;

import hu.pte.mik.prog4.model.Person;
import hu.pte.mik.prog4.service.IdProvider;
import hu.pte.mik.prog4.service.PersonService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Optional;
import java.util.stream.Stream;

public class PersonPaymentServlet extends HttpServlet {

    private final PersonService personService = new PersonService();
    private final IdProvider idProvider = IdProvider.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String name = null;
//        Cookie[] cookies = req.getCookies();
//        if(cookies!=null) {
//            for (int i = 0; i < cookies.length; i++) {
//                if("name".equals(cookies[i].getName())) {
//                    name = URLDecoder.decode(cookies[i].getValue());
//                    break;
//                }
//            }
//        }

        String name = Optional.ofNullable(req.getCookies())
                .map(Stream::of)
                .orElse(Stream.empty())
                .filter(cookie -> "name".equals(cookie.getName()))
                .findFirst()
                .map(cookie -> URLDecoder.decode(cookie.getValue()))
                .orElse(null);

        this.createResponse(resp, name);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        String idNumber =  req.getParameter("idNumber");
        Person person = new Person(this.idProvider.nextId(), name, address, idNumber);

        Cookie cookie = new Cookie("name", URLEncoder.encode(name, "UTF-8"));
        resp.addCookie(cookie);

//        this.personService.save(person);
        this.personService.pay(person);
//        this.createResponse(resp, name);
        resp.sendRedirect("clientList");
    }

    private void createResponse(HttpServletResponse resp, String name) throws IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("<html><head><meta charset=\"UTF-8\"></head><body>");
        if(name != null && !name.isEmpty()) {
            writer.println("<h1> Hello " + name + "!</h1>");
        }
        writer.println("<form method=\"POST\">");
        writer.println("Name: <input type=\"text\" name=\"name\" />");
        writer.println("Address: <input type=\"text\" name=\"address\" />");
        writer.println("ID number: <input type=\"text\" name=\"idNumber\" />");
        writer.println("<input type=\"submit\" />");
        writer.println("</form>");

        writer.println("<table border=\"1\">");
        writer.println("<tbody>");
        this.personService.listAll().forEach(person -> {
            writer.println("<tr>");
            writer.println("<td>" + person.getId() + "</td>");
            writer.println("<td>" + person.getName() + "</td>");
            writer.println("<td>" + person.getAddress() + "</td>");
            writer.println("</tr>");
        });
        writer.println("</tbody>");
        writer.println("</table>");
        writer.println("</body>");
        writer.println("</html>");
    }


}
