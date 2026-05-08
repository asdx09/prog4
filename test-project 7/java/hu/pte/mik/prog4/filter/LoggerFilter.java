package hu.pte.mik.prog4.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class LoggerFilter extends HttpFilter {

    private static final Logger LOGGER = Logger.getLogger(LoggerFilter.class);

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        LOGGER.info(req.getServletPath());
        req.getParameterMap()
                .forEach((key, value) -> LOGGER.info("Name: " + key + ", value: " + Arrays.toString(value)));


        Optional<String> forbiddenName = Optional.ofNullable(req.getParameterMap().get("name"))
                                                            .stream()
                .flatMap(Arrays::stream)
                .filter("FooBar"::equals)
                .findFirst();

        if(forbiddenName.isPresent()) {
            res.setStatus(HttpServletResponse.SC_FORBIDDEN);
        } else {
            super.doFilter(req, res, chain);
        }

        LOGGER.info(res.getStatus());
    }
}
