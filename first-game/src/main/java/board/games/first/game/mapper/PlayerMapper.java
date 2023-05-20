package board.games.first.game.mapper;

import board.games.first.game.dto.response.*;
import board.games.first.game.entity.CardState;
import board.games.first.game.entity.Player;
import board.games.first.game.enums.PlayerStatus;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerMapper {

    public static List<PlayerDTO> entitiesToDTOList(List<Player> players) {
        return players
                .stream()
                .map(PlayerMapper::entityToPLayerDTO)
                .collect(Collectors.toList());
    }

    public static PlayerDTO entityToPLayerDTO(Player player) {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setId(player.getId());
        playerDTO.setName(player.getName());
        playerDTO.setPosition(player.getPosition());
        playerDTO.setRole(player.getRole().toString());
        playerDTO.setBalance(player.getBalance());
        playerDTO.setStatus(player.getStatus());
        return playerDTO;
    }

    public static PayForCardDTO playerBalancesToDTO(
            String buyer, Long buyerBalance, String owner, Long ownerBalance) {
        PayForCardDTO payForCardDTO = new PayForCardDTO();
        payForCardDTO.setBuyer(PlayerMapper.playerBalanceToDTO(buyer, buyerBalance));
        payForCardDTO.setOwner(PlayerMapper.playerBalanceToDTO(owner, ownerBalance));
        return payForCardDTO;


    }

    public static PlayerPositionDTO playerPositionToDTO(String playerName, int position) {
        return new PlayerPositionDTO(playerName, position);
    }

    public static PlayerBalanceDTO playerBalanceToDTO(String playerName, Long balance) {
        return new PlayerBalanceDTO(playerName, balance);
    }

    public static SurrenderPlayerDTO surrenderPlayerToDTO(String playerName, PlayerStatus status, List<CardState> cardStates) {
        SurrenderPlayerDTO surrenderPlayerDTO = new SurrenderPlayerDTO();
        surrenderPlayerDTO.setPlayer(PlayerMapper.playerStatusToDTO(playerName, status));
        surrenderPlayerDTO.setCardStates(PlayingFieldMapper.cardStatesEntitiesToDTOList(cardStates));
        return surrenderPlayerDTO;

    }

    public static PlayerStatusDTO playerStatusToDTO(String playerName, PlayerStatus status) {
        return new PlayerStatusDTO(playerName, String.valueOf(status));
    }

    public static CardStatePlayerBalanceDTO changeBalanceCardStateToDTO(String playerName, Long balance, List<CardState> cardStates) {
        CardStatePlayerBalanceDTO changeBalanceCardStateDTO = new CardStatePlayerBalanceDTO(
                PlayerMapper.playerBalanceToDTO(playerName, balance),
                PlayingFieldMapper.cardStatesEntitiesToDTOList(cardStates));
        return changeBalanceCardStateDTO;

    }
}
