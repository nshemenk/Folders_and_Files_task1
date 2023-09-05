import java.io.*;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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

        text.append(makeFile("C:\\Users\\nmele\\Games\\src\\main", "Main.java") + '\n');
        text.append(makeFile("C:\\Users\\nmele\\Games\\src\\main", "Utils.java") + '\n');
        text.append(makeFile("C:\\Users\\nmele\\Games\\temp", "temp.txt") + '\n');


        File fileToWrite = new File("C:\\Users\\nmele\\Games\\temp", "temp.txt");
        try (FileWriter writer = new FileWriter(fileToWrite)) {
            writer.write(String.valueOf(text));

            writer.append('\n');
            writer.append('!');

            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        ArrayList<GameProgress> gamesProgress = new ArrayList<>();
        gamesProgress.add(new GameProgress(94, 10, 2, 254.32));
        gamesProgress.add(new GameProgress(58, 6, 3, 300.58));
        gamesProgress.add(new GameProgress(33, 2, 4, 320.25));

        ArrayList<String> filesPaths = new ArrayList<>();
        filesPaths.add("C:\\Users\\nmele\\Games\\savegames\\save1.dat");
        filesPaths.add("C:\\Users\\nmele\\Games\\savegames\\save2.dat");
        filesPaths.add("C:\\Users\\nmele\\Games\\savegames\\save3.dat");

        for (int i = 0; i < filesPaths.size(); i++) {
            saveGame(filesPaths.get(i), gamesProgress.get(i));
        }


        String zipPath = "C:\\Users\\nmele\\Games\\savegames\\zip.zip";
        zipFiles(zipPath, filesPaths);

        for (String filePath : filesPaths) {
            File fileForDelete = new File(filePath);
            if (fileForDelete.delete()) {
                System.out.println("Файл " + fileForDelete.getName() + " удален");
            } else System.out.println("Файл " + fileForDelete.getName() + " не найден");
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

    public static void saveGame(String path, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String zipPath, ArrayList<String> filesPaths) {

        try (ZipOutputStream zout = new ZipOutputStream(new
                FileOutputStream(zipPath))) {
            for (String filePath : filesPaths) {
                try (FileInputStream fis = new FileInputStream(filePath)) {
                    File fileForZip = new File(filePath);
                    ZipEntry entry = new ZipEntry(fileForZip.getName());
                    zout.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zout.write(buffer);
                    zout.closeEntry();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}