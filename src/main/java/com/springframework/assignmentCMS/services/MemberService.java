package com.springframework.assignmentCMS.services;

import com.springframework.assignmentCMS.models.Member;
import com.springframework.assignmentCMS.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public Member findByEmailAndPassword(String email, String password){
        return memberRepository.findByEmailAndPassword(email, password);
    }

    public Member findByEmail(String email){
        return memberRepository.findByEmail(email);
    }

    public void saveMember(Member m){
        memberRepository.save(m);
    }
}
