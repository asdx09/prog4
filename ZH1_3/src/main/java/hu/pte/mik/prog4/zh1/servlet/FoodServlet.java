package hu.pte.mik.prog4.zh1.servlet;

import hu.pte.mik.prog4.zh1.model.Food;
import hu.pte.mik.prog4.zh1.service.FoodService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class FoodServlet extends HttpServlet {
    private final FoodService foodService = new FoodService();
    //private final Logger LOGGER = Logger.getLogger(FoodServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var insertCookie = req.getParameter("cookieid");
        if (insertCookie != null) {
            Cookie cookie = new Cookie("favourite", URLEncoder.encode(insertCookie, StandardCharsets.UTF_8));
            resp.addCookie(cookie);
        }
        var XMLid = req.getParameter("XMLid");
        if(XMLid != null) {
            var longXMLid = Long.parseLong(XMLid);
            Food found=foodService.findById(longXMLid);
            foodService.convertToXml(found);
        }
        List<Food> model= foodService.findAll();
        req.setAttribute("model", model);
        req.getRequestDispatcher("/food.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var id = req.getParameter("deleteid");
        var longid = Long.parseLong(id);
        foodService.delete(longid);
        List<Food> model= foodService.findAll();
        req.setAttribute("model", model);
        req.getRequestDispatcher("/food.jsp").forward(req, resp);
    }
}
