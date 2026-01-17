package io.security.oauth2.auth.config.login;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.util.UrlUtils;

public class AccountLoginEntryPoint implements AuthenticationEntryPoint {

    private final String loginUrl;

    public AccountLoginEntryPoint(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        String requestUrl = UrlUtils.buildFullRequestUrl(request);
        String encoded = URLEncoder.encode(requestUrl, StandardCharsets.UTF_8);
        response.sendRedirect(loginUrl + "?redirect_uri=" + encoded);
    }
}
