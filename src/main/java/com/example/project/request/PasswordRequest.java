package com.example.project.request;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PasswordRequest {
    @Size(min = 3)
    private String oldPassword;
    @Size(min = 3)
    private String newPassword;
}
