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

public class PCComponentTableModel extends AbstractTableModel {

    private String[] cols = {"Name", "Component Type", "Status", "Quantity", "Price", "Image"};

    private LinkedList<PCComponent> data = new LinkedList<PCComponent>();

    private int thumbW;
    private int thumbH;

    public PCComponentTableModel(int thumbW, int thumbH) {
        this.thumbW = thumbW;
        this.thumbH = thumbH;
    }

    public void setData(LinkedList<PCComponent> newData) {
        if (newData == null) {
            data = new LinkedList<PCComponent>();
        } else {
            data = newData;
        }
        fireTableDataChanged();
    }

    public PCComponent getComponentAt(int modelRow) {
        return data.get(modelRow);
    }

    public int getRowCount() {
        return data.size();
    }

    public int getColumnCount() {
        return cols.length;
    }

    public String getColumnName(int column) {
        return cols[column];
    }

    public Class getColumnClass(int columnIndex) {
        if (columnIndex == 3) {
            return Integer.class;  // Quantity
        }
        if (columnIndex == 4) {
            return Double.class;   // Price
        }
        if (columnIndex == 5) {
            return Icon.class;     // Image column => JTable renders Icon [web:175][web:406]
        }
        return String.class;
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        PCComponent pc = data.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return pc.getName();
            case 1:
                return pc.getType();
            case 2:
                return pc.getStatus();
            case 3:
                return pc.getQuantity();
            case 4:
                return pc.getPrice();
            case 5:
                return makeThumbnail(pc.getImagePath());
            default:
                return null;
        }
    }

    private ImageIcon makeThumbnail(String imagePath) {
        if (imagePath == null) return null;
        if (imagePath.trim().length() == 0) return null;

        ImageIcon icon = new ImageIcon(imagePath);
        Image img = icon.getImage();
        Image scaled = img.getScaledInstance(thumbW, thumbH, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }
}

