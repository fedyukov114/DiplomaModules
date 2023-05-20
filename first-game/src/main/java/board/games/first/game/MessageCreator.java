package board.games.first.game;

import board.games.first.game.entity.Message;
import board.games.first.game.mapper.MessageContentMapper;

import java.util.List;

import static board.games.first.game.params.ResultMessage.*;
import static board.games.first.game.enums.MessageType.COMMON;

public class MessageCreator {

    public static Message createAddPlayerMessage(String playerName) {
        Message message = new Message();
        message.setContent(NEW_PLAYER);
        message.setSender(playerName);
        message.setType(COMMON);
        return message;
    }

    public static Message createRollDicesMessage(String playerName, List<Integer> diceResult) {
        Message message = new Message();
        message.setContent(MessageContentMapper.rollDiceToMessageContent(diceResult));
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

    public static Message createBuyCardMessage(String playerName) {
        Message message = new Message();
        message.setContent(BUY_CARD);
        message.setSender(playerName);
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

    public static Message createPayForCardMessage(String playerName) {
        Message message = new Message();
        message.setContent(PAY_FOR_CARD);
        message.setSender(playerName);
        message.setType(COMMON);
        return message;
    }

    public static Message createImproveCardMessage(String playerName) {
        Message message = new Message();
        message.setContent(IMPROVE_CARD);
        message.setSender(playerName);
        message.setType(COMMON);
        return message;
    }

    public static Message createSellCardMessage(String playerName) {
        Message message = new Message();
        message.setContent(IMPROVE_CARD);
        message.setSender(playerName);
        message.setType(COMMON);
        return message;
    }

    public static Message createLowerCardLevelMessage(String playerName) {
        Message message = new Message();
        message.setContent(LOWER_CARD_LEVEL);
        message.setSender(playerName);
        message.setType(COMMON);
        return message;
    }

    public static Message createStartBonusMessage(String playerName) {
        Message message = new Message();
        message.setContent(START_BONUS_PASS);
        message.setSender(playerName);
        message.setType(COMMON);
        return message;
    }
}
