package ru.stqa.pft.sandbox;

/**
 * Класс прямоугольника.
 */
public class Rectangle {
    public double a;
    public double b;

    /**
     * Конструктор принимающий высоту и ширину прямоугольника.
     *
     * @param a высота
     * @param b ширина
     */
    public Rectangle(double a, double b) {
        this.a = a;
        this.b = b;
    }

    /**
     * Вычислить площать прямоугольника.
     *
     * @return площадь прямоугольника
     */
    public double area() {
        return this.a * this.b;
    }
}
