package hu.pte.mik.prog4.zh1.servlet;

import hu.pte.mik.prog4.zh1.model.Food;
import hu.pte.mik.prog4.zh1.service.FoodService;
import hu.pte.mik.prog4.zh1.util.IdProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class AddFoodServlet extends HttpServlet {
    private final FoodService foodService = new FoodService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var name = req.getParameter("name");
        var restaurantName = req.getParameter("restaurant");
        var price = req.getParameter("price");
        foodService.save(restaurantName,name,price);
        resp.sendRedirect(req.getContextPath() + "/Food");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/addFood.jsp").forward(req,resp);
    }
}
