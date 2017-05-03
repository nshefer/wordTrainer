package wordtrainer;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Baerbel Hanle and Natalia Shefer on 16.04.2017.
 */
public class MainMenu {
    /**
     * Start Programm.
     */
    public static void start() {
        System.out.println("Willkommen zu Ihrem Vokabeltrainer!");
        startInputOfNewLessons();
        startTraining();

    }

    /**
     * Start Eingabe von neuen Lektionen.
     */
    public static void startInputOfNewLessons(){
        System.out.println("Wollen Sie jetzt eine neue Lektion eingeben?");
        Scanner scanner = new Scanner(System.in);
        String userAnswer = scanner.nextLine();
        if (userAnswer.toUpperCase().equals("JA")){
              System.out.println("ACHTUNG! Sie verlassen das Eingabemenu, wenn Sie statt Wort Enter drucken.");
             inputNewLessons();
        } else if (!userAnswer.toUpperCase().equals("NEIN")){
            System.out.println("Geben Sie bitte eindeutig \"ja\" oder \"nein\"");
            startInputOfNewLessons();
        }
    }

    /**
     * Regelt die Eingabe der Neuen Lektionen.
     */
    public static void inputNewLessons(){
        Input.writeLesson();
        System.out.println();
        welcomeMenuSwitcher();
    }

    /**
     * Macht Eingabe von neuen Lektionen weiter oder geht zu anderem Teil des Programms.
     */
    private static void welcomeMenuSwitcher(){

        int continueSwitcher=askIfUserWantsToContinue();
        if (continueSwitcher==0){
            inputNewLessons();
        } else if(continueSwitcher==1){
            System.out.println("Sie haben Eingabemenu verlassen.");
        } else {
            welcomeMenuSwitcher();
        }
    }

    /**
     * Gibt zurueck 0 falls User will weiter machen. <br>
     * Gibt zurueck 1 falls User will nicht weiter machen. <br>
     * Gibt zurueck 2 falls Eingabe inkorrekt ist.
     * @return Kode fuer menu switcher
     */
    private static int askIfUserWantsToContinue(){
        int switchCode=0;
        System.out.println("Moechten Sie noch eine Lektion aufschreiben?");
        Scanner scan = new Scanner(System.in);
        String userAnswer = scan.nextLine().toLowerCase();

        if(userAnswer.equals("ja")){
           switchCode=0;
        } else if(userAnswer.equals("nein")){
            switchCode=1;
        } else {
            System.out.println("Geben Sie bitte eindeutig \"ja\" oder \"nein\"");
            switchCode=2;
        }
        return switchCode;
    }

    /**
     * Start vom Vokabeltraining.
     */
    private static void startTraining (){
        askIfUserWantsToLearnLesson();
    }

    /**
     * Fragt, ob User Vokabeln lernen will.
     */
    private static void askIfUserWantsToLearnLesson(){
        System.out.println("Wollen Sie jetzt Vokabeln lernen?");
        Scanner scanner = new Scanner(System.in);
        String userAnswer = scanner.nextLine();
        if (userAnswer.toUpperCase().equals("JA")){
            askLessonNumber();
        } else if (!userAnswer.toUpperCase().equals("NEIN")){
            System.out.println("Geben Sie bitte eindeutig \"ja\" oder \"nein\"");
            askIfUserWantsToLearnLesson();
        }

    }

    /**
     * Fragt User nach der Lektionnummer.
     */
    private static void askLessonNumber(){
        System.out.println("Geben Sie bitte die Lektionsnummer ein!");
        Scanner scanner = new Scanner(System.in);
        int lessonNumber=0;
        if (scanner.hasNextInt()){
            lessonNumber=scanner.nextInt();
            if (Input.checkIfFileExists("Lesson"+lessonNumber+".txt")){
                askInWhatOrderToShowCards(lessonNumber);
            }
            else {
                System.out.println("Es gibt keine Lektionnummer mit dieser Nummer!");
                askLessonNumber();
            }
        } else {
            System.out.println("Lektionsnummer soll eine ganze positive Zahl sein!");
            askLessonNumber();
        }
    }

    /**
     * Fragt User, in welcher Reihenfolge die Karten sollen gezeigt werden.
     * @param lessonNumber Nummer der Lektion
     */
    private static void askInWhatOrderToShowCards(int lessonNumber){
        Training training = new Training();
        System.out.println("Wollen Sie die Vokabeln sortiert oder durcheinander durchgehen?");
        Scanner scanner = new Scanner(System.in);
        String userAnswer = scanner.nextLine();
        if (userAnswer.toUpperCase().equals("SORTIERT")){
            training.practiceOrdered(lessonNumber);
            askIfUserWantsToLearnMore();
            deleteLesson0IfExists();
        } else if (userAnswer.toUpperCase().equals("DURCHEINANDER")){
            training.practiceRandomly(lessonNumber);
            askIfUserWantsToLearnMore();
            deleteLesson0IfExists();
        } else {
            System.out.println("Geben Sie bitte eindeutig \"sortiert\" oder \"durcheinander\".");
            askInWhatOrderToShowCards(lessonNumber);
        }
    }

    /**
     * Fragt ob User will noch eine Lektion lernen.
     */
    private static void askIfUserWantsToLearnMore(){
        System.out.println("Wollen Sie durch noch eine Lektion durchgehen?");
        Scanner scanner = new Scanner(System.in);
        String userAnswer = scanner.nextLine();
        if (userAnswer.toUpperCase().equals("JA")){
            askLessonNumber();
        } else if (!userAnswer.toUpperCase().equals("NEIN")){
            askIfUserWantsToLearnMore();
        }
    }

    /**
     * Loescht Lektion 0, falls sie existiert.
     */
    private static void deleteLesson0IfExists(){
        if (Input.checkIfFileExists("Lesson0.txt")){
            askIfUserWantsToDeleteLesson0();
        }
    }

    /**
     * Fragt User, ob die Lktion 9 geloescht werden soll.
     */
    private static void askIfUserWantsToDeleteLesson0(){
        System.out.println("Wollen Sie Lektion 0 loeschen?");
        Scanner scanner = new Scanner(System.in);
        String userAnswer = scanner.nextLine();
        if (userAnswer.toUpperCase().equals("JA")){
            Input.deleteLesson("Lesson0.txt");
            System.out.println("Lektion 0 wurde geloescht.");
        } else if (userAnswer.toUpperCase().equals("NEIN")){
            System.out.println("OK, die Datei wird behaltet.");
        } else {
            System.out.println("Antworten Sie bitte eindeutig mit \"ja\" oder \"nein\"!");
            askIfUserWantsToDeleteLesson0();
        }
    }
}
