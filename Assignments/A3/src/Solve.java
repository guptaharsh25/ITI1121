/**
 * In the application <b>Instant Insanity</b>, the <b>Solve</b> class uses two separate methods to solve the Instant
 * Insanity puzzle.
 *
 * The generateAndTest method is a brute-force approach to solving the puzzle. It generates every single possible
 * orientation for the set of cubes and checks whether they are valid or not.
 *
 * The breadthFirstSearch method is a systematic approach to solving the puzzle. It starts by creating a Solution out of
 * one cube, and it incrementally adds cubes in specific orientations only if the solution would remain valid.
 *
 * @author Harsh Gupta, uOttawa (hgupt033@uottawa.ca)
 */
public class Solve {

    /**
     * finds all the solutions to the instant insanity problem by exhaustively generating all the possible solutions. It
     * returns a Queue that contains all the valid solutions to the problem.
     *
     * @return a Queue that contains all the valid solutions to the problem
     */
    public static Queue<Solution> generateAndTest(){
        Queue<Solution> queue= new LinkedQueue<Solution>();

        Cube[] cubes = new Cube[4];
        cubes[0] = new Cube(new Cube.Color[]{Cube.Color.BLUE, Cube.Color.GREEN, Cube.Color.WHITE, Cube.Color.GREEN, Cube.Color.BLUE, Cube.Color.RED});
        cubes[1] = new Cube(new Cube.Color[]{Cube.Color.WHITE, Cube.Color.GREEN, Cube.Color.BLUE, Cube.Color.WHITE, Cube.Color.RED, Cube.Color.RED});
        cubes[2] = new Cube(new Cube.Color[]{Cube.Color.GREEN, Cube.Color.WHITE, Cube.Color.RED, Cube.Color.BLUE, Cube.Color.RED, Cube.Color.RED});
        cubes[3] = new Cube(new Cube.Color[]{Cube.Color.BLUE, Cube.Color.RED, Cube.Color.GREEN, Cube.Color.GREEN, Cube.Color.WHITE, Cube.Color.WHITE});

        Solution s = new Solution(cubes);
        s.resetNumberOfCalls();
        int num =0;
        Queue<Solution> temp = new LinkedQueue<Solution>();
        while(s.getCube(3).hasNext()){
            s.getCube(3).next();
            s.getCube(2).reset();
            while(s.getCube(2).hasNext()){
                s.getCube(2).next();
                s.getCube(1).reset();
                while(s.getCube(1).hasNext()){
                    s.getCube(1).next();
                    s.getCube(0).reset();
                    while(s.getCube(0).hasNext()){
                        s.getCube(0).next();
                        if(s.isValid()){
                            boolean isThisInTheQueue = false;
                            Cube[] correctCubes = new Cube[s.size()];
                            for(int i = 0; i < cubes.length; i++){
                                correctCubes[i] = s.getCube(i).copy();
                            }
                            queue.enqueue(new Solution(correctCubes));
                        }
                    }
                }
            }
        }
        System.out.println(s.getNumberOfCalls());
        return queue;
    }

    /**
     * finds all the solutions to the instant insanity problem using the “breadth-first-search” algorithm presented in
     * class. It returns a Queue that contains all the valid solutions to the problem.
     *
     * @return a Queue that contains all the valid solutions to the problem
     */
    public static Queue<Solution> breadthFirstSearch(){
        Queue<Solution> open = new LinkedQueue<Solution>();
        Queue<Solution> solutions = new LinkedQueue<Solution>();

        int numSteps = 0;

        Cube[] cubes = new Cube[4];
        Cube cube0 = new Cube(new Cube.Color[]{Cube.Color.BLUE, Cube.Color.GREEN, Cube.Color.WHITE, Cube.Color.GREEN, Cube.Color.BLUE, Cube.Color.RED});
        Cube cube1 = new Cube(new Cube.Color[]{Cube.Color.WHITE, Cube.Color.GREEN, Cube.Color.BLUE, Cube.Color.WHITE, Cube.Color.RED, Cube.Color.RED});
        Cube cube2 = new Cube(new Cube.Color[]{Cube.Color.GREEN, Cube.Color.WHITE, Cube.Color.RED, Cube.Color.BLUE, Cube.Color.RED, Cube.Color.RED});
        Cube cube3 = new Cube(new Cube.Color[]{Cube.Color.BLUE, Cube.Color.RED, Cube.Color.GREEN, Cube.Color.GREEN, Cube.Color.WHITE, Cube.Color.WHITE});

        cubes[0] = cube0;
        cubes[1] = cube1;
        cubes[2] = cube2;
        cubes[3] = cube3;

        Solution s = new Solution(new Cube[]{cube0.copy()});
        s.resetNumberOfCalls();

        while(s.getCube(0).hasNext()){
            s.getCube(0).next();
            open.enqueue(new Solution(new Cube[]{s.getCube(0).copy()}));
        }

        while(!open.isEmpty()){

            Solution current = open.dequeue();
            current = new Solution(current, cubes[current.size()]);
            while(current.getCube(current.size()-1).hasNext()){
                current.getCube(current.size()-1).next();
                if(current.isValid()){
                    Cube[] newCubes = new Cube[current.size()];
                    for(int j = 0; j < current.size(); j++){
                        newCubes[j] = current.getCube(j).copy();
                    }
                    if(current.size() == cubes.length){
                        solutions.enqueue(new Solution(newCubes));
                    }
                    else{
                        open.enqueue(new Solution(newCubes));
                    }
                }
            }
            numSteps += current.getNumberOfCalls();
        }

        System.out.println(numSteps);
        return solutions;
    }

    /**
     * runs the two methods for solving Instant Insanity. Prints the runtime for each method.
     *
     * @param args n/a
     */
    public static void main(String[] args){
        long start, stop;

        System.out.println("generateAndTest:");
        start = System.currentTimeMillis(); // could also use nanoTime
        generateAndTest();
        stop = System.currentTimeMillis();
        long diff = stop - start;
        System.out.println("Elapsed time: " + diff + " milliseconds");

        System.out.println("breadthFirstSearch:");
        start = System.currentTimeMillis ();
        breadthFirstSearch();
        stop = System.currentTimeMillis();
        diff = stop - start;
        System.out.println("Elapsed time: " + diff + " milliseconds");

    }
}
