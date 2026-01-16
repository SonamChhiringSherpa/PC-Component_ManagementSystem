/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author user
 */
public class CartRepository {

    private static final Map<String, CartStack> carts = new HashMap<>();

    public static CartStack getOrCreateCart(String username) {
        if (username == null) {
            return null;
        }

        CartStack cart = carts.get(username);
        if (cart == null) {
            cart = new CartStack(100); // for example, capacity 100
            carts.put(username, cart);
        }
        return cart;
    }
}
