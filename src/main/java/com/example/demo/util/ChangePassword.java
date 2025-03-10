package com.example.demo.util;

import lombok.Builder;

@Builder
public record ChangePassword(String password, String repeatPassword) {
}
