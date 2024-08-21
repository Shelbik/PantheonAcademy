package thirdOption;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ChatWebSocketHandler extends TextWebSocketHandler {

    // Mapa na uchovanie aktivnych WebSocket
    private static final Map<String, WebSocketSession> sessions = Collections.synchronizedMap(new HashMap<>());
    // Mapa na uchovanie ID pouzivatelov a ich WebSocket
    private static final Map<WebSocketSession, String> userIds = Collections.synchronizedMap(new HashMap<>());

    // Metoda na ziskanie vsetkych aktivnych WebSocket
    public static Set<WebSocketSession> getSessions() {
        return Set.copyOf(sessions.values());
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
        // Pridanie noveho sedenia do mapy
        sessions.put(session.getId(), session);
        // Priradenie ID pouzivatela k jeho WebSocket sedeniu
        userIds.put(session, session.getId()); // Predpokladame, ze ID pouzivatela je rovnake ako ID sedenia
        // Odoslat zoznam pouzivatelov
        broadcastUserList();
        broadcast("User joined: " + session.getId());
    }

    // Metoda na odoslanie zoznamu pouzivatelov vsetkym pripojenym klientom
    private void broadcastUserList() throws IOException {
        String userListMessage = "USER_LIST:" + String.join(",", sessions.keySet());
        broadcast(userListMessage);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        String payload = message.getPayload();
        // Format spravy: "userId:sprava"
        String[] parts = payload.split(":", 2);
        if (parts.length == 2) {
            String recipientId = parts[0];
            String msg = parts[1];
            WebSocketSession recipientSession = sessions.get(recipientId);
            if (recipientSession != null && recipientSession.isOpen()) {
                // Odoslat spravu prijemcovi
                recipientSession.sendMessage(new TextMessage("From " + userIds.get(session) + ": " + msg));
            } else {
                // Informovat odosielatela, ze prijemca nie je dostupny
                session.sendMessage(new TextMessage("Recipient not found or is offline."));
            }
        } else {
            // nespravny format spravy
            session.sendMessage(new TextMessage("Invalid message format. Use 'recipientId:message'."));
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws IOException {

        sessions.remove(session.getId());
        userIds.remove(session);
        // Informovat vsetkych o tom ze pouzivatel opustil chat
        broadcast("User left: " + session.getId());
    }

    // Metoda na odoslanie spravy vsetkym pripojenym klientom
    private void broadcast(String message) throws IOException {
        for (WebSocketSession session : sessions.values()) {
            if (session.isOpen()) {
                session.sendMessage(new TextMessage(message));
            }
        }
    }
}
