package com.springframework.assignmentCMS.services;

import com.springframework.assignmentCMS.models.Content;
import com.springframework.assignmentCMS.repositories.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentService {
    @Autowired
    private ContentRepository contentRepository;

    public List<Content> getAllContent(){
        return contentRepository.findAll();
    }

    public void saveContent(Content c){
        contentRepository.save(c);
    }
}
