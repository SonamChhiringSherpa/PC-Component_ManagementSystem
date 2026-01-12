/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.LinkedList;

/**
 *
 * @author user
 */
public class PCComponent {

    private static LinkedList<PCComponent> components = new LinkedList<>();

    public static void initDummyData() {
        if (!components.isEmpty()) {
            return; // s prevents duplicate insertion
        }

        // CPUs
        components.add(new PCComponent("Ryzen 5 5600X", "CPU", "Available", 2, 160.0, "images/cpu1.png"));
        components.add(new PCComponent("Ryzen 7 5800X", "CPU", "Available", 3, 230.0, "images/cpu2.png"));
        components.add(new PCComponent("Intel Core i5-12400F", "CPU", "Available", 5, 170.0, "images/cpu3.png"));
        components.add(new PCComponent("Intel Core i7-12700K", "CPU", "Available", 2, 300.0, "images/cpu4.png"));

        // GPUs
        components.add(new PCComponent("RTX 3060", "GPU", "Available", 3, 300.0, "images/gpu1.png"));
        components.add(new PCComponent("RTX 3070", "GPU", "Available", 2, 420.0, "images/gpu2.png"));
        components.add(new PCComponent("RTX 4060", "GPU", "Available", 4, 330.0, "images/gpu3.png"));
        components.add(new PCComponent("RX 6700 XT", "GPU", "Available", 2, 320.0, "images/gpu4.png"));

        // Motherboards
        components.add(new PCComponent("B550M", "Motherboard", "Available", 4, 120.0, "images/mb1.png"));
        components.add(new PCComponent("B660M", "Motherboard", "Available", 6, 130.0, "images/mb2.png"));
        components.add(new PCComponent("X570 ATX", "Motherboard", "Available", 2, 200.0, "images/mb3.png"));
        components.add(new PCComponent("Z690 ATX", "Motherboard", "Available", 3, 210.0, "images/mb4.png"));

        // RAM
        components.add(new PCComponent("DDR4 3200MHz 16GB", "RAM", "Available", 7, 55.0, "images/ram1.png"));
        components.add(new PCComponent("DDR4 3200MHz 32GB", "RAM", "Available", 5, 95.0, "images/ram2.png"));
        components.add(new PCComponent("DDR5 5200MHz 16GB", "RAM", "Available", 4, 75.0, "images/ram3.png"));
        components.add(new PCComponent("DDR5 5600MHz 32GB", "RAM", "Available", 3, 140.0, "images/ram4.png"));

        // Power Supply Units
        components.add(new PCComponent("650W PSU Bronze", "PSU", "Available", 10, 75.0, "images/psu1.png"));
        components.add(new PCComponent("750W PSU Gold", "PSU", "Available", 6, 110.0, "images/psu2.png"));
        components.add(new PCComponent("850W PSU Gold", "PSU", "Available", 4, 140.0, "images/psu3.png"));
        components.add(new PCComponent("1000W PSU Platinum", "PSU", "Available", 2, 200.0, "images/psu4.png"));

    }

    public static LinkedList<PCComponent> getComponents() {
        return components;
    }

    public static void addComponent(PCComponent component) {
        components.add(component);
    }

    public static void removeComponent(PCComponent component) {
        components.remove(component);
    }
    private String name;
    private String type;
    private String status;
    private int quantity;
    private double price;
    private String imagePath;   // path to image file

    public PCComponent(String name, String type, String status, int quantity, double price, String imagePath) {
        this.name = name;
        this.type = type;
        this.status = status;
        this.quantity = quantity;
        this.price = price;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

}
