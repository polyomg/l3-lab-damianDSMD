package org.example.lab8.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service("mailService")
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    // simple queue
    private final List<Mail> queue = new ArrayList<>();

    @Override
    public synchronized void send(Mail mail) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");

            // 2.1 from
            if (mail.getFrom() != null && !mail.getFrom().isBlank()) {
                helper.setFrom(mail.getFrom());
                helper.setReplyTo(mail.getFrom());
            }

            // 2.2 recipients
            helper.setTo(mail.getTo());
            if (mail.getCc() != null && !mail.getCc().isBlank()) helper.setCc(mail.getCc());
            if (mail.getBcc() != null && !mail.getBcc().isBlank()) helper.setBcc(mail.getBcc());

            // 2.3 subject & body
            helper.setSubject(mail.getSubject());
            helper.setText(mail.getBody() == null ? "" : mail.getBody(), true);

            // 2.4 attachments
            String filenames = mail.getFilenames();
            if (filenames != null && !filenames.isBlank()) {
                for (String filename : filenames.split("[,;]+")) {
                    File f = new File(filename.trim());
                    if (f.exists() && f.isFile()) {
                        FileSystemResource fr = new FileSystemResource(f);
                        helper.addAttachment(f.getName(), fr);
                    }
                }
            }

            mailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public synchronized void push(Mail mail) {
        queue.add(mail);
    }

    // scheduled worker: every 500 ms check queue
    @Scheduled(fixedDelay = 500)
    public synchronized void run() {
        while (!queue.isEmpty()) {
            try {
                Mail m = queue.remove(0);
                this.send(m);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
