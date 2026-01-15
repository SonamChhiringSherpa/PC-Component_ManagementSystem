/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author user
 */
public class AppStore {
    private static final AppStore INSTANCE = new AppStore();

    private final OrderQueue orders = new OrderQueue();
    private final DeleteProductStack deletedProducts = new DeleteProductStack();

    private AppStore() {}

    public static AppStore getInstance() { return INSTANCE; }

    public OrderQueue getOrders() { return orders; }
    public DeleteProductStack getDeletedProducts() { return deletedProducts; }
}
