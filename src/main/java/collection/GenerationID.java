package collection;
import java.io.*;
import java.util.HashSet;



public class GenerationID {
    private static final String fileName = "last_id.txt";
    private static HashSet<Long> generatedIDs = new HashSet<>();

    static {
        readLastIDFromFile();
    }

    public static Long generateID() {
        Long ID = System.currentTimeMillis();
        while (generatedIDs.contains(ID)) {
            ID = System.currentTimeMillis();
        }
        generatedIDs.add(ID);
        saveLastIDToFile(ID);
        return ID;
    }

    private static void readLastIDFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String lastIDString = reader.readLine();
            if (lastIDString != null) {
                Long lastID = Long.parseLong(lastIDString);
                generatedIDs.add(lastID);
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Не удалось прочитать последнее значение ID, значение сгенерировано случайным образом.");

        }
    }

    private static void saveLastIDToFile(Long ID) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println(ID);
        } catch (IOException e) {
            System.err.println("Не удалось сохранить последнее значение ID");
        }
    }
}

