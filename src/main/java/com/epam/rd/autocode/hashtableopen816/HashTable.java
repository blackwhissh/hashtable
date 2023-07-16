package com.epam.rd.autocode.hashtableopen816;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HashTable implements HashtableOpen8to16 {
    private int bucketSize;
    private ArrayList<HashNode> bucketArray; //Hashtable
    private HashNode bucket; //Node

    public HashTable(){
        this.bucketSize = 8;
        bucketArray = new ArrayList<>();

    }

    @Override
    public void insert(int key, Object value) {
        System.out.println("bucket size before adding an element: " + bucketSize);
        int bucketIndex = hashCode(key);
        for(HashNode currentBucket : bucketArray){
            if(currentBucket != null && currentBucket.key == bucketIndex){
                currentBucket.values.add(value);
                return;
            }else if(currentBucket == null){
                bucketArray.removeIf(Objects::isNull);
                currentBucket = new HashNode(bucketIndex, new ArrayList<>());
                currentBucket.values.add(value);
                bucketArray.add(currentBucket);
                System.out.println("added new bucket");
                return;
            }
        }
        HashNode newBucket = new HashNode(bucketIndex, new ArrayList<>());
        newBucket.values.add(value);
        bucketArray.add(newBucket);
        System.out.println("bucket array before resize: " + bucketArray.size());
        resize(bucketArray);
        System.out.println("bucket array: " + bucketArray.size());
    }

    @Override
    public Object search(int key) {
        return null;
    }

    @Override
    public void remove(int key) {

    }

    @Override
    public int size() {
        return this.bucketSize;
    }

    public List<HashNode> resize(List<HashNode> bucketArray){
        bucketSize++;
        if(this.bucketArray.size() == 1){
            bucketSize = 2;
            for(int i = bucketArray.size()-1; i < bucketSize-1; i++){
                bucketArray.add(null);
            }
        } else if (this.bucketArray.size() == 3) {
            bucketSize = 4;
            for(int i = bucketArray.size()-1; i < bucketSize; i++){
                bucketArray.add(null);
            }
        } else if (this.bucketArray.size() > 4 && this.bucketArray.size() < 8) {
            bucketSize = 8;
            for(int i = bucketArray.size()-1; i < bucketSize; i++){
                bucketArray.add(null);
            }
        } else if (this.bucketArray.size() > 8 && this.bucketArray.size() < 16) {
            bucketSize = 16;
            for(int i = bucketArray.size()-1; i < bucketSize; i++){
                bucketArray.add(null);
            }
        } else if(this.bucketArray.size() > 16){
            throw new IllegalStateException();
        }

        return bucketArray;
    }

    @Override
    public int[] keys() {
        return new int[0];
    }

    public int hashCode(int key){
        return key%this.bucketSize;
    }

    public static void main(String[] args) {
        HashTable hashTable = new HashTable();
        System.out.println("Starting array size: " + hashTable.bucketSize);
        hashTable.insert(1,10);
        hashTable.insert(1,10);
        hashTable.insert(2,10);

        System.out.println("after adding element size: " + hashTable.bucketSize);
        for (HashNode bucket : hashTable.bucketArray){
            if(bucket != null){
                System.out.println("keys: " + bucket.key);
            }else{
                System.out.println("found null bucket");
            }

        }
        System.out.println("Ending array size: " + hashTable.bucketSize);
    }
}