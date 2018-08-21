// Author: Harsh Gupta
// Student number: 300042828
// Course: ITI 1121-C
// Assignment: 1
// Question: 2.3

/**
 * The class  <b>Assignment</b> is used to
 * test our LinearRegression class.
 *
 * @author Harsh Gupta (hgupt033@uottawa.ca)
 *
 */

public class Assignment {


    /**
     * Random generator
     */
    private static java.util.Random generator = new java.util.Random();

    /**
     * In this first method, we are simply using sample points that are
     * on a straight plane. We will use the plane z= x + 2x.
     * In his method,
     * 	1) we create an instance of LinearRegression.
     * 	2) we add 2,000 samples from the plane z= x + 2x as follows:
     * 		add the sample [(i, 2i), 5i] for 0<=i<=999
     * 		add the sample [(2i, i), 4i] for 0<=i<=999
     *  3) we iterate gradient descent 10,000, printing out the
     * current hypothesis and the current cost every 1,000
     * iterations, using a step alpha of 0.000000003
     */
    private static void setPlane(){

        // your code goes there
        LinearRegression regressor = new LinearRegression(2, 2000);

        for(int i = 0; i < 1000; i++) {
            regressor.addSample(new double [] {1, i, 2*i}, 5*i);
            regressor.addSample(new double [] {1, 2*i, i}, 4*i);
        }

        System.out.println("Current hypothesis: " + regressor.currentHypothesis());
        System.out.println("Current cost: " + regressor.currentCost());

        for(int i = 0; i < 10; i++) {
            regressor.gradientDescent(0.000000003, 1000);
            System.out.println("Current hypothesis: " + regressor.currentHypothesis());
            System.out.println("Current cost: " + regressor.currentCost());
        }

    }

    /**
     * In this second method, we will select a plane at random.
     * 	1) we select a line z = ax + by + c, with a, b and c
     * randomly selected between -100 and +100
     * 	2) we add 5000 samples randomly selected on the plane
     * with x and y both randomly selected between 50 and 4000.
     * For each sample we add a "noise"
     * randomly selected between -20 and +20 (that is, for
     * each randomly selected x and y we add the sample
     *[ (x,y), ax+by+c+noise).
     * where "noise" is randomly selected between -20 and 20
     *  4) we iterate gradient descent (find a number of iterations,
     * and a step alpha that seems to work, regularly printing
     * the target,  the current hypothesis and the current cost)
     */

    private static void randomPlane(){

        // your code goes there
        LinearRegression regressor = new LinearRegression(2, 5000);

        double a = generator.nextDouble()*200 - 100;
        double b = generator.nextDouble()*200 - 100;
        double c = generator.nextDouble()*200 - 100;

        for(int i = 0; i < 5000; i++){
            double x_1 = generator.nextDouble()*3950 + 50;
            double x_2 = generator.nextDouble()*3950 + 50;

            double d = generator.nextDouble()*40 - 20;

            double z = a*x_1 + b*x_2 + c + d;

            regressor.addSample(new double[] {1, x_1, x_2}, z);
        }

        System.out.println("Current hypothesis: " + regressor.currentHypothesis());
        System.out.println("Current cost: " + regressor.currentCost());
        System.out.println("Aiming for: " + c + "+" + a + "x_1" + "+" + b + "x_2");

        for(int i = 0; i < 10; i++) {
            regressor.gradientDescent(0.0000000001, 100000);
            System.out.println("Current hypothesis: " + regressor.currentHypothesis());
            System.out.println("Current cost: " + regressor.currentCost());
            System.out.println("Aiming for: " + c + "+" + a + "x_1" + "+" + b + "x_2");
        }


    }


    /**
     * In this third method, we will follow the same approach
     * that the one followed in the method  randomPlane, but
     * this time we will have a variable number of dimensions,
     * specified by the parameter "dimension". We will
     * create 5000 samples of "dimension" dimension, where each
     * dimension will be ranmly selected between  -100 and +100,
     * and a randomly selected noise between -20 and +20 will be
     * added to the result.We will then iterate gradient descent
     * (find a number of iterations,
     * and a step alpha that seems to work, regularly printing
     * the target,  the current hypothesis and the current cost)
     *
     * @param dimension the number of features
     */
    private static void randomDimension(int dimension){

        // your code goes there
        LinearRegression regressor = new LinearRegression(dimension, 5000);

        double [] coefficients = new double[dimension+1];

        for(int i = 0; i < coefficients.length; i++){
            coefficients[i] = generator.nextDouble()*200 - 100;
        }

        for(int i = 0; i < 5000; i++) {
            double [] sample = new double[dimension+1];
            sample[0] = 1;

            double r = 0;

            for(int j = 1; j < sample.length; j++){
                sample[j] = generator.nextDouble()*3950 + 50;
            }

            for(int j = 0; j < sample.length; j++){
                r += sample[j]*coefficients[j];
            }

            regressor.addSample(sample, r);
        }

        System.out.println("Current hypothesis: " + regressor.currentHypothesis());
        System.out.println("Current cost: " + regressor.currentCost());

        String aim = "Aiming for: " + coefficients[0] + "+";
        for(int i = 1; i < (coefficients.length-1); i++){
            aim += (coefficients[i] + "x_" + i + "+");
        }
        aim += "" + coefficients[coefficients.length - 1] + "x_" + (coefficients.length-1);

        System.out.println(aim);

        for(int i = 0; i < 10; i++) {
            regressor.gradientDescent(0.0000000005, 1000);
            System.out.println("Current hypothesis: " + regressor.currentHypothesis());
            System.out.println("Current cost: " + regressor.currentCost());
            System.out.println(aim);
        }

    }

    public static void main(String[] args) {

        StudentInfo.display();

        System.out.println("randomDimension");
        randomDimension(50);


    }

}