package wordtrainer;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Nati on 17.04.2017.
 */
public class MainMenu {

    public static void start() {
        System.out.println("Willkommen zu Ihrem Vokabeltrainer!");
        inputNewLessons();
        // user trains
        // ask user if (s)he wants to delete Lesson 0 (deleteFile is in Input Class)

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
}
