import collection.CollectionManager;
import collection.Parser;
import command.CommandManager;
import console.ConsoleManager;

import jakarta.xml.bind.JAXBException;
import java.io.*;
import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) {
        try {
            run(args);
        } catch (IOException | JAXBException | IllegalArgumentException e) {
            System.err.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        } catch (NoSuchElementException e) {
            System.err.println("Ошибка: " + e.getMessage() + ". Пожалуйста, проверьте ввод.");
        } catch (Exception e) {
            System.err.println("Неизвестная ошибка: " + e.getMessage());
            e.printStackTrace();
        }

    }
ц
    private static void run(String[] args) throws JAXBException, IOException {
        CollectionManager collectionManager = new CollectionManager();
        ConsoleManager consoleManager = new ConsoleManager();

        while (true) {
            try {
                processArgs(args, collectionManager, consoleManager);
            } catch (IllegalArgumentException e) {
                consoleManager.fileRead();
            }
        }
    }

    private static void processArgs(String[] args, CollectionManager collectionManager, ConsoleManager consoleManager) throws JAXBException, IOException {
        if (args.length > 0) {
            String link = args[0];
            File file = new File(link);
            if (file.exists() && !file.isDirectory()) {
                loadCollectionFromFile(link, collectionManager, consoleManager);
            } else {
                consoleManager.writeLine("Файл не существует. Проверьте ввод!");
                consoleManager.fileRead();
            }
        } else {
            consoleManager.fileRead();
        }
    }

    private static void loadCollectionFromFile(String link, CollectionManager collectionManager, ConsoleManager consoleManager) throws JAXBException, IOException {
        collectionManager.setCollection(Parser.loadFromXml(link).getCollection());

        CommandManager commandManager = new CommandManager(collectionManager, consoleManager);
        CommandManager.setFileLink(link);
//        collectionManager.setCommandManager(commandManager);

        while (CommandManager.getWork()) {
            commandManager.existCommand();
        }
    }


}