package wordtrainer;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Nati on 17.04.2017.
 */
public class MainMenu {

    public static void start() {
        System.out.println("Willkommen zu Ihrem Vokabeltrainer!");
        startInputOfNewLessons();
        startTraining();

    }

    public static void startInputOfNewLessons(){
        System.out.println("Wollen Sie jetzt eine neue Lektion eingeben?");
        Scanner scanner = new Scanner(System.in);
        String userAnswer = scanner.nextLine();
        if (userAnswer.toUpperCase().equals("JA")){
             inputNewLessons();
        } else if (!userAnswer.toUpperCase().equals("NEIN")){
            System.out.println("Geben Sie bitte eindeutig \"ja\" oder \"nein\"");
            startInputOfNewLessons();
        }
    }

    public static void inputNewLessons(){
        Input.writeLesson();
        System.out.println();
        welcomeMenuSwitcher();
    }

    /**
     * Continues input of new lessons or goes to the next part of the programm.
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
     * Returns 0 if the user wants to continue. <br>
     * Returns 1 if the user doesn't want to continue. <br>
     * Returns 2 if the user put in invalid answer.
     * @return code for menu switcher
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

    private static void startTraining (){
        askIfUserWantsToLearnLesson();
    }



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

    // TODO: fill the gaps
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
            System.out.println("Geben Sie bitte eindeutig \"sortiert\" oder \"gemischt\".");
            askIfUserWantsToLearnLesson();
        }
    }

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

    private static void deleteLesson0IfExists(){
        if (Input.checkIfFileExists("Lesson0.txt")){
            askIfUserWantsToDeleteLesson0();
        }
    }
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
