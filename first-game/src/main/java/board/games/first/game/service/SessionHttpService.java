package board.games.first.game.service;

import board.games.first.game.dto.response.PlayingFieldStateDTO;
import board.games.first.game.dto.response.PlayingFieldStaticDTO;
import board.games.first.game.entity.CardState;
import board.games.first.game.entity.CommonCard;
import board.games.first.game.entity.Player;
import board.games.first.game.entity.session.Session;
import board.games.first.game.entity.repo.SessionRepository;
import board.games.first.game.mapper.PlayingFieldMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static board.games.first.game.params.InitialGameValue.INITIAL_CURRENT_PLAYER_NAME;
import static board.games.first.game.params.InitialGameValue.INITIAL_MOVE_STATUS;
import static board.games.first.game.enums.PlayerRole.ADMIN;

@Service
public class SessionHttpService {
    private final SessionRepository sessionRepository;
    private final CommonCardService commonCardService;
    private final SessionCommonService sessionCommonService;
    private final PlayerService playerService;

    public SessionHttpService(SessionRepository sessionRepository, CommonCardService commonCardService, SessionCommonService sessionCommonService, PlayerService playerService) {
        this.sessionRepository = sessionRepository;
        this.commonCardService = commonCardService;
        this.sessionCommonService = sessionCommonService;
        this.playerService = playerService;
    }

    @Transactional
    public void saveSession(String sessionId, String playerName, List<CardState> cardStates) {
        playerService.savePlayer(sessionId, playerName, ADMIN);
        Player player = playerService.getPlayer(sessionId, playerName);

        Session session = new Session();
        session.setId(sessionId);
        session.setCurrentPlayer(INITIAL_CURRENT_PLAYER_NAME);
        session.setMoveStatus(INITIAL_MOVE_STATUS);
        session.setCardStates(cardStates);
        session.getPlayers().add(player);
        sessionRepository.save(session);
    }

    @Transactional(readOnly = true)
    public PlayingFieldStateDTO getStatePlayingField(String sessionId) {
        Session session = sessionCommonService.getSession(sessionId);

        return PlayingFieldMapper.buildPlayingFieldState(session);
    }

    @Transactional(readOnly = true)
    public PlayingFieldStaticDTO getStaticPlayingField() {
        List<CommonCard> cards = commonCardService.getAllCards();

        return PlayingFieldMapper.buildPlayingFieldStatic(cards);
    }

    @Transactional
    public void finishSession(String sessionId) {
        sessionRepository.deleteById(sessionId);
    }

    @Transactional
    public List<Session> getSessions() {
        return sessionRepository.findAll();
    }


}
