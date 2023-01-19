package com.kirito.tool.ac;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class AcNode {

    private Map<Character,AcNode> children;

    private int position;

    /**
     * 失败结点
     */
    private AcNode fail;

    private Boolean end;

    private int rank;

    public AcNode(Map<Character, AcNode> children) {
        this.children = children;
    }

    public static AcNode newAcNode() {
        return new AcNode(new HashMap<>());
    }

}
