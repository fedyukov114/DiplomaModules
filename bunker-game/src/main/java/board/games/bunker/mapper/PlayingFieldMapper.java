package board.games.bunker.mapper;

import board.games.bunker.dto.response.PlayingFieldStateDTO;
import board.games.bunker.entity.Player;
import board.games.bunker.entity.session.Session;

import java.util.Comparator;
import java.util.stream.Collectors;

public class PlayingFieldMapper {

    public static PlayingFieldStateDTO buildPlayingFieldState(Session session) {
        PlayingFieldStateDTO playingFieldStateDTO = new PlayingFieldStateDTO();
        playingFieldStateDTO.setPlayers(PlayerMapper.entitiesToDTOList(session.getPlayers().stream()
                .sorted(Comparator.comparing(Player::getId))
                .collect(Collectors.toList())));
        playingFieldStateDTO.setState(String.valueOf(session.getState()));
        playingFieldStateDTO.setCurrentPlayer(session.getCurrentPlayer());
//        playingFieldStateDTO.setMoveStatus(String.valueOf(session.getMoveStatus()));
//        playingFieldStateDTO.setChatHistory(ResultMessageMapper.entitiesToDTOList(session.getMessages()));
        return playingFieldStateDTO;
    }

}
