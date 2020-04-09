package com.fablab.booking.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AbstractResponseEntityDto {
    private Long id;
    private Date createDate;
    private Date updateDate;
}
