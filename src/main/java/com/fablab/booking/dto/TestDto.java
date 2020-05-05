package com.fablab.booking.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
// TODO delete
public class TestDto {
    private String name;
    private String surname;
    //private MultipartFile multipartFile;
}
