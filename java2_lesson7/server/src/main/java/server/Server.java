package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
    private ServerSocket server;
    private Socket socket;
    private final int PORT = 8189;
    private List<ClientHandler> clients;
    private AuthService authService;

    public Server() {
        clients = new CopyOnWriteArrayList<>();
        authService = new SimpleAuthService();

        try {
            server = new ServerSocket(PORT);
            System.out.println("Server started");

            while (true) {
                socket = server.accept();
                System.out.println("Client connected");
                new ClientHandler(this, socket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void broadcastMsg(ClientHandler clientHandler, String msg){
        for (ClientHandler c : clients) {
            c.sendMsg(this.messageWithNickname(clientHandler, msg));
        }
    }

    public void tryToSendPrivateMsg(final ClientHandler author, final String toNickname, final String msg) {
        ClientHandler clientHandler = this.findClientHandlerByNickname(toNickname);
        if (clientHandler != null) {
            clientHandler.sendMsg(this.messageWithNickname(author, msg));
            author.sendMsg(this.messageWithNickname(author, msg));
        }
    }

    void subscribe(ClientHandler clientHandler){
        clients.add(clientHandler);
    }

    void unsubscribe(ClientHandler clientHandler){
        clients.remove(clientHandler);
    }

    public AuthService getAuthService() {
        return authService;
    }

    private ClientHandler findClientHandlerByNickname(final String nickname) {
        if (nickname == null || nickname.isEmpty()) {
//            throw new RuntimeException("Nickname not found");
            return null;
        }
        for (ClientHandler clientHandler : clients) {
            if (clientHandler.getNickname().equals(nickname)) {
                return clientHandler;
            }
        }
        return null;
    }

    private String messageWithNickname(final ClientHandler clientHandler, final String msg) {
        return String.format("[ %s ]: %s", clientHandler.getNickname(), msg);
    }
}
