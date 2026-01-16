/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.LinkedList;

/**
 *
 * @author sonamchhiringsherpa
 */
public class OrderHistoryStore {
    private static final LinkedList<Order> history = new LinkedList<>();

    public static void add(Order o) {
        if (o != null) {
            history.add(o);
        }
    }

    public static LinkedList<Order> getAll() {
        return history;
    }

    public static int size() {
        return history.size();
    }

    public static Order getAt(int idx) {
        if (idx < 0 || idx >= history.size()) return null;
        return history.get(idx);
    }
}
