/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FrameManager;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Andrés
 */
public class ImageFrameManagerUtil {
    
    public static JPanel createPanelWithImage(String rutaImagen) {
        Image imagenFondo = new ImageIcon(ImageFrameManagerUtil.class.getResource(rutaImagen)).getImage();

        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
            }
        };
    }
}
