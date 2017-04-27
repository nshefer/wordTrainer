package wordtrainer;

/**
 * Created by Nati on 16.04.2017.
 */
public class Card implements Comparable{
    private String wordLanguage1;
    private String wordLanguage2;
    //private int position;
    private boolean isInLesson0;
    private int countOfFalseAnswers;
    private int countOfCorrectAnswers;

    public Card(String wordLanguage1, String wordLanguage2) {
        this.wordLanguage1 = wordLanguage1;
        this.wordLanguage2 = wordLanguage2;
        isInLesson0 = false;
        countOfFalseAnswers = 0;
        countOfCorrectAnswers = 0;
    }

    public String getWordLanguage1(){
        return wordLanguage1;
    }

    public String getWordLanguage2(){
        return wordLanguage2;
    }

    public boolean getInLesson0(){
        return isInLesson0;
    }

    public int getCountOfFalseAnswers(){
        return countOfFalseAnswers;
    }

    public int getCountOfCorrectAnswers(){
        return countOfCorrectAnswers;
    }

    public void setInLesson0(boolean isInLesson0){
        this.isInLesson0=isInLesson0;
    }

    public void increaseCountOfFalseAnswers(){
        countOfFalseAnswers++;
    }

    public void increaseCountOfCorrectAnswers(){
        countOfCorrectAnswers++;
    }

    @Override
    public int compareTo(Object anotherObject){
        Card anotherCard = (Card) anotherObject;

        if((anotherCard.getWordLanguage1().equals(this.wordLanguage1)) && (anotherCard.getWordLanguage1().equals(this.wordLanguage1))){
            return 0;
        }else{
            return 1;
        }
    }

    @Override
    public String toString(){
        return wordLanguage1 + ", " + wordLanguage2;
    }

}
