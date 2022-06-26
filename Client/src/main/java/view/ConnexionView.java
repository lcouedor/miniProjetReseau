package view;

import java.io.IOException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

public class ConnexionView extends GridPane {

	public ConnexionView() {
		ColumnConstraints col = new ColumnConstraints();
	    col.setPercentWidth(50);
	    getColumnConstraints().addAll(col, col);
	    
	    RowConstraints row = new RowConstraints();
	    row.setPercentHeight(100);
	    getRowConstraints().add(row);
	    
	    
	    VBox vLeft = new VBox();
	    vLeft.setStyle("-fx-border-style: hidden solid hidden hidden; -fx-border-width: 2.5; -fx-border-color: black;");
	    vLeft.setAlignment(Pos.CENTER);
	    vLeft.setSpacing(30);
	    
	    Label lConnexion = new Label("CONNEXION");
	    lConnexion.setStyle("-fx-font-size: 40px;");
	    Label lUser = new Label("Nom d'utilisateur");
	    lUser.setStyle("-fx-font-size: 25px;");
	    TextField tUser = new TextField();
	    tUser.setStyle("-fx-font-size: 15px; -fx-border-color : black;");
	    tUser.setPadding(new Insets(10));
	    VBox vUser = new VBox();
	    vUser.setSpacing(10);
	    vUser.setPadding(new Insets(5,100,5,100)); //Les paddings
	    vUser.getChildren().addAll(lUser, tUser);
	    Label lPass = new Label("Mot de passe");
	    lPass.setStyle("-fx-font-size: 25px;");
	    TextField tPass = new TextField();
	    tPass.setStyle("-fx-font-size: 15px; -fx-border-color : black;");
	    tPass.setPadding(new Insets(10));
	    VBox vPass = new VBox();
	    vPass.setSpacing(10);
	    vPass.setPadding(new Insets(5,100,5,100)); //Les paddings
	    vPass.getChildren().addAll(lPass, tPass);
	    
	    VBox vConnexion = new VBox();
	    vConnexion.setPadding(new Insets(5,100,5,100));
	    vConnexion.setPrefWidth(Integer.MAX_VALUE);
	    vConnexion.setStyle("-fx-font-size: 25px;");
	    Label lConnexionError = new Label();
	    lConnexionError.setStyle("-fx-text-fill: red; -fx-font-size : 15px;");
	    Button bConnexion = new Button("Connexion");
	    vConnexion.getChildren().addAll(bConnexion, lConnexionError);
	    
	    vLeft.getChildren().addAll(lConnexion, vUser, vPass, vConnexion);
	    
	    bConnexion.setOnMouseClicked(e->{
	    	lConnexionError.setText("");
	    	try {
				App.getClient().ecriture("user", tUser.getText());
				App.getClient().lecture();
				App.getClient().ecriture("pass", tPass.getText());
				App.getClient().lecture();
				App.setScene(new AccueilView());
			} catch (InterruptedException | IOException e1) {
				e1.printStackTrace();
			} catch (UserException e1) {
				lConnexionError.setText(e1.getMessage());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
	    });
	    
	    VBox vRight = new VBox();
	    vRight.setStyle("-fx-border-style: hidden hidden hidden solid; -fx-border-width: 2.5; -fx-border-color: black;");
	    vRight.setAlignment(Pos.CENTER);
	    vRight.setSpacing(30);
	    
	    Label lInscription = new Label("INSCRIPTION");
	    lInscription.setStyle("-fx-font-size: 40px;");
	    Label lIUser = new Label("Nom d'utilisateur");
	    lIUser.setStyle("-fx-font-size: 25px;");
	    TextField tIUser = new TextField();
	    tIUser.setStyle("-fx-font-size: 15px; -fx-border-color : black;");
	    tIUser.setPadding(new Insets(10));
	    VBox vIUser = new VBox();
	    vIUser.setSpacing(10);
	    vIUser.setPadding(new Insets(5,100,5,100)); //Les paddings
	    vIUser.getChildren().addAll(lIUser, tIUser);
	    Label lIPass = new Label("Mot de passe");
	    lIPass.setStyle("-fx-font-size: 25px;");
	    TextField tIPass = new TextField();
	    tIPass.setStyle("-fx-font-size: 15px; -fx-border-color : black;");
	    tIPass.setPadding(new Insets(10));
	    VBox vIPass = new VBox();
	    vIPass.setSpacing(10);
	    vIPass.setPadding(new Insets(5,100,5,100)); //Les paddings
	    vIPass.getChildren().addAll(lIPass, tIPass);
	    
	    VBox vInscription = new VBox();
	    vInscription.setPadding(new Insets(5,100,5,100));
	    vInscription.setPrefWidth(Integer.MAX_VALUE);
	    vInscription.setStyle("-fx-font-size: 25px;");
	    Button bInscription = new Button("Inscription");
	    Label lInscriptionError = new Label();
	    lInscriptionError.setStyle("-fx-text-fill: red; -fx-font-size : 15px;");
	    vInscription.getChildren().addAll(bInscription, lInscriptionError);
	    
	    vRight.getChildren().addAll(lInscription, vIUser, vIPass, vInscription);
	    
	    bInscription.setOnMouseClicked(e->{
	    	lInscriptionError.setText("");
	    	try {
				App.getClient().ecriture("create", tIUser.getText() + " " + tIPass.getText());
				App.getClient().lecture();
				App.setScene(new AccueilView());
			} catch (InterruptedException | IOException e1) {
				e1.printStackTrace();
			} catch (UserException e1) {
				lInscriptionError.setText(e1.getMessage());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
	    });
	    
	    
	    add(vLeft,0,0);
	    add(vRight,1,0);
	    
	}
}
