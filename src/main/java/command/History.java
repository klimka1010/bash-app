package command;
import console.ConsoleManager;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class History extends Base {
    private final List<String> commandList;
    private final int maxSize;
    private final ConsoleManager consoleManager;

    public History() {
        this.commandList = new LinkedList<>();
        this.maxSize = 10;
        this.consoleManager = null;
    }

    public History(int maxSize, ConsoleManager consoleManager) {
        this.maxSize = maxSize;
        this.consoleManager = consoleManager;
        this.commandList = new LinkedList<>();
    }


    public void addCommand(String command) {
        if (commandList.size() >= maxSize) {
            commandList.remove(0);
        }
        commandList.add(command);
    }

    @Override
    public void execute(String[] args) throws IOException {
        if (commandList.isEmpty()) {
            consoleManager.writeLine("История команд пуста");
        } else {
            for (String command : commandList) {
                consoleManager.writeLine(command);
            }
        }
    }

    @Override
    public String getDescription() {
        String description = "- history: вывести последние " + maxSize + " команд (без их аргументов)";
        return description;
    }
}
