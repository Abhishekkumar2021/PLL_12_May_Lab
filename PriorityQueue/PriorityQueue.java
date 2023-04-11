package Lab.PriorityQueue;

import java.io.File;
import java.io.FileWriter;

public class PriorityQueue {
    private Pair[] heap;
    private int size;
    private int maxsize;

    public PriorityQueue(int maxsize) {
        this.maxsize = maxsize;
        this.size = 0;
        heap = new Pair[this.maxsize + 1]; 
        heap[0] = new Pair(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    private int parent(int pos) {
        return pos / 2;
    }

    private int leftChild(int pos) {
        return (2 * pos);
    }

    private int rightChild(int pos) {
        return (2 * pos) + 1;
    }

    private boolean isLeaf(int pos) {
        if (pos > (size / 2) && pos <= size) {
            return true;
        }
        return false;
    }

    private void swap(int firstPos, int secondPos) {
        Pair temp = heap[firstPos];
        heap[firstPos] = heap[secondPos];
        heap[secondPos] = temp;
    }

    private void minHeapify(int pos) {
        if(isLeaf(pos)) return;
        Pair left = heap[leftChild(pos)];
        Pair right = heap[rightChild(pos)];
        Pair current = heap[pos];
        
        if((left != null && right != null) && (current.second > left.second || current.second > right.second)) {
            if (left.second < right.second) {
                swap(pos, leftChild(pos));
                minHeapify(leftChild(pos));
            } else {
                swap(pos, rightChild(pos));
                minHeapify(rightChild(pos));
            }
        } else if(left != null && current.second > left.second) {
            swap(pos, leftChild(pos));
            minHeapify(leftChild(pos));
        } else if(right != null && current.second > right.second) {
            swap(pos, rightChild(pos));
            minHeapify(rightChild(pos));
        }
    }

    public void insert(Pair element) {
        heap[++size] = element;
        int current = size;
        while (heap[current].second < heap[parent(current)].second) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    public void showHeap(String filename) {
        File file = new File("./PLL_AS6/" + filename + ".dot");
        if (file.exists())
            file.delete();
        try {
            file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try{
            FileWriter writer = new FileWriter(file);
            writer.write("graph G {\n");
            writer.write("node [shape = circle];\n");
            for (int i = 1; i <= size; i++) {
                writer.write(i + " [label = \"{" + String.valueOf(heap[i].first) + "," + String.valueOf(heap[i].second) + "}\"];\n");
            }
            for (int i = 1; i <=size/2; i++) {
                if (leftChild(i) <= size)
                    writer.write(i + " -- " + leftChild(i) + ";\n");
                if (rightChild(i) <= size)
                    writer.write(i + " -- " + rightChild(i) + ";\n");
            }
            writer.write("}");
            writer.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void minHeap() {
        for (int pos = (size / 2); pos >= 1; pos--) {
            minHeapify(pos);
        }
    }

    public Pair remove() {
        Pair popped = heap[1];
        heap[1] = heap[size--];
        minHeapify(1);
        return popped;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Pair peek() {
        return heap[1];
    }

    public static void main(String[] arg) {
        System.out.println("The Min Heap is ");
        PriorityQueue minHeap = new PriorityQueue(15);
        minHeap.insert(new Pair(5, 1));
        minHeap.insert(new Pair(3, 2));
        minHeap.insert(new Pair(17, 3));
        minHeap.insert(new Pair(10, 4));
        minHeap.insert(new Pair(84, 5));
        minHeap.insert(new Pair(19, 6));
        minHeap.insert(new Pair(6, 7));
        minHeap.insert(new Pair(22, 8));
        minHeap.showHeap("MinHeap");
    }
}
