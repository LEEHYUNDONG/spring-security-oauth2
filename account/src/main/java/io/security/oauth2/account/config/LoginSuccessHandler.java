package io.security.oauth2.account.config;

import java.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.web.util.HtmlUtils;
import org.springframework.util.StringUtils;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        String redirectUri = request.getParameter("redirect_uri");
        if (StringUtils.hasText(redirectUri)) {
            redirectUri = HtmlUtils.htmlUnescape(redirectUri);
        }
        if (!StringUtils.hasText(redirectUri)) {
            redirectUri = "/";
        }
        redirectStrategy.sendRedirect(request, response, redirectUri);
    }
}
