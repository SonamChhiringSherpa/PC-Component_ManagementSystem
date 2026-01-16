/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.*;
import java.util.LinkedList;

/**
 *
 * @author user
 */
public class UserController {

    public UserController() {
        // Ensure inventory has dummy data before showing product table
        Inventory.initDummyDataIfEmpty();
    }

    /**
     * Returns shared inventory list (Admin will use same list later).
     */
    public LinkedList<PCComponent> getInventory() {
        return Inventory.getAll();
    }

    public CartStack getCart() {
        String u = Model.UserSession.getUsername();
        return Model.CartRepository.getOrCreateCart(u);
    }

    public void addToCart(PCComponent selected) {
        CartStack cart = getCart();
        if (cart == null) {
            return;
        }
        if (selected == null) {
            return;
        }

        int index = cart.indexOfProductName(selected.getName());
        if (index != -1) {
            CartItem existing = cart.getAt(index);
            existing.setQty(existing.getQty() + 1);
            return;
        }

        cart.push(new CartItem(selected, 1));            // MUST push here
    }

    public boolean addToCartAndDecreaseStock(PCComponent selected, int qty) {
        CartStack cart = getCart();
        if (cart == null) {
            return false;
        }
        if (selected == null || qty <= 0) {
            return false;
        }

        // correct stock check
        if (selected.getQuantity() < qty) {
            return false;
        }

        // add/update cart
        int idx = cart.indexOfProductName(selected.getName());
        if (idx >= 0) {
            CartItem existing = cart.getAt(idx);
            existing.setQty(existing.getQty() + qty);
        } else {
            cart.push(new CartItem(selected, qty));
        }

        // decrease quantity
        selected.setQuantity(selected.getQuantity() - qty);

        if (selected.getQuantity() <= 0) {
            selected.setQuantity(0);
            selected.setStatus("Out of Stock");
        } else {
            selected.setStatus("Available");
        }

        return true;
    }

    /**
     * Linear search by name (case-insensitive). Returns a new LinkedList
     * containing only matches. This is linear search as requested. [web:54]
     */
    public LinkedList<PCComponent> searchByNameLinear(String text) {
        LinkedList<PCComponent> all = Inventory.getAll();
        LinkedList<PCComponent> result = new LinkedList<PCComponent>();

        if (text == null) {
            return result;
        }
        String q = text.trim().toLowerCase();
        if (q.isEmpty()) {
            return result;
        }

        for (PCComponent pc : all) {  // linear scan
            String name = pc.getName() == null ? "" : pc.getName().toLowerCase();
            if (name.contains(q)) {
                result.add(pc);
            }
        }
        return result;
    }

    /**
     * Filter by component type. If type is " " then return full list.
     */
    public LinkedList<PCComponent> filterByType(String type) {
        LinkedList<PCComponent> all = Inventory.getAll();
        if (type == null || type.trim().isEmpty() || " ".equals(type)) {
            return all; // show all
        }

        LinkedList<PCComponent> result = new LinkedList<PCComponent>();
        for (PCComponent pc : all) {
            if (pc.getType().equalsIgnoreCase(type)) {
                result.add(pc);
            }
        }
        return result;
    }

    /**
     * Sort by name (bubble sort) without using built-in sort. Returns a NEW
     * LinkedList (keeps original inventory unchanged).
     */
    public LinkedList<PCComponent> sortByName() {
        LinkedList<PCComponent> copy = copyList(Inventory.getAll());

        int n = copy.size();
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                PCComponent a = copy.get(j);
                PCComponent b = copy.get(j + 1);
                if (a.getName().compareToIgnoreCase(b.getName()) > 0) {
                    copy.set(j, b);
                    copy.set(j + 1, a);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
        return copy;
    }

    /**
     * Sort by price (bubble sort) without using built-in sort. Returns a NEW
     * LinkedList (keeps original inventory unchanged).
     */
    public LinkedList<PCComponent> sortByPrice() {
        LinkedList<PCComponent> copy = copyList(Inventory.getAll());

        int n = copy.size();
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                PCComponent a = copy.get(j);
                PCComponent b = copy.get(j + 1);
                if (a.getPrice() > b.getPrice()) {
                    copy.set(j, b);
                    copy.set(j + 1, a);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
        return copy;
    }

    // Helper: manual copy list
    private LinkedList<PCComponent> copyList(LinkedList<PCComponent> original) {
        LinkedList<PCComponent> copy = new LinkedList<PCComponent>();
        for (PCComponent pc : original) {
            copy.add(pc);
        }
        return copy;
    }

    public boolean updateCartItemQty(int cartIndex, int newQty) {
        CartStack cart = getCart();
        if (cart == null) {
            return false; // or return; depending on method
        }
        CartItem item = cart.getAt(cartIndex);
        if (item == null) {
            return false;
        }

        PCComponent p = item.getProduct();
        int oldQty = item.getQty();
        if (newQty < 0) {
            return false;
        }

        int diff = newQty - oldQty;

        if (diff > 0) {
            // Need more stock
            if (p.getQuantity() < diff) {
                return false;   // not enough inventory
            }
            p.setQuantity(p.getQuantity() - diff);
        } else if (diff < 0) {
            // Returning stock back to inventory
            p.setQuantity(p.getQuantity() + (-diff));
        }

        // Update/remove cart item
        if (newQty == 0) {
            cart.removeAt(cartIndex);
        } else {
            item.setQty(newQty);
        }

        // Update status based on inventory
        if (p.getQuantity() <= 0) {
            p.setQuantity(0);
            p.setStatus("Out of Stock");
        } else {
            p.setStatus("Available");
        }

        return true;
    }
    private static int nextOrderId = 1;

    public Model.Order placeOrder(String location) {
        CartStack cart = getCart();
        if (cart == null) {
            return null;
        }
        if (location == null) {
            return null;
        }
        location = location.trim();
        if (location.length() == 0) {
            return null;
        }

        if (cart.size() == 0) {
            return null;
        }

        String date = java.time.LocalDate.now().toString();

        Model.OrderItemList list = new Model.OrderItemList(cart.size());
        double total = 0.0;

        int i;
        for (i = 0; i < cart.size(); i++) {
            Model.CartItem ci = cart.getAt(i);
            if (ci == null) {
                continue;
            }

            Model.PCComponent p = ci.getProduct();

            Model.PCComponent snap = new Model.PCComponent(
                    p.getName(), p.getType(), p.getStatus(),
                    ci.getQty(), p.getPrice(), p.getImagePath()
            );

            Model.OrderItem oi = new Model.OrderItem(snap, ci.getQty());
            list.add(oi);
            total = total + oi.getTotal();
        }

        int orderId = nextOrderId;
        nextOrderId++;

        String username = Model.UserSession.getUsername();
        if (username == null || username.trim().length() == 0) {
            return null;
        }

// create order with username
        Model.Order order = new Model.Order(orderId, date, location, "Pending", total, list, username);
        Model.OrderArrayQueue.enqueue(order);

        while (!cart.isEmpty()) {
            cart.pop();
        }

        return order;
    }

}
