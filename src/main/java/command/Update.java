package command;

import client.ClientManager;
import collection.CollectionManager;
import collection.CollectionUtil;
import console.ConsoleManager;

/**
 * Command update id {element} : update the value of the collection item whose id is equal to the given one
 */
public class Update extends Base {
    private final CollectionManager collection;
    private final ConsoleManager consoleManager;
    private final CollectionUtil collectionUtil = new CollectionUtil();
    private final ClientManager clientManager = new ClientManager();

    public Update(CollectionManager collection, ConsoleManager consoleManager) {
        this.collection = collection;
        this.consoleManager = consoleManager;
    }

    @Override
    public void execute(String[] args) {
        if (ExecuteScript.getFlag()) {
            collection.add(ClientManager.createCityFromScript(ExecuteScript.getCityList()));
        } else if (args.length != 2) {
            consoleManager.writeLine("Вы неправильно ввели команду");
        } else if (collection.getCollection().size() == 0) {
            consoleManager.writeLine("Сказать нечего, файл пуст...");
        } else {
            try {
                Long ID = Long.parseLong(args[1]);
                if (collectionUtil.checkExist(ID)) {
                    collection.updateId(clientManager.getCity(), ID);
                    consoleManager.writeLine("Команда выполнена");
                } else {
                    consoleManager.writeLine("Элемента с таким ID не существует");
                }
            } catch (NumberFormatException e) {
                consoleManager.writeLine("Введён неккоретный ID, попробуйте снова");
            }
        }
    }

    @Override
    public String getDescription() {
        String description = "- update id {element} : обновить значение элемента коллекции, id которого равен заданному";
        return description;
    }
}
