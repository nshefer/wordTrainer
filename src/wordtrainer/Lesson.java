package wordtrainer;

import java.util.*;

/**
 * Created by Nati on 16.04.2017.
 */
public class Lesson {
    /**
     * Karteikarte enthaelt ein Vokabelpaar
     */
    private ArrayList<Card> cards;

    /**
     * Lektionsnummer
     */
    private int numberOfLesson;

    /**
     * Thema der Lektion
     */
    private String topic;

    /**
     * Woerterbuch zur uebersetzung von Sprache 1 in Sprache 2
     * (Key: Wort in Sprache 1, Value: alle moeglichen Uebersetzungen dieser Lektion)
     */
    private Map<String, Set<String>> translations1;

    /**
     * Woerterbuch zur uebersetzung von Sprache 2 in Sprache 1
     * (Key: Wort in Sprache 2, Value: alle moeglichen Uebersetzungen dieser Lektion)
     */
    private Map<String, Set<String>> translations2;

    /**
     * Konstruktor fuer Lesson
     * bekommt das Thema und die Nummer der Lektion
     * cards wird als leere ArrayList initialisiert, translations1 und translations2 als leere HashMaps
     *
     * @param numberOfLesson
     * @param topic
     */
    public Lesson(int numberOfLesson, String topic) {
        cards = new ArrayList<>();
        this.numberOfLesson = numberOfLesson;
        this.topic = topic;
        translations1 = new HashMap<>();
        translations2 = new HashMap<>();
    }

    /**
     * Getter fuer cards
     *
     * @return
     */
    public ArrayList<Card> getCards() {
        return cards;
    }

    /**
     * Getter fuer numberOfLesson
     *
     * @return
     */
    public int getNumberOfLesson() {
        return numberOfLesson;
    }

    /**
     * Getter fuer topic
     * @return
     */
    public String getTopic() {
        return topic;
    }

    /**
     * Getter fuer translations1
     *
     * @return
     */
    public Map<String, Set<String>> getTranslations1() {return translations1;}

    /**
     * Getter fuer translations2
     *
     * @return
     */
    public Map<String, Set<String>> getTranslations2() {
        return translations2;
    }

    /**
     * Fuegt eine Karteikarte zu cards hinzu
     *
     * @param card
     */
    public void addCard(Card card) {
        cards.add(card);
    }

    /**
     * Setter fuer die Lektionsnummer
     *
     * @param numberOfLesson
     */
    public void setNumberOfLesson(int numberOfLesson) {
        this.numberOfLesson = numberOfLesson;
    }

    /**
     * Setter fuer topic
     *
     * @param topic
     */
    public void setTopic(String topic) {
        this.topic = topic;
    }

    /**
     * Karteikarten werden gemischt.
     * (Nuetzlich fuer die Methode practiceRandomly der Klasse Training)
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Erzeugt das Woerterbuch translations1 aus cards
     */
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

    /**
     * Erzeugt das Woerterbuch translations2 aus cards
     */
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

