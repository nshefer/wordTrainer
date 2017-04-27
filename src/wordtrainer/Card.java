package wordtrainer;

/**
 * Created by Nati on 16.04.2017.
 */
public class Card{//} implements Comparable{

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
     * @param wordLanguage1
     * @param wordLanguage2
     */
    public Card(String wordLanguage1, String wordLanguage2) {

        this.wordLanguage1 = wordLanguage1;
        this.wordLanguage2 = wordLanguage2;

    }

    /**
     * Getter fuer wordLanguage1
     * @return
     */
    public String getWordLanguage1(){
        return wordLanguage1;
    }

    /**
     * Getter fuer WordLanguage2
     *
     * @return
     */
    public String getWordLanguage2(){
        return wordLanguage2;
    }


    @Override
    public String toString(){
        return wordLanguage1 + ", " + wordLanguage2;
    }

}
