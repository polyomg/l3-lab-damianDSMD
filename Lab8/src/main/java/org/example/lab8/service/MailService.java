package org.example.lab8.service;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public interface MailService {

    @Data
    @Builder
    public static class Mail {
        private String from;
        private String to;
        private String cc;
        private String bcc;
        private String subject;
        private String body;
        private String filenames; // comma separated file paths
    }

    void send(Mail mail);
    default void send(String to, String subject, String body) {
        Mail mail = Mail.builder().to(to).subject(subject).body(body).build();
        this.send(mail);
    }

    // queue methods
    void push(Mail mail);
    default void push(String to, String subject, String body) {
        this.push(Mail.builder().to(to).subject(subject).body(body).build());
    }
}
