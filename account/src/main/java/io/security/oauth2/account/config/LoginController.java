package io.security.oauth2.account.config;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(@RequestParam(name = "redirect_uri", required = false) String redirectUri,
                        Model model) {
        model.addAttribute("redirectUri", redirectUri);
        return "login";
    }
}
