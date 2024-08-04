package com.caua.api_post_it.dto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class PostItDTO {
    private Long id;
    private String title;
    private String description;
    private Long author;
}
