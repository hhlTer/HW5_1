package javafx51.PaintCircles;

        import javafx51.star.*;
        import javafx.application.Application;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.scene.control.TextField;
        import javafx.scene.layout.Pane;
        import javafx.scene.paint.Color;
        import javafx.scene.shape.Circle;
        import javafx.scene.shape.Polyline;
        import javafx.scene.text.Font;
        import javafx.scene.text.Text;
        import javafx.stage.Stage;

        import java.util.ArrayList;
        import java.util.List;
        import java.util.Random;

public class PaintCircles extends Application {
    private final double WIDTH = 800;
    private final double HEIGHT = 600;
    private final double ACTUAL_HEIGHT = 600 - 28;

    public static void main(String[] args) {
        launch(args);
    }
    private void windowProperty(Stage primaryStage) {
        primaryStage.setWidth(WIDTH);
        primaryStage.setHeight(HEIGHT);
        primaryStage.setMaxHeight(HEIGHT);
        primaryStage.setMaxWidth(WIDTH);
        primaryStage.setMinHeight(HEIGHT);
        primaryStage.setMinWidth(WIDTH);
    }

    private Pane uiPaint() {

        Pane root = new Pane();

        /*
 *  ---------------------------------------------------------
 *  -----------------------  Text  --------------------------
 *  ---------------------------------------------------------
 */
        Text text = new Text(20, 30, "Fill data");
        text.setFont(new Font(20));

        Text text1 = new Text(80, 88, "Count of circles");
        text1.setFont(new Font(20));

        Text text2 = new Text(80, 118, "Minimal radius");
        text2.setFont(new Font(20));

        Text text3 = new Text(80, 148, "Maximal radius");
        text3.setFont(new Font(20));
        List<Text> textList = new ArrayList<>();

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
        TextField countCircles = new TextField("10");
        countCircles.setTranslateX(20);
        countCircles.setTranslateY(70);

        TextField minimalRad = new TextField("20");
        minimalRad.setTranslateX(20);
        minimalRad.setTranslateY(100);

        TextField maximalRad = new TextField("100");
        maximalRad.setTranslateX(20);
        maximalRad.setTranslateY(130);

        countCircles.setMaxSize(50,200);
        minimalRad.setMaxSize(50,200);
        maximalRad.setMaxSize(50,200);

        List<Circle> lcircles = new ArrayList<>();
        button.setOnMouseClicked((event) -> {
            root.getChildren().removeAll(lcircles);
            lcircles.clear();
            int countCirc = Integer.parseInt(countCircles.getText());
            int minRadius = Integer.parseInt(minimalRad.getText());
            int maxRadius = Integer.parseInt(maximalRad.getText());
            int radiuses[] = new int[countCirc];
            int sumRad = 0;
            final int SUM_VOL_X = 400;
            Random random = new Random();
            double yfin;
            for (int i = countCirc-1; i >= 0; i--) {
                int finHeight = (int) ACTUAL_HEIGHT - sumRad;
                int finMaxRadius = maxRadius;
                int finMinRadius = minRadius;
                if (minRadius < finHeight/(i+1)/2) finMinRadius = finHeight/(i+1)/2;
                if (maxRadius > finHeight/(i+1)/2) finMaxRadius = finHeight/(i+1)/2 - minRadius;
                else finMaxRadius = maxRadius - minRadius;
                radiuses[i] = random.nextInt(finMaxRadius) + finMinRadius;
                if (i == 0) radiuses[i] = finHeight/2 > maxRadius ? maxRadius : finHeight/2;
                sumRad += radiuses[i] * 2;
                yfin = sumRad - radiuses[i];
                Circle circle = new Circle(SUM_VOL_X, yfin, radiuses[i]);
                circle.setFill(Color.color(Math.random(),Math.random(),Math.random()));
                lcircles.add(circle);
                if (i == 0){
                    final int FACE_RADIUS = radiuses[countCirc-1];
//nose
                    circle = new Circle(SUM_VOL_X, FACE_RADIUS + FACE_RADIUS/3,
                            random.nextInt(FACE_RADIUS/8) + FACE_RADIUS/10);
                    circle.setFill(Color.color(Math.random(),Math.random(),Math.random()));
                    lcircles.add(circle);
//eyes
                    final int LEFT_X = SUM_VOL_X + FACE_RADIUS/2;
                    final int RIGT_X = SUM_VOL_X - FACE_RADIUS/2;
                    final int Y = FACE_RADIUS - FACE_RADIUS/2;
//-------------------------l - maximum radius of eyes.
                    double l = (FACE_RADIUS - Math.sqrt((SUM_VOL_X - LEFT_X)*(SUM_VOL_X - LEFT_X) +
                            (FACE_RADIUS - Y)*(FACE_RADIUS - Y)))/2 - 1;

                    circle = new Circle(SUM_VOL_X - FACE_RADIUS/2,// Math.sin(Math.toRadians(30)*FACE_RADIUS),
                            FACE_RADIUS - FACE_RADIUS/2,
                            random.nextInt((int)l) + FACE_RADIUS/7);
                    circle.setFill(Color.color(Math.random(), Math.random(), Math.random()));
                    lcircles.add(circle);

                    circle = new Circle(SUM_VOL_X + FACE_RADIUS/2,//Math.sin(Math.toRadians(330)*FACE_RADIUS),
                            FACE_RADIUS - FACE_RADIUS/2,
                            random.nextInt((int)l) + FACE_RADIUS/7);
                    circle.setFill(Color.color(Math.random(), Math.random(), Math.random()));
                    lcircles.add(circle);
                }
            }
            root.getChildren().addAll(lcircles);
        });

        root.getChildren().addAll(text, button, minimalRad, maximalRad, countCircles,
                text1, text2, text3);
        return root;
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        windowProperty(primaryStage);
        Pane root = uiPaint();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
