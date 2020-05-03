package ru.stqa.pft.sandbox;

/**
 * Класс квадрата.
 */
public class Square {
    public double l;

    /**
     * Конструктор принимающий длину стороны квадрата.
     *
     * @param l длина стороны
     */
    public Square(double l) {
        this.l = l;
    }

    /**
     * Вычислить площать квадрата.
     *
     * @return площадь квадрата
     */
    public double area() {
        return this.l * this.l;
    }
}
