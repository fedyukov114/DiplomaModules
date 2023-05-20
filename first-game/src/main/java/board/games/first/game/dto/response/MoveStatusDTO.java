package board.games.first.game.dto.response;

public class MoveStatusDTO {
    private String moveStatus;

    public MoveStatusDTO(String moveStatus) {
        this.moveStatus = moveStatus;
    }

    public String getMoveStatus() {
        return moveStatus;
    }

    public void setMoveStatus(String moveStatus) {
        this.moveStatus = moveStatus;
    }
}
