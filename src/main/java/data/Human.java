package data;

import error.InvalidInputException;

import jakarta.xml.bind.annotation.*;


@XmlRootElement
@XmlAccessorType (XmlAccessType.FIELD)
public class Human {
    private String name;
    private Long height;
//    Governor todo

    public Human() {
    }

    public Human(String name, Long height) {
        if (name == null || name.isEmpty() || name.isBlank()){
            throw new InvalidInputException("Строка не может быть пустой");
        }
        if (height == null){
            throw new InvalidInputException("height не может быть null");
        }
        this.name = name;
        this.height = height;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public Human setHeight(Long height) {
        if (0 >= height || height > 1000){
            throw new InvalidInputException("Значение поля height должно быть больше 0, Максимальное значение поля: 1000");
        }
        this.height = height;
        return null;
    }
    public Long getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", height=" + height +
                '}';
    }
}
