package scenes.multiplayer;
import classes.Client;
import classes.Server;
import controllers.multiplayer.WaitingRoomController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WaitingRoom {

    private Parent root;
    private Scene scene;
    private WaitingRoomController controller;

    private Server server;
    private Client client;

    private final static int WINDOW_WIDTH = 1280;
    private final static int WINDOW_HEIGHT = 720;

    public WaitingRoom(boolean isServer, String username, int playerCount, int difficulty, int duration, int lives) throws IOException {
        System.out.println("Lobby created");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../screens/multiplayer/WaitingRoom.fxml"));
        this.root = loader.load();
        this.scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.controller = loader.getController();
        this.controller.setIsServer(isServer);

        if (isServer) {
            this.server = new Server(new ServerSocket(1234), username);
            this.controller.setServer(server);
            this.server.startServer(this.controller.getChatBox());
        } else {
            this.client = new Client(new Socket("localhost", 1234), username);
            this.controller.setClient(client);
        }
    }

    public Scene getScene() {
        return scene;
    }

}
