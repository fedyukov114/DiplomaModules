CREATE TABLE session_to_message
(
    session_id character varying(255) NOT NULL,
    message_id character varying(255) NOT NULL
);

ALTER TABLE session_to_message
    ADD CONSTRAINT session_to_message_session_fk FOREIGN KEY (session_id) REFERENCES session (id);
ALTER TABLE session_to_message
    ADD CONSTRAINT session_to_message_message_fk FOREIGN KEY (message_id) REFERENCES message (id);

CREATE TABLE session_to_card_state
(
    session_id    character varying(255) NOT NULL,
    card_state_id character varying(255) NOT NULL
);

ALTER TABLE session_to_card_state
    ADD CONSTRAINT session_to_card_state_session_fk FOREIGN KEY (session_id) REFERENCES session (id);
ALTER TABLE session_to_card_state
    ADD CONSTRAINT session_to_card_state_card_state_fk FOREIGN KEY (card_state_id) REFERENCES card_state (id);

CREATE TABLE session_to_players
(
    session_id character varying(255) NOT NULL,
    player_id  character varying(255) NOT NULL
);

ALTER TABLE session_to_players
    ADD CONSTRAINT session_to_players_session_fk FOREIGN KEY (session_id) REFERENCES session (id);
ALTER TABLE session_to_players
    ADD CONSTRAINT session_to_players_player_fk FOREIGN KEY (player_id) REFERENCES player (id);