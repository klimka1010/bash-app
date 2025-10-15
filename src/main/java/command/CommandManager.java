package command;

import collection.CollectionManager;
import console.ConsoleManager;
import jakarta.xml.bind.JAXBException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CommandManager {
    private static boolean isWorking = true;
    private static String fileLink;
    private final ConsoleManager consoleManager;
    private static final Map<String, Base> commandMap = new HashMap<>();

    public CommandManager(CollectionManager collection, ConsoleManager consoleManager) {
        this.consoleManager = consoleManager;

        commandMap.put("help", new Help(consoleManager, this));
        commandMap.put("info", new Info(collection, consoleManager));
        commandMap.put("show", new Show(collection, consoleManager));
        commandMap.put("add", new Add(collection, consoleManager));
        commandMap.put("update", new Update(collection, consoleManager));
        commandMap.put("clear", new Clear(collection, consoleManager));
        commandMap.put("save", new Save(collection, consoleManager));
        commandMap.put("exit", new Exit(consoleManager));
        commandMap.put("remove_greater", new RemoveGreater(collection, consoleManager));
        commandMap.put("history", new History(10, consoleManager));
        commandMap.put("print_asc", new PrintAscending(collection, consoleManager));
        commandMap.put("remove_first", new RemoveFirst(collection, consoleManager));
        commandMap.put("group_counting_by_climate", new GroupCountingByClimate(collection, consoleManager));
        commandMap.put("execute_script", new ExecuteScript(consoleManager, (HashMap<String, Base>) commandMap));


    }



    public Map<String, Base> getCommandMap() {
        return commandMap;
    }

    public void existCommand() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("> ");
            String command = sc.nextLine().trim().toLowerCase();

            String[] commandArg = command.split(" ");
            String argument = null;

            if (commandArg.length > 1) {
                argument = commandArg[1];
            }

            Base cmd = commandMap.get(commandArg[0]);
            if (cmd != null) {
                cmd.setArgument(argument);
                try {
                    cmd.execute(commandArg);
                } catch (JAXBException | IOException e) {
                    consoleManager.writeLine("Ошибка выполнения команды: " + e.getMessage());
                }
            } else {
                consoleManager.writeLine("Команда " + commandArg[0] + " не существует");
            }
        } catch (NoSuchElementException e) {
            consoleManager.writeLine("Ошибка: " + e.getMessage());
            isWorking = false;
            System.exit(0);
        }
    }

    public static Base getCommand(String commandName){
        return commandMap.get(commandName);
    }

    public static boolean getWork() {
        return isWorking;
    }

    public static void setFileLink(String fileLink) {
        CommandManager.fileLink = fileLink;
    }
}

