package com.example.lukef_000.final05.Objects;

public class StringJoiner {

    private String delimeter;

    String str;

    public StringJoiner(String delimeter){
        this.delimeter = delimeter;
        str = "";
    }

    public void add(String s){
        if(str.isEmpty()){
            str += s;
        } else {
            str += delimeter + s;
        }
    }

    @Override
    public String toString() {
        return str;
    }
}
