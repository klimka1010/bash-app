package data;

import collection.GenerationID;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.io.Serializable;
import java.time.ZonedDateTime;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)


public class City implements Comparable<City> , Serializable {
    private Long id;                                        // Поле не может быть null,      Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name;                                    // Поле не может быть null,      Строка не может быть пустой
    private Coordinates coordinates;                        // Поле не может быть null

    @XmlTransient
    private java.time.ZonedDateTime creationDate;           // Поле не может быть null,       Значение этого поля должно генерироваться автоматически



//  ______________________________________
    private Float area;                                     // Поле не может быть null,       Значение должно быть больше 0
    private long population;                                // Поле не может быть null,       Значение должно быть больше 0
    private double metersAboveSeaLevel;
//  ______________________________________
    private Climate climate;                                // Поле не может быть null
    private Government government;                          // Поле не может быть null
    private StandardOfLiving standardOfLiving;              // Поле может быть null






    public City() {
    }

    public City(String name, Coordinates coordinates, long population, double metersAboveSeaLevel, Climate climate, Government government, StandardOfLiving standardOfLiving) {
//        if (name == null || coordinates == null || area == null || String.valueOf(population) == null || String.valueOf(metersAboveSeaLevel) == null || climate == null || government == null || standardOfLiving == null){
//            throw new InvalidInputException("Значение не может быть null");
//        }
        this.id = GenerationID.generateID();
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = ZonedDateTime.now();
        this.area = area;
        this.population = population;
        this.metersAboveSeaLevel = metersAboveSeaLevel;
        this.climate = climate;
        this.government = government;
        this.standardOfLiving = standardOfLiving;
//        this.governor = governor; todo
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = GenerationID.generateID();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @XmlElement(name = "creation_date")
    @XmlJavaTypeAdapter(ZonedDateTimeAdapter.class)
    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Float getArea() {
        return area;
    }

    public void setArea(Float area) {
        this.area = area;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public double getMetersAboveSeaLevel() {
        return metersAboveSeaLevel;
    }

    public void setMetersAboveSeaLevel(double metersAboveSeaLevel) {
        this.metersAboveSeaLevel = metersAboveSeaLevel;
    }

    public Climate getClimate() {
        return climate;
    }

    public void setClimate(Climate climate) {
        this.climate = climate;
    }

    public Government getGovernment() {
        return government;
    }

    public void setGovernment(Government government) {
        this.government = government;
    }

    public StandardOfLiving getStandardOfLiving() {
        return standardOfLiving;
    }

    public void setStandardOfLiving(StandardOfLiving standardOfLiving) {
        this.standardOfLiving = standardOfLiving;
    }

//    todo
//    public Human getGovernor() {
//        return governor;
//    }
//    public void setGovernor(Human governor) {
//        this.governor = governor;
//    }

    @Override
    public int compareTo(City o) {
        if (this.getArea() > o.getArea()) {
            return 1;
        } else if (this.getArea() < o.getArea()) {
            return -1;
        } else {
            return 0;
        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("City{")
                .append("id=").append(id)
                .append(", name='").append(name).append('\'')
                .append(", coordinates=").append(coordinates)
                .append(", creationDate=").append(creationDate)
                .append(", area=").append(area)
                .append(", population=").append(population)
                .append(", metersAboveSeaLevel=").append(metersAboveSeaLevel)
                .append(", climate=").append(climate)
                .append(", government=").append(government)
                .append(", standardOfLiving=").append(standardOfLiving)
                //.append(", governor=").append(governor) // todo
                .append('}');

        return sb.toString();
    }
    public boolean validate() {
        if (this.id == null ||
                this.id < 0 ||
                this.name == null ||
                this.name.isEmpty() ||
                this.coordinates == null ||
                this.creationDate == null ||
                this.government == null) {
            return false;
        } else {
            return true;
        }
    }

}

