import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Dmitriy Bokach
 */
public class Server {
    private static ServerSocket server;
    private static Socket socket;
    private static final int PORT = 8189;

    public Server() {
        try {
            server = new ServerSocket(PORT);
            System.out.println("Server started");

            socket = server.accept();
            System.out.println("Client connected");

            Scanner sc = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            new Thread(() -> {
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    out.println("Server: " + scanner.nextLine());
                }
            }).start();

            while (true) {
                String str = sc.nextLine();
                System.out.println("Client: " + str);
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
}
