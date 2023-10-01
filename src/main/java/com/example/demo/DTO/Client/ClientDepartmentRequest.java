package com.example.demo.DTO.Client;


import com.example.demo.model.EPronoun;
import com.example.demo.model.ERole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDepartmentRequest {

    public String id;
    public String firstname;

    public String lastname;

    public String age;

    public String client_info;

    @Enumerated(EnumType.STRING)
    public ERole role;


    public Boolean isDeleted;
    private String email;
    public long department_id;
}
