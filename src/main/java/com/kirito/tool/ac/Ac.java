package com.kirito.tool.ac;

import com.kirito.tool.common.dto.FindResponse;
import com.kirito.tool.common.dto.SearchItem;
import com.kirito.tool.common.dto.SensitiveWords;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
public class Ac {

    private AcNode root;

    public void loadWords(List<SensitiveWords> words) {
        long now = System.currentTimeMillis() / 1000;
        for (SensitiveWords word : words) {
            this.add(word.getWord(), word.getRank());
        }
        log.info("ac load Word:{}sec:{}", words.size(), System.currentTimeMillis() / 1000 - now);
    }

    public Ac(AcNode root) {
        this.root = root;
    }

    public static Ac newAc() {
        return new Ac(AcNode.newAcNode());
    }

    public void add(String word, int rank) {
        if (word.length() == 0) {
            return;
        }
        AcNode nd = this.getRoot();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!nd.getChildren().containsKey(c)) {
                nd.getChildren().put(c, AcNode.newAcNode());
            }
            nd.getChildren().get(c).setPosition(i);
            nd = nd.getChildren().get(c);
        }
        nd.setEnd(true);
        nd.setRank(rank);
    }

    public void make() {
        List<AcNode> queue = new ArrayList<>();
        queue.add(this.getRoot());
        //TODO
    }

    public List<SearchItem> search(String contentStr) {
        //TODO
        return null;
    }

    public FindResponse replace(String content, int rank) {
        //TODO
        return null;
    }

}
