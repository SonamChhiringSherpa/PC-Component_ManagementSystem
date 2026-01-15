/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.AppStore;
import Model.Order;
import java.util.List;
/**
 *
 * @author user
 */
public class AdminOrderController {
    private final AppStore store = AppStore.getInstance();

    public List<Order> getAllOrdersSnapshot() {
        return store.getOrders().toList();
    }

    public Order findOrderById(int id) {
        return store.getOrders().findById(id);
    }
}
