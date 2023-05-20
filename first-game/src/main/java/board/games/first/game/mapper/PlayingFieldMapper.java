package board.games.first.game.mapper;

import board.games.first.game.dto.response.CardStateDTO;
import board.games.first.game.dto.response.PlayingFieldStateDTO;
import board.games.first.game.dto.response.PlayingFieldStaticDTO;
import board.games.first.game.entity.CardState;
import board.games.first.game.entity.CommonCard;
import board.games.first.game.entity.Player;
import board.games.first.game.entity.session.Session;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PlayingFieldMapper {

    public static PlayingFieldStateDTO buildPlayingFieldState(Session session) {
        PlayingFieldStateDTO playingFieldStateDTO = new PlayingFieldStateDTO();
        playingFieldStateDTO.setPlayers(PlayerMapper.entitiesToDTOList(session.getPlayers().stream()
                .sorted(Comparator.comparing(Player::getId))
                .collect(Collectors.toList())));
        playingFieldStateDTO.setState(String.valueOf(session.getState()));
        playingFieldStateDTO.setCurrentPlayer(session.getCurrentPlayer());
        playingFieldStateDTO.setMoveStatus(String.valueOf(session.getMoveStatus()));
        playingFieldStateDTO.setCardStates(PlayingFieldMapper.cardStatesEntitiesToDTOList(session.getCardStates()));
        playingFieldStateDTO.setChatHistory(ResultMessageMapper.entitiesToDTOList(session.getMessages()));
        return playingFieldStateDTO;
    }

    public static PlayingFieldStaticDTO buildPlayingFieldStatic(List<CommonCard> cards) {
        return new PlayingFieldStaticDTO(cards);
    }

    public static Map<String, CardStateDTO> cardStatesEntitiesToDTOList(List<CardState> cardStates) {
        return cardStates
                .stream()
                .collect(Collectors.toMap(
                        CardStateMapper::cardStateToCardId,
                        CardStateMapper::cardStateEntityToDTO, (a, b) -> b)
                );
    }

}
