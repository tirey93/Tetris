import java.io.InputStream;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
    static int dx = 1;
    static int dy = 1;
    static BooleanProperty state = new SimpleBooleanProperty();
    static Pane root = new Pane();
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setTitle("Animation");
        new WinnerList();
        new Physics();
        new SqArr();
        new Control();
        InputStream in = getClass().getResourceAsStream("img/bc2.png");
        Image image = new Image(in);
        BackgroundImage im = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
        		BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        root.setBackground(new Background(im));
		Scene scene = new Scene(root, 595, 793);
        primaryStage.setScene(scene);
        Physics.bindKeys(scene);
        primaryStage.setResizable(false);
        state.addListener((a,oldValue,newValue)-> {
			if(newValue) {
				state.setValue(false);
				Physics.setDroping();
			}
        });
        primaryStage.show();
    }

}












