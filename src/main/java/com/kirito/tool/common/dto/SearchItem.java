package com.kirito.tool.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchItem implements Serializable {

    private int startP;

    private int endP;

    private String word;

    private int rank;

}
