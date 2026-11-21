package com.gymfit.gymfitmanager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.scene.layout.BorderPane;

import javafx.scene.layout.VBox;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class HelloController {
    //Contenedores
    @FXML
    private BorderPane rootPane;
    @FXML
    private VBox panelTabla;
    @FXML
    private VBox panelError;

    //Tablas
    @FXML
    private TableView<Socio> tablaSocios;
    @FXML
    private TableColumn<Socio, String> colSocio;
    @FXML
    private TableColumn<Socio, String> colCuota;
    @FXML
    private TableColumn<Socio, String> colFecha;
    @FXML
    private TableColumn<Socio, String> colClases;

    //Campos del formulario
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellidos;
    @FXML
    private DatePicker dpFecha;
    @FXML
    private ComboBox<String> cbProvincia;
    @FXML
    private ComboBox<String> cbClases;
    @FXML
    private TextField txtIban;
    @FXML
    private CheckBox chkAutorizar;
    @FXML
    private ToggleGroup grupoSexo;
    @FXML
    private ToggleGroup grupoCuota;

    //Radios
    @FXML
    private RadioButton rbBasica;
    @FXML
    private RadioButton rbPlus;
    @FXML
    private RadioButton rbPremium;
    @FXML
    private RadioButton rbJoven;
    @FXML
    private RadioButton rbSenior;

    //Lista de datos
    private final ObservableList<Socio> listSocios = FXCollections.observableArrayList();



    @FXML
    public void initialize(){
        //Para que la tarjeta de la tabla empiece oculta
        if (panelTabla != null){
            panelTabla.setVisible(false);
            panelTabla.setManaged(false);
        }

        if (panelError !=null) {
            panelError.setVisible(false);
            panelError.setManaged(false);
        }

        //Rellenar los desplegables
        if (cbProvincia != null){
            cbProvincia.getItems().addAll("Sevilla", "Madrid", "Barcelona", "Valencia", "Málaga", "Córdoba", "Cádiz");
        }
        if (cbClases != null){
            cbClases.getItems().addAll("Musculación", "Spinning", "Pilates", "Yoga", "Natación", "Boxeo");
        }

        //Conectar columnas y ejemplos de socios
        if (tablaSocios != null){
            colSocio.setCellValueFactory(cell -> cell.getValue().socioProperty());
            colCuota.setCellValueFactory(cell -> cell.getValue().cuotaProperty());
            colFecha.setCellValueFactory(cell -> cell.getValue().fechaProperty());
            colClases.setCellValueFactory(cell -> cell.getValue().clasesProperty());

            listSocios.addAll(
                    new Socio("Ana Romero Ruiz", "Premium", "15/05/1998", "Pilates"),
                    new Socio("Jose Ortega Lopez", "Basica", "22/11/1995", "Musculación")
            );
            tablaSocios.setItems(listSocios);
        }


    }
    //Mostrar o ocultar tabla de Socios
    @FXML
    void alternarTabla(ActionEvent event){
        boolean estaVisible = panelTabla.isVisible();
        panelTabla.setVisible(!estaVisible);
        panelTabla.setManaged(!estaVisible);
    }

    //Validar y guardar Socio
    @FXML
    void guardarSocio(ActionEvent event) {
        boolean nombreVacio = txtNombre == null || txtNombre.getText().trim().isEmpty();
        boolean apellidosVacio = txtApellidos == null || txtApellidos.getText().trim().isEmpty();
        boolean fechaVacia = dpFecha == null || dpFecha.getValue() == null;
        boolean provinciaVacia = cbProvincia == null || cbProvincia.getValue() == null;
        boolean cuotaVacia = grupoCuota != null && grupoCuota.getSelectedToggle() == null;
        boolean sinAutorizacion = chkAutorizar != null && !chkAutorizar.isSelected();

        String iban = (txtIban != null) ? txtIban.getText().replaceAll("\\s+", "").toUpperCase() : "";
        boolean ibanInvalido = !iban.startsWith("ES") || iban.length() != 24;

        //Si hay error que se muestre el panel de error
        if (nombreVacio || apellidosVacio || fechaVacia || provinciaVacia || cuotaVacia || sinAutorizacion || ibanInvalido){
            if (panelError != null){
                panelError.setVisible(true);
                panelError.setManaged(true);
            }
            return;
        }

        //Si todo esta correcto se oculta el panel error
        if(panelError != null){
            panelError.setVisible(false);
            panelError.setManaged(false);
        }

        //Obterner los datos escritos y añadirlos a la tabla
        String nombreCompleto = txtNombre.getText().trim() + " " + txtApellidos.getText().trim();
        String cuotaElegida = obtenerCuotaSeleccionada();
        String fechaFormateada = dpFecha.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String claseElegida = (cbClases.getValue() != null) ? cbClases.getValue() : "General";

        listSocios.add(new Socio(nombreCompleto, cuotaElegida, fechaFormateada, claseElegida));

        //Mostrar automaticamente la tabla cuando hay un nuevo socio
        panelTabla.setVisible(true);
        panelTabla.setManaged(true);



        Alert exito = new Alert(Alert.AlertType.INFORMATION);
        exito.setTitle("Alta completada");
        exito.setHeaderText(null);
        exito.setContentText("Se ha dado de alta a " + txtNombre.getText() + " " +txtApellidos.getText() + ".");
        exito.showAndWait();

        limpiarCampos();
    }

    private String obtenerCuotaSeleccionada(){
        if (rbBasica != null && rbBasica.isSelected()) return "Básica";
        if (rbPlus != null && rbPlus.isSelected()) return "Plus";
        if (rbPremium != null && rbPremium.isSelected()) return "Premium";
        if (rbJoven != null && rbJoven.isSelected()) return "Joven";
        if (rbSenior != null && rbSenior.isSelected()) return "Senior";
        return "Básica";
    }

    @FXML
    void confirmarBorrado(ActionEvent event) {
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar borrado");
        confirmacion.setHeaderText("¿Seguro de borrar todos los campos?");
        confirmacion.setContentText("Se borran todos los campos y no podrá recuperar los datos");

        Optional<ButtonType> resultado = confirmacion.showAndWait();
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            limpiarCampos();
        }
    }

    private void limpiarCampos(){
        if (txtNombre != null) txtNombre.clear();
        if (txtApellidos != null) txtApellidos.clear();
        if (dpFecha != null) dpFecha.setValue(null);
        if (cbProvincia != null) cbProvincia.setValue(null);
        if (cbClases != null) cbClases.setValue(null);
        if (txtIban != null) txtIban.clear();
        if (chkAutorizar != null) chkAutorizar.setSelected(false);
        if (grupoSexo != null && grupoSexo.getSelectedToggle() != null) {
            grupoSexo.getSelectedToggle().setSelected(false);
        }
        if (grupoCuota != null && grupoCuota.getSelectedToggle() != null) {
            grupoCuota.getSelectedToggle().setSelected(false);
        }
        if (panelError != null){
            panelError.setVisible(false);
            panelError.setManaged(false);
        }

    }

}
