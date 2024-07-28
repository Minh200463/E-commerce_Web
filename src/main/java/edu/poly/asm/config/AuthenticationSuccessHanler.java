package edu.poly.asm.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import edu.poly.asm.service.SessionService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthenticationSuccessHanler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Autowired
    SessionService session;

    public void setSessionService(SessionService sessionService) {
        session = sessionService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
        session.set("user", authentication.getPrincipal());
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
        System.out.println("");
        session.set("checkadmin", isAdmin);
        if (isAdmin) {
            setDefaultTargetUrl("/admin/report/index");
        } else {
            setDefaultTargetUrl("/home/index");
        }

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
