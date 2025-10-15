package command;

import collection.CollectionManager;
import console.ConsoleManager;

public class Clear extends Base {
    private final CollectionManager collection;
    private final ConsoleManager consoleManager;

    public Clear(CollectionManager collection, ConsoleManager consoleManager) {
        this.collection = collection;
        this.consoleManager = consoleManager;
    }


    public void execute(String[] args) {
        if (args.length > 1) {
            consoleManager.writeLine("Вы неправильно ввели команду");
        } else {
            collection.clear();
            consoleManager.writeLine("Команда выполнена");
        }
    }

    public String getDescription() {
        return "- clear: очистить коллекцию";
    }
}
