package com.example.demo.form;

import lombok.Data;

@Data
public class FormUpdateUser {
    private String firstName;
    private String lastName;
    private String level;
    private String avatar;
    private String background;
    private String gender;
    private String oldPassword;
    private String newPassword;
}
