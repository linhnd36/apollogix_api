package com.apollogix.managerskill.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "m_question")
public class Question {

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
    @JoinColumn(name = "exam_id", nullable = false)
    private Exam exam;

    @OneToMany(mappedBy="question")
    private List<Answer> answers;

    @Column(name = "is_delete", nullable = false)
    private Boolean isDelete = false;

    @NotNull
    @CreationTimestamp
    @Column(name = "create_at", nullable = false)
    private Instant createAt;

    @NotNull
    @UpdateTimestamp
    @Column(name = "update_at", nullable = false)
    private Instant updateAt;

}