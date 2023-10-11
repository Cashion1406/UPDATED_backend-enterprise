package com.example.demo.DTO;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdatePassRequest {
    private String currentPassword;
    private String newPassword;
    private String confirmationPassword;

}
