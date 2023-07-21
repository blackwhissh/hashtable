package com.epam.rd.autocode.hashtableopen816;

public class HashTable implements HashtableOpen8to16 {
    private HashNode[] buckets;
    private int numOfBuckets;
    static final double DEFAULT_LOAD_FACTOR = 0.75;
    int[] keysArray;
    HashNode dummy;
    static final double DELETION_LOAD_FACTOR = 0.75;
    private int size;
    public HashTable(){
        this.numOfBuckets = 8;
        buckets = new HashNode[numOfBuckets];
        this.dummy = new HashNode(0, -1);
    }

    @Override
    public void insert(int key, Object value) {
        HashNode temp = new HashNode(key,value);
        int nextSize = buckets.length + 1;
        int hashIndex = getBucketIndex(key);

        while(this.buckets[hashIndex] != null && this.buckets[hashIndex].key != key && this.buckets[hashIndex].key != 0){
            hashIndex++;
            hashIndex %= this.numOfBuckets;
        }

        if(this.buckets[hashIndex] == null || this.buckets[hashIndex].key == 0){
            this.size++;
            if(this.size > 16){
                throw new IllegalStateException();
            }
        }
        this.buckets[hashIndex] = temp;

        if(nextSize == 5 || nextSize == 9){
            rehashInsert();
        }

    }

    @Override
    public Object search(int key) {
        int hashIndex = getBucketIndex(key);
        int counter = 0;
        // finding the node with given key
        while (this.buckets[hashIndex] != null) {
            // If counter is greater than capacity to avoid infinite loop
            if (counter > this.numOfBuckets) {
                return -1;
            }
            // if node found return its value
            if (this.buckets[hashIndex].key == key) {
                return this.buckets[hashIndex].value;
            }
            hashIndex++;
            hashIndex %= this.numOfBuckets;
            counter++;
        }
        // If not found return 0
        return null;
    }

    @Override
    public void remove(int key) {

        int hashIndex = getBucketIndex(key);

        while (this.buckets[hashIndex] != null) {

            if (this.buckets[hashIndex].key == key) {

                this.buckets[hashIndex] = this.dummy;

                this.size--;

            }
            hashIndex++;
            hashIndex %= this.numOfBuckets;
        }



    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int[] keys() {
        keysArray = new int[numOfBuckets];
        for(int i = 0; i < numOfBuckets; i++){
            if(buckets[i] == null){
                keysArray[i] = 0;
            }else{
                keysArray[i] = buckets[i].key;
            }
        }

        return keysArray;
    }

    public int getBucketIndex(int key){
        if(key < 0){
            return key%numOfBuckets*-1;
        }
        return key % numOfBuckets;
    }

    private void rehashInsert(){

        HashNode[] temp = buckets;
        buckets = new HashNode[numOfBuckets*2];

        size = 0;
        numOfBuckets *= 2;

        for (HashNode hashNode : temp) {
            HashNode head = hashNode;

            while (head != null) {
                int key = head.key;
                Object value = head.value;

                insert(key, value);
                head = head.next;
            }
        }

    }

    public static void main(String[] args) {
        HashTable table = new HashTable();

        table.insert(-74, "Tom");
        table.insert(98, "Tom");
        table.insert(-44, "blas");
        table.insert(75, "Tom");
        table.insert(-27, "Tom");
        table.insert(76, "blas");
        table.insert(-33, "Tom");
        table.insert(42, "Tom");
        table.insert(-71, "Tom");
        table.insert(77, "Tom");


        for(int i : table.keys()){
            System.out.println(i);
        }

        table.remove(77);

        for(int i : table.keys()){
            System.out.println(i);
        }

        System.out.println(table.size());



    }
}