package rodrigues.leandro.prova;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.awt.image.BufferedImage;
import java.io.File;

public class MainController {
    @FXML
    public ImageView imageView;
    @FXML
    public Slider brightnessSlider;

    private Image imagemOriginal;

    public void onAbrir(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        File diretorioInicial = new File("D://FERRMENTAS COMPUTACIONAIS II//imagens");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Todas imagens", "*.png", "*.jpg", "*.jpeg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        if (diretorioInicial.exists() && diretorioInicial.isDirectory())
            fileChooser.setInitialDirectory(diretorioInicial);
        else
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            BufferedImage bimg;
            Image image;
            image = new Image(file.toURI().toString());
            bimg = SwingFXUtils.fromFXImage(image, null);
            imageView.setImage(image);
            imageView.setFitHeight(image.getHeight());
            imageView.setFitWidth(image.getWidth());
            imagemOriginal = SwingFXUtils.toFXImage(bimg, null);
        }
    }

    public void onSair(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void onBrilho(ActionEvent actionEvent) {
        int brilho = (int) brightnessSlider.getValue();
        Image image = Conversora.mecheBrilho(imagemOriginal, brilho);
        imageView.setImage(image);
    }

    public void onMesclar(ActionEvent actionEvent) {
        Image image = imageView.getImage();
        imagemOriginal = imageView.getImage();
        image = Conversora.mesclaImagem(image);
        imageView.setImage(image);
    }

    public void onSliderChange() {
        int brilho = (int) brightnessSlider.getValue();
        Image image = Conversora.mecheBrilho(imagemOriginal, brilho);
        imageView.setImage(image);
    }
}
