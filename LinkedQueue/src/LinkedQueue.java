public class LinkedQueue<T> implements Queue<T> {
    private static class Elem<E>{
        private E value;
        private Elem<E> next;
        private Elem(E value, Elem<E> next){
            this.value = value;
            this.next = next;
        }
    }

    private Elem<T> front;
    private Elem<T> rear;

    public boolean isEmpty(){
        return this.rear == null;
    }

    public void enqueue(T o){
        Elem<T> thisElem = new Elem<T>(o, null);

        if(this.front == null) {
            this.front = thisElem;
        }
        else {
            this.rear.next = thisElem;
        }
        this.rear = thisElem;

    }

    public T dequeue(){
        T saved;
        if(this.front != null) {
            saved = this.front.value;
            this.front = this.front.next;
            if(this.front == this.rear){
                this.rear = null;
            }
            return saved;
        }
        else{
            throw new EmptyQueueException();
        }
    }
}
