/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Inventory;
import Model.PCComponent;
import java.util.LinkedList;

/**
 *
 * @author user
 */
public class AdminController {

    public LinkedList<PCComponent> getInventory() {
        return Inventory.getAll(); // shared inventory (same list user sees)
    }

    public boolean addProduct(String name,
            String type,
            int qty,
            double price,
            String status,
            String imagePath) {

        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        if (type == null || type.trim().isEmpty() || " ".equals(type)) {
            return false;
        }
        if (qty < 0) {
            return false;
        }
        if (price < 0) {
            return false;
        }

        String finalStatus;
        if (qty > 0) {
            finalStatus = (status == null || status.trim().isEmpty()) ? "Available" : status.trim();
        } else {
            finalStatus = "Out of Stock";
        }

        PCComponent newProduct = new PCComponent(
                name.trim(),
                type.trim(),
                finalStatus,
                qty,
                price,
                imagePath
        );

        Inventory.addComponent(newProduct);
        return true;
    }
}
