package org.example.lab4.controller;

import org.example.lab4.model.Staff;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class StaffController {

    @RequestMapping("/staff/create/form")
    public String createForm(Model model, @ModelAttribute("staff") Staff staff) {
        model.addAttribute("message", "Vui lòng nhập thông tin nhân viên!");
        return "demo/staff-create";
    }

    @RequestMapping("/staff/create/save")
    public String createSave(Model model,
                             @RequestPart(value = "photo_file", required = false) MultipartFile photoFile,
                             @Valid @ModelAttribute("staff") Staff staff,
                             Errors errors) {

        // Handle file upload
        if(photoFile != null && !photoFile.isEmpty()) {
            try {
                // cái này này lụm chỗ để up ảnh nè
                String uploadDir = "src/main/resources/static/photos/";
                File dir = new File(uploadDir);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                String filename = photoFile.getOriginalFilename();
                File file = new File(uploadDir + filename);
                photoFile.transferTo(file);

                staff.setPhoto(filename);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(errors.hasErrors()) {
            model.addAttribute("message", "Vui lòng sửa các lỗi sau!");
        } else {
            model.addAttribute("message", "Xin chào " + staff.getFullname() + "! Dữ liệu đã nhập đúng!");
        }

        return "demo/staff-create";
    }
}