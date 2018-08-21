import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * The class <b>com.harshgupta.GameView</b> provides the current view of the entire Game. It extends
 * <b>JFrame</b> and lays out a matrix of <b>com.harshgupta.DotButton</b> (the actual game) and
 * two instances of JButton. The action listener for the buttons is the controller.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class GameView extends JFrame {

     // ADD YOUR INSTANCE VARIABLES HERE

    private GameModel model;
    private GameController controller;

    private DotButton[][] displayBoard;

    private int numSteps;

    private int numMines;

    private JLabel counter;

    /**
     * Constructor used for initializing the Frame
     * 
     * @param gameModel
     *            the model of the game (already initialized)
     * @param gameController
     *            the controller
     */

    public GameView(GameModel gameModel, GameController gameController) {
        
    // ADD YOU CODE HERE
        super("Minesweeper it -- the ITI 1121 version");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.model = gameModel;
        this.controller = gameController;

        this.numSteps = 0;

        this.numMines = 0;

        for(int i = 0; i < this.model.getHeigth(); i++){
            for(int j = 0; j < this.model.getWidth(); j++){
                if(this.model.get(j,i).isMined()){
                    this.numMines++;
                }
            }
        }

        this.displayBoard = new DotButton[this.model.getHeigth()][this.model.getWidth()];

        JPanel p = new JPanel(new BorderLayout());
        p.setBorder(new EmptyBorder(20,20,0,20));

        JPanel boardContainer = new JPanel(new GridLayout(this.model.getHeigth(), this.model.getWidth()));

        for(int i = 0; i < this.model.getHeigth(); i++){
            for(int j = 0; j < this.model.getWidth(); j++){
                this.displayBoard[i][j] = new DotButton(j,i, this.getIcon(j,i));
                this.displayBoard[i][j].setBorder(null);

                boardContainer.add(this.displayBoard[i][j]);

                this.displayBoard[i][j].addActionListener(this.controller);
            }
        }

        p.add(boardContainer, BorderLayout.NORTH);

        JPanel dash = new JPanel(new FlowLayout());
        this.counter = new JLabel("Number of steps: " + this.numSteps);
        dash.add(this.counter);

        JButton reset = new JButton("Reset");
        JButton quit = new JButton("Quit");

        dash.add(reset);
        dash.add(quit);

        reset.addActionListener(this.controller);
        quit.addActionListener(this.controller);

        p.add(dash, BorderLayout.SOUTH);

        add(p);

        pack();

        setVisible(true);

    }

    /**
     * update the status of the board's com.harshgupta.DotButton instances based
     * on the current game model, then redraws the view
     */

    public void update(){
        
    // ADD YOU CODE HERE
        int width = this.model.getWidth();
        int height = this.model.getHeigth();

        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                int newIconNum = this.getIcon(i,j);
                this.displayBoard[j][i].setIconNumber(newIconNum);
                this.repaint();
            }
        }

        this.model.step();

        this.numSteps = this.model.getNumberOfSteps();

        this.counter.setText("Number of steps: " + this.numSteps);
        this.counter.paintImmediately(this.counter.getVisibleRect());

    }

    /**
     * returns the icon value that must be used for a given dot 
     * in the game
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the icon to use for the dot at location (i,j)
     */   
    private int getIcon(int i, int j){
        
    // ADD YOU CODE HERE
        DotInfo dot = this.model.get(i,j);

        if(dot.isMined()){
            if(!dot.isCovered() && !dot.hasBeenClicked()){
                return 9;
            }
            else if(dot.hasBeenClicked()){
                return 10;
            }
            else{
                return 11;
            }
        }
        else{
            if(dot.hasBeenClicked() || !dot.isCovered()){
                return this.model.getNeighbooringMines(i,j);
            }
            else{
                return 11;
            }
        }
    }
}
