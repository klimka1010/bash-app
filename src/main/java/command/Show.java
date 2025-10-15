package command;

import collection.CollectionManager;
import console.ConsoleManager;

/**
 * Command show. Output to the standard output stream all elements of the collection in string representation
 */
public class Show extends Base {
    private final CollectionManager collection;
    private final ConsoleManager consoleManager;

    public Show(CollectionManager collection, ConsoleManager consoleManager) {
        this.collection = collection;
        this.consoleManager = consoleManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 1) {
            consoleManager.writeLine("Вы неправильно ввели команду");
        } else {
            collection.show();
        }
    }

    @Override
    public String getDescription() {
        String description = "- show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
        return description;
    }
}
