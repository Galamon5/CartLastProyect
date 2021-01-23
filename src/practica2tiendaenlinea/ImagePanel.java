package practica2tiendaenlinea;

import java.awt.*;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

    private Image img;

    public ImagePanel() {
        load(null);
    }

    public void load(Image img) {
        this.img = img;
        if (img != null) {
            Dimension size = new Dimension(250, 250);
            setPreferredSize(size);
            setMinimumSize(size);
            setMaximumSize(size);
            setSize(size);
            setLayout(null);
        }
        repaint();
    }

    public void paintComponent(Graphics g) {
        if (img != null) {
            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }

}
