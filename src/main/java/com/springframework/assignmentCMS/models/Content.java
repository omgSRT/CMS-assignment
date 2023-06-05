package com.springframework.assignmentCMS.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.text.html.Option;
import java.util.Date;

@NoArgsConstructor
@Data
@Entity
@Table(name = "Content")
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contentId;

    @Column(length = 255)
    private int authorId;
    private String title;
    private String brief;
    private String content;
    private Integer sort;
    private Date createDate;
    private Date updateTime;

    public Content(int authorId, String title, String brief,
                   String content, int sort, Date createDate, Date updateTime) {
        this.authorId = authorId;
        this.title = title;
        this.brief = brief;
        this.content = content;
        this.sort = sort;
        this.createDate = createDate;
        this.updateTime = updateTime;
    }

    public Content(int authorId, String title, String brief, String content){
        this.authorId = authorId;
        this.title = title;
        this.brief = brief;
        this.content = content;
        this.createDate = new Date();
        this.updateTime = new Date();
    }
}
