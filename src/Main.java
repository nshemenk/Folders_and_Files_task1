import java.io.*;

public class Main {
    public static void main(String[] args) {
        StringBuilder text = new StringBuilder();
        String[] paths = {
                "C:\\Users\\nmele\\Games\\src",
                "C:\\Users\\nmele\\Games\\res",
                "C:\\Users\\nmele\\Games\\savegames",
                "C:\\Users\\nmele\\Games\\temp",
                "C:\\Users\\nmele\\Games\\src\\main",
                "C:\\Users\\nmele\\Games\\src\\test",
                "C:\\Users\\nmele\\Games\\res\\drawables",
                "C:\\Users\\nmele\\Games\\res\\vectors",
                "C:\\Users\\nmele\\Games\\res\\icons"
        };
        for (String path : paths) {
            text.append(makeDir(path) + '\n');
        }

        String[][] files = {
                {"C:\\Users\\nmele\\Games\\src\\main",
                        "C:\\Users\\nmele\\Games\\src\\main",
                        "C:\\Users\\nmele\\Games\\temp"},
                {"Main.java",
                        "Utils.java",
                        "temp.txt"}
        };

        String file;
        String path;


        for (int j = 0; j < files[0].length; j++) {
            path = files[0][j];
            file = files[1][j];
            text.append(makeFile(path, file) + '\n');
        }


        File fileToWrite = new File("C:\\Users\\nmele\\Games\\temp", "temp.txt");
        try (FileWriter writer = new FileWriter(fileToWrite)) {
            writer.write(String.valueOf(text));

            writer.append('\n');
            writer.append('!');

            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public static String makeDir(String path) {
        File directory = new File(path);
        if (directory.mkdir())
            return "Папка " + directory.getName() + " создана";
        else
            return "Папка " + directory.getName() + " НЕ создана";
    }

    public static String makeFile(String path, String file) {
        File newFile = new File(path, file);
        try {
            if (newFile.createNewFile())
                return "Файл " + newFile.getName() + " создан";
            else
                return "Файл " + newFile.getName() + " НЕ создан";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}