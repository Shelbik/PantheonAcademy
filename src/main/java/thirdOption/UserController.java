package thirdOption;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.WebSocketSession;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/users")
    public Set<String> listUsers() {
        return ChatWebSocketHandler.getSessions().stream()
                .map(WebSocketSession::getId)
                .collect(Collectors.toSet());
    }
}
