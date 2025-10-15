package command;

import console.ConsoleManager;
import java.util.Map;

public class Help extends Base {
    private final ConsoleManager consoleManager;
    private final CommandManager commandManager;

    public Help(ConsoleManager consoleManager, CommandManager commandManager) {
        this.consoleManager = consoleManager;
        this.commandManager = commandManager;
    }

    @Override
    public void execute(String[] args) {
        Map<String, Base> commandMap = commandManager.getCommandMap();
        consoleManager.writeLine("Доступные команды:");
        for (Map.Entry<String, Base> entry : commandMap.entrySet()) {
            consoleManager.writeLine(entry.getValue().getDescription());
        }
    }

    @Override
    public String getDescription() {
        return "- help: получить краткую справку по доступным командам";
    }
}
