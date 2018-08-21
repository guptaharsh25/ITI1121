public interface Queue<T> {
    public abstract boolean isEmpty();
    public abstract T dequeue();
    public abstract void enqueue(T o);
}
