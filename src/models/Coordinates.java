package models;


import validation.Ask;
import validation.MaxValue;

import jakarta.xml.bind.annotation.XmlElement;

public class Coordinates {
    @XmlElement
    @Ask
    private double x;
    @XmlElement
    @MaxValue(810)
    @Ask
    private int y; //Максимальное значение поля: 810

    public Coordinates(double x, int y) {
        this.x = x;
        this.y = y;
    }
    public Coordinates() {

    }
    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public void setX(double parseDouble) {
        this.x = parseDouble;
    }

    public void setY(int parseInt) {
        this.y = parseInt;
    }
}