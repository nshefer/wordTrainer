package wordtrainer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by Nati on 16.04.2017.
 */
public class Input {

    //TODO: add check, when the user wants to get a lesson with the number that doesn't exist
    //TODO: define behaviour when the user puts in the lesson number that already exists (edit or ask to put a new lesson number?) Where should we save lessonnumbers?
    public static void writeLesson() {
        int lessonNumber = readLessonNumber();
        String filename = "Lesson" + lessonNumber + ".txt";
        if (checkIfFileExists(filename)){
            readWordpairLines(filename);
        } else {
            String topic = readTopic();
            writeWordpairLines(filename, Integer.toString(lessonNumber), topic);
            readWordpairLines(filename);
        }

    }

    public static int readLessonNumber() {
        Scanner scan = new Scanner(System.in);
        int lessonNumber = 0;
        boolean flag = true;
        while (flag) {
            System.out.println("Geben Sie bitte die Lektionnummer ein. Bei der Eingabe der bereits existierenden Nummer wird die Lektion erweitert.");
            if (scan.hasNextInt()) {
                lessonNumber = scan.nextInt();
                if (lessonNumber > 0) {
                    flag = false;
                } else {
                    System.out.println("Geben Sie bitte eine natuerliche Zahl ein!");
                    continue;
                }
            } else {
                System.out.println("Geben Sie bitte eine natuerliche Zahl ein!");
                scan.next();
            }
        }
        return lessonNumber;
    }

    public static String readTopic() {
        Scanner scan = new Scanner(System.in);
        boolean flag = true;
        String topic = null;
        while (flag) {
            System.out.println("Geben Sie bitte das Thema der Lektion ein!");
            topic = readLine(scan);
            if (topic.isEmpty()) {
                System.out.println("Das Thema ist leer.");
            } else {
                flag = false;
            }
        }
        return topic;
    }

    public static void readWordpairLines(String filename) {

        boolean flag = true;
        String line1;
        String line2;
        Scanner scan = new Scanner(System.in);

        while (flag) {
            System.out.println("Geben Sie bitte ein neues Wortpaar: erste Bedeutung auf der ersten Zeile und die Uebersetzung auf der zweiten Zeile.");
            line1 = readLine(scan);
            if (line1.isEmpty()) {
                System.out.println("Sie haben die Eingabe beendet.");
                break;
            }
            line2 = readLine(scan);
            if (line2.isEmpty()) {
                System.out.println("Deine Uebersetzung ist leer. Bitte gib das Paar erneut ein.");
                continue;
            }

            writeWordpairLines(filename, line1, line2);
        }
    }

    public static String readLine(Scanner scan) {
        String line = scan.nextLine();
        return line;
    }

    //TODO: add String filename as a parameter to call it from the other function
    public static void writeWordpairLines(String filename, String line1, String line2) {
        File file = new File(filename);
        try (FileWriter fileWriter = new FileWriter(file, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
             PrintWriter printWriter = new PrintWriter(bufferedWriter)) {
            writeLine(printWriter, line1);
            writeLine(printWriter, line2);
        } catch (IOException ioEx) {
            ioEx.printStackTrace(System.err);
        }
    }

    public static void writeLine(PrintWriter printWriter, String line) {
        printWriter.write(line + "\n");
    }

    /**
     * Checks if the given file (only name and extension, e.g. "file.txt" needed) exists in the project directory.
     * @param file filename with extension, path not needed
     * @return
     */
    public static boolean checkIfFileExists(String file) {
        boolean fileExists=false;
        String path = null;
        try {
            path = new File("").getCanonicalPath();
        } catch (IOException ioEx) {
            //do smth
        }
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        // loop through each of the files looking for filenames that match
        for (int i = 0; i < listOfFiles.length; i++) {
            String filename = listOfFiles[i].getName();
            if (listOfFiles[i].getName().equals(file)) {
                fileExists = true;
                break;
            } else {
                fileExists = false;
            }

        }
        return fileExists;
    }

    public static File findFile(String file) {

        String path = null;
        try {
            path = new File("").getCanonicalPath();
        } catch (IOException ioEx) {
            //do smth
        }
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        // loop through each of the files looking for filenames that match
        for (int i = 0; i < listOfFiles.length; i++) {
            String filename = listOfFiles[i].getName();
            if (listOfFiles[i].getName().equals(file)) {
                return listOfFiles[i];
            }
        }
        return null;
    }

    /**
     * Deletes a file in project folder with given name.
     * It's important to put a file extension! E.g. "fileToBeDeleted.txt".
     * @param filename the name of the file to be deleted with extension, path not needed
     */
    public static void deleteLesson(String filename){
        Path path=null;
        File lesson0 = findFile(filename);
        try {
            path= Paths.get(lesson0.getCanonicalPath());
            Files.delete(path);
        }catch (IOException ioEx){
            System.out.println("Wir koennen das File leider nicht finden. ");
        }

    }




}
