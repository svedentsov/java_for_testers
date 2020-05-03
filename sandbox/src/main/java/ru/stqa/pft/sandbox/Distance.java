package ru.stqa.pft.sandbox;

/**
 * расчет расстояния.
 */
public class Distance {
    public static void main(String[] args) {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(10, 20);
        System.out.println("Расстояние между точками  = " + p1.distance(p2));
    }
}
