package command;

import collection.CollectionManager;
import console.ConsoleManager;

public class GroupCountingByClimate extends Base{
    private final CollectionManager collection;

    public GroupCountingByClimate(CollectionManager collection, ConsoleManager consoleManager){
        this.collection = collection;
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 1){
            System.out.println("Неправильный ввод команды");
        } else {
            collection.groupCountingByClimate();
            System.out.println("Команда выполнена");
        }
    }

    @Override
    public String getDescription() {
        return "- group_counting_by_climate: сгруппировать элементы коллекции по значению поля climate, вывести количество элементов в каждой группе ";
    }
}
