package com.epam.rd.autocode.hashtableopen816;

import java.util.ArrayList;

public class HashNode {
    protected Integer key;
    protected Object value;
    protected HashNode next;

    public HashNode(int key, Object value){
        this.key = key;
        this.value = value;
    }

}
