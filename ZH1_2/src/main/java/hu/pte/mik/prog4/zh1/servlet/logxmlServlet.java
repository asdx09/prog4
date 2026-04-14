package hu.pte.mik.prog4.zh1.servlet;

import hu.pte.mik.prog4.zh1.repository.FoodRepository;
import hu.pte.mik.prog4.zh1.service.FoodService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class logxmlServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var id = req.getParameter("id");
        FoodService fs = new FoodService();
        fs.convertToXml(FoodRepository.getInstance().findById(Long.valueOf(id)));
    }
}
