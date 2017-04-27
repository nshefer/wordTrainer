package wordtrainer;

import java.io.*;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


/**
 * Created by Bärbel on 19.04.2017.
 */
public class Training {

    public void practice(int numberOfLesson){

        Lesson lesson = readLesson(numberOfLesson);
        lesson.shuffle();
        lesson.setTranslations1();
        lesson.setTranslations2();

        askAndCorrect(lesson);
    }

    public Lesson readLesson(int numberOfLesson){

        Lesson lesson = new Lesson(numberOfLesson, "");
        String topic;
        String word1;
        String word2;
        Card card;

        String fileName;
        File file;

        fileName = "Lesson" + numberOfLesson + ".txt";
        file = new File(fileName);

        //reading in topic and cards
        try(FileReader fr = new FileReader(file); BufferedReader br= new BufferedReader(fr)){

            br.readLine(); //da in der ersten Zeile die Lektionsnr steht
            topic = br.readLine();
            lesson.setTopic("topic");

            word1 = br.readLine();

            while(word1 != null){
                word2 = br.readLine();
                card = new Card(word1, word2);
                word1 = br.readLine();
                lesson.addCard(card);
            }

        }catch(FileNotFoundException fnfEx) {
            fnfEx.printStackTrace();
        }catch(IOException ioEx){
            ioEx.printStackTrace();
        }

        return lesson;
    }

    public void askAndCorrect(Lesson lesson){
        Scanner scan = new Scanner(System.in);
        String answer;
        int language;

        for(Card card : lesson.getCards()){
            language = (int)(Math.random()*2.0 + 1.0);
            askRandomly(card, language, lesson);
            answer = scan.nextLine();
            check(card, answer,language, lesson);
        }

    }

    public void askRandomly(Card card, int language, Lesson lesson){

        String word;
        int freq;
        Map<String, Set<String>> translations;

        if (language == 1){
            translations  = lesson.getTranslations1();
            word = card.getWordLanguage1();
            freq = translations.get(word).size();
            System.out.println(word + " (" + freq + ")");
        }else{
            translations  = lesson.getTranslations2();
            word = card.getWordLanguage2();
            freq = translations.get(word).size();
            System.out.println(word + " (" + freq + ")");
        }
    }


    public void check(Card card, String answer, int language, Lesson lesson){
        String word;
        Set<String> answerSet = convertToSet(answer);
        Set<String> correctAnswers;
        boolean correct = false;

        if(language == 1) {
            word = card.getWordLanguage1();
            correctAnswers = lesson.getTranslations1().get(word);
        }else {
            word = card.getWordLanguage2();
            correctAnswers = lesson.getTranslations2().get(word);
        }

        if (correctAnswers.containsAll(answerSet)) {
                System.out.println("Richtig!");
        } else {

            System.out.println("Leider nicht ganz korrekt. Die richtige(n) Uebersetzung(en) waere(n) gewesen:");
            System.out.println();

            for(String translation: correctAnswers){
                System.out.println(translation);
            }

            addToLesson0(card);

        }
    }


    public Set<String> convertToSet(String answer){

        Set<String> result = new HashSet<String>();
        answer.replace(" ", "");
        String[] answerArray = answer.split(",");

        for(int i=0; i < answerArray.length; i++){
            result.add(answerArray[i]);
        }

        return result;
    }

    private void addToLesson0(Card card){

        String filename = "Lesson0";
        String line1 = card.getWordLanguage1();
        String line2 = card.getWordLanguage2();

        Input.writeWordpairLines(filename, line1, line2);

    }

}
