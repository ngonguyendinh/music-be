package com.example.demo.util;

import lombok.Builder;

@Builder
public record MailBody(String to, String subject, String text) {

}
