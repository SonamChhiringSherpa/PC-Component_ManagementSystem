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
public class CartStack {

    private static class Node {

        CartItem item;
        Node next;

        Node(CartItem item) {
            this.item = item;
        }
    }

    private Node top;
    private int size;

    public void push(CartItem item) {
        Node n = new Node(item);
        n.next = top;
        top = n;
        size++;
    }

    public CartItem pop() {
        if (top == null) {
            return null;
        }

        CartItem item = top.item;
        top = top.next;
        size--;
        return item;
    }

    public CartItem peek() {
        if (top == null) {
            return null;
        }
        return top.item;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return size;
    }

    // Used to display items in JTable (top -> bottom)
    public List<CartItem> toList() {
        ArrayList<CartItem> out = new ArrayList<CartItem>();
        Node cur = top;
        while (cur != null) {
            out.add(cur.item);
            cur = cur.next;
        }
        return out;
    }

    public CartItem getAt(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        Node cur = top;
        int i = 0;
        while (cur != null) {
            if (i == index) {
                return cur.item;
            }
            cur = cur.next;
            i++;
        }
        return null;
    }

    public boolean updateQuantityAt(int index, int newQty) {
        if (index < 0 || index >= size) {
            return false;
        }

        Node cur = top;
        int i = 0;
        while (cur != null) {
            if (i == index) {
                // CartItem in my earlier version had no setter; easiest is replace object:
                PCComponent pc = cur.item.getComponent();
                cur.item = new CartItem(pc, newQty);
                return true;
            }
            cur = cur.next;
            i++;
        }
        return false;
    }

}
