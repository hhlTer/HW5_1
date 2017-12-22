package javafx51.star;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Star extends Application {
    private final double WIDTH = 600;
    private final double HEIGHT = 400;

    public static void main(String[] args) {
        launch(args);
    }

    protected void windowProperty(Stage primaryStage) {
        primaryStage.setWidth(WIDTH);
        primaryStage.setHeight(HEIGHT);
        primaryStage.setMaxHeight(HEIGHT);
        primaryStage.setMaxWidth(WIDTH);
        primaryStage.setMinHeight(HEIGHT);
        primaryStage.setMinWidth(WIDTH);
    }


    private Pane uiPaint(Pane root) {

/*
 *  ---------------------------------------------------------
 *  -----------------------  Text  --------------------------
 *  ---------------------------------------------------------
 */
        Text text = new Text(20, 30, "Fill data");
        text.setFont(new Font(20));

        Text text1 = new Text(80, 88, "R");
        text1.setFont(new Font(20));

        Text text2 = new Text(80, 118, "X");
        text2.setFont(new Font(20));

        Text text3 = new Text(80, 148, "Y");
        text3.setFont(new Font(20));

/*
 *  ---------------------------------------------------------
 *  ----------------------- Button --------------------------
 *  ---------------------------------------------------------
 */
        Button button = new Button("Show star");
        button.setTranslateX(20);
        button.setTranslateY(40);
/*
 *  ---------------------------------------------------------
 *  --------------------- TextField -------------------------
 *  ---------------------------------------------------------
 */
        TextField radius = new TextField("100");
        radius.setTranslateX(20);
        radius.setTranslateY(70);
        radius.setAccessibleHelp("Radius");

        TextField coordinateX = new TextField("300");
        coordinateX.setTranslateX(20);
        coordinateX.setTranslateY(100);

        TextField coordinateY = new TextField("200");
        coordinateY.setTranslateX(20);
        coordinateY.setTranslateY(130);

        radius.setMaxSize(50,200);
        coordinateX.setMaxSize(50,200);
        coordinateY.setMaxSize(50,200);

        TextField sinX = new TextField("0");
        coordinateY.setTranslateX(20);
        coordinateY.setTranslateY(130);

        TextField cosY = new TextField("90");
        coordinateY.setTranslateX(20);
        coordinateY.setTranslateY(130);


        Polyline polyline = new Polyline();

        button.setOnMouseClicked((event) -> {
                    double rad = Integer.parseInt(radius.getText());
                    double x = Integer.parseInt(coordinateX.getText());
                    double y = Integer.parseInt(coordinateY.getText());
                    double inc = 2.7;

                    double sin72sin288 = Math.sin(Math.toRadians(72));
                    double sin144sin216 = Math.sin(Math.toRadians(144));
                    double cos72cos288 = Math.cos(Math.toRadians(72));
                    double cos144cos216 = Math.cos(Math.toRadians(144));

                    double sin36sin324 = Math.sin(Math.toRadians(36));
                    double sin108sin252 = Math.sin(Math.toRadians(108));
                    double cos36cos324 = Math.cos(Math.toRadians(36));
                    double cos108cos252 = Math.cos(Math.toRadians(108));
                    polyline.getPoints().addAll(
                            x + 0, y - rad, //1
                            x + rad / inc * sin36sin324,  y - rad / inc * cos36cos324,//2
                            x + rad * sin72sin288,        y - rad * cos72cos288, //3
                            x + rad / inc * sin108sin252, y - rad / inc * cos108cos252,//4
                            x + rad * sin144sin216,       y - rad * cos144cos216, //5
                            x,                            y + rad / inc,//6
                            x - rad * sin144sin216,       y - rad * cos144cos216, //7
                            x - rad / inc * sin108sin252, y - rad / inc * cos108cos252,//8
                            x - rad * sin72sin288,        y - rad * cos72cos288, //9
                            x - rad / inc * sin36sin324,  y - rad / inc * cos36cos324,//10
                            x,                            y - rad//fin
                    );
                }
        );

        root.getChildren().addAll(text, button, coordinateX, coordinateY, radius, polyline,
                text1, text2, text3);
        return root;
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        windowProperty(primaryStage);
        Pane root = new Pane();
        root = uiPaint(root);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

