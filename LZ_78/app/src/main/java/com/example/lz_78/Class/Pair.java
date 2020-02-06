package com.example.lz_78.Class;

public class Pair {
    private int key;
    private char value;

    public Pair(int key, char value){
        this.key = key;
        this.value = value;
    }
    public int getKey(){ return this.key; }
    public char getValue(){ return this.value; }

    public void setKey(int key){ this.key = key; }
    public void setValue(char value){ this.value = value; }
}
