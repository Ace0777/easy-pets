package br.com.vianna.edu.EasyPets.infra;

import br.com.vianna.edu.EasyPets.Service.UserSecurityDetails;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {


        UserSecurityDetails userDetails = (UserSecurityDetails) authentication.getPrincipal();

        String tipoUser = userDetails.getTipoUserString();

        String redirectUrl = "/home?tipoUser=" + tipoUser;

        HttpSession session = request.getSession();
        session.setAttribute("usuarioLogado", userDetails.getUser());

        response.sendRedirect(redirectUrl);
    }
}
