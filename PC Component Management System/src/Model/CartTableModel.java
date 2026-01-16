/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author user
 */
public class CartTableModel extends AbstractTableModel {

  private final String[] cols = {"Image", "Name", "Qty", "Price", "Total"};
    private CartStack cart;
    private final int thumbW;
    private final int thumbH;

    public CartTableModel(Model.CartStack cart, int thumbW, int thumbH) {
        this.cart = cart;          
        this.thumbW = thumbW;
        this.thumbH = thumbH;
    }

    public void setCart(CartStack cart) {
       this.cart = cart;          
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        return (cart == null) ? 0 : cart.size();
    }

    public void refresh() {
        fireTableDataChanged(); // refresh table when cart changes [web:50]
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
        if (col == 0) {
            return Icon.class;      // show image icon [web:52]
        }
        if (col == 2) {
            return Integer.class;   // qty
        }
        if (col == 3) {
            return Double.class;    // price
        }
        if (col == 4) {
            return Double.class;    // total
        }
        return String.class;                  // name
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int row, int col) {
        if (cart == null) {
            return null;
        }

        CartItem item = cart.getAt(row);
        if (item == null) {
            return null;
        }

        PCComponent p = item.getProduct();

        if (col == 0) {
            return makeThumbnail(p.getImagePath());
        }
        if (col == 1) {
            return p.getName();
        }
        if (col == 2) {
            return item.getQty();
        }
        if (col == 3) {
            return p.getPrice();
        }
        if (col == 4) {
            return item.getTotal();
        }
        return null;
    }

    private ImageIcon makeThumbnail(String path) {
        if (path == null || path.trim().isEmpty()) {
            return null;
        }

        ImageIcon icon = null;

        if (path.startsWith("/")) {
            java.net.URL url = getClass().getResource(path);
            if (url == null) {
                return null;
            }
            icon = new ImageIcon(url);
        } else {
            icon = new ImageIcon(path);
        }

        Image img = icon.getImage();
        Image scaled = img.getScaledInstance(thumbW, thumbH, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }
}
