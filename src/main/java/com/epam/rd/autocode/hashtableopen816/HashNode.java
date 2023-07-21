package com.epam.rd.autocode.hashtableopen816;

import java.util.ArrayList;

public class HashNode {
    protected Integer key;
    protected Object value;
    protected HashNode next;

    public HashNode(){

    }

    public HashNode(int key, Object value){
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
