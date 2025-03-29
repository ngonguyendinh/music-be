package com.example.demo.form;

import lombok.Data;

@Data
public class FormCreateUser {
    private String fullName;
    private String email;
    private String password;
    private String gender;
}
