package application;

import model.Image;
import view.ImageDisplay;
import java.awt.Graphics;
import javax.swing.JPanel;

public class ImagePanel extends JPanel implements ImageDisplay{
    private Image image;

    public ImagePanel(Image image) {
        this.image = image;
    }
    
    @Override
    public Image image() {
        return image;
    }

    @Override
    public void show(Image image) {
        this.image = image;
        this.repaint();
        
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        g.drawImage(image.bitmap(), 0, 0, this);
    }
    
    
    
}
