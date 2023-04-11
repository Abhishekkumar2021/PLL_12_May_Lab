package Lab.Queue;

import java.util.ArrayList;

public class Queue<T> {
    private ArrayList<T> queue;
    private int size;
    private int front;
    private int rear;

    public Queue(int size) {
        this.size = size;
        this.front = -1;
        this.rear = -1;
        this.queue = new ArrayList<T>(size);
    }

    public boolean isEmpty() {
        return queue.size() == 0;
        // return front == -1 && rear == -1;
    }

    public boolean isFull() {
        return queue.size() == size;
        // return rear == size - 1;
    }

    public void enqueue(T data) {
        if (isFull()) {
            System.out.println("Queue is full");
            return;
        }
        if (isEmpty()) {
            front = 0;
        }
        rear++;
        queue.add(data);
    }

    public T dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        }
        T data = queue.get(0);
        queue.remove(0);
        if (front == rear) {
            front = -1;
            rear = -1;
        } else {
            front++;
        }
        return data;
    }

    public T peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        }
        return queue.get(0);
    }

    public void print() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        for(int i=0; i<queue.size(); i++) {
            System.out.println(queue.get(i));
        }
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<Integer>(5);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.print();
        System.out.println("Dequeue: " + queue.dequeue());
        System.out.println("Dequeue: " + queue.dequeue());
        queue.print();
        queue.enqueue(6);
        queue.enqueue(7);
        queue.print();
        System.out.println("Dequeue: " + queue.dequeue());
        System.out.println("Dequeue: " + queue.dequeue());
        System.out.println("Dequeue: " + queue.dequeue());
        System.out.println("Dequeue: " + queue.dequeue());
        queue.print();
    }
}
