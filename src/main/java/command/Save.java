package command;

import collection.CollectionManager;
import collection.Parser;
import console.ConsoleManager;

import jakarta.xml.bind.JAXBException;
import java.io.IOException;

public class Save extends Base {
    private final CollectionManager collection;
    private final ConsoleManager consoleManager;

    public Save(CollectionManager collection, ConsoleManager consoleManager) {
        this.collection = collection;
        this.consoleManager = consoleManager;
    }

    @Override
    public void execute(String[] args) throws JAXBException, IOException {
        if (args.length != 1) {
            consoleManager.writeLine("Вы неправильно ввели команду");
        } else {
            String filename = args[0]; // путь к файлу
            Parser.saveToXml(collection, filename); // передаем оба аргумента
            consoleManager.writeLine("Команда выполнена успешно!");
        }
    }



    @Override
    public String getDescription() {
        String description = "- save : сохранить коллекцию в файл";
        return description;
    }
}
