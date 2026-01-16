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
public class OrderHistoryTableModel extends AbstractTableModel {
private final String[] cols = {"OrderId", "Date", "Location", "Status", "Total Amount"};

    @Override
    public int getRowCount() {
        return OrderHistoryStore.size();
    }

    @Override
    public int getColumnCount() {
        return cols.length;
    }

    @Override
    public String getColumnName(int col) {
        return cols[col];
    }

    @Override
    public Class<?> getColumnClass(int col) {
        if (col == 0) return Integer.class;
        if (col == 4) return Double.class;
        return String.class;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Order o = OrderHistoryStore.getAt(row);
        if (o == null) return null;

        switch (col) {
            case 0: return o.getOrderId();
            case 1: return o.getDate();
            case 2: return o.getLocation();
            case 3: return o.getStatus();
            case 4: return o.getTotalAmount();
            default: return null;
        }
    }

    public void refresh() {
        fireTableDataChanged();
    }

    public void addToHistory(Order o) {
        OrderHistoryStore.add(o);
        refresh();
    }
}
