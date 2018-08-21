/**
 * The class <b>Mineseeper</b> launches the game
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */
public class Minesweeper {

    // ADD YOUR INSTANCE VARIABLES HERE
    private static final int DEFAULT_WIDTH = 20;
    private static final int DEFAULT_HEIGTH = 12;
    private static final int DEFAULT_MINES = 36;

   /**
     * <b>main</b> of the application. Creates the instance of  com.harshgupta.GameController
     * and starts the game. If three parameters width, heigth,
     * number of mines are passed, they are used. 
     * Otherwise, a default value is used. Defaults values are also
     * used if the paramters are too small (minimum 10 for width,
     * 5 for heigth and 1 for number of mines).
     * Additionally, the maximum number of mines is capped at
     * width*heigth -1
     * 
     * @param args
     *            command line parameters
     */
     public static void main(String[] args) {

        StudentInfo.display();

        //set the variables to the constants declared above
        int width   = DEFAULT_WIDTH;
        int heigth  = DEFAULT_HEIGTH;
        int numberOfMines = DEFAULT_MINES;

        //if 3 args are passed, try to assign values, if errors occur except block catches it.
        if (args.length == 3) {
            try{
                width = Integer.parseInt(args[0]);
                if(width<10){
                    System.out.println("Invalid argument, using default...");
                    width = DEFAULT_WIDTH;
                }
                heigth = Integer.parseInt(args[1]);
                if(heigth<5){
                    System.out.println("Invalid argument, using default...");
                    heigth = DEFAULT_HEIGTH;
                }
                numberOfMines = Integer.parseInt(args[2]);
                if(numberOfMines<1){
                    System.out.println("Invalid argument, using default...");
                    numberOfMines = DEFAULT_MINES;
                }
            } catch(NumberFormatException e){
                System.out.println("Invalid argument, using default...");
                width   = DEFAULT_WIDTH;
                heigth  = DEFAULT_HEIGTH;
                numberOfMines = DEFAULT_MINES;
            }
        }
        //if more mines than dots, print out error.
        if(numberOfMines >= width*heigth) {
            System.out.println("Too many mines: " + numberOfMines 
                + " mines on " + (width*heigth) + " spots. Using " 
                + (width*heigth - 1) + " instead. Good luck!");
            numberOfMines = (width*heigth - 1);
        }

        //instantiate the game.
        GameController game = new GameController(width, heigth, numberOfMines);
    }


}
