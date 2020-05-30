package com.fablab.booking.dto;

import lombok.Data;

@Data
public class RsArticleCategoryDto extends AbstractResponseEntityDto {
    private String name;
    private String description;
}
