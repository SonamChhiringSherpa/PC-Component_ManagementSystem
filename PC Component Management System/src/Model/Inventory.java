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
public class Inventory {

    /**
     * Shared inventory list. Admin will later add/remove/update products in
     * this SAME list.
     */
    // One shared list for the whole program (Admin + User).
    private static final LinkedList<PCComponent> components = new LinkedList<>();

    public static LinkedList<PCComponent> getAll() {
        return components;
    }

    public static void addComponent(PCComponent c) {
        if (c != null) {
            components.add(c);
        }
    }

    //Loads dummy data only once
    public static void initDummyDataIfEmpty() {
        if (!components.isEmpty()) {
            return;
        }
        //CPUs
        components.add(new PCComponent("Ryzen 5 5600X", "CPU", "Available", 2, 160.0, "/Image/AMD_Ryzen_5_5600X.jpg"));
        components.add(new PCComponent("Ryzen 7 5800X", "CPU", "Available", 3, 230.0, "/Image/Ryzen_7_5800X.jpg"));
        components.add(new PCComponent("Intel Core i5-12400F", "CPU", "Available", 5, 170.0, "/Image/Intel_Core_i5-12400F.jpg"));
        components.add(new PCComponent("Intel Core i7-12700K", "CPU", "Available", 2, 300.0, "/Image/Intel_Core_i7-12700K.jpg"));

        // GPUs
        components.add(new PCComponent("RTX 3060", "GPU", "Available", 3, 300.0, "/Image/RTX_3060.jpg"));
        components.add(new PCComponent("RTX 3070", "GPU", "Available", 2, 420.0, "/Image/RTX_3070.jpg"));
        components.add(new PCComponent("RTX 4060", "GPU", "Available", 4, 330.0, "/Image/RTX_4060.jpg"));
        components.add(new PCComponent("RX 6700 XT", "GPU", "Available", 2, 320.0, "/Image/RX_6700_XT.jpg"));

        // Motherboards
        components.add(new PCComponent("B550M", "Motherboard", "Available", 4, 120.0, "/Image/B550M.jpg"));
        components.add(new PCComponent("B660M", "Motherboard", "Available", 6, 130.0, "/Image/B660M.jpg"));
        components.add(new PCComponent("X570 ATX", "Motherboard", "Available", 2, 200.0, "/Image/X570_ATX.jpg"));
        components.add(new PCComponent("Z690 ATX", "Motherboard", "Available", 3, 210.0, "/Image/Z690_ATX.jpg"));

        // RAM
        components.add(new PCComponent("DDR4 3200MHz 16GB", "RAM", "Available", 7, 55.0, "/Image/DDR4_16GB.jpg"));
        components.add(new PCComponent("DDR4 3200MHz 32GB", "RAM", "Available", 5, 95.0, "/Image/DDR4_32GB.jpg"));
        components.add(new PCComponent("DDR5 5200MHz 16GB", "RAM", "Available", 4, 75.0, "/Image/DDR5_16GB.jpg"));
        components.add(new PCComponent("DDR5 5600MHz 32GB", "RAM", "Available", 3, 140.0, "/Image/DDR5_32GB.jpg"));

        // Power Supply Units
        components.add(new PCComponent("650W PSU Bronze", "PSU", "Available", 10, 75.0, "/Image/650W_PSU_BRONZE.jpg"));
        components.add(new PCComponent("750W PSU Gold", "PSU", "Available", 6, 110.0, "/Image/750W_PSU_GOLD.jpg"));
        components.add(new PCComponent("850W PSU Gold", "PSU", "Available", 4, 140.0, "/Image/850W_PSU_GOLD.jpg"));
        components.add(new PCComponent("1000W PSU Platinum", "PSU", "Available", 2, 200.0, "/Image/1000W_PSU_PLATINUM.jpg"));
    }

}
