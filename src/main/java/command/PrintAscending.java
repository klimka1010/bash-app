package command;

import collection.CollectionManager;
import collection.CollectionUtil;
import console.ConsoleManager;
import data.City;

import jakarta.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Comparator;

public class PrintAscending extends Base {

    private final CollectionManager collectionManager;
    private final ConsoleManager consoleManager;

    public PrintAscending(CollectionManager collectionManager, ConsoleManager consoleManager) {
        this.collectionManager = collectionManager;
        this.consoleManager = consoleManager;
    }


    public void execute(String[] args) throws JAXBException, IOException, FileNotFoundException {
        if (collectionManager.getCollection().isEmpty()) {
            readerWriter.writeLine("Коллекция пуста.");
            return;
        }

        if (args.length > 1) {
            consoleManager.writeLine("Вы неправильно ввели команду");
        } else {
            collectionManager.getCollection().stream()
                    .sorted(Comparator.comparing(City::getId))
                    .forEach(CollectionUtil::display);
            consoleManager.writeLine("Команда выполнена");
        }
    }

    @Override
    public String getDescription() {
        return "Сортирует элементы коллекции по возрастанию их ID и выводит их.";
    }
}
