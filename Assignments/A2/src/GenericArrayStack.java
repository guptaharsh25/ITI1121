public class GenericArrayStack<E> implements Stack<E> {
   
   // ADD YOUR INSTANCE VARIABLES HERE
    private E [] elements;
    private int top;

   // Constructor
    @SuppressWarnings("unchecked")
    public GenericArrayStack( int capacity ) {
        
    // ADD YOU CODE HERE
        this.elements = (E[]) new Object[capacity];
        this.top = 0;
    }

    // Returns true if this ArrayStack is empty
    public boolean isEmpty() {
        
    // ADD YOU CODE HERE
        return this.top == 0;

    }

    public void push( E elem ) {
        
    // ADD YOU CODE HERE
        this.elements[this.top++] = elem;

    }
    public E pop() {
        
    // ADD YOU CODE HERE
        E answer = this.elements[--this.top];
        this.elements[this.top] = null;

        return answer;

    }

    public E peek() {
        
    // ADD YOU CODE HERE
        return this.elements[this.top-1];

    }
}
