package com.example.demo.DTO.Client;


import com.example.demo.model.ERole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientUpdateRequest {

    private String id;

    private String firstname;

    private String lastname;

    private String age;

    private String email;

    private String client_info;

    private ERole role;


}
