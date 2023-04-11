// Purpose: To create a stack class
package Lab.Stack;

import java.util.ArrayList;

// by default, everything is private
public class Stack<T> {
    private int size;
    private int top;
    private ArrayList<T> stack;

    public Stack(int size) {
        this.size = size;
        this.top = -1;
        this.stack = new ArrayList<T>(size);
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == size - 1;
    }

    public void push(T data) {
        if (isFull()) {
            System.out.println("Stack is full");
            return;
        }
        stack.add(data);
        top++;
    }

    public T pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return null;
        }
        T data = stack.get(top);
        stack.remove(top);
        top--;
        return data;
    }

    public T peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return null;
        }
        return stack.get(top);
    }

    public void print() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.println(stack.get(i));
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>(5);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.print();
        System.out.println("Peek: " + stack.peek());
        System.out.println("Pop: " + stack.pop());
        System.out.println("Peek: " + stack.peek());
        stack.print();
    }


}
