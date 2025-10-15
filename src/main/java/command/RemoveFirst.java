package command;

import collection.CollectionManager;
import console.ConsoleManager;

/**
 * Command remove_first : удалить первый элемент из коллекции
 */
public class RemoveFirst extends Base {
    private final CollectionManager collection;
    private final ConsoleManager consoleManager;

    public RemoveFirst(CollectionManager collection, ConsoleManager consoleManager) {
        this.collection = collection;
        this.consoleManager = consoleManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            consoleManager.writeLine("Вы неправильно ввели команду");
        } else if (collection.getCollection().isEmpty()) {
            consoleManager.writeLine("Удалять нечего, коллекция пуста...");
        } else {
            try {
                collection.removeFirst();
            } catch (Exception e) {
                consoleManager.writeLine("Произошла ошибка при удалении элемента: " + e.getMessage());
            }
        }
    }

    @Override
    public String getDescription() {
        return "- remove_first : удалить первый элемент из коллекции";
    }
}
