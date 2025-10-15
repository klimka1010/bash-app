package command;

import collection.CollectionManager;
import console.ConsoleManager;

/**
 * Command remove_by_id id : remove an item from the collection by its id
 */
public class RemoveBYID extends Base {
    private final CollectionManager collection;
    private final ConsoleManager consoleManager;

    public RemoveBYID(CollectionManager collection, ConsoleManager consoleManager) {
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
                Long id = Long.parseLong(args[1]);
                boolean result = Boolean.parseBoolean(collection.removeById(id));
                if (result) {
                    consoleManager.writeLine("Элемент с ID " + id + " был удалён.");
                } else {
                    consoleManager.writeLine("Элемент с ID " + id + " не найден.");
                }
            } catch (NumberFormatException e) {
                consoleManager.writeLine("Введён некорректный ID, попробуйте снова.");
            }
        }
    }

    @Override
    public String getDescription() {
        String description = "- remove_by_id <id> : удалить элемент из коллекции по его id";
        return description;
    }
}
