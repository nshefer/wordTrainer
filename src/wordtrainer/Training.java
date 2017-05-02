package wordtrainer;

import java.io.*;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


/**
 * Created by Baerbel Hanle and Natalia Shefer on 16.04.2017.
 */
public class Training {

    /**
     * Fragt die Vokabeln in einer zufaelligen Reihenfolge ab
     *
     * @param numberOfLesson
     */
    public void practiceRandomly(int numberOfLesson) {

        Lesson lesson = readLesson(numberOfLesson);
        lesson.shuffle();

        askAndCorrect(lesson);
    }

    /**
     * Fragt die Vokabeln in Eingabereihenfolge ab
     *
     * @param numberOfLesson
     */
    public void practiceOrdered(int numberOfLesson) {

        Lesson lesson = readLesson(numberOfLesson);
        lesson.setTranslations1();
        lesson.setTranslations2();

        askAndCorrect(lesson);
    }

    /**
     * Liest Vokabeln ein, die zuvor in einer txt-Datein gespeichert wurden und gibt eine Instanz von Lesson zurueck
     *
     * @param numberOfLesson
     * @return
     */
    private Lesson readLesson(int numberOfLesson) {

        Lesson lesson = new Lesson(numberOfLesson, "");
        String topic;
        String word1;
        String word2;
        Card card;

        String fileName;
        File file;

        fileName = "Lesson" + numberOfLesson + ".txt";
        file = new File(fileName);

        //topic and cards werden eingelesen
        try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr)) {

            br.readLine(); //da in der ersten Zeile die Lektionsnr steht
            topic = br.readLine();
            lesson.setTopic(topic);

            word1 = br.readLine();

            while (word1 != null) {
                word2 = br.readLine();
                card = new Card(word1, word2);
                word1 = br.readLine();
                lesson.addCard(card);
            }

        } catch (FileNotFoundException fnfEx) {
            fnfEx.printStackTrace();
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }

        lesson.setTranslations1();
        lesson.setTranslations2();

        return lesson;
    }

    /**
     * Fragt alle Vokabeln einer Lektion ab und gibt dem User eine Rueckmeldung, ob seine Antwort korrekt ist.
     *
     * @param lesson
     */
    private void askAndCorrect(Lesson lesson) {
        Scanner scan = new Scanner(System.in);
        String answer;
        int language;

        for (Card card : lesson.getCards()) {
            language = (int) (Math.random() * 2.0 + 1.0);
            ask(card, language, lesson);
            answer = scan.nextLine();
            check(card, answer, language, lesson);
        }

    }

    /**
     * Fragt nach dem Wort in der Sprache auf der Karteikarte in der Lektion
     *
     * @param card
     * @param language
     * @param lesson
     */
    private void ask(Card card, int language, Lesson lesson) {

        String word;
        int freq;
        Map<String, Set<String>> translations;

        if (language == 1) {
            translations = lesson.getTranslations1();
            word = card.getWordLanguage1();
            freq = translations.get(word).size();
            System.out.println("Sprache 1: " + word + " (" + freq + ")");
        } else {
            translations = lesson.getTranslations2();
            word = card.getWordLanguage2();
            freq = translations.get(word).size();
            System.out.println("Sprache 2: " + word + " (" + freq + ")");
        }
    }


    /**
     * Prueft, ob die eingegebenen User-Antworten eine Teilmenge der richtigen Uebersetzung bilden und gibt dem User Rueckmeldung.
     * Falls die Antwort falsch war, wird die Karteikarte als Vokabelpaar in Lektion 0 eingefuegt
     *
     * @param card
     * @param answer
     * @param language
     * @param lesson
     */
    private void check(Card card, String answer, int language, Lesson lesson) {
        String word;
        Set<String> answerSet = convertToSet(answer);
        Set<String> correctAnswers;

        if (language == 1) {
            word = card.getWordLanguage1();
            correctAnswers = lesson.getTranslations1().get(word);
        } else {
            word = card.getWordLanguage2();
            correctAnswers = lesson.getTranslations2().get(word);
        }

        if (correctAnswers.containsAll(answerSet)) {
            System.out.println("Richtig!");
        } else {

            System.out.println("Leider nicht ganz korrekt. Die richtige(n) Uebersetzung(en) waere(n) gewesen:");
            System.out.println();

            for (String translation : correctAnswers) {
                System.out.println(translation);
            }

            addToLesson0(card);

        }
    }

    /**
     * Konvertiert die User-Antwort (String) zu einem Hash-Set, damit sie mit den richtigen Uebersetzungen besser verglichen werden koennen.
     *
     * @param answer
     * @return
     */
    private Set<String> convertToSet(String answer) {

        Set<String> result = new HashSet<>();
        String[] answerArray = answer.split(",");

        for (int i = 0; i < answerArray.length; i++) {
            String s = answerArray[i].trim();
            result.add(s);
        }

        return result;
    }

    /**
     * fuegt eine Karteikarte als Vokabelpaar in Lektion 0 ein
     *
     * @param card
     */
    private void addToLesson0(Card card) {

        String filename = "Lesson0.txt";
        String line1 = card.getWordLanguage1();
        String line2 = card.getWordLanguage2();

        if (Input.checkIfFileExists("Lesson0.txt")) {
            Input.writeWordpairLines(filename, line1, line2);
        } else {
            Input.writeWordpairLines("Lesson0.txt", "0", "Die oft vergessene Vokabeln");
            Input.writeWordpairLines(filename, line1, line2);
        }

    }

}
