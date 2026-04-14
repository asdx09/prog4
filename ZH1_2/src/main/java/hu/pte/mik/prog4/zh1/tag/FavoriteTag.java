package hu.pte.mik.prog4.zh1.tag;

import hu.pte.mik.prog4.zh1.repository.FoodRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.PageContext;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class FavoriteTag extends SimpleTagSupport {
    private String name;
    private FoodRepository repo = FoodRepository.getInstance();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void doTag() throws JspException, IOException {

        JspWriter out = this.getJspContext().getOut();

        PageContext pageContext = (PageContext) getJspContext();
        HttpServletRequest req = (HttpServletRequest) pageContext.getRequest();

        for (Cookie c : req.getCookies())
        {
            if(c.getName().equals("favorite"))
            {
                var food = this.repo.findById(Long.valueOf(URLDecoder.decode(c.getValue(), StandardCharsets.UTF_8)));
                if (food != null) this.setName(food.getFoodName());
            }
        }

        if (name != null) out.print("A kedvenc ételed: " + this.name);
        else out.print("Nincs kedvenc ételed");
    }


}