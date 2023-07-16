package com.epam.rd.autocode.hashtableopen816;

import com.google.common.primitives.Ints;

import java.nio.file.Path;
import java.util.*;

public class HashTable implements HashtableOpen8to16 {
    private HashNode[] buckets;
    private int numOfBuckets;
    private int size;
    public HashTable(){
        this.numOfBuckets = 8;
        buckets = new HashNode[numOfBuckets];
    }

    @Override
    public void insert(int key, Object value) {
        int bucketIndex = getBucketIndex(key);
        HashNode head = buckets[bucketIndex];
        while(head != null){
            if (head.key.equals(key)){
                head.value = value;
                return;
            }
            head = head.next;
        }
        size++;
        head = buckets[bucketIndex];
        HashNode node = new HashNode(key,value);
        node.next = head;
        buckets[bucketIndex] = node;
        if (this.size == this.numOfBuckets) {
            resize(2 * this.numOfBuckets); // Double the capacity
        }
    }

    @Override
    public Object search(int key) {
        int bucketIndex = getBucketIndex(key);
        HashNode head = buckets[bucketIndex];
        while(head != null){
            if (head.key == key){
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    @Override
    public void remove(int key) {
        int bucketIndex = getBucketIndex(key);
        HashNode head = buckets[bucketIndex];
        HashNode previous = null;
        while(head != null){
            if (head.key == key){
                break;
            }
            previous = head;
            head = head.next;
        }
        if (head == null){
            return;
        }
        size--;
        if (previous != null){
            previous.next = head.next;
        }else {
            buckets[bucketIndex] = head.next;
        }

        if (this.numOfBuckets > 8 && this.size <= this.numOfBuckets / 4) {
            resize(this.numOfBuckets / 2); // Halve the capacity
        }
    }

    @Override
    public int size() {
        return this.size;
    }



    public void resize(int newCapacity){
        HashNode[] newBucketsArray = new HashNode[newCapacity];

        for (HashNode node : this.buckets){
            while (node != null){
                int bucketIndex = node.key % newCapacity;

                HashNode newNode = new HashNode(node.key, node.value);
                newNode.next = newBucketsArray[bucketIndex];
                newBucketsArray[bucketIndex] = newNode;

                node = node.next;
            }
        }
        this.buckets = newBucketsArray;
        this.numOfBuckets = newCapacity;
    }

    @Override
    public int[] keys() {
        ArrayList<Integer> keysArray = new ArrayList<>();
        for(HashNode node : buckets){
            if(node != null)
            keysArray.add(node.key);
        }
        return Ints.toArray(keysArray);
    }

    public int getBucketIndex(int key){
        return key % numOfBuckets;
    }



    public static void main(String[] args) {
        HashTable table = new HashTable();

        table.insert(1, "Tom");
        table.insert(2, "Harry");
        table.insert(3, "Dinesh");
        table.insert(4, "blah");
        table.insert(5, "blah");
        table.insert(6, "blah");
        table.insert(7, "blah");
        table.insert(8, "blah");
        table.insert(9, "blah");
        table.insert(10, "blah");

        System.out.println(table.size());



    }
}