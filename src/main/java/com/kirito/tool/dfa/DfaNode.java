package com.kirito.tool.dfa;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class DfaNode {

    private Map<Character, DfaNode> children;

    private int rank;

    private Boolean end;

    public DfaNode(Map<Character, DfaNode> children) {
        this.children = children;
    }

    public static DfaNode NewDfaNode() {
        return new DfaNode(new HashMap<>());
    }

}
