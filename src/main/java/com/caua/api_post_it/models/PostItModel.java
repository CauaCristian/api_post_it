package com.caua.api_post_it.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "post_It")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PostItModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true,nullable = false)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @ManyToOne
    @JoinColumn(name = "author_id",nullable = false)
    private UserModel author;
}
