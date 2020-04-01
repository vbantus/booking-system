package com.fablab.booking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public abstract class AbstractEntityDto {
//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
//    @ApiModelProperty(readOnly = true)
    private Long id;
    private Date createDate;
    private Date updateDate;
}
