import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;

public class ImageLoad extends Canvas { // La classe ImageLoad est utilisée pour
                                        // permettre l'affichage des images dans les canvas

    Image img;

    public ImageLoad(Image img) { //lors de l'appel du contructeur avec une image en paramètre celle ci
                                //  est récupérée dans une variable de classe "img"
        this.img = img;
    }

    @Override
    public void paint(Graphics g) { //cette fonction permet de "dessiner" l'image à l'endroit indiquer
                                    // à l'aide de la fonctionnalité drawImage de la bibliothèque Graphics
        if (img != null) {
            g.drawImage(img,0,0,this);
        }
    }

    public void setImage(Image img) { // cette fonction permet de renouveler l'image
                                      // sans instancier un nouveau canvas
        this.img = img;
    }
}
