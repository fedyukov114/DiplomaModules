package board.games.bunker.mapper;

import board.games.bunker.dto.response.KickedPlayerDTO;
import board.games.bunker.dto.response.PlayerCardsDTO;
import board.games.bunker.dto.response.PlayerDTO;
import board.games.bunker.dto.response.PlayerStatusDTO;
import board.games.bunker.entity.Character;
import board.games.bunker.entity.Player;
import board.games.bunker.enums.PlayerStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerMapper {

    public static List<PlayerDTO> entitiesToDTOList(List<Player> players) {
        return players.stream().map(PlayerMapper::entityToPLayerDTO).collect(Collectors.toList());
    }

    public static PlayerDTO entityToPLayerDTO(Player player) {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setId(player.getId());
        playerDTO.setName(player.getName());
        playerDTO.setRole(player.getRole().toString());
        playerDTO.setStatus(player.getStatus());
        return playerDTO;
    }

    public static PlayerStatusDTO playerStatusToDTO(String playerName, PlayerStatus status) {
        return new PlayerStatusDTO(playerName, String.valueOf(status));
    }

    public static PlayerStatusDTO playerStatusToDTO(Player player) {
        return new PlayerStatusDTO(player.getName(), String.valueOf(player.getStatus()));
    }

    public static KickedPlayerDTO surrenderPlayerToDTO(String playerName, PlayerStatus status, Character playerCards) {
        KickedPlayerDTO kickedPlayerDTO = new KickedPlayerDTO();
        kickedPlayerDTO.setPlayer(PlayerMapper.playerStatusToDTO(playerName, status));
        kickedPlayerDTO.setPlayerCards(characterToPlayerCardsDTO(playerCards));
        return kickedPlayerDTO;

    }

    public static KickedPlayerDTO surrenderPlayerToDTO(Player player) {
        KickedPlayerDTO kickedPlayerDTO = new KickedPlayerDTO();
        kickedPlayerDTO.setPlayer(playerStatusToDTO(player));
        kickedPlayerDTO.setPlayerCards(characterToPlayerCardsDTO(player.getCharacter()));
        return kickedPlayerDTO;

    }

    public static PlayerCardsDTO characterToPlayerCardsDTO(Character character) {
        PlayerCardsDTO playerCardsDTO = new PlayerCardsDTO();
        playerCardsDTO.setProfession(character.getProfession());
        playerCardsDTO.setHealth(character.getHealth());
        playerCardsDTO.setBiology(character.getBiology());
        playerCardsDTO.setExtraSkills(character.getExtraSkills());
        playerCardsDTO.setHumanQualities(character.getHumanQualities());
        playerCardsDTO.setHobby(character.getHobby());
        playerCardsDTO.setPhobias(character.getPhobias());
        playerCardsDTO.getSpecialCard().addAll(character.getSpecialCard());
        playerCardsDTO.setLuggage(character.getLuggage());
        return playerCardsDTO;
    }

}
