/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Model.CartItem;
import Model.CartStack;
import Model.PCComponent;
/**
 *
 * @author sonamchhiringsherpa
 */
public class CartController {
    private final CartStack cartStack;

    public CartController(CartStack cartStack) {
        this.cartStack = cartStack;
    }

    public CartStack getCartStack() {
        return cartStack;
    }

    // Add to cart and reduce stock immediately
    public void addToCart(PCComponent selected, int qty) {
        if (selected == null) throw new IllegalArgumentException("No product selected.");
        if (qty <= 0) throw new IllegalArgumentException("Quantity must be > 0.");
        if (selected.getQuantity() < qty) throw new IllegalArgumentException("Not enough stock.");

        // reduce inventory
        selected.setQuantity(selected.getQuantity() - qty);

        // update status
        updateStockStatus(selected);

        // push into stack (LIFO)
        cartStack.push(new CartItem(selected, qty));
    }

    // Update cart quantity for an existing cart row (0 = top of stack)
    public void updateCartQuantityAt(int cartIndex, int newQty) {
        CartItem existing = cartStack.getAt(cartIndex);
        if (existing == null) throw new IllegalArgumentException("Invalid cart item.");

        if (newQty <= 0) throw new IllegalArgumentException("Quantity must be > 0.");

        PCComponent pc = existing.getComponent();
        int oldQty = existing.getQuantity();

        // available to increase = currentStock + oldQty
        int maxAllowed = pc.getQuantity() + oldQty;
        if (newQty > maxAllowed) throw new IllegalArgumentException("Not enough stock.");

        int diff = newQty - oldQty;      // + means user wants more
        int newStock = pc.getQuantity() - diff;  // reduce stock when diff positive

        if (newStock < 0) throw new IllegalArgumentException("Not enough stock.");

        pc.setQuantity(newStock);
        updateStockStatus(pc);

        cartStack.updateQuantityAt(cartIndex, newQty);
    }

    private void updateStockStatus(PCComponent pc) {
        if (pc.getQuantity() <= 0) {
            pc.setQuantity(0);
            pc.setStatus("Out of Stock");
        } else {
            pc.setStatus("Available");
        }
    }
}
