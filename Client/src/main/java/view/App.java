package view;

import controleur.Client;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class App extends Application {
	private static Stage stage;

	private static GridPane grid = new GridPane(); // le corps de la page
	private static Client client;	

	@Override
	public void start(Stage stage) throws Exception {
		
		App.stage = stage;

		Scene scene = new Scene(grid);
		
		client = new Client("localhost", 3000);
		
		setScene(new ConnexionView());

		stage.setScene(scene);

		ColumnConstraints col = new ColumnConstraints();
		col.setPercentWidth(100);
		grid.getColumnConstraints().add(col);
		RowConstraints row = new RowConstraints();
		row.setPercentHeight(100);
		grid.getRowConstraints().add(row);

		Rectangle2D screenBounds = Screen.getPrimary().getBounds();
		
		stage.show();
		stage.setTitle("Phaïle Zya");
		//stage.setMaximized(true);
		
		stage.setResizable(false);

		stage.setHeight(screenBounds.getHeight()/1.5);
		stage.setWidth(screenBounds.getWidth()/1.5);
	}

	public static Stage getStage() {
		return stage;
	}
	
	public static Client getClient() {
	    return client;
	  }

	public static void setScene(GridPane n) {
		grid.getChildren().clear();
		grid.add(n, 0, 0);
	}

	public static void main(String[] args) {
		launch();
	}

}
