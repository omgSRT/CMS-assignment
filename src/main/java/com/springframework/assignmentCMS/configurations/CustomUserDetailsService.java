package com.springframework.assignmentCMS.configurations;

import com.springframework.assignmentCMS.models.Member;
import com.springframework.assignmentCMS.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member m = memberRepository.findByEmail(username);
        if(m == null){
            throw new UsernameNotFoundException("Username not found");
        }
        else{
            return new CustomUserDetails(m);
        }
    }
}
