package board.games.bunker.controllers;

import board.games.bunker.dto.request.CommonMessageDTO;
import board.games.bunker.dto.response.ResultMessageDTO;
import board.games.bunker.dto.response.SuccessMessageDTO;
import board.games.bunker.services.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import static board.games.bunker.params.ResultMessage.COMMON_MESSAGE_WAS_SAVED;

@Controller
public class ChatController {
    private final ChatService chatService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public ChatController(ChatService chatService, SimpMessagingTemplate simpMessagingTemplate) {
        this.chatService = chatService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping(value = "/api/bunker/chat/common")
    public ResponseEntity<SuccessMessageDTO> saveCommonMessage(CommonMessageDTO dto) {
        chatService.addCommonMessageToChatHistory(dto.getSessionId(), dto.getSender(), dto.getMessage());
        simpMessagingTemplate.convertAndSend(
                "/topic/chat/" + dto.getSessionId(),
                new ResultMessageDTO(dto.getSender(), dto.getMessage())
        );

        return ResponseEntity.ok().body(new SuccessMessageDTO(COMMON_MESSAGE_WAS_SAVED));
    }
}
