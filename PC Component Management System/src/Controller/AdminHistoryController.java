/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Model.AppStore;
import Model.PCComponent;
import java.util.List;
/**
 *
 * @author user
 */
public class AdminHistoryController {
     private final AppStore store = AppStore.getInstance();

    public void recordDeletedProduct(PCComponent pc) {
        store.getDeletedProducts().push(pc);
    }

    public List<PCComponent> getDeletedProductsSnapshot() {
        return store.getDeletedProducts().toList();
    }

    public PCComponent restoreLastDeleted() {
        return store.getDeletedProducts().pop();
}
}