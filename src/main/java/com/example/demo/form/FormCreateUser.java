package com.example.demo.form;

import lombok.Data;

@Data
public class FormCreateUser {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String gender;
}
