package command;

import collection.CollectionManager;
import console.ConsoleManager;

public class RemoveGreater extends Base {
    private final CollectionManager collection;
    private final ConsoleManager consoleManager;

    public RemoveGreater(CollectionManager collection, ConsoleManager consoleManager) {
        this.collection = collection;
        this.consoleManager = consoleManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 2) {
            consoleManager.writeLine("Вы неправильно ввели команду");
        } else if (collection.getCollection().isEmpty()) {
            consoleManager.writeLine("Удалять нечего, коллекция пуста...");
        } else {
            try {
                Float threshold = Float.valueOf(args[1]);
                collection.removeGreater(threshold);
                consoleManager.writeLine("Все элементы, превышающие " + threshold + ", были удалены из коллекции.");
            } catch (NumberFormatException e) {
                consoleManager.writeLine("Ошибка: Некорректный формат числа. Попробуйте снова.");
            }
        }
    }

    @Override
    public String getDescription() {
        String description = "- remove_greater <element> : удалить из коллекции все элементы, превышающие заданный";
        return description;
    }
}
