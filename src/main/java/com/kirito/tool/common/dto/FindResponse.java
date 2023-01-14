package com.kirito.tool.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
public class FindResponse implements Serializable {

    private int status;

    private String newContent;

    private String errMsg;

    private Map<Integer, List<SearchItem>> badWords;

}
