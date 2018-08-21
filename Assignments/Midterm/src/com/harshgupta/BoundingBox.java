package com.harshgupta;

public class BoundingBox {

    private Point[] points;
    private Point topLeft, bottomRight;

    public BoundingBox(Point[] points){
        if(points == null){
            this.topLeft = new Point(0,0);
            this.bottomRight = new Point(0,0);
        }

        else{
            this.points = points;
            int minX, minY, maxX, maxY;

            minX = this.points[0].getX();
            minY = this.points[0].getY();
            maxX = this.points[0].getX();
            maxY = this.points[0].getY();

            for(int i = 0; i < this.points.length; i++) {
                if (this.points[i].getX() < minX) {
                    minX = this.points[i].getX();
                }
                else if (this.points[i].getX() > maxX) {
                    maxX = this.points[i].getX();
                }

                if (this.points[i].getY() < minY) {
                    minY = this.points[i].getY();
                }
                else if (this.points[i].getY() > maxY) {
                    maxY = this.points[i].getY();
                }
            }

            this.topLeft = new Point(minX, maxY);
            this.bottomRight = new Point(maxX, minY);

        }
    }

    public Point[] getPoints() {
        return points;
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    public void setPoints(Point[] points) {
        this.points = points;
    }

    public void setTopLeft(Point topLeft) {
        this.topLeft = topLeft;
    }

    public void setBottomRight(Point bottomRight) {
        this.bottomRight = bottomRight;
    }
}
