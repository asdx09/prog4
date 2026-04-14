package hu.pte.mik.prog4.zh1.servlet;

import hu.pte.mik.prog4.zh1.repository.FoodRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class FoodListServlet extends HttpServlet {

    private FoodRepository repo = FoodRepository.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("foods", repo.findAll());

        req.getRequestDispatcher("/foodList.jsp").forward(req,resp);
    }
}