/**
 * In the application <b>Instant Insanity</b>, a <b>Cube</b> is a representation of a single cube in the puzzle.
 * The cube memorizes it's initial color orientation.
 * The cube has the ability to transform itself (if there is a valid new transformation that hasn't been performed yet.
 * The cube can also reset itself to it's initial color orientation.
 *
 * @author Harsh Gupta, uOttawa (hgupt033@uottawa.ca)
 */

public class Cube {

    /**
     * Declaration of the possible Colors
     */
    public enum Color{
        BLUE, GREEN, RED, WHITE
    }

    private Color[] faces;
    private final Color[] identity;

    private int nextStep;

    /**
     * Constructor, used to initialize the instance variables
     *
     * @param faces
     *            an array of all the Color faces of this cube (up, front, right, back, left, down)
     */
    public Cube(Color[] faces){
        this.faces = new Color[faces.length];

        for(int i = 0; i < faces.length; i++){
            this.faces[i] = faces[i];
        }

        this.identity = new Color[]{this.faces[0], this.faces[1], this.faces[2], this.faces[3], this.faces[4], this.faces[5]};

        this.nextStep = 0;
    }

    /**
     * Getter for color on upper face.
     *
     * @return value of upper face.
     *
     */
    public Color getUp(){
        return this.faces[0];
    }

    /**
     * Getter for color on front face.
     *
     * @return value of front face.
     *
     */
    public Color getFront(){
        return this.faces[1];
    }

    /**
     * Getter for color on right face.
     *
     * @return value of right face.
     *
     */
    public Color getRight(){
        return this.faces[2];
    }

    /**
     * Getter for color on back face.
     *
     * @return value of back face.
     *
     */
    public Color getBack(){
        return this.faces[3];
    }

    /**
     * Getter for color on left face.
     *
     * @return value of left face.
     */
    public Color getLeft(){
        return this.faces[4];
    }

    /**
     * Getter for color on down face.
     *
     * @return value of down face.
     */
    public Color getDown(){
        return this.faces[5];
    }

    /**
     * Represents the cube as a string.
     *
     * @return string representation of cube.
     */
    public String toString() {
        String ans = "[";

        for(int i = 0; i < (this.faces.length - 1); i++){
            ans += this.faces[i] +", ";
        }

        ans += this.faces[this.faces.length - 1] + "]";

        return ans;
    }

    /**
     * Does this cube have another unique transformation?
     *
     * @return T/F does this cube have another unique transformation?
     *
     */
    public boolean hasNext(){
        return (this.nextStep < 24);
    }

    /**
     * Performs the next transformation of the cube.
     * If there is no next unique state of the cube, throws an IllegalStateException
     *
     */
    public void next(){

        switch(this.nextStep){
            default:
                Rotate();
                break;
            case 0:
                Identity();
                break;
            case 4: case 8: case 20:
                RightRoll();
                break;
            case 12: case 16:
                LeftRoll();
                break;
            case 25:
                throw new IllegalStateException();

        }

        this.nextStep++;
    }

    /**
     * Resets the cube to its original state.
     *
     */
    public void reset(){
        for(int i = 0; i < this.faces.length; i++){
            this.faces[i] = this.identity[i];
        }

        this.nextStep = 0;
    }

    /**
     * rotates the cube to the right around the top-bottom axis so that the left side is now facing front.
     *
     */
    private void Rotate(){
        Color temp;
        temp = faces[4];
        faces[4] = faces[3];
        faces[3] = faces[2];
        faces[2] = faces[1];
        faces[1] = temp;
    }

    /**
     * rolls the cube to the right around the back-front axis so that the left side is now up.
     *
     */
    private void RightRoll(){
        Color temp;
        temp = faces[5];
        faces[5] = faces[2];
        faces[2] = faces[0];
        faces[0] = faces[4];
        faces[4] = temp;
    }

    /**
     * rolls the cube to the left around the back-front axis so that the right side is now up.
     */
    private void LeftRoll(){
        Color temp;
        temp = faces[5];
        faces[5] = faces[4];
        faces[4] = faces[0];
        faces[0] = faces[2];
        faces[2] = temp;
    }

    /**
     * returns all the faces to their original state (colours).
     *
     * @return Array of Colors for original state.
     */
    private Color[] Identity(){
        for(int i = 0; i < this.faces.length; i++){
            this.faces[i] = this.identity[i];
        }

        return this.identity;
    }

    /**
     * the constructor initializes this cube to be an identical but independent copy of other. In other words,
     * this Cube is a deep copy of the cube designated by other.
     *
     * @param other the cube which needs to be deep copied.
     */
    public Cube(Cube other){
        this.faces = new Color[other.faces.length];
        this.identity = new Color[other.faces.length];
        for(int i = 0; i < this.faces.length; i++){
            this.faces[i] = this.identity[i] = other.faces[i];
        }

        this.nextStep = other.nextStep;
    }

    /**
     * returns a deep copy of this Cube.
     *
     * @return deep copy of this cube.
     */
    public Cube copy(){
        return new Cube(this);
    }
}
