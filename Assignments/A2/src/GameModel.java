import java.util.Random;

/*
 * The class <b>com.harshgupta.GameModel</b> holds the model, the state of the systems.
 * It stores the following information:
 * - the state of all the ``dots'' on the board (mined or not, clicked
 * or not, number of neighbooring mines...)
 * - the size of the board
 * - the number of steps since the last reset
 *
 * The model provides all of this informations to the other classes trough 
 *  appropriate Getters. 
 * The controller can also update the model through Setters.
 * Finally, the model is also in charge of initializing the game
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */
public class GameModel {


     // ADD YOUR INSTANCE VARIABLES HERE
    private int width;
    private int heigth;

    private int numberOfMines;

    private int numSteps;

    private DotInfo [][] board;

    private Random generator = new Random();

    /**
     * Constructor to initialize the model to a given size of board.
     * 
     * @param width
     *            the width of the board
     * 
     * @param heigth
     *            the heigth of the board
     * 
     * @param numberOfMines
     *            the number of mines to hide in the board
     */
    public GameModel(int width, int heigth, int numberOfMines) {
        
    // ADD YOU CODE HERE
        this.width = width;
        this.heigth = heigth;
        this.numberOfMines = numberOfMines;

        this.board = new DotInfo[width][heigth];

        for(int i = 0; i < this.width; i++){
            for(int j = 0; j < this.heigth; j++){
                board[i][j] = new DotInfo(i,j);
            }
        }

        for(int i = 0; i < this.numberOfMines; i++){
            int minePosX = -1;
            int minePosY = -1;
            while((minePosX < 0) || (minePosY < 0) || ((this.board[minePosX][minePosY].isMined()) || (this.board[minePosX][minePosY].isMined()))){
                minePosX = (int) (this.generator.nextDouble()*this.width);
                minePosY = (int) (this.generator.nextDouble()*this.heigth);
            }

            this.board[minePosX][minePosY].setMined();
        }

        this.numSteps = 0;
    }


 
    /**
     * Resets the model to (re)start a game. The previous game (if there is one)
     * is cleared up . 
     */
    public void reset(){

        
    // ADD YOU CODE HERE
        for(int i = 0; i < this.width; i++){
            for(int j = 0; j < this.heigth; j++){
                board[i][j] = new DotInfo(i,j);
            }
        }

        for(int i = 0; i < this.numberOfMines; i++){
            int minePosX = -1;
            int minePosY = -1;

            while((minePosX < 0) || (minePosY < 0) || ((this.board[minePosX][minePosY].isMined()) || (this.board[minePosX][minePosY].isMined()))){
                minePosX = (int) (this.generator.nextDouble()*this.width);
                minePosY = (int) (this.generator.nextDouble()*this.heigth);
            }

            this.board[minePosX][minePosY].setMined();
        }

        this.numSteps = -1;
    }


    /**
     * Getter method for the heigth of the game
     * 
     * @return the value of the attribute heigthOfGame
     */   
    public int getHeigth(){
        
    // ADD YOU CODE HERE
        return this.heigth;

    }

    /**
     * Getter method for the width of the game
     * 
     * @return the value of the attribute widthOfGame
     */   
    public int getWidth(){
        
    // ADD YOU CODE HERE
        return this.width;

    }



    /**
     * returns true if the dot at location (i,j) is mined, false otherwise
    * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean isMined(int i, int j){
        
    // ADD YOU CODE HERE
        return this.board[i][j].isMined();
    }

    /**
     * returns true if the dot  at location (i,j) has 
     * been clicked, false otherwise
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean hasBeenClicked(int i, int j){
        
    // ADD YOU CODE HERE
        return this.board[i][j].hasBeenClicked();
    }

  /**
     * returns true if the dot  at location (i,j) has zero mined 
     * neighboor, false otherwise
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean isBlank(int i, int j){
        
    // ADD YOU CODE HERE
        return this.getNeighbooringMines(i, j) == 0;

    }
    /**
     * returns true if the dot is covered, false otherwise
    * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean isCovered(int i, int j){
        
    // ADD YOU CODE HERE
        return this.board[i][j].isCovered();
    }

    /**
     * returns the number of neighbooring mines os the dot  
     * at location (i,j)
     *
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the number of neighbooring mines at location (i,j)
     */   
    public int getNeighbooringMines(int i, int j){
        
    // ADD YOU CODE HERE
        int counter = 0;

        if(i > 0 && j > 0 && this.board[i-1][j-1].isMined()){
            counter++;
        }
        if(j > 0 && this.board[i][j-1].isMined()){
            counter++;
        }
        if(i < (this.width-1) && j > 0 && this.board[i+1][j-1].isMined()){
            counter++;
        }
        if(i > 0 && this.board[i-1][j].isMined()){
            counter++;
        }
        if(i < (this.width-1) && this.board[i+1][j].isMined()){
            counter++;
        }
        if(i > 0 && j < (this.heigth-1) && this.board[i-1][j+1].isMined()){
            counter++;
        }
        if(j < (this.heigth-1) && this.board[i][j+1].isMined()){
            counter++;
        }
        if(i < (this.width-1) && j < (this.heigth-1) && this.board[i+1][j+1].isMined()){
            counter++;
        }

        this.board[i][j].setNeighbooringMines(counter);

        return counter;
    }


    /**
     * Sets the status of the dot at location (i,j) to uncovered
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     */   
    public void uncover(int i, int j){
        
    // ADD YOU CODE HERE
        this.board[i][j].uncover();

    }

    /**
     * Sets the status of the dot at location (i,j) to clicked
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     */   
    public void click(int i, int j){
        
    // ADD YOU CODE HERE
        this.board[i][j].click();

    }
     /**
     * Uncover all remaining covered dot
     */   
    public void uncoverAll(){
        
    // ADD YOU CODE HERE
        for(int i = 0; i < this.width; i++){
            for(int j = 0; j < this.heigth; j++){

                if(this.board[i][j].isCovered()){
                    this.board[i][j].uncover();
                }

            }
        }

    }

 

    /**
     * Getter method for the current number of steps
     * 
     * @return the current number of steps
     */   
    public int getNumberOfSteps(){
        
    // ADD YOU CODE HERE
        return this.numSteps;
    }

  

    /**
     * Getter method for the model's dotInfo reference
     * at location (i,j)
     *
      * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     *
     * @return model[i][j]
     */   
    public DotInfo get(int i, int j) {
        
    // ADD YOU CODE HERE
        return this.board[i][j];
    }


   /**
     * The metod <b>step</b> updates the number of steps. It must be called 
     * once the model has been updated after the payer selected a new square.
     */
   public void step(){
        
    // ADD YOU CODE HERE
         this.numSteps++;

    }
 
   /**
     * The metod <b>isFinished</b> returns true iff the game is finished, that
     * is, all the nonmined dots are uncovered.
     *
     * @return true if the game is finished, false otherwise
     */
    public boolean isFinished(){
        
    // ADD YOU CODE HERE
        for(int i = 0; i<this.width; i++){
            for(int j = 0; j<this.heigth; j++){

                if(!this.board[i][j].isMined()){
                    if(this.board[i][j].isCovered()){
                        return false;
                    }
                }

            }
        }

        return true;
    }


   /**
     * Builds a String representation of the model
     *
     * @return String representation of the model
     */
    public String toString(){
        
    // ADD YOU CODE HERE
        return "This is a board of width " + this.width + " and height " + this.heigth +
                ".\nThere are " + this.numberOfMines + "mines.\n" + this.numSteps +
                " have been played.";

    }
}
