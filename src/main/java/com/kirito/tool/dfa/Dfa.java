package com.kirito.tool.dfa;

import com.kirito.tool.common.MyUtil;
import com.kirito.tool.common.dto.FindResponse;
import com.kirito.tool.common.dto.SearchItem;
import com.kirito.tool.common.dto.SensitiveWords;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
@Slf4j
public class Dfa {

    private DfaNode root;

    public Dfa(DfaNode root) {
        this.root = root;
    }

    public static Dfa NewDfa() {
        return new Dfa(DfaNode.NewDfaNode());
    }

    public void loadWords(List<SensitiveWords> words) {
        long now = System.currentTimeMillis() / 1000;
        for (SensitiveWords word : words) {
            this.add(word.getWord(), word.getRank());
        }
        log.info("load Word:{}sec:{}", words.size(), System.currentTimeMillis() / 1000 - now);
    }

    public void add(String word, int rank) {
        if (word.length() == 0) {
            return;
        }
        DfaNode nd = this.root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!nd.getChildren().containsKey(c)) {
                nd.getChildren().put(c, DfaNode.NewDfaNode());
            }
            nd = nd.getChildren().get(c);
        }
        nd.setRank(rank);
        nd.setEnd(true);
    }

    public List<SearchItem> search(String contentStr) {
        var result = new ArrayList<SearchItem>();
        int size = contentStr.length();
        var currentNode = this.getRoot();
        for (int i = 0; i < size; i++) {
            char c = contentStr.charAt(i);
            var child = currentNode;
            for (int end = i; end < size; ++end) {
                if (!child.getChildren().containsKey(contentStr.charAt(end))) {
                    break;
                }
                child = child.getChildren().get(contentStr.charAt(end));
                if (!child.getEnd()) {
                    continue;
                }
                if (size < end - 1 && MyUtil.isWordCell(c) && MyUtil.isWordCell(contentStr.charAt(end + 1))) {
                    continue;
                }
                if (i > 0 && MyUtil.isWordCell(c) && MyUtil.isWordCell(contentStr.charAt(i - 1))) {
                    continue;
                }
                result.add(new SearchItem(i, end, contentStr.substring(i, end + 1), child.getRank()));
            }
        }
        return result;
    }

    public FindResponse replace(String content, int rank) {
        var res = new FindResponse();
        res.setBadWords(new HashMap<>());
        var result = this.search(content);
        StringBuilder contentBuff = new StringBuilder(content);
        for (SearchItem item : result) {
            if (item.getRank() > rank && rank != 0) {
                continue;
            }
            for (int i = item.getStartP(); i < item.getEndP(); ++i) {
                contentBuff.setCharAt(i, '*');
            }
            List<SearchItem> itemList = res.getBadWords().getOrDefault(item.getRank(), new ArrayList<>());
            itemList.add(item);
            res.getBadWords().put(item.getRank(), itemList);
        }
        res.setStatus(0);
        res.setNewContent(contentBuff.toString());
        return res;
    }

}
