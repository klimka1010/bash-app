package command;

import console.ConsoleManager;
import jakarta.xml.bind.JAXBException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class ExecuteScript extends Base {
    private final ConsoleManager consoleManager;
    private final Map<String, Base> commandMap;
    private static final List<String> filePaths = new ArrayList<>();
    private static boolean isExecutingScript = false;
    static ArrayList<String> cityList = new ArrayList<>();
    static boolean flag = false;

    public ExecuteScript(ConsoleManager consoleManager, Map<String, Base> commandMap) {
        this.consoleManager = consoleManager;
        this.commandMap = commandMap;
    }

    @Override
    public void execute(String[] args) throws JAXBException, IOException {
        if (args.length != 2) {
            consoleManager.writeLine("Вы неправильно ввели команду");
            return;
        }

        String scriptFileName = args[1];

        if (filePaths.contains(scriptFileName)) {
            consoleManager.writeLine("Файл содержит рекурсию");
            return;
        }

        filePaths.add(scriptFileName);

        ArrayList<String> commandList = new ArrayList<>();
        try (Scanner reader = new Scanner(new FileInputStream(scriptFileName))) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine().trim();
                if (!line.isEmpty()) {
                    commandList.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            consoleManager.writeLine("Файл не найден");
            filePaths.remove(scriptFileName);
            return;
        }

        for (int i = 0; i < commandList.size(); i++) {
            String commandLine = commandList.get(i);
            String[] commandParts = commandLine.split(" ", 2); // Разделение на команду и аргументы

            String commandName = commandParts[0].trim();
            String argument = commandParts.length > 1 ? commandParts[1].trim() : null;

            Base command = commandMap.get(commandName);
            if (command != null) {
                if (argument != null) {
                    command.setArgument(argument);
                }
                try {
                    // Выполнение команды
                    command.execute(argument != null ? new String[]{commandName, argument} : new String[]{commandName});
                    // Вывод результата выполнения команды
                    consoleManager.writeLine("Команда '" + commandName + "' выполнена.");
                } catch (JAXBException | IOException e) {
                    consoleManager.writeLine("Ошибка выполнения команды '" + commandName + "': " + e.getMessage());
                }
            } else {
                consoleManager.writeLine("Команда '" + commandName + "' не найдена.");
            }
        }

        filePaths.remove(scriptFileName);
    }


    public static boolean getFlag() {
        return flag;
    }

    public static ArrayList<String> getCityList() {
        return cityList;
    }

    @Override
    public String getDescription() {
        return "- execute_script <file_name> : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме";
    }

    public static boolean isExecutingScript() {
        return isExecutingScript;
    }
}
