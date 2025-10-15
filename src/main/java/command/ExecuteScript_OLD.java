//package command;
//
//import console.ConsoleManager;
//
//import javax.xml.bind.JAXBException;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Scanner;
//
//public class ExecuteScript_OLD extends Base {
//    private final ConsoleManager consoleManager;
//    private final HashMap<String, Base> commandMap;
//    private final ArrayList<String> filePaths;
//    static ArrayList<String> cityList = new ArrayList<>();
//    static boolean flag = false;
//
//    public ExecuteScript_OLD(ConsoleManager consoleManager, HashMap<String, Base> commandMap) {
//        this.consoleManager = consoleManager;
//        this.commandMap = commandMap;
//        this.filePaths = new ArrayList<>();
//    }
//
//    @Override
//    public void execute(String[] args) throws JAXBException, IOException {
//        if (args.length != 2) {
//            consoleManager.writeLine("Вы неправильно ввели команду");
//        } else {
//            flag = true;
//            filePaths.add((String) getArgument());
//            ArrayList<String> commandList = new ArrayList<>();
//            try (Scanner reader = new Scanner(new FileInputStream((String) getArgument()))) {
//                while (reader.hasNextLine()) {
//                    String line = reader.nextLine().trim();
//                    commandList.add(line);
//                }
//            } catch (FileNotFoundException e) {
//                consoleManager.writeLine("Файл не найден");
//            }
//
//            for (int i = 0; i < commandList.size(); i++) {
//                while (commandList.get(i).contains("  "))
//                    commandList.set(i, commandList.get(i).replaceAll("  ", " "));
//
//                String[] commandAndArgument = commandList.get(i).split(" ");
//                String argument;
//
//                if (commandAndArgument.length == 1)
//                    argument = null;
//                else if (commandAndArgument.length == 2)
//                    argument = commandAndArgument[1];
//                else {
//                    consoleManager.writeLine("Введите команду и аргумент, если нужно");
//                    return;
//                }
//                boolean a = !commandAndArgument[0].equals("add") && !commandAndArgument[0].equals("update"); // todo
//                try {
//                    if (commandMap.containsKey(commandAndArgument[0]) && a) {
//                        if (commandAndArgument[0].equals("execute_script")) {
//                            if (filePaths.contains(commandAndArgument[1])) {
//                                consoleManager.writeLine("Файл содержит рекурсию");
//                                continue;
//                            }
//                        }
//                        commandMap.get(commandAndArgument[0]).setArgument(argument);
//                        commandMap.get(commandAndArgument[0]).execute(commandAndArgument);
//
//                    } else if (!a) {
//                        for (int j = 1; j < 10; j++) {
//                            if (i + j < commandList.size()) {
//                                cityList.add(commandList.get(i + j));
//                            }
//                        }
//                        commandMap.get(commandAndArgument[0]).execute(commandAndArgument);
//                        i += 10;
//                    }
//
//                } catch (NullPointerException | IndexOutOfBoundsException e) {
//                    consoleManager.writeLine("Неверные данные в скрипте, персонаж не создан");
//                }
//            }
//            filePaths.remove(getArgument());
//        }
//    }
//
//    public static boolean getFlag() {
//        return flag;
//    }
//
//    public static ArrayList<String> getCityList() {
//        return cityList;
//    }
//
//    @Override
//    public String getDescription() {
//        return "- execute_script <file_name> : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме";
//    }
//}
