package com.real013228.dto;

import com.real013228.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest{
    private String login;
    private String password;
    private String firstName;
    private String LastName;
}
