package collection;

import command.Base;
import command.CommandManager;
import command.History;
import console.ConsoleManager;
import data.*;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CollectionManager implements ICollectionManager {

    @XmlElement
    private static LinkedList<City> collection = new LinkedList<>();

    @XmlElement(name = "creation_date_collectionManager")
    @XmlJavaTypeAdapter(ZonedDateTimeAdapter.class)
    private ZonedDateTime date = ZonedDateTime.now();

    private final History history;
    private CommandManager commandManager;


    public CollectionManager(ConsoleManager consoleManager, CommandManager commandManager) {
        this.history = new History(10, consoleManager);
        this.commandManager = commandManager;
    }

    public CollectionManager() {
        this(new ConsoleManager(), null);
    }

    public static LinkedList<City> getCollection() {
        Collections.sort(collection);
        return collection;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public void setCollection(LinkedList<City> cityCollection) {
        CollectionManager.collection = cityCollection;
    }

    @Override
    public String info() {
        StringBuilder infoBuilder = new StringBuilder();

        infoBuilder.append("Тип коллекции: ").append(collection.getClass().getSimpleName()).append("\nДата создания: ").append(date).append("\nКол-во элементов: ").append(collection.size());

        return infoBuilder.toString();
    }

    @Override
    public void show() {
        if (!collection.isEmpty()) {
            collection.forEach(CollectionUtil::display);
        } else {
            System.out.println("В коллекции нет объектов, доступных для просмотра!");
        }
    }

    @Override
    public int add(City city) {
        collection.add(city);
        return 0;
    }

    @Override
    public void updateId(City newCity, Long ID) {
        for (City city : collection) {
            if (city.getId().equals(ID)) {
                city.setName(newCity.getName());
                city.setCoordinates(newCity.getCoordinates());
                city.setArea(newCity.getArea());
                city.setPopulation(newCity.getPopulation());
                city.setMetersAboveSeaLevel(newCity.getMetersAboveSeaLevel());
                if (newCity.getClimate() != null) {
                    city.setClimate(newCity.getClimate());
                }
                if (newCity.getGovernment() != null) {
                    city.setGovernment(newCity.getGovernment());
                }
                if (newCity.getStandardOfLiving() != null) {
                    city.setStandardOfLiving(newCity.getStandardOfLiving());
                }
                return;
            }
        }
    }

    @Override
    public String removeById(Long ID) {
        StringBuilder resultBuilder = new StringBuilder();
        Iterator<City> iterator = collection.iterator();
        while (iterator.hasNext()) {
            City city = iterator.next();
            if (city.getId().equals(ID)) {
                iterator.remove();
                resultBuilder.append("Элемент удален из коллекции!");
                return resultBuilder.toString();
            }
        }
        resultBuilder.append("Элемента с таким ID не существует!");
        return resultBuilder.toString();
    }

    @Override
    public void clear() {
        collection.clear();
    }

    @Override
    public void removeGreater(Float area) {
        StringBuilder resultBuilder = new StringBuilder();
        Iterator<City> iterator = collection.iterator();
        boolean removed = false;
        while (iterator.hasNext()) {
            City city = iterator.next();
            if (city.getArea() > area) {
                iterator.remove();
                resultBuilder.append("Элемент удален из коллекции: ").append(city.getName()).append("\n");
                removed = true;
            }
        }
        if (!removed) {
            resultBuilder.append("Нет городов с такой площадью!");
        }
        System.out.println(resultBuilder.toString());
    }


    @Override
    public void filterThanGovernment(Government government) {
        StringBuilder resultBuilder = new StringBuilder();
        ArrayList<City> filterObjects = new ArrayList<>();
        for (City city : collection) {
            if (city.getGovernment() == government) {
                filterObjects.add(city);
            }
        }
        if (filterObjects.isEmpty()) {
            resultBuilder.append("Нет ни одного экземпляра с таким полем");
        } else {
            filterObjects.forEach(CollectionUtil::display);
        }
        System.out.println(resultBuilder.toString());
    }


    @Override
    public void printAscending() {
        if (collection.isEmpty()) {
            System.out.println("Коллекция пуста.");
            return;
        }

        collection.stream().sorted(Comparator.comparing(City::getId)).forEach(CollectionUtil::display);
    }

    @Override
    public void removeFirst() {
        if (collection.isEmpty()) {
            System.out.println("Коллекция пуста.");
            return;
        }

        // Найти элемент с минимальным ID
        City minIdCity = collection.stream().min(Comparator.comparing(City::getId)).orElse(null);

        if (minIdCity != null) {
            // Удалить найденный элемент
            Iterator<City> iterator = collection.iterator();
            while (iterator.hasNext()) {
                City city = iterator.next();
                if (city.equals(minIdCity)) {
                    iterator.remove();
                    System.out.println("Элемент удален из коллекции: " + city.getName());
                    return;
                }
            }
        } else {
            System.out.println("Не удалось найти элемент для удаления.");
        }
    }


    @Override
    public void groupCountingByClimate() {
        Map<Climate, Long> climateGroupCounts = collection.stream().collect(Collectors.groupingBy(City::getClimate, Collectors.counting()));

        if (climateGroupCounts.isEmpty()) {
            System.out.println("Нет данных для группировки.");
        } else {
            climateGroupCounts.forEach((climate, count) -> System.out.println("Климат: " + climate + " - Количество элементов: " + count));
        }
    }

    public void setCommandManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    //    todo
    @Override
    public void executeScript(File filename) {
        if (filename == null || !filename.exists() || filename.isDirectory()) {
            System.out.println("Файл не найден или является директорией.");
            return;
        }


        try (Scanner scanner = new Scanner(filename)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                // Игнорируем пустые строки и комментарии
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }

                String[] parts = line.split(" ", 2);
                String commandName = parts[0];
                String argument = parts.length > 1 ? parts[1] : "";

                Base command = CommandManager.getCommand(commandName);
                if (command != null) {
                    command.setArgument(argument);
                    try {
                        command.execute(new String[]{commandName, argument});
                    } catch (Exception e) {
                        System.out.println("Ошибка при выполнении команды '" + commandName + "': " + e.getMessage());
                    }
                } else {
                    System.out.println("Неизвестная команда: " + commandName);
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }


}
