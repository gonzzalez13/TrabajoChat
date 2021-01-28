package Controlador;

import java.io.IOException;

import Modelo.Conversacion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginTefaController {
	
	@FXML
	private TextField txtNombre;
	
	@FXML
	private Button btnEnviar;
	
	Conversacion nick;
	
	@FXML
	private void IniciarSesion(ActionEvent event) {
		
	try {
		FXMLLoader cargar = new FXMLLoader (getClass().getResource("/interfaz/ChatTefa.fxml"));
		
		Parent root = cargar.load();
		
		ChatTefaController ChatC = cargar.getController();
		
		Scene escena = new Scene(root);
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(escena);
		stage.showAndWait();
		
		String usuario = this.txtNombre.getText();
		this.nick = new Conversacion(usuario);

		
		Stage stageLogin = (Stage) this.btnEnviar.getScene().getWindow();
		stageLogin.close();
		
			
	} catch (IOException e) {
			e.printStackTrace();
	}
		
	}

	public Conversacion getNick() {
		return nick;
	}

	
	

}
