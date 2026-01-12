/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author user
 */
public class CartItem {
    private PCComponent component;
    private int quantity;

    public CartItem(PCComponent component, int quantity) {
        this.component = component;
        this.quantity = quantity;
    }

    public PCComponent getComponent() {
        return component;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return component.getPrice() * quantity;
    }
}
