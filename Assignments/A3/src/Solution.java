/**
 * In the application <b>Instant Insanity</b>, a <b>Solution</b> is a representation of a set of cubes that could be a
 * valid solution.
 * The solution has the ability to check whether the cubes are in a valid orientation or not.
 *
 * @author Harsh Gupta, uOttawa (hgupt033@uottawa.ca)
 */
public class Solution {

    private Cube[] cubes;
    private int numberOfCalls;

    /**
     * initializes this solution using the cubes provided in the array cubes. Because the cubes are
     * mutable, the solution must also copy the cubes.
     *
     * @param cubes an array of cubes
     */
    public Solution(Cube[] cubes){
        this.cubes = new Cube[cubes.length];
        for(int i = 0; i < cubes.length; i++){
            this.cubes[i] = cubes[i].copy();
        }
        this.numberOfCalls = 0;
    }

    /**
     * initializes this solution using the specified information. It receives a partial solution and a cube.
     * The new solution has the same elements, in the same order, as the solution designated by other. The value null
     * is a valid value for other, but not for cube. Make sure that this solution and other do not share cubes.
     *
     * @param other a reference to another <b>Solution</b>
     * @param c the cube to add to the other <b>Solution</b>
     */
    public Solution(Solution other, Cube c){
        try{
            this.cubes = new Cube[other.cubes.length + 1];
            for (int i = 0; i < other.cubes.length; i++) {
                this.cubes[i] = new Cube(new Cube.Color[]{other.cubes[i].getUp(),
                        other.cubes[i].getFront(),
                        other.cubes[i].getRight(),
                        other.cubes[i].getBack(),
                        other.cubes[i].getLeft(),
                        other.cubes[i].getDown()});
            }
            this.cubes[other.cubes.length] = new Cube(new Cube.Color[]{c.getUp(), c.getFront(), c.getRight(), c.getBack(), c.getLeft(), c.getDown()});
        }
        catch(NullPointerException e){
            this.cubes = new Cube[]{new Cube(new Cube.Color[]{c.getUp(), c.getFront(), c.getRight(), c.getBack(), c.getLeft(), c.getDown()})};
        }
        this.numberOfCalls = 0;
    }

    /**
     * returns the total number of calls to the method isValid of any object of the class Solution since the
     * last call to the method resetNumberOfCalls.
     *
     * @return number of calls to isValid()
     *
     */
    public int getNumberOfCalls(){
        return this.numberOfCalls;
    }

    /**
     * reset the counter for isValid to zero.
     */
    public void resetNumberOfCalls(){
        this.numberOfCalls = 0;
    }

    /**
     * returns the number of cubes in this solution.
     *
     * @return number of cubes.
     *
     */
    public int size(){
        return this.cubes.length;
    }

    /**
     * returns the cube at pos in the cubes array.
     *
     * @return cube at pos in cubes array.
     *
     */
    public Cube getCube(int pos){
        return this.cubes[pos];
    }

    /**
     * returns a boolean to check whether this solution is valid or not.
     *
     * A solution is valid if all the colors on each of the 4 sides of the cube "tower" are distinct.
     *
     * @return boolean for whether this Solution is valid or not.
     *
     */
    public boolean isValid(){

        this.numberOfCalls++;

        for(int i = 0; i < this.cubes.length; i++){
            Cube.Color left = this.cubes[i].getLeft();
            Cube.Color front = this.cubes[i].getFront();
            Cube.Color right = this.cubes[i].getRight();
            Cube.Color back = this.cubes[i].getBack();
            for(int j = (i+1); j < this.cubes.length; j++){
                if((this.cubes[j].getLeft() == left) || (this.cubes[j].getFront() == front) || (this.cubes[j].getRight() == right) || (this.cubes[j].getBack() == back)){
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * returns a boolean for whether adding the next cube will render this solution valid or invalid.
     *
     * @param next the cube proposed to be added to this Solution
     * @return boolean for whether adding the next cube will render this solution valid or invalid.
     *
     */
    public boolean isValid(Cube next){
        Solution newSolution = new Solution(this, next);
        return newSolution.isValid();
    }

    /**
     * Represents the Solution as a string.
     *
     * @return string representation of solution.
     */
    public String toString(){
        String str = "";
        Cube.Color[][] c = new Cube.Color[6][this.cubes.length];

        for(int i = 0; i < this.cubes.length; i++){
            str += (this.cubes[i] + "\n");
        }

        if(this.isValid()){
            str += "\nThis solution is valid.";
        }
        else{
            str += "\nThis solution is invalid.";
        }

        return str;
    }
}
