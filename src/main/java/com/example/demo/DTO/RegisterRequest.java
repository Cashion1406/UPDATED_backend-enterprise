package com.example.demo.DTO;

import com.example.demo.model.ERole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {


    public String id;
    public String firstname;

    public String lastname;

    public String age;

    public String password;

    public String client_info;

    @Enumerated(EnumType.STRING)
    public ERole role;


    public Boolean isDeleted;
    private String email;

}
