package view;

import java.io.IOException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class AccueilView extends GridPane {

	VBox vScroll = new VBox(); //Le contenu unique du scrollPane
	
	public AccueilView() throws Exception {
		ColumnConstraints col40 = new ColumnConstraints();
		ColumnConstraints col60 = new ColumnConstraints();
	    col40.setPercentWidth(40);
	    col60.setPercentWidth(60);
	    getColumnConstraints().addAll(col60, col40);
	    
	    RowConstraints row100 = new RowConstraints();
	    row100.setPercentHeight(100);
	    getRowConstraints().add(row100);

	    ScrollPane center = new ScrollPane();
	    center.setStyle("-fx-background-color:transparent; -fx-border-style: hidden solid hidden solid; -fx-border-width: 2.5; -fx-border-color: black;");
	    center.setContent(vScroll);
	    center.setPadding(new Insets(50,0,0,20));
		vScroll.prefWidthProperty().bind(center.prefWidthProperty());
		vScroll.prefHeightProperty().bind(center.prefHeightProperty());
	    arborescence();
	    
	    VBox cmdPart = new VBox();
	    cmdPart.setStyle("-fx-border-style: hidden hidden hidden solid; -fx-border-width: 2.5; -fx-border-color: black;");
	    Button bMkdir = new Button("Mkdir");	    
	    TextField tMkdir = new TextField();
	    tMkdir.setPromptText("Folder name");
	    
	    Button bGet = new Button("Get");
	    TextField tGet = new TextField();
	    tGet.setPromptText("Get File Name");
	    
	    Label lToolGet = new Label("?");
	    lToolGet.setPadding(new Insets(2,5,2,5));
	    lToolGet.setStyle("-fx-font-size : 15px; -fx-cursor: hand; -fx-background-radius: 500px; -fx-border-radius: 500px;-fx-border-style: solid solid solid solid; -fx-border-width: 1; -fx-border-color: black;");
	    Tooltip toolGet= new Tooltip("Usage : path_to_file_to_download (without home)");
	    toolGet.setShowDuration(Duration.INDEFINITE);
	    Tooltip.install(lToolGet, toolGet);
	    HBox hGet= new HBox();
	    hGet.setAlignment(Pos.CENTER);
	    hGet.getChildren().addAll(bGet, lToolGet);	    
	    
	    Button bStor = new Button("Stor");
	    TextField tStor = new TextField();
	    tStor.setPromptText("Stor File Name");
	    
	    Label lToolStor= new Label("?");
	    lToolStor.setPadding(new Insets(2,5,2,5));
	    lToolStor.setStyle("-fx-font-size : 15px; -fx-cursor: hand; -fx-background-radius: 500px; -fx-border-radius: 500px;-fx-border-style: solid solid solid solid; -fx-border-width: 1; -fx-border-color: black;");
	    Tooltip toolStor= new Tooltip("Usage : file_name_after_upload");
	    toolStor.setShowDuration(Duration.INDEFINITE);
	    Tooltip.install(lToolStor, toolStor);
	    HBox hStor= new HBox();
	    hStor.setAlignment(Pos.CENTER);
	    hStor.getChildren().addAll(bStor, lToolStor);
	    
	    Button bDeconnexion = new Button("Deconnexion");
	    bDeconnexion.setOnMouseClicked(e->{
	    	try {
				App.getClient().ecriture("exit", "");
				App.setScene(new ConnexionView());
			} catch (InterruptedException | IOException e1) {
				e1.printStackTrace();
			}
	    });
	    
	    Label lPath = new Label("Chemin du fichier ");
	    Label lToolPath = new Label("?");
	    lToolPath.setPadding(new Insets(2,5,2,5));
	    lToolPath.setStyle("-fx-font-size : 15px; -fx-cursor: hand; -fx-background-radius: 500px; -fx-border-radius: 500px;-fx-border-style: solid solid solid solid; -fx-border-width: 1; -fx-border-color: black;");
	    Tooltip toolPath = new Tooltip("Usage :\nCmd Get : path/file_name_after_download \nCmd Stor : path/file_name_to_upload");
	    toolPath.setShowDuration(Duration.INDEFINITE);
	    Tooltip.install(lToolPath, toolPath);
	    HBox hPath = new HBox();
	    hPath.setAlignment(Pos.CENTER);
	    hPath.getChildren().addAll(lPath, lToolPath);
	    
	    TextField tPath = new TextField();
	    tPath.setPromptText("Local path");
	    
	    cmdPart.getChildren().addAll(hPath, tPath, bMkdir, tMkdir, hGet, tGet, hStor, tStor, bDeconnexion);
	    cmdPart.setAlignment(Pos.CENTER);
	    cmdPart.setSpacing(30);
	    cmdPart.setPadding(new Insets(0,20,0,20));
	    
	    bMkdir.setDisable(true);
	    bGet.setDisable(true);
	    bStor.setDisable(true);
	    
	    tMkdir.textProperty().addListener((observable, oldValue, newValue) -> {
	        if(newValue.equals("")) {
	        	bMkdir.setDisable(true);
	        }else {
	        	bMkdir.setDisable(false);
	        }
	    });
	    
	    tGet.textProperty().addListener((observable, oldValue, newValue) -> {
	        if(!newValue.equals("") && !tPath.getText().equals("")) {
	        	bGet.setDisable(false);
	        }else {
	        	bGet.setDisable(true);
	        }
	    });
	    
	    tStor.textProperty().addListener((observable, oldValue, newValue) -> {
	        if(!newValue.equals("") && !tPath.getText().equals("")) {
	        	bStor.setDisable(false);
	        }else {
	        	bStor.setDisable(true);
	        }
	    });
	    
	    tPath.textProperty().addListener((observable, oldValue, newValue) -> {
	        if(!newValue.equals("") && !tStor.getText().equals("")) {
	        	bStor.setDisable(false);
	        	if(!newValue.equals("") && !tGet.getText().equals("")){
		        	bGet.setDisable(false);
		        }
	        }else if(!newValue.equals("") && !tGet.getText().equals("")) {
	        	bGet.setDisable(false);
	        }else {
	        	bStor.setDisable(true);
	        	bGet.setDisable(true);
	        }
	    });
	    
	    bMkdir.setOnMouseClicked(e->{
	    	try {
				App.getClient().ecriture("mkdir", tMkdir.getText());
				App.getClient().lecture();
				arborescence();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
	    });
	    
	    bGet.setOnMouseClicked(e->{
	    	try {
				App.getClient().ecriture("get", tPath.getText()+" "+tGet.getText());
				App.getClient().get();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
	    });
	    
	    bStor.setOnMouseClicked(e->{
	    	try {
				App.getClient().ecriture("stor", tPath.getText()+" "+tStor.getText());
				App.getClient().stor();
				arborescence();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
	    });
		
	    add(center,0,0);
	    add(cmdPart,1,0);
	}
	
	public void arborescence() throws Exception {
		vScroll.getChildren().clear();
		
		vScroll.setPadding(new Insets(10,15,10,15));
		vScroll.setSpacing(10);		
		App.getClient().ecriture("pwd", "");
		Label path = new Label(App.getClient().lecture()+">");
		path.setStyle("-fx-font-weight : bold; -fx-font-size : 20px; -fx-border-style: solid solid solid solid; -fx-border-width: 1; -fx-border-color: #f4f4f4; -fx-cursor: hand;");
		path.setOnMouseEntered(e->{
			path.setStyle("-fx-font-weight : bold; -fx-font-size : 20px; -fx-border-style: solid solid solid solid; -fx-border-width: 1; -fx-border-color: black; -fx-cursor: hand;");
		});
		path.setOnMouseExited(e->{
			path.setStyle("-fx-font-weight : bold; -fx-font-size : 20px; -fx-border-style: solid solid solid solid; -fx-border-width: 1; -fx-border-color: #f4f4f4; -fx-cursor: hand;");
		});
		path.setOnMouseClicked(e->{
			try {
				App.getClient().ecriture("cd", "..");
				arborescence();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		vScroll.getChildren().add(path);
		App.getClient().ecriture("ls", "");
		String s = App.getClient().lecture();
		while(!s.equals("-----")) {
			Label element = new Label(s);
			element.setPadding(new Insets(0,10,0,10));
			element.prefWidthProperty().bind(vScroll.prefWidthProperty());
			String tmp = element.getText().substring(3);
			vScroll.getChildren().add(element);
			if(s.startsWith("d-")) {
				element.setOnMouseEntered(e->{
					element.setStyle("-fx-cursor: hand; -fx-font-size : 15px; -fx-border-width: 1; -fx-border-style: solid solid solid solid; -fx-border-color: black;");
				});
				element.setOnMouseExited(e->{
					element.setStyle("-fx-cursor: hand; -fx-font-size : 15px; -fx-border-width: 1; -fx-border-style: hidden hidden hidden hidden; -fx-border-color: #f4f4f4;");
				});
				element.setStyle("-fx-cursor: hand; -fx-font-size : 15px;  -fx-border-width: 1; -fx-border-style: solid solid solid solid; -fx-border-color: #f4f4f4;");
				element.setOnMouseClicked(e->{
					String folder = tmp;
					try {
						App.getClient().ecriture("cd", folder);
						arborescence();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				});
			}else {
				element.setOnMouseEntered(e->{
					element.setStyle("-fx-font-size : 15px; -fx-border-width: 1; -fx-border-style: solid solid solid solid; -fx-border-color: black;");
				});
				element.setOnMouseExited(e->{
					element.setStyle("-fx-font-size : 15px; -fx-border-width: 1; -fx-border-style: hidden hidden hidden hidden; -fx-border-color: #f4f4f4;");
				});
				element.setStyle("-fx-font-size : 15px; -fx-border-width: 1; -fx-border-style: solid solid solid solid; -fx-border-color: #f4f4f4;");
			}
			s = App.getClient().lecture();
		}
	}
}
