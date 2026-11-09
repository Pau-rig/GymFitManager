module com.gymfit.gymfitmanager {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.gymfit.gymfitmanager to javafx.fxml;
    exports com.gymfit.gymfitmanager;
}