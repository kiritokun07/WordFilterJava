package com.kirito.tool.common.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SensitiveWords implements Serializable {

    private String word;

    private int rank;

}
