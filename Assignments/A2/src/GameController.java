
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * The class <b>com.harshgupta.GameController</b> is the controller of the game. It is a listener
 * of the view, and has a method <b>play</b> which computes the next
 * step of the game, and  updates model and view.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */


public class GameController implements ActionListener {

    // ADD YOUR INSTANCE VARIABLES HERE
    private int width;
    private int height;

    private int numberOfMines;

    private GameModel model;
    private GameView view;

    /**
     * Constructor used for initializing the controller. It creates the game's view 
     * and the game's model instances
     * 
     * @param width
     *            the width of the board on which the game will be played
     * @param height
     *            the height of the board on which the game will be played
     * @param numberOfMines
     *            the number of mines hidden in the board
     */

    public GameController(int width, int height, int numberOfMines) {

    // ADD YOU CODE HERE
        this.width = width;
        this.height = height;

        this.numberOfMines = numberOfMines;

        this.model = new GameModel(this.width, this.height, this.numberOfMines);
        this.view = new GameView(this.model,this);

    }


    /**
     * Callback used when the user clicks a button (reset or quit)
     *
     * @param e
     *            the ActionEvent
     */
    public void actionPerformed(ActionEvent e) {


        // ADD YOU CODE HERE
        if(e.getSource() instanceof DotButton){
            DotButton dot = (DotButton) e.getSource();
            this.play(dot.getColumn(), dot.getRow());
        }
        else if(e.getSource() instanceof JButton){
            JButton button = (JButton) e.getSource();
            if(button.getText().equals("Reset") || button.getText().equals("Play Again")){
                this.reset();
            }
            if(button.getText().equals("Quit")){
                System.exit(0);
            }
        }


    }

    /**
     * resets the game
     */
    private void reset(){

    // ADD YOU CODE HERE
        this.model.reset();
        this.view = new GameView(this.model, this);
        view.update();

    }

    /**
     * <b>play</b> is the method called when the user clicks on a square.
     * If that square is not already clicked, then it applies the logic
     * of the game to uncover that square, and possibly end the game if
     * that square was mined, or possibly uncover some other squares. 
     * It then checks if the game
     * is finished, and if so, congratulates the player, showing the number of
     * moves, and gives to options: start a new game, or exit
     * @param width
     *            the selected column
     * @param heigth
     *            the selected line
     */
    private void play(int width, int heigth){

    // ADD YOU CODE HERE
        DotInfo thisDot = this.model.get(width, heigth);

        if(!thisDot.hasBeenClicked() && thisDot.isMined()){
            thisDot.click();

            this.model.uncoverAll();
            this.view.update();

            String[] buttons = {"Play Again", "Quit"};
            int result = JOptionPane.showOptionDialog(null,"Aouch, you lost in " + this.model.getNumberOfSteps() + " steps!\n Would you like to play again?", "BOOM!", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, buttons, buttons[1]);
            if(result == 0){
                this.reset();
            }
            else if(result == 1){
                System.exit(0);
            }
        }
        else if (!thisDot.hasBeenClicked()){
            if(this.model.getNeighbooringMines(thisDot.getX(), thisDot.getY()) == 0) {
                thisDot.click();
                thisDot.uncover();
                clearZone(thisDot);
            }
            else{
                thisDot.click();
                thisDot.uncover();
            }

            this.view.update();
        }
        if(model.isFinished()){
            this.model.uncoverAll();
            this.view.update();
            String[] buttons = {"Play Again", "Quit"};
            int result = JOptionPane.showOptionDialog(null, "Congratulations, you won in " + model.getNumberOfSteps() + " steps!\nWould you like to play again?", "Won", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, buttons, buttons[1]);
            if(result == 0){
                this.reset();
            }
            else if(result == 1){
                System.exit(0);
            }
        }

    }

   /**
     * <b>clearZone</b> is the method that computes which new dots should be ``uncovered'' 
     * when a new square with no mine in its neighborood has been selected
     * @param initialDot
     *      the com.harshgupta.DotInfo object corresponding to the selected com.harshgupta.DotButton that
     * had zero neighbouring mines
     */
   //@SuppressWarnings("unchecked")
    private void clearZone(DotInfo initialDot) {


    // ADD YOU CODE HERE

       //max capacity for a stack is the number of dots on the board i.e. width*height
        Stack<DotInfo> stack = new GenericArrayStack<DotInfo>(this.model.getWidth()*this.model.getHeigth());
        stack.push(initialDot);
        while(!stack.isEmpty()){
            DotInfo popped = stack.pop();

            int dotX = popped.getX();
            int dotY = popped.getY();

            //setup array of all neighbouring dots

            DotInfo [] neighbouringDots = new DotInfo[8];
            if((dotX > 0) && (dotY > 0)){
                neighbouringDots[0] = this.model.get(dotX-1, dotY-1);
            }
            if(dotY > 0){
                neighbouringDots[1] = this.model.get(dotX, dotY-1);
            }
            if((dotX < (this.model.getWidth()-1)) && (dotY > 0)){
                neighbouringDots[2] = this.model.get(dotX+1, dotY-1);
            }
            if(dotX > 0){
                neighbouringDots[3] = this.model.get(dotX-1, dotY);
            }
            if(dotX < (this.model.getWidth()-1)){
                neighbouringDots[4] = this.model.get(dotX+1, dotY);
            }
            if((dotX > 0) && (dotY < (this.model.getHeigth()-1))){
                neighbouringDots[5] = this.model.get(dotX-1, dotY+1);
            }
            if(dotY < (this.model.getHeigth()-1)){
                neighbouringDots[6] = this.model.get(dotX, dotY+1);
            }
            if((dotX < (this.model.getWidth()-1)) && (dotY < (this.model.getHeigth()-1))){
                neighbouringDots[7] = this.model.get(dotX+1, dotY+1);
            }

            for(int i = 0; i<8; i++){
                if(neighbouringDots[i] != null){
                    if(neighbouringDots[i].isCovered() && (!neighbouringDots[i].isMined())){
                        neighbouringDots[i].uncover();
                        if(this.model.getNeighbooringMines(neighbouringDots[i].getX(), neighbouringDots[i].getY()) == 0){
                            stack.push(neighbouringDots[i]);
                        }
                    }
                }
            }
        }

    }



}
