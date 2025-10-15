package client;

import console.ConsoleManager;
import data.*;
import error.InvalidInputException;
import java.util.ArrayList;

public class ClientManager {
    ConsoleManager consoleManager = new ConsoleManager();

    public City getCity() {
        String name = consoleManager.readName();
        float x = consoleManager.readCoordinateX();
        int y = consoleManager.readCoordinateY();
        float area = consoleManager.readArea();
        long population = consoleManager.readPopulation();
        double metersAboveSeaLevel = consoleManager.readMetersAboveSeaLevel();
        Climate climate = consoleManager.readClimate();
        Government government = consoleManager.readGovernment();
        StandardOfLiving standardOfLiving = consoleManager.readStandardOfLiving();

        return new City(name, new Coordinates(x, y), population, metersAboveSeaLevel, climate, government, standardOfLiving);
    }


    public static City createCityFromScript(ArrayList<String> data) {
        try {
            if (data.size() != 9) {
                throw new InvalidInputException("Неправильное количество аргументов!");
            }

            String name = data.get(0);
            float x = Float.parseFloat(data.get(1));
            int y = Integer.parseInt(data.get(2));
            float area = Float.parseFloat(data.get(3));
            long population = Long.parseLong(data.get(4));
            double metersAboveSeaLevel = Double.parseDouble(data.get(5));
            Climate climate = Enum.valueOf(Climate.class, data.get(6));
            Government government = Enum.valueOf(Government.class, data.get(7));
            StandardOfLiving standardOfLiving = Enum.valueOf(StandardOfLiving.class, data.get(8));

            Coordinates coordinates = new Coordinates(x, y);

            return new City(name, coordinates, population, metersAboveSeaLevel, climate, government, standardOfLiving);
        } catch (NumberFormatException e) {
            System.out.println("Неправильный формат данных!");
            return null;
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void print() {
        System.out.print("> ");
    }
}
