/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.awt.Image;
import java.util.LinkedList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author user
 */
public class ProductTableModel extends AbstractTableModel {

    private final String[] cols = {"Name", "Component Type", "Status", "Quantity", "Price", "Image"};
    private LinkedList<PCComponent> data = new LinkedList<PCComponent>();

    private int thumbW;
    private int thumbH;

    public ProductTableModel(int thumbW, int thumbH) {
        this.thumbW = thumbW;
        this.thumbH = thumbH;
    }

    public void setData(LinkedList<PCComponent> list) {
        if (list == null) {
            data = new LinkedList<PCComponent>();
        } else {
            data = list;
        }
        fireTableDataChanged(); // tells JTable to refresh [web:50]
    }

    public PCComponent getAt(int row) {
        return data.get(row);
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return cols.length;
    }

    @Override
    public String getColumnName(int column) {
        return cols[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 3) {
            return Integer.class;
        }
        if (columnIndex == 4) {
            return Double.class;
        }
        if (columnIndex == 5) {
            return Icon.class; // Image column [web:52][web:51]
        }
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int row, int col) {
        PCComponent p = data.get(row);   // use data, not list
        switch (col) {
            case 0:
                return p.getName();
            case 1:
                return p.getType();
            case 2:
                return p.getStatus();
            case 3:
                return p.getQuantity();
            case 4:
                return p.getPrice();
            case 5:
                return makeThumbnail(p.getImagePath());
            default:
                return null;
        }
    }

    private ImageIcon makeThumbnail(String path) {
        if (path == null || path.trim().isEmpty()) {
            return null;
        }

        ImageIcon icon = null;

        // 1) Try as real file path (for products added via Browse)
        java.io.File f = new java.io.File(path);
        if (f.exists() && f.isFile()) {
            icon = new ImageIcon(path);
        } else {
            // 2) Try as classpath resource (for dummy data like "/Image/RTX_3060.jpg")
            java.net.URL url = getClass().getResource(path);
            if (url == null) {
                System.err.println("ProductTableModel image not found: " + path);
                return null;
            }
            icon = new ImageIcon(url);
        }

        Image img = icon.getImage();
        Image scaled = img.getScaledInstance(thumbW, thumbH, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

}
