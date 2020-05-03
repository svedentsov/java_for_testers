package ru.stqa.pft.sandbox;

/**
 * Класс точки.
 */
public class Point {
    public double x;
    public double y;

    /**
     * Конструктор принимающий координаты точки в 2-х пространствах.*
     *
     * @param x координата x
     * @param y координата y
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Расстояние между двумя точками.
     *
     * @return расстояние
     */
    public double distance(Point that) {
        return Math.sqrt(Math.pow(this.x - that.x, 2) + Math.pow(this.y - that.y, 2));
    }
}
