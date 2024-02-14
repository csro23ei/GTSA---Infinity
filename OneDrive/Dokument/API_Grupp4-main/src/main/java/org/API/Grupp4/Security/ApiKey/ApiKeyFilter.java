package org.API.Grupp4.Security.ApiKey;

import java.io.IOException;

import jakarta.inject.Inject;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = { "/api/recipe", "/api/user" })
public class ApiKeyFilter implements Filter {
    @Inject
    KeyService ks;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        if (httpRequest.getRequestURI().equals("/api/user") && httpRequest.getMethod().equals("POST")) {
            chain.doFilter(request, response);
        } else {

            String apiKey = httpRequest.getHeader("group_4_is_the_best");
            if (apiKey != null) {

                try {

                    final String theKey = ks.findKey(apiKey);
                    if (theKey.equals(apiKey)) {
                        chain.doFilter(request, response);
                    }
                } catch (Exception e) {

                    HttpServletResponse httpResponse = (HttpServletResponse) response;
                    httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    httpResponse.getWriter().write("Din nyckel kunde inte hittas");

                }
            } else {
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
                httpResponse.getWriter().write("Ogiltlig header");

            }
        }
    }

}
