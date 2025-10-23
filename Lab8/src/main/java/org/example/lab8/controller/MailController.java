package org.example.lab8.controller;

import org.example.lab8.service.MailService;
import org.example.lab8.service.MailService.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    @GetMapping("/send-form")
    public String sendForm() {
        return "mail/send";
    }

    // send directly
    @PostMapping("/send-direct")
    public String sendDirect(@RequestParam String to,
                             @RequestParam String subject,
                             @RequestParam String body,
                             Model model) {
        Mail mail = Mail.builder().to(to).subject(subject).body(body).build();
        mailService.send(mail);
        model.addAttribute("message", "Mail sent directly");
        return "mail/send";
    }

    // push to queue
    @PostMapping("/send-queue")
    public String sendQueue(@RequestParam String to,
                            @RequestParam String subject,
                            @RequestParam String body,
                            Model model) {
        Mail mail = Mail.builder().to(to).subject(subject).body(body).build();
        mailService.push(mail);
        model.addAttribute("message", "Mail pushed to queue");
        return "mail/send";
    }
}
