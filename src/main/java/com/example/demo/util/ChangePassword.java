package com.example.demo.util;

import lombok.Builder;

@Builder
public record ChangePassword(String email,String password, String repeatPassword) {
}
