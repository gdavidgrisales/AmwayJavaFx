package david.apps.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.controlsfx.dialog.Dialogs;

import david.apps.model.Cliente;
import david.apps.util.DateUtil;



/**
 * Dialog to edit details of a person.
 * 
 * @author Marco Jakob
 */
public class ClienteEditDialogController {

    @FXML
    private TextField nombreField;
    @FXML
    private TextField apellidoField;
    @FXML
    private TextField telefonoField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField fechaCompraField;
    @FXML
    private TextField productoField;


    private Stage dialogStage;
    private Cliente cliente;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the person to be edited in the dialog.
     * 
     * @param person
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;

        nombreField.setText(cliente.getNombre());
        apellidoField.setText(cliente.getApellido());
        telefonoField.setText(Integer.toString(cliente.getTelefono()));
        emailField.setText(cliente.getEmail());
        fechaCompraField.setText(DateUtil.format(cliente.getFechaCompra()));
        productoField.setText(cliente.getProducto());
     
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
        	
        	cliente.setNombre(nombreField.getText());
        	cliente.setApellido(apellidoField.getText());
        	cliente.setTelefono(Integer.parseInt(telefonoField.getText()));
        	cliente.setEmail(emailField.getText());
        	cliente.setFechaCompra(DateUtil.parse(fechaCompraField.getText()));
        	cliente.setProducto(productoField.getText());

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (nombreField.getText() == null || nombreField.getText().length() == 0) {
            errorMessage += "No valid first name!\n"; 
        }
        if (apellidoField.getText() == null || apellidoField.getText().length() == 0) {
            errorMessage += "No valid last name!\n"; 
        }
        if (telefonoField.getText() == null || telefonoField.getText().length() == 0) {
            errorMessage += "No valid street!\n"; 
        }

        if (emailField.getText() == null || emailField.getText().length() == 0) {
            errorMessage += "No valid postal code!\n"; 
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(telefonoField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Telefono no valido (debe ser numero)!\n"; 
            }
        }

        if (emailField.getText() == null || emailField.getText().length() == 0) {
            errorMessage += "Email no valido!\n"; 
        }

        if (fechaCompraField.getText() == null || fechaCompraField.getText().length() == 0) {
            errorMessage += "No valid birthday!\n";
        } else {
            if (!DateUtil.validDate(fechaCompraField.getText())) {
                errorMessage += "Fecha de compra no valida. Use el formato dd.mm.aaaa!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Dialogs.create()
                .title("Campos Incorrectos")
                .masthead("Por favor ingrese correctamente los campos")
                .message(errorMessage)
                .showError();
            return false;
        }
    }
}