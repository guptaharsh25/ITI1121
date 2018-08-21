// Author: Harsh Gupta
// Student number: 300042828
// Course: ITI 1121-C
// Assignment: 1
// Question: 1.2

/**
 * The class  <b>LinearRegression</b> implements gradient
 * descent with 1 variable.
 *
 * @author Harsh Gupta (hgupt033@uottawa.ca)
 *
 */

public class LinearRegression {


    /**
     * Number of samples (usually "m" in litterature)
     */
    private int nbreOfSamples;


    /**
     * the sample vector
     */
    private double[] samples;

    /**
     * the samples target values
     */
    private double[] samplesValues;

    /**
     * the current hypthesis function: theta0 + theta1 x
     */
    private double theta0, theta1;


    /**
     * used to ensure that the object is ready
     */
    private int currentNbreOfSamples;



    /**
     * counts how many iterations have been performed
     */
    private int iteration;


    /**
     * Object's contructor. The constructor initializes the instance
     * variables. The starting hypthesis is y = 0;
     *
     *
     * @param m the number of samples that we will have
     *
     */
    public LinearRegression(int m){

        // your code goes there
        this.nbreOfSamples = m;

        this.samples = new double [0];
        this.samplesValues = new double [0];

        this.theta0 = 0;
        this.theta1 = 0;

        this.iteration = 0;


    }

    /**
     * Adds a new sample to sample and to samplesValues. This
     * method must be iteratively called with all the samples
     * before the gradient descent can be started
     *
     * @param x the new sample
     * @param y the corresponding expected value
     *
     */
    public void addSample(double x, double y){

        // your code goes there

        this.currentNbreOfSamples = this.getSamples().length;

        double [] temp_sample = new double[currentNbreOfSamples + 1];
        double [] temp_sampleValue = new double[currentNbreOfSamples + 1];

        for(int i = 0; i < currentNbreOfSamples; i++){
            temp_sample[i] = this.getSamples()[i];
            temp_sampleValue[i] = this.getSamplesValues()[i];
        }

        temp_sample[this.currentNbreOfSamples] = x;
        temp_sampleValue[this.currentNbreOfSamples] = y;

        this.currentNbreOfSamples++;

        this.samples = temp_sample;
        this.samplesValues = temp_sampleValue;
    }

    /**
     * Returns the current hypothesis for the value of the input
     * @param x the input for which we want the current hypothesis
     *
     * @return theta0 + theta1 x
     */
    private double hypothesis(double x){
        // your code goes there
        return this.getTheta0() + (this.getTheta1()*x);
    }

    /**
     * Returns a string representation of hypthesis function
     *
     * @return the string "theta0 + theta1 x"
     */
    public String currentHypothesis(){

        // your code goes there
        return this.getTheta0() + "+" + this.getTheta1() + "x";
    }

    /**
     * Returns the current cost
     *
     * @return the current value of the cost function
     */
    public double currentCost(){
        // your code goes there

        double sig = 0;

        for(int i = 0; i < this.nbreOfSamples; i++){
            sig += (hypothesis(this.getSamples()[i])-this.getSamplesValues()[i])*(hypothesis(this.getSamples()[i])-this.getSamplesValues()[i]);
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
        double sig0;
        double sig1;

        // your code goes there
        for(int i = 0; i < numberOfSteps; i++){
            double t0 = this.getTheta0();
            double t1 = this.getTheta1();

            sig0 = 0;
            sig1 = 0;

            for(int j = 0; j < this.nbreOfSamples; j++){
                sig0 += (this.hypothesis(this.getSamples()[j])-this.getSamplesValues()[j]);
                sig1 += ((this.hypothesis(this.getSamples()[j])-this.getSamplesValues()[j])*this.getSamples()[j]);
            }

            this.theta0 = t0 - alpha * 2 /this.nbreOfSamples * sig0;
            this.theta1 = t1 - alpha * 2 /this.nbreOfSamples * sig1;

            this.iteration++;
        }

    }



    /**
     * Getter for theta0
     *
     * @return theta0
     */

    public double getTheta0(){
        // your code goes there
        return this.theta0;
    }

    /**
     * Getter for theta1
     *
     * @return theta1
     */

    public double getTheta1(){
        // your code goes there
        return this.theta1;
    }

    /**
     * Getter for samples
     *
     * @return samples
     */

    public double[] getSamples(){
        // your code goes there
        return this.samples;
    }

    /**
     * Getter for getSamplesValues
     *
     * @return getSamplesValues
     */

    public double[] getSamplesValues(){
        // your code goes there
        return this.samplesValues;
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