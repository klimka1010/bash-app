package command;

import console.ConsoleManager;

public class Exit extends Base {
    private final ConsoleManager consoleManager;

    public Exit(ConsoleManager consoleManager) {
        this.consoleManager = consoleManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 1) {
            consoleManager.writeLine("Вы неправильно ввели команду");
        } else {
            consoleManager.writeLine("Выход. Подключение закрыто!");
            System.exit(0);
        }
    }

    @Override
    public String getDescription() {
        String description = "- exit : завершить программу (без сохранения в файл)";
        return description;
    }
}
