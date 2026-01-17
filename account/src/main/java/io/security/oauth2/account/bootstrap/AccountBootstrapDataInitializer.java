package io.security.oauth2.account.bootstrap;

import io.security.oauth2.account.domain.Member;
import io.security.oauth2.account.repository.MemberRepository;
import io.security.oauth2.core.SecurityConstants;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AccountBootstrapDataInitializer implements CommandLineRunner {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountBootstrapDataInitializer(MemberRepository memberRepository,
                                           PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (memberRepository.findByUsername("user").isEmpty()) {
            Member member = new Member("user",
                    passwordEncoder.encode("password"),
                    SecurityConstants.DEFAULT_USER_ROLE);
            memberRepository.save(member);
        }
    }
}
