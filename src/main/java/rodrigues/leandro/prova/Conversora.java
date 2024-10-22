package rodrigues.leandro.prova;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

public class Conversora {
    public static Image mecheBrilho(Image image, int brilho) {
        BufferedImage bimg = SwingFXUtils.fromFXImage(image, null);
        WritableRaster raster = bimg.getRaster();
        int[] pixel = new int[4];

        for (int y = 0; y < bimg.getHeight(); y++) {
            for (int x = 0; x < bimg.getWidth(); x++) {
                raster.getPixel(x, y, pixel);
                for (int i = 0; i < 3; i++) {
                    pixel[i] = Math.min(255, Math.max(0, pixel[i] + brilho));
                }
                raster.setPixel(x, y, pixel);
            }
        }

        return SwingFXUtils.toFXImage(bimg, null);
    }

    public static Image mesclaImagem(Image image) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            try {
                BufferedImage bimg = SwingFXUtils.fromFXImage(image, null);
                BufferedImage mescladaImage = ImageIO.read(file);

                if (mescladaImage.getWidth() > bimg.getWidth() || mescladaImage.getHeight() > bimg.getHeight()) {
                    throw new IOException("A imagem que deseja inserir é maior que a já carregada!");
                }

                int offsetX = (bimg.getWidth() - mescladaImage.getWidth()) / 2;
                int offsetY = (bimg.getHeight() - mescladaImage.getHeight()) / 2;

                for (int y = 0; y < mescladaImage.getHeight(); y++) {
                    for (int x = 0; x < mescladaImage.getWidth(); x++) {
                        int[] pixel = mescladaImage.getRaster().getPixel(x, y, (int[]) null);
                        bimg.getRaster().setPixel(x + offsetX, y + offsetY, pixel);
                    }
                }

                return SwingFXUtils.toFXImage(bimg, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return image;
    }
}
