package io.security.oauth2.springsecurityoauth2;

import org.springframework.security.config.annotation.AbstractConfiguredSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

public class CustomSecurityConfigurer extends AbstractHttpConfigurer<CustomSecurityConfigurer, HttpSecurity> {

    private boolean isSecure;

    @Override
    public void init(HttpSecurity builder) throws Exception {
        super.init(builder);
        System.out.println("Init method starting ...");
    }

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        super.configure(builder);

        if(isSecure) {
            System.out.println("Https is secure ..");
        } else {
            System.out.println("Https is not secure ..");
        }
        System.out.println("Configure method starting ...");
    }

    public CustomSecurityConfigurer setIsSecure(boolean isSecure) {
        this.isSecure = isSecure;
        return this;
    }
}
