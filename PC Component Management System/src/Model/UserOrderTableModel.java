/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author user
 */
public class UserOrderTableModel extends AbstractTableModel {
    private final String[] cols = {"OrderId", "Date", "Location", "Status", "Total Amount"};

    // visible row -> real index in global queue
    private int[] visibleIndex = new int[500];
    private int visibleCount = 0;

    private void rebuild() {
        visibleCount = 0;

        String currentUser = UserSession.getUsername();
        int n = OrderArrayQueue.size();

        int i;
        for (i = 0; i < n; i++) {
            Order o = OrderArrayQueue.getAt(i);
            if (o == null) continue;

            if (currentUser != null && currentUser.equalsIgnoreCase(o.getUsername())) {
                if (visibleCount < visibleIndex.length) {
                    visibleIndex[visibleCount] = i;
                    visibleCount++;
                }
            }
        }
    }

    @Override
    public int getRowCount() {
        rebuild();
        return visibleCount;
    }

    @Override
    public int getColumnCount() { return cols.length; }

    @Override
    public String getColumnName(int col) { return cols[col]; }

    @Override
    public Class<?> getColumnClass(int col) {
        if (col == 0) return Integer.class;
        if (col == 4) return Double.class;
        return String.class;
    }

    @Override
    public Object getValueAt(int row, int col) {
        rebuild();
        if (row < 0 || row >= visibleCount) return null;

        Order o = OrderArrayQueue.getAt(visibleIndex[row]);
        if (o == null) return null;

        if (col == 0) return o.getOrderId();
        if (col == 1) return o.getDate();
        if (col == 2) return o.getLocation();
        if (col == 3) return o.getStatus();
        if (col == 4) return o.getTotalAmount();
        return null;
    }

    // IMPORTANT for View Order Detail button
    public Order getOrderAtRow(int row) {
        rebuild();
        if (row < 0 || row >= visibleCount) return null;
        return OrderArrayQueue.getAt(visibleIndex[row]);
    }

    public void refresh() {
        fireTableDataChanged(); // makes JTable refresh [web:237]
    }
}
