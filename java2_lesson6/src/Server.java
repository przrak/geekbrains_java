import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author Dmitriy Bokach
 */
public class Server {
    private static ServerSocket server;
    private static Socket socket;
    private static final int PORT = 8189;
    private final Set<ClientHandler> clients;

    public Server() {
        clients = new CopyOnWriteArraySet<>();
        try {
            server = new ServerSocket(PORT);
            System.out.println("Server started");

            while (true) {
                socket = server.accept();
                System.out.println("Client connected");
                clients.add(new ClientHandler(this, socket));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void broadcastMsg(final String msg) {
        clients.forEach(s -> s.sendMsg(msg));
    }
}
