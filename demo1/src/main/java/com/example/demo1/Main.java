package com.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main extends Application {


    public static Node stage;

    public static Stage primaryStage;
    public static Scene1Controller scene1Controller;
    public static Scene3Controller scene3Controller;
    public static Scene2Controller scene2Controller;
    public static Scene4Controller scene4Controller;
    public static WorkersceneCont workersceneCont;
    public static Scene scene1;
    public static Scene scene3;
    public static Scene scene2;
    public static Scene scene4;

    public static Scene scene5;
    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene1.fxml"));
        AnchorPane pane1 = loader.load();
        scene1 = new Scene(pane1);
        scene1Controller = loader.getController();

       loader = new FXMLLoader(getClass().getResource("Scene2.fxml"));
        AnchorPane pane2 = loader.load();
        scene2 =new Scene(pane2);
        scene2Controller = loader.getController();

        loader = new FXMLLoader(getClass().getResource("Scene3.fxml"));
        AnchorPane pane3 = loader.load();
        scene3 = new Scene(pane3);
        scene3Controller = loader.getController();

        loader = new FXMLLoader(getClass().getResource("Scene4.fxml"));
        AnchorPane pane4 = loader.load();
        scene4 = new Scene(pane4);
        scene4Controller = loader.getController();

        loader = new FXMLLoader(getClass().getResource("WorkerScene.fxml"));
        AnchorPane pane5 = loader.load();
        scene5 = new Scene(pane5);
        workersceneCont = loader.getController();

        stage.setScene(scene1);
        stage.show();



        primaryStage.setOnCloseRequest(event -> {
            Scene1Controller.closeApplicationIfConfirmed(primaryStage);
            event.consume();
        });
    }

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

    public static Scene getCurrentScene() {
        if (primaryStage.getScene() == scene1) {
            return scene1;
        } else if (primaryStage.getScene() == scene2) {
            return scene2;
        } else if (primaryStage.getScene() == scene3) {
            return scene3;
        } else if (primaryStage.getScene() == scene4) {
            return scene4;
        } else if (primaryStage.getScene() == scene5) {
            return scene5;
        } else {
            return null;
        }
    }

    public static boolean isApplicationClosed(String applicationName) {
        Process process;
        try {
            if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                process = Runtime.getRuntime().exec("tasklist");
            } else {
                process = Runtime.getRuntime().exec("ps aux");
            }
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains(applicationName)) {
                        return false;
                    }
                }
            }
        } catch (IOException e) {
        }
        return true;
    }
}
