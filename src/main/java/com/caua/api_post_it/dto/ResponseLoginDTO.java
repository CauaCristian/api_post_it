package com.caua.api_post_it.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseLoginDTO {
    private Boolean error;
    private String message;
    private String token;
    private UserDTO user;
}
