package board.games.first.game.mapper;

import board.games.first.game.dto.response.MoveResultDTO;
import board.games.first.game.dto.response.PlayerBalanceDTO;
import board.games.first.game.dto.response.PlayerPositionDTO;
import board.games.first.game.dto.response.RollDiceResultDTO;

import java.util.List;

public class RollDicesMapper {

    public static RollDiceResultDTO rollResultTODTO(List<Integer> digits, String playerName, int position, Long balance) {
        RollDiceResultDTO rollDiceResultDTO = new RollDiceResultDTO();
        rollDiceResultDTO.setPlayerBalance(new PlayerBalanceDTO(playerName, balance));
        rollDiceResultDTO.setDigits(digits);
        rollDiceResultDTO
                .setPlayer(new PlayerPositionDTO(playerName, position));
        return rollDiceResultDTO;
    }

    public static MoveResultDTO rollResultToMoveDTO(RollDiceResultDTO dto) {
        MoveResultDTO moveResultDTO = new MoveResultDTO();
        moveResultDTO.setDigits(dto.getDigits());
        moveResultDTO.setPlayer(dto.getPlayer());
        return moveResultDTO;


    }
}
