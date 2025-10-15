package command;

import collection.CollectionManager;
import console.ConsoleManager;

/**
 * Command remove_greater {element} : remove all elements from the collection that exceed the specified
 */
public class Remove extends Base {
    private final CollectionManager collection;
    private final ConsoleManager consoleManager;

    public Remove(CollectionManager collection, ConsoleManager consoleManager) {
        this.collection = collection;
        this.consoleManager = consoleManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 2) {
            consoleManager.writeLine("Вы неправильно ввели команду");
        } else if (collection.getCollection().isEmpty()) {
            consoleManager.writeLine("Удалять нечего, файл пуст...");
        } else {
            try {
                float value = Float.parseFloat(args[1]);
                collection.removeGreater(value);
                consoleManager.writeLine("Элементы, превышающие " + value + ", удалены.");
            } catch (NumberFormatException e) {
                consoleManager.writeLine("Значение " + args[1] + " не является числом.");
            }
        }
    }

    @Override
    public String getDescription() {
        String description = "- remove_greater <element> : удалить из коллекции все элементы, превышающие заданный";
        consoleManager.writeLine(description);
        return description;
    }
}
