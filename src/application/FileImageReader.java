package application;

import model.Image;
import view.ImageReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import javax.imageio.ImageIO;

public class FileImageReader implements ImageReader{
    private File[] files;
    private File folder;
    private final static String[] extensions = {".png",".jpg",". jpeg"};
    
    public FileImageReader(String path) {
        this(new File(path));
    }

    public FileImageReader(File folder) {
        this.files = folder.listFiles(imageType());
    }

    private FilenameFilter imageType() {
        return new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                for (String extension : extensions) 
                    if (name.toLowerCase().endsWith(extension)) return true;
                return false;
            }
        };
    }

    @Override
    public Image read() {
        return imageAt(0);
    }

    private Image imageAt(final int index) {
        return new Image() {
            @Override
            public <T> T bitmap() {
                try {
                   return (T) ImageIO.read(files[index]);
                } catch (IOException ex) {
                    return null;
                }
            }

            @Override
            public Image next() {
                return imageAt((index==files.length-1) ? index:index+1);
            }

            @Override
            public Image prev() {
                return imageAt((index==0)? index:index-1);
            }
        };
    }
    
    
    
    
    
}
