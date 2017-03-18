package xyz.kots.circleapplication;

/**
Предок всех кругов
 */

public class SimpleCircle {

    // координаты
    protected int x;
    protected int y;
    protected int radius;
    private int color;

    public SimpleCircle(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    //область вокруг главного круга
    public SimpleCircle getCircleArea() {
        return  new SimpleCircle(x,y, radius*3);
    }

    //узнаем, пересекаются ли круги при старте
    public boolean isIntersect(SimpleCircle circle) {
        return radius + circle.radius >= Math.sqrt(Math.pow(x - circle.x, 2) + Math.pow(y - circle.y, 2));
    }
}
