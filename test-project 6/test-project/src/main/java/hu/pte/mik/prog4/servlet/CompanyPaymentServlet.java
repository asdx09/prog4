package hu.pte.mik.prog4.servlet;

import hu.pte.mik.prog4.model.Company;
import hu.pte.mik.prog4.service.CompanyService;
import hu.pte.mik.prog4.service.IdProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.stream.Stream;

public class CompanyPaymentServlet extends HttpServlet {

    private final IdProvider idProvider = IdProvider.getInstance();

    private final CompanyService companyService = new CompanyService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        var name = Optional.ofNullable(req.getCookies())
                .map(Stream::of)
                .orElse(Stream.empty())
                .filter(cookie -> "name".equals(cookie.getName()))
                .findFirst()
                .map(cookie ->
                        URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8))
                .orElse(null);

        req.setAttribute("name", name);

        req.getRequestDispatcher("/companyPayment.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        var name = req.getParameter("name");
        var address = req.getParameter("address");
        var taxNumber = req.getParameter("taxNumber");
        var company = new Company(this.idProvider.nextId(), name, address, taxNumber);

        this.companyService.pay(company);

        var cookie = new Cookie("name",
                URLEncoder.encode(name, StandardCharsets.UTF_8));
        resp.addCookie(cookie);

        req.setAttribute("name", name);

//        req.getRequestDispatcher("/companyPayment.jsp").forward(req, resp);
        resp.sendRedirect("clientList");

    }
}
