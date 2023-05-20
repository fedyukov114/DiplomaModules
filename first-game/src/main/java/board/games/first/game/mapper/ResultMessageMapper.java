package board.games.first.game.mapper;

import board.games.first.game.dto.response.ResultMessageDTO;
import board.games.first.game.entity.Message;

import java.util.List;
import java.util.stream.Collectors;

public class ResultMessageMapper {

    public static List<ResultMessageDTO> entitiesToDTOList(List<Message> messages) {
        return messages
                .stream()
                .map(ResultMessageMapper::entityToMessageDTO)
                .collect(Collectors.toList());
    }

    private static ResultMessageDTO entityToMessageDTO(Message message) {
        return new ResultMessageDTO(message.getSender(), message.getContent());
    }
}
