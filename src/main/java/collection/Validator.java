package collection;

import data.*;

public class Validator {


    public boolean checkName(String name) {
        if (name.equals("") || !name.matches("^[a-zA-Z-А-Яа-я]*$")) {
            return false;
        }
        return true;
    }

    public boolean checkCoordinateX(float X) {
        if (X <= -59 || String.valueOf(X) == null) {
            return false;
        }
        return true;
    }

    public boolean checkCoordinateY(int Y) {
        if (String.valueOf(Y) == null) {
            return false;
        }
        return true;
    }

    public boolean checkArea(Float area) {
        if (area < 0) {
            return false;
        }
        return true;
    }

    public boolean checkPopulation(Long population) {
        if (0 >= population || population > 1000) {
            return false;
        }
        return true;
    }

    public boolean checkMetersAboveSeaLevel(double metersAboveSeaLevel) {
        if (0 >= metersAboveSeaLevel || metersAboveSeaLevel > 1000) {
            return false;
        }
        return true;
    }

    public boolean checkClimate(Climate climate) {
        for (Climate category1 : Climate.values()) {
            if (!category1.name().equals(climate)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkGovernment(Government government) {
        for (Government government1 : Government.values()) {
            if (!government1.name().equals(government)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkStandardOfLiving(StandardOfLiving standardOfLiving) {
        for (StandardOfLiving standardOfLiving1 : StandardOfLiving.values()) {
            if (!standardOfLiving1.name().equals(standardOfLiving)) {
                return false;
            }
        }
        return true;
    }
}
