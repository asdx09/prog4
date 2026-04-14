package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repository.FoodRepository;

import java.io.IOException;

public class NewFoodServlet extends HttpServlet {

    private FoodRepository repo = new FoodRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/newFood.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        var name = req.getParameter("name");
        var restaurant = req.getParameter("restaurant");
        var price = Integer.valueOf(req.getParameter("price"));

        this.repo.AddFood(restaurant,name,price);
        resp.sendRedirect("list");
    }
}
