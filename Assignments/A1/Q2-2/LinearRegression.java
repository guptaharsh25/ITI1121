// Author: Harsh Gupta
// Student number: 300042828
// Course: ITI 1121-C
// Assignment: 1
// Question: 2.2

/**
 * The class  <b>LinearRegression</b> implements gradient
 * descent for multiple variables
 *
 * @author Harsh Gupta (hgupt033@uottawa.ca)
 *
 */

public class LinearRegression{


    /**
     * Number of features (usually "n" in litterature)
     */
    private int nbreOfFeatures;

    /**
     * Number of samples (usually "m" in litterature)
     */
    private int nbreOfSamples;


    /**
     * the nbreOfFeatures X nbreOfSamples Matrix of samples
     */
    private double[][] samplesMatrix;

    /**
     * the nbreOfSamples Matrix of samples target values
     */
    private double[] samplesValues;

    /**
     * the nbreOfFeatures Matrix theta of current hypthesis function
     */
    private double[] theta;


    /**
     * number of samples received so far
     */
    private int currentNbreOfSamples;

    /**
     * a place holder for theta during descent calculation
     */
    private double[] tempTheta;


    /**
     * counts how many iterations have been performed
     */
    private int iteration;


    /**
     * Object's contructor. The constructor initializes the instance
     * variables. The starting hypthesis is theta[i]=0.0 for all i
     *
     * @param n the number of features that we will have
     * @param m the number of samples that we will have
     *
     */
    public LinearRegression(int n, int m){

        // your code goes there
        this.nbreOfFeatures = n;
        this.nbreOfSamples = m;

        this.samplesMatrix = new double[m][n+1];
        this.samplesValues = new double[m];

        this.currentNbreOfSamples = 0;

        this.iteration = 0;

        this.theta = new double[n+1];
        this.tempTheta = new double[n+1];
        for(int i = 0; i < (n+1); i++){
            this.theta[i] = 0;
            this.tempTheta[i] = 0;
        }

    }

    /**
     * Add a new sample to samplesMatrix and samplesValues
     *
     * @param x the vectors of samples
     * @param y the coresponding expected value
     *
     */
    public void addSample(double[] x, double y){

        // your code goes there
        this.samplesMatrix[this.currentNbreOfSamples] = x;
        this.samplesValues[this.currentNbreOfSamples] = y;

        this.currentNbreOfSamples++;
    }

    /**
     * Returns the current hypothesis for the value of the input
     * @param x the input vector for which we want the current hypothesis
     *
     * @return h(x)
     */

    private double hypothesis(double[] x){

        // your code goes there

        double ans = 0;

        for(int i = 0; i < x.length; i++){
            ans += (this.theta[i]*x[i]);
        }

        return ans;
    }

    /**
     * Returns a string representation of hypthesis function
     *
     * @return the string "theta0 x_0 + theta1 x_1 + .. thetan x_n"
     */

    public String currentHypothesis(){

        // your code goes there
        String str = "" + this.theta[0] + " + ";
        for(int i = 1; i < (this.theta.length-1); i++){
            str += (this.theta[i] + "x_" + i + " + ");
        }
        str += (this.theta[this.theta.length-1] + "x_" + (this.theta.length-1));

        return str;

    }

    /**
     * Returns the current cost
     *
     * @return the current value of the cost function
     */

    public double currentCost(){

        // your code goes there

        double sig = 0;

        for(int i = 1; i < this.nbreOfSamples; i++){
            sig += (this.hypothesis(this.samplesMatrix[i])-this.samplesValues[i])*(this.hypothesis(this.samplesMatrix[i])-this.samplesValues[i]);
        }

        return sig/this.nbreOfSamples;

    }

    /**
     * runs several iterations of the gradient descent algorithm
     *
     * @param alpha the learning rate
     *
     * @param numberOfSteps how many iteration of the algorithm to run
     */

    public void gradientDescent(double alpha, int numberOfSteps) {
        //double [] sig = new double [this.theta.length];
        //System.out.println("\n\n\n" + this.nbreOfSamples + "\n\n\n" + this.samplesMatrix[0].length);

        // your code goes there
        for(int i = 0; i < numberOfSteps; i++){
            this.tempTheta = new double [this.theta.length];
            
            for(int j = 0; j < this.theta.length; j++){
                //System.out.println("\n\n\n" + j + "\n\n\n");
                for(int k = 0; k < this.nbreOfSamples; k++){
                    this.tempTheta[j] += ((this.hypothesis(this.samplesMatrix[k])-this.samplesValues[k])*this.samplesMatrix[k][j]);
                }
            }

            for(int j = 0; j < this.theta.length; j++){
                this.theta[j] -= alpha*2/this.nbreOfSamples*this.tempTheta[j];
            }

            this.iteration++;
        }

    }


    /**
     * Getter for theta
     *
     * @return theta
     */

    public double[] getTheta(){

        // your code goes there
        return this.theta;

    }

    /**
     * Getter for iteration
     *
     * @return iteration
     */

    public int getIteration(){

        // your code goes there
        return this.iteration;

    }
}