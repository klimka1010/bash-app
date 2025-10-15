package data;

import error.InvalidInputException;

import jakarta.xml.bind.annotation.*;
import java.io.Serializable;
@XmlRootElement
@XmlAccessorType (XmlAccessType.FIELD)
@XmlType(propOrder = {"x", "y"})

public class Coordinates implements Serializable {
    @XmlTransient
    private float x;
    @XmlTransient
    private int y;

    public Coordinates(Integer x, Float aFloat) {
    }
    public Coordinates() {
    }

    public Coordinates(float x, int y) {
        if (String.valueOf(x) == null || String.valueOf(y) == null) {
            throw new InvalidInputException("Значение не может быть null");
        }
//        if (x <= -595) {
//            throw new InvalidInputException("Значение поля должно быть больше -595");
//        }
        this.x = x;
        this.y = y;
    }

    @XmlElement
    public void setX(float x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }

    @XmlElement
    public void setY(int y) {
        this.y = y;
    }
    public float getX() {
        return x;
    }


    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}


