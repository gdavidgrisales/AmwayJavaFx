package david.apps.view;

import org.controlsfx.dialog.Dialogs;

import david.apps.MainApp;
import david.apps.model.Cliente;
import david.apps.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ClienteOverviewController {
	
	@FXML
	private TableView<Cliente> clienteTable;
	@FXML
	private TableColumn<Cliente, String> nombreColumna;
	@FXML
	private TableColumn<Cliente, String> apellidoColumna;
	
	@FXML
	private Label nombreLabel;
	@FXML
	private Label apellidoLabel;
	@FXML
	private Label telefonoLabel;
	@FXML
	private Label emailLabel;
	@FXML
	private Label fechaCompraLabel;
	@FXML
	private Label productoLabel;
	
	private MainApp mainApp;
	
	public ClienteOverviewController() {
		
	}
	
	@FXML
	private void initialize() {
		
		 nombreColumna.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
		 apellidoColumna.setCellValueFactory(cellData -> cellData.getValue().apellidoProperty());
		 
		 // Clear person details.
		 showPersonDetails(null);

		 // Listen for selection changes and show the person details when changed.
		clienteTable.getSelectionModel().selectedItemProperty().addListener(
		            (observable, oldValue, newValue) -> showPersonDetails(newValue));
	}
	
	 /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        clienteTable.setItems(mainApp.getClienteData());
    }
    
    /**
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared.
     * 
     * @param cliente the cliente or null
     */
    private void showPersonDetails(Cliente cliente) {
        if (cliente != null) {
            // Fill the labels with info from the person object.
            nombreLabel.setText(cliente.getNombre());
            apellidoLabel.setText(cliente.getApellido());
            telefonoLabel.setText(Integer.toString(cliente.getTelefono()));
            emailLabel.setText(cliente.getEmail());
            productoLabel.setText(cliente.getProducto());
            fechaCompraLabel.setText(DateUtil.format(cliente.getFechaCompra()));
        
        } else {
            // Person is null, remove all the text.
        	
        	nombreLabel.setText("");
        	apellidoLabel.setText("");
        	telefonoLabel.setText("");
        	emailLabel.setText("");
        	productoLabel.setText("");
        	
        }
    }

    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeletePerson() {
    	int selectedIndex = clienteTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            clienteTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Dialogs.create()
                .title("Sin Seleccion")
                .masthead("Ningun cliente seleccionado")
                .message("Por favor seleccione un cliente en la tabla.")
                .showWarning();
        }
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewPerson() {
        Cliente tempPerson = new Cliente();
        boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
        if (okClicked) {
            mainApp.getClienteData().add(tempPerson);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditPerson() {
        Cliente selectedPerson = clienteTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                showPersonDetails(selectedPerson);
            }

        } else {
            // Nothing selected.
            Dialogs.create()
                .title("No Selection")
                .masthead("No Person Selected")
                .message("Please select a person in the table.")
                .showWarning();
        }
    }
	

}
