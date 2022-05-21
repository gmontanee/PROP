package presentacio.vistes;

import presentacio.vistes.mainView;
import presentacio.vistes.welcomeView;

public class app {

    private mainView mainScreen;

    private welcomeView welcomeScreen; 


    /*
    main principal que posa inicialitza tots els frames i nomes posa en visible el welcome screen.
    */
    public static void main(String args[]){
        mainView mainScreen = new mainView();
        welcomeView welcomeScreen = new welcomeView();

        welcomeScreen.setMainWSVisible(true);
        mainScreen.setMainMSVisible(false);
    }
}
