package board.games.first.game.dto.response;

import java.util.List;
import java.util.Map;


public class PlayingFieldStateDTO {
    private List<PlayerDTO> players;
    private String state;
    private String currentPlayer;
    private String moveStatus;
    private Map<String, CardStateDTO> cardStates;
    private List<ResultMessageDTO> chatHistory;

    public List<PlayerDTO> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerDTO> players) {
        this.players = players;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public String getMoveStatus() {
        return moveStatus;
    }

    public void setMoveStatus(String moveStatus) {
        this.moveStatus = moveStatus;
    }

    public Map<String, CardStateDTO> getCardStates() {
        return cardStates;
    }

    public void setCardStates(Map<String, CardStateDTO> cardStates) {
        this.cardStates = cardStates;
    }

    public List<ResultMessageDTO> getChatHistory() {
        return chatHistory;
    }

    public void setChatHistory(List<ResultMessageDTO> chatHistory) {
        this.chatHistory = chatHistory;
    }
}
