/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author user
 */
public class ImagePreviewer {

    public String chooseImage(java.awt.Component parent) {
        javax.swing.JFileChooser fc = new javax.swing.JFileChooser();
        java.io.File imageDir = new java.io.File("src/Image");
        if (imageDir.exists() && imageDir.isDirectory()) {
            fc.setCurrentDirectory(imageDir);
        }
        fc.setAcceptAllFileFilterUsed(false);
        fc.addChoosableFileFilter(new FileNameExtensionFilter("Images (jpg, jpeg, png)", "jpg", "jpeg", "png"));

        int result = fc.showOpenDialog(parent);
        if (result != javax.swing.JFileChooser.APPROVE_OPTION) {
            return null;
        }

        java.io.File f = fc.getSelectedFile();
        return (f == null) ? null : f.getAbsolutePath();
    }

    public void preview(JLabel label, String path, int w, int h) {
        if (path == null || path.trim().isEmpty()) {
            label.setIcon(null);
            return;
        }

        javax.swing.ImageIcon icon = null;

        // 1) Try as real file path (for images chosen with JFileChooser)
        java.io.File f = new java.io.File(path);
        if (f.exists() && f.isFile()) {
            icon = new javax.swing.ImageIcon(path);
        } else {
            // 2) Try as classpath resource (for paths like "/Image/AMD_Ryzen_5_5600X.jpg")
            java.net.URL url = getClass().getResource(path);
            if (url == null) {
                System.err.println("Image not found (file or resource): " + path);
                label.setIcon(null);
                return;
            }
            icon = new javax.swing.ImageIcon(url);
        }

        java.awt.Image img = icon.getImage();
        java.awt.Image scaled = img.getScaledInstance(w, h, java.awt.Image.SCALE_SMOOTH);
        label.setIcon(new javax.swing.ImageIcon(scaled));
    }

}
