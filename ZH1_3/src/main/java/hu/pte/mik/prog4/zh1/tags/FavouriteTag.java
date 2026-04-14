package hu.pte.mik.prog4.zh1.tags;

import hu.pte.mik.prog4.zh1.model.Food;
import hu.pte.mik.prog4.zh1.service.FoodService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.PageContext;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.stream.Stream;

public class FavouriteTag extends SimpleTagSupport {
    private final FoodService foodService = new FoodService();
    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = this.getJspContext().getOut();
        var context = (PageContext) this.getJspContext();
        var request = (HttpServletRequest) context.getRequest();
        var favourite = Optional.ofNullable(request.getCookies())
                .map(Stream::of)
                .orElseGet(Stream::empty)
                .filter(cookie->cookie.getName().equals("favourite"))
                .findFirst()
                .map(cookie-> URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8))
                .orElse(null);
        if (favourite != null) {
            var longid= Long.parseLong(favourite);
            Food found=foodService.findById(longid);
            out.println("A kedvenc ételed a következő:" + found.getFoodName()+" ("+found.getRestaurantName()+")");
        }else{
            out.println("Még nincs kedvenc ételed");
        }
    }
}