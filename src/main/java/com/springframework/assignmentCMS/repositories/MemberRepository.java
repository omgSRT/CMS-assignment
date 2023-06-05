package com.springframework.assignmentCMS.repositories;

import com.springframework.assignmentCMS.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("SELECT mem FROM Member mem WHERE mem.email like ?1 AND mem.password like ?2")
    Member findMember(String email, String password);

    Member findByEmailAndPassword(String email, String password);
    Member findByEmail(String email);


}
