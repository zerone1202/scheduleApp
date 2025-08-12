package org.example.scheduleapp.schedule.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Schedule extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String author;
    private String password;

    public Schedule(
            String title,
            String content,
            String author,
            String password
    ) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.password = password;
    }

    public void updateTitleAuthor(String title, String author) {
        this.title = title;
        this.author = author;
    }
}
