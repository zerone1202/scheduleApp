package org.example.scheduleapp.schedule.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.scheduleapp.user.entity.User;

@Entity
@Getter
@NoArgsConstructor
public class Schedule extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
//    private String author; -> 작성 유저명 제거
    private String password;

    // 유저와의 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false) // 외래키
    private User user;

    public Schedule(
            String title,
            String content,
            String password,
            User user
    ) {
        this.title = title;
        this.content = content;
        this.password = password;
        this.user = user;
    }

    public void updateTitle(String title) {
        this.title = title;
    }
}
