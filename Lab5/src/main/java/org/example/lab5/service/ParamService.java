package org.example.lab5.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ParamService {

    @Autowired
    HttpServletRequest request;

    /**
     * Đọc chuỗi giá trị của tham số
     */
    public String getString(String name, String defaultValue) {
        String value = request.getParameter(name);
        return value != null ? value : defaultValue;
    }

    /**
     * Đọc số nguyên giá trị của tham số
     */
    public int getInt(String name, int defaultValue) {
        String value = request.getParameter(name);
        if (value != null) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }
        return defaultValue;
    }

    /**
     * Đọc số thực giá trị của tham số
     */
    public double getDouble(String name, double defaultValue) {
        String value = request.getParameter(name);
        if (value != null) {
            try {
                return Double.parseDouble(value);
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }
        return defaultValue;
    }

    /**
     * Đọc giá trị boolean của tham số
     */
    public boolean getBoolean(String name, boolean defaultValue) {
        String value = request.getParameter(name);
        if (value != null) {
            return value.equalsIgnoreCase("true") || value.equals("1");
        }
        return defaultValue;
    }

    /**
     * Đọc giá trị thời gian của tham số
     */
    public Date getDate(String name, String pattern) {
        String value = request.getParameter(name);
        if (value != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                return sdf.parse(value);
            } catch (Exception e) {
                throw new RuntimeException("Invalid date format: " + value);
            }
        }
        return null;
    }

    /**
     * Lưu file upload vào thư mục
     */
    public File save(MultipartFile file, String path) {
        if (file == null || file.isEmpty()) {
            return null;
        }

        try {
            File dir = new File("src/main/resources/static" + path);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String filename = file.getOriginalFilename();
            File savedFile = new File(dir, filename);
            file.transferTo(savedFile);

            return savedFile;
        } catch (IOException e) {
            throw new RuntimeException("Error saving file: " + e.getMessage());
        }
    }
}