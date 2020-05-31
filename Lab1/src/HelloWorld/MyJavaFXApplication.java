
package HelloWorld;

        import javafx.scene.paint.Color;

        import javafx.application.Application;
        import javafx.scene.Group;
        import javafx.scene.Scene;
        import javafx.stage.Stage;

        import javafx.scene.shape.*;

public class MyJavaFXApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Кузнєцов Ілля КП-72");

        Group root = new Group();
        Scene scene = new Scene(root, 380, 230);

        scene.setFill(Color.GREEN);

        Polygon triangle = new Polygon();
        triangle.getPoints().setAll(
                110.0, 165.0,
                175.0, 20.0,
                265.0, 165.0
        );
        triangle.setFill(Color.YELLOW);
        root.getChildren().add(triangle);

        Polyline polyline = new Polyline();
        polyline.getPoints().setAll(
                16.0, 20.0,
                80.0, 215.0,
                300.0, 215.0,
                355.0, 20.0
        );
        polyline.setStrokeWidth(5.0);
        polyline.setStroke(Color.RED);
        root.getChildren().add(polyline);

        Line line1 = new Line(65, 20, 115, 20);
        line1.setStrokeWidth(5.0);
        line1.setStroke(Color.BLUE);
        root.getChildren().add(line1);

        Polyline line2 = new Polyline(220, 20, 290, 20, 292, 18 );
        line2.setStrokeWidth(5.0);
        line2.setStroke(Color.BLUE);
        root.getChildren().add(line2);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
