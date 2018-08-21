package com.harshgupta;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Point[] points = new Point[1];
        points[0] = new Point(5,5);

        BoundingBox box = new BoundingBox(points);
        System.out.println(box.getTopLeft() + " " + box.getBottomRight());
    }
}
