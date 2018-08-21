package com.harshgupta;


public class ColoredPoint extends Point {

    private String c;

    public ColoredPoint(int x, int y, String c){
        super(x, y);
        this.c = c;
    }

    public String getColor(){
        return this.c;
    }

    public void setColor(String c){
        this.c = c;
    }

    public String toString(){
        return super.toString() + " is of color " + this.c + ".";
    }
}
