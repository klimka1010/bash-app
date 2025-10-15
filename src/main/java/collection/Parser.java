package collection;

import console.ConsoleManager;

import jakarta.xml.bind.*;

import java.io.*;

public final class Parser {
    public static ConsoleManager consoleManager = new ConsoleManager();

    private static String fileName;

    public Parser(String fileName) {
        Parser.fileName = fileName;
    }

    public Parser() {
    }

    public static void saveToXml(CollectionManager collectionManager, String fileName) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(CollectionManager.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            marshaller.marshal(collectionManager, writer);
            writer.close();
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }


    public static CollectionManager loadFromXml(String fileName) throws FileNotFoundException, JAXBException {
        Parser.fileName = fileName;
        try {
            BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(fileName));
            JAXBContext jaxbContext = JAXBContext.newInstance(CollectionManager.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return (CollectionManager) unmarshaller.unmarshal(inputStream);
        } catch (FileNotFoundException ex) {
            System.err.println("Указанный файл не найден: " + fileName);
            throw ex; // пусть вызывающий код решает, что делать
        } catch (JAXBException e) {
            System.err.println("Ошибка при разборе XML: " + e.getMessage());
            throw e; // не подменяем на пустую коллекцию
        }
    }
}

