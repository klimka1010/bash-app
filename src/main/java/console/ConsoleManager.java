package console;

import client.ClientManager;
import collection.CollectionManager;
import command.CommandManager;
import jakarta.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import static collection.Parser.loadFromXml;
import data.*;
import jakarta.xml.bind.annotation.XmlElement;

public class ConsoleManager implements ReaderWriter {

    private final Scanner scanner = new Scanner(System.in);


    private List<City> collection;

    @XmlElement(name = "item")
    public List<City> getCollection() {
        return collection;
    }

    public void setCollection(List<City> collection) {
        this.collection = collection;
    }


    @Override
    public Long readLong() {
        return Long.valueOf(scanner.nextLine().trim());
    }

    @Override
    public String readLine() {
        return scanner.nextLine().trim();
    }

    @Override
    public void writeLine(String text) {
        System.out.println(text);
    }

    @Override
    public void write(String text) {
        System.out.print(text);
    }

    @Override
    public String getValidatedValue(String message) {
        write(message);
        while (true) {
            String userPrint = readLine();
            if (!userPrint.isEmpty() && !userPrint.isBlank()) {
                return userPrint;
            }
        }
    }

    public void fileRead() throws JAXBException, FileNotFoundException {
        while (true) {
            try {
                write("Введите название файла: ");
                String inputPath = readLine().trim();

                // Используем рабочую директорию проекта
                File file = new File(System.getProperty("user.dir"), inputPath);

//                File file = new File(path);

                if (!file.exists()) {
                    writeLine("Файл не найден!");
                    continue;
                }

                CollectionManager collectionManager = new CollectionManager();
                collectionManager.setCollection(loadFromXml(inputPath).getCollection());

                CommandManager commandManager = new CommandManager(collectionManager, this);

                CommandManager.setFileLink(inputPath);

                while (CommandManager.getWork()) {
                    commandManager.existCommand();
                }

                break;
            } catch (IllegalArgumentException e) {
                writeLine("Произошла ошибка: " + e.getMessage());
            }
        }
    }


    public String readName() {
        writeLine("Введите название города: ");
        ClientManager.print();
        String name = readLine();
        while (true) {
            if (name.isEmpty()) {
                writeLine("Имя не может быть пустой строкой, введите имя");
                name = readLine();
            } else if (!name.matches("^[a-zA-Z-А-Яа-я]*$")) {
                writeLine("Имя не может быть иными знаками кроме букв");
                name = readLine();
            } else {
                return name;
            }
        }
    }

    public float readArea() {
        writeLine("Введите площадь города: ");
        ClientManager.print();
        while (true) {
            try {
                String areaString = readLine();
                float area = Float.parseFloat(areaString);
                if (area <= 0) {
                    writeLine("Значение поля должно быть больше 0. Введите значение снова");
                    continue;
                }
                if (!areaString.equals("")) {
                    return area;
                } else {
                    writeLine("Вы должны ввести число, а не пустую строку");
                }
            } catch (NumberFormatException e) {
                writeLine("Число введено неверно");
            }
        }
    }

    public long readPopulation() {
        writeLine("Введите население города: ");
        ClientManager.print();
        while (true) {
            try {
                String populationString = readLine();
                long population = Long.parseLong(populationString);
                if (population <= 0) {
                    writeLine("Значение поля должно быть больше 0. Введите значение снова");
                    continue;
                }
                if (!populationString.equals("")) {
                    return population;
                } else {
                    writeLine("Вы должны ввести число, а не пустую строку");
                }
            } catch (NumberFormatException e) {
                writeLine("Число введено неверно");
            }
        }
    }

    public double readMetersAboveSeaLevel() {
        writeLine("Введите высоту над уровнем моря: ");
        ClientManager.print();
        while (true) {
            try {
                String metersAboveSeaLevelString = readLine();
                double metersAboveSeaLevel = Double.parseDouble(metersAboveSeaLevelString);
                if (metersAboveSeaLevel <= 0) {
                    writeLine("Значение поля должно быть больше 0. Введите значение снова");
                    continue;
                }
                if (!metersAboveSeaLevelString.equals("")) {
                    return metersAboveSeaLevel;
                } else {
                    writeLine("Вы должны ввести число, а не пустую строку");
                }
            } catch (NumberFormatException e) {
                writeLine("Число введено неверно");
            }
        }
    }

    public float readCoordinateX() {
        writeLine("Введите координату X: ");
        ClientManager.print();
        while (true) {
            try {
                String xString = readLine();
                float x = Float.parseFloat(xString);
                if (!xString.equals("")) {
                    return x;
                } else {
                    writeLine("Вы должны ввести число, а не пустую строку");
                }
            } catch (NumberFormatException e) {
                writeLine("Число введено неверно");
            }
        }
    }

    public int readCoordinateY() {
        writeLine("Введите координату Y:");
        ClientManager.print();
        while (true) {
            try {
                String yString = readLine();
                int y = Integer.parseInt(yString);
                if (!yString.equals("")) {
                    return y;
                } else {
                    writeLine("Вы должны ввести число, а не пустую строку");
                }
            } catch (NumberFormatException e) {
                writeLine("Число введено неверно");
            }
        }
    }


    public Climate readClimate() {
        Climate[] climates = Climate.values();
        writeLine("Выберите климат города:");

        for (int i = 0; i < climates.length; i++) {
            writeLine((i + 1) + " - " + climates[i].name());
        }

        while (true) {
            write("> ");
            String input = readLine().trim();

            try {
                int index = Integer.parseInt(input);
                if (index > 0 && index <= climates.length) {
                    return climates[index - 1];
                } else {
                    writeLine("Неверный номер. Введите число от 1 до " + climates.length);
                }
            } catch (NumberFormatException e) {
                try {
                    return Climate.valueOf(input.toUpperCase());
                } catch (IllegalArgumentException ex) {
                    writeLine("Неверное значение. Попробуйте снова.");
                }
            }
        }
    }

    public Government readGovernment() {
        Government[] governments = Government.values();
        writeLine("Выберите управление города: ");

        for (int i = 0; i < governments.length; i++) {
            writeLine((i + 1) + " - " + governments[i].name());
        }

        while (true) {
            write("> ");
            String input = readLine().trim();

            try {
                int index = Integer.parseInt(input);
                if (index > 0 && index <= governments.length) {
                    return governments[index - 1];
                } else {
                    writeLine("Неверный номер. Введите число от 1 до " + governments.length);
                }
            } catch (NumberFormatException e) {
                try {
                    return Government.valueOf(input.toUpperCase());
                } catch (IllegalArgumentException ex) {
                    writeLine("Неверное значние. Попробуйте снова");

                }


            }
        }
    }

    public StandardOfLiving readStandardOfLiving() {
        StandardOfLiving[] standardOfLiving = StandardOfLiving.values();
        writeLine("Выберите стандарт жизни города: ");

        for (int i = 0; i < standardOfLiving.length; i++) {
            writeLine((i + 1) + " - " + standardOfLiving[i].name());
        }

        while (true) {
            write("> ");
            String input = readLine().trim();

            try {
                int index = Integer.parseInt(input);
                if (index > 0 && index <= standardOfLiving.length) {
                    return standardOfLiving[index - 1];
                } else {
                    writeLine("Неверный номер. Введите число от 1 до " + standardOfLiving.length);
                }
            } catch (NumberFormatException e) {
                try {
                    return StandardOfLiving.valueOf(input.toUpperCase());
                } catch (IllegalArgumentException ex) {
                    writeLine("Неверное значние. Попробуйте снова");

                }


            }
        }

    }

}