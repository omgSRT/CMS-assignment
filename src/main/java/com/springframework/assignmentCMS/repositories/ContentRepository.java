package com.springframework.assignmentCMS.repositories;


import com.springframework.assignmentCMS.models.Content;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<Content, Long> {
}
