/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author user
 */
public class PCComponent {
    private String name;
    private String type;
    private String status;
    private double price;
    private String imagePath;   // path to image file

    public PCComponent(String name, String type, String status, double price, String imagePath) {
        this.name = name;
        this.type = type;
        this.status = status;
        this.price = price;
        this.imagePath = imagePath;
    }

    public String getName() { return name; }
    public String getType() { return type; }
    public String getStatus() { return status; }
    public double getPrice() { return price; }
    public String getImagePath() { return imagePath; }

    public void setName(String name) { this.name = name; }
    public void setType(String type) { this.type = type; }
    public void setStatus(String status) { this.status = status; }
    public void setPrice(double price) { this.price = price; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
}

