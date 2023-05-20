package board.games.first.game.mapper;

import board.games.first.game.exception.data.ApiError;
import org.springframework.http.ResponseEntity;

public class ResponseErrorMapper {

    public static ResponseEntity errorToEntity(ApiError error) {
        return new ResponseEntity(error, error.getError());
    }
}
