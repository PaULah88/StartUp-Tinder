package com.example.demo.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Data
public class StartupStateDTO {

    private Integer id;

    @NotNull
    private String type;

}
