package org.example.lab3.model;

import lombok.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Staff {
    private String id;
    private String fullname;
    @Builder.Default
    private String photo = "photo.jpg";
    @Builder.Default
    private Boolean gender = true;
    @Builder.Default
    private Date birthday = new Date();
    @Builder.Default
    private Double salary = 12345.6789;
    @Builder.Default
    private Integer level = 0;
}
