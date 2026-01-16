/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class OrderArrayQueue {

    private static Order[] data = new Order[200]; // capacity
    private static int front = 0;
    private static int rear = 0;
    private static int size = 0;

    public static boolean isEmpty() {
        return size == 0;
    }

    public static boolean isFull() {
        return size == data.length;
    }

    public static int size() {
        return size;
    }

    public static void enqueue(Order o) {
        if (o == null) {
            return;
        }
        if (isFull()) {
            return;
        }

        data[rear] = o;
        rear = (rear + 1) % data.length;
        size++;
    }

    public static Order dequeue() {
        if (isEmpty()) {
            return null;
        }

        Order o = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        return o;
    }

    // For JTable display: access by index without removing
    public static Order getAt(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int realIndex = (front + index) % data.length;
        return data[realIndex];
    }
    public static void removeAt(int index) {
    if (index < 0 || index >= size) return;

    // compute real index in circular buffer
    int realIndex = (front + index) % data.length;

    // shift elements between realIndex and rear-1 one step left in circular fashion
    for (int i = realIndex; i != rear; i = (i + 1) % data.length) {
        int next = (i + 1) % data.length;
        data[i] = data[next];
        if (next == rear) {
            break;
        }
    }

    // clear last element and update rear/size
    if (rear == 0) {
        rear = data.length - 1;
    } else {
        rear = rear - 1;
    }
    data[rear] = null;
    size--;
}

}
