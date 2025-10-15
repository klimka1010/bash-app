package command;

import collection.CollectionManager;
import console.ConsoleManager;

public class Info extends Base {
    private final CollectionManager collectionManager;
    private final ConsoleManager consoleManager;

    public Info(CollectionManager collectionManager, ConsoleManager consoleManager) {
        this.collectionManager = collectionManager;
        this.consoleManager = consoleManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 1) {
            consoleManager.writeLine("Вы неправильно ввели команду");
        } else {
            String info = collectionManager.info();
            consoleManager.writeLine(info);
        }
    }

    @Override
    public String getDescription() {
        return "- info: получить информацию о коллекции";
    }
}
