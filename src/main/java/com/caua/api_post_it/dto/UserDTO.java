package com.caua.api_post_it.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class UserDTO {
    private Long id;
    private String username;
    private String password;
}
