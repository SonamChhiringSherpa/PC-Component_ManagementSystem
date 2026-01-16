/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import javax.swing.table.AbstractTableModel;
import java.util.LinkedList;

/**
 *
 * @author sonamchhiringsherpa
 */
public class RemovedProductTableModel extends AbstractTableModel {

    private final String[] cols = {"Name", "Component Type", "Status", "Quantity", "Price", "Image"};
    private final LinkedList<PCComponent> removed = new LinkedList<>();

    @Override
    public int getRowCount() {
        return removed.size();
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
        return String.class;
    }

    @Override
    public Object getValueAt(int row, int col) {
        PCComponent p = removed.get(row);
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
                return p.getImagePath();
            default:
                return null;
        }
    }

    public void addRemoved(PCComponent p) {
        if (p == null) {
            return;
        }
        removed.add(p);
        fireTableRowsInserted(removed.size() - 1, removed.size() - 1);
    }

    public PCComponent getAt(int row) {
        return removed.get(row);
    }

    public void removeAt(int row) {
        if (row < 0 || row >= removed.size()) {
            return;
        }
        removed.remove(row); // LinkedList.remove(index) [web:224][web:225]
        fireTableRowsDeleted(row, row);
    }
}
