package wordtrainer;

import java.util.*;

/**
 * Created by Nati on 16.04.2017.
 */
public class Lesson {
    private ArrayList<Card> cards;
    private int numberOfLesson;
    private String topic;
    private Map<String, Set<String>> translations1;
    private Map<String, Set<String>> translations2;

    public Lesson(int numberOfLesson, String topic) {
        cards = new ArrayList<>();
        this.numberOfLesson = numberOfLesson;
        this.topic = topic;
        translations1 = new HashMap<>();
        translations2 = new HashMap<>();
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public int getNumberOfLesson() {
        return numberOfLesson;
    }

    public String getTopic() {
        return topic;
    }


    public Map<String, Set<String>> getTranslations1() {return translations1;}

    public Map<String, Set<String>> getTranslations2() {
        return translations2;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void setNumberOfLesson(int numberOfLesson) {
        this.numberOfLesson = numberOfLesson;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }



    public void setTranslations1(){

        String key = "";
        String translation = "";
        Set<String> value = new HashSet<>();

        for (Card card : cards){
            key = card.getWordLanguage1();
            translation = card.getWordLanguage2();

            if(!translations1.isEmpty()) {

                if (translations1.containsKey(key)) {
                    value = translations1.get(key);
                } else {
                    value = new HashSet<>();
                }

            }else{
                value = new HashSet<>();
            }

            value.add(translation);
            translations1.put(key, value);
        }
    }

    public void setTranslations2(){

        String key = "";
        String translation = "";
        Set<String> value = new HashSet<>();

        for (Card card : cards){
            key = card.getWordLanguage2();
            translation = card.getWordLanguage1();

            if(!translations2.isEmpty()) {

                if (translations2.containsKey(key)) {
                    value = translations2.get(key);
                } else {
                    value = new HashSet<>();
                }

            }else{
                value = new HashSet<>();
            }

            value.add(translation);
            translations2.put(key, value);
        }
    }





}

