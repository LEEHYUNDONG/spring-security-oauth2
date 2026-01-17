package io.security.oauth2.account.service;

import io.security.oauth2.account.domain.Member;
import io.security.oauth2.account.repository.MemberRepository;
import java.util.Arrays;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MemberUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public MemberUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByUsername(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("Member not found: " + username));
    }

    private UserDetails createUserDetails(Member member) {
        String[] authorities = Arrays.stream(member.getRoles().split(","))
                .map(String::trim)
                .filter(role -> !role.isEmpty())
                .toArray(String[]::new);
        return User.withUsername(member.getUsername())
                .password(member.getPassword())
                .authorities(authorities)
                .build();
    }
}
