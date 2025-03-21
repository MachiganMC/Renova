package be.machigan.renova.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

public class AuthenticationRedirection implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String adminPages = "/admin";
        String loginPage = "/admin/login";
        if (authentication != null && authentication.isAuthenticated()) {
            if (request.getRequestURI().equals(loginPage)) {
                response.sendRedirect(request.getContextPath() + adminPages);
                return;
            }
        } else if (request.getRequestURI().startsWith(adminPages) && !request.getRequestURI().equals(loginPage)) {
            response.sendRedirect(request.getContextPath() + loginPage);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
