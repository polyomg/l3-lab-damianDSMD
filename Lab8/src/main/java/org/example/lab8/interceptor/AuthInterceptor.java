package org.example.lab8.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.lab8.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    HttpSession session;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        // store intended uri
        session.setAttribute("securityUri", uri);

        Account user = (Account) session.getAttribute("user");
        // must be logged in for certain URIs
        if (uri.startsWith("/account/edit-profile") ||
                uri.startsWith("/account/change-password") ||
                uri.startsWith("/order/")) {
            if (user == null) {
                response.sendRedirect("/auth/login");
                return false;
            }
        }

        // admin-only for /admin/** except /admin/home/index
        if (uri.startsWith("/admin") && !uri.equals("/admin/home/index")) {
            if (user == null || !user.isAdmin()) {
                response.sendRedirect("/auth/login");
                return false;
            }
        }

        return true;
    }
}
