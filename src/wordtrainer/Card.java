package wordtrainer;

/**
 * Created by Baerbel Hanle and Natalia Shefer on 16.04.2017.
 */
public class Card{

    /**
     * Wort in Sprache 1
     */
    private String wordLanguage1;

    /**
     * Wort in Sprache 2
     */
    private String wordLanguage2;

    /**
     * Konstruktor fuer card.
     * uebergeben wird ein Vokabelpaar(Wort in Sprache 1 und in Sprache 2)
     *
     * @param wordLanguage1 Sprache1
     * @param wordLanguage2 Sprache2
     */
    public Card(String wordLanguage1, String wordLanguage2) {

        this.wordLanguage1 = wordLanguage1;
        this.wordLanguage2 = wordLanguage2;

    }

    /**
     * Getter fuer wordLanguage1
     * @return wordLanguage1
     */
    public String getWordLanguage1(){
        return wordLanguage1;
    }

    /**
     * Getter fuer WordLanguage2
     *
     * @return wordLanguage2
     */
    public String getWordLanguage2(){
        return wordLanguage2;
    }


    @Override
    public String toString(){
        return wordLanguage1 + ", " + wordLanguage2;
    }

}
