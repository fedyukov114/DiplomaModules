package board.games.bunker;

import static board.games.bunker.enums.MessageType.COMMON;
import static board.games.bunker.params.ResultMessage.*;

import board.games.bunker.entity.Message;

public class MessageCreator {

    public static Message createAddPlayerMessage(String playerName) {
        Message message = new Message();
        message.setContent(NEW_PLAYER);
        message.setSender(playerName);
        message.setType(COMMON);
        return message;
    }

    public static Message createStartGameMessage() {
        Message message = new Message();
        message.setContent(GAME_WAS_STARTED);
        message.setSender(null);
        message.setType(COMMON);
        return message;
    }

    public static Message createSentMessage(String content, String playerName) {
        Message message = new Message();
        message.setContent(content);
        message.setSender(playerName);
        message.setType(COMMON);
        return message;
    }

    public static Message createVoteMessage(String playerName) {
        Message message = new Message();
        message.setContent(String.format(PLAYER_VOTED, playerName));
        message.setSender(null);
        message.setType(COMMON);
        return message;
    }
}
