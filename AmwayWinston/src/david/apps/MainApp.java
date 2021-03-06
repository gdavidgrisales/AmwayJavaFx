package david.apps;

import java.io.IOException;

import david.apps.model.Cliente;
import david.apps.view.ClienteEditDialogController;
import david.apps.view.ClienteOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {


	private Stage primaryStage;
	private BorderPane rootLayout;
	
	 // ... AFTER THE OTHER VARIABLES ...

    /**
     * The data as an observable list of Persons.
     */
    private ObservableList<Cliente> clienteData = FXCollections.observableArrayList();

    /**
     * Constructor
     */
    public MainApp() {
        // Add some sample data
        clienteData.add(new Cliente("David", "Grisales"));
        
    }

    /**
     * Returns the data as an observable list of Persons. 
     * @return
     */
    public ObservableList<Cliente> getClienteData() {
        return clienteData;
    }

    // ... THE REST OF THE CLASS ...
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage=primaryStage;
		this.primaryStage.setTitle("AmwayWinston");
		
		initRootLayout();
		
		showPersonOverview();
		
	}
	
	
	public void initRootLayout() {
		try {
			  // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Shows the person overview inside the root layout.
	 */
	public void showPersonOverview() {
	    try {
	        // Load person overview.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("view/ClienteOverview.fxml"));
	        AnchorPane clienteOverview = (AnchorPane) loader.load();

	        // Set person overview into the center of root layout.
	        rootLayout.setCenter(clienteOverview);

	        // Give the controller access to the main app.
	        ClienteOverviewController controller = loader.getController();
	        controller.setMainApp(this);

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
    
    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * Opens a dialog to edit details for the specified person. If the user
	 * clicks OK, the changes are saved into the provided person object and true
	 * is returned.
	 * 
	 * @param person the person object to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
	public boolean showPersonEditDialog(Cliente cliente) {
	    try {
	        // Load the fxml file and create a new stage for the popup dialog.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("view/ClienteEditDialog.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();

	        // Create the dialog Stage.
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Editar Cliente");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(primaryStage);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);

	        // Set the person into the controller.
	        ClienteEditDialogController controller = loader.getController();
	        controller.setDialogStage(dialogStage);
	        controller.setCliente(cliente);

	        // Show the dialog and wait until the user closes it
	        dialogStage.showAndWait();

	        return controller.isOkClicked();
	    } catch (IOException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
}
