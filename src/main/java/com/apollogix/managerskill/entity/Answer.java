package com.apollogix.managerskill.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "m_answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Lob
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @Column(name = "is_delete", nullable = false)
    private Boolean isDelete = false;

    @Column(name = "is_correct", nullable = false)
    private Boolean isCorrect = false;

    @NotNull
    @CreationTimestamp
    @Column(name = "create_at", nullable = false)
    private Instant createAt;

    @NotNull
    @UpdateTimestamp
    @Column(name = "update_at", nullable = false)
    private Instant updateAt;

}