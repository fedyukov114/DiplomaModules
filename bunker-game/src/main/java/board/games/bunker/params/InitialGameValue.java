package board.games.bunker.params;

import board.games.bunker.enums.PlayerStatus;

import static board.games.bunker.enums.PlayerStatus.PLAYING;

public class InitialGameValue {
    public static final Long INITIAL_BALANCE = 15000L;
    public static final Long INITIAL_CARD_FINE = null;
    public static final Integer INITIAL_CARD_LEVEL = 0;
    public static final String INITIAL_CARD_OWNER_NAME = null;
    public static final Integer INITIAL_PLAYER_POSITION = 0;
    public static final String INITIAL_CURRENT_PLAYER_NAME = null;
//    public static final MoveStatus INITIAL_MOVE_STATUS = null;
    public static final PlayerStatus INITIAL_PLAYER_STATUS = PLAYING;
}

