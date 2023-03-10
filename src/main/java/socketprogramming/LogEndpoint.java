package socketprogramming;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

public class LogEndpoint extends WebSocketServer {
    private static final int MAX_LINES = 10;
    private final Deque<String> recentLines = new ArrayDeque<>(MAX_LINES);

    public LogEndpoint(int port) throws UnknownHostException {
        super(new InetSocketAddress("127.0.0.1",port));
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        Path filePath = Paths.get("logfile.txt");
        final long[] lastModified = {filePath.toFile().lastModified()};
        scheduler.scheduleAtFixedRate(() -> {
            long modified = filePath.toFile().lastModified();
            if (modified > lastModified[0]) {
                sendNewLines();
                lastModified[0] = modified;
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        sendRecentLines(conn);
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        // Ignore incoming messages
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        // Nothing to do
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
    }

    private void sendRecentLines(WebSocket conn) {
        for (String line : recentLines) {
            conn.send(line);
        }
    }

    private void sendNewLines() {
        try (BufferedReader reader = new BufferedReader(new FileReader("logfile.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (recentLines.size() == MAX_LINES) {
                    recentLines.removeFirst();
                }
                recentLines.addLast(line);
            }
            for (WebSocket conn : connections()) {
                sendRecentLines(conn);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        WebSocketImpl.DEBUG = true;
        int port = 8765;
        try {
            WebSocketServer server = new LogEndpoint(port);
            server.start();
            System.out.println("Log server started on port " + port);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
