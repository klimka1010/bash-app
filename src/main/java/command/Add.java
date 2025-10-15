package command;

import client.ClientManager;
import collection.CollectionManager;
import console.ConsoleManager;
import data.City;

import java.io.IOException;

public class Add extends Base {
    private final CollectionManager collection;
    private final ConsoleManager consoleManager;

    public Add(CollectionManager collection, ConsoleManager consoleManager) {
        this.collection = collection;
        this.consoleManager = consoleManager;
    }

    @Override
    public void execute(String[] args) throws IOException {
        if (args.length > 1) {
            consoleManager.writeLine("Команда не принимает аргументы!");
        } else {
            ClientManager clientManager = new ClientManager();
            City newCity = clientManager.getCity();

            collection.add(newCity);
            consoleManager.writeLine("Элемент успешно добавлен в коллекцию.");
        }
    }

    @Override
    public String getDescription() {
        String description = "- add: добавить новый элемент в коллекцию";
        return description;
    }
}
