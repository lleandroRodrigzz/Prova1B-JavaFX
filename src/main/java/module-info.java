module rodrigues.leandro.prova {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.swing;

    opens rodrigues.leandro.prova to javafx.fxml;
    exports rodrigues.leandro.prova;
}