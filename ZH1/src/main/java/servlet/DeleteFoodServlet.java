package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repository.FoodRepository;

import java.io.IOException;

public class DeleteFoodServlet extends HttpServlet {

    private FoodRepository repo = new FoodRepository();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        var id = Long.valueOf(req.getParameter("id"));

        this.repo.DeleteFood(id);
        resp.sendRedirect("list");
    }
}
