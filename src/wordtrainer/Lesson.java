package wordtrainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

/**
 * Created by Nati on 16.04.2017.
 */
public class Lesson {
    private ArrayList<Card> cards;
    private int numberOfLesson;
    private String topic;
    //Sorted??
    private Map<String, ArrayList<String>> translations1;
    //Sorted??
    private Map<String, ArrayList<String>> translations2;

    public Lesson(int numberOfLesson, String topic) {
        cards = new ArrayList<>();
        this.numberOfLesson = numberOfLesson;
        this.topic = topic;
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

    public Map<String, ArrayList<String>> getTranslations1() {
        return translations1;
    }

    public Map<String, ArrayList<String>> getGetTranslations2() {
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

}

