package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Entity
@Table(name = "task")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue
    private Long id;

    private OffsetDateTime createdAt = OffsetDateTime.now();

    private String title;
    private String description;

    @Builder.Default
    private String status = "TODO";

    @ManyToOne
    private User assignee;
}
