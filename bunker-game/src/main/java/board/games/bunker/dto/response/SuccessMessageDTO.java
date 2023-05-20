package board.games.bunker.dto.response;

public class SuccessMessageDTO {
    private String message;

    public SuccessMessageDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
