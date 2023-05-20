CREATE TABLE common_card
(
    id    varchar(255) PRIMARY KEY,
    text  varchar(255) not null,
    dtype varchar(255)
);

CREATE TABLE character
(
    id                varchar(255) primary key,
    profession        varchar(255),
    health            varchar(255),
    biology           varchar(255),
    extra_skills      varchar(255),
    human_qualities   varchar(255),
    hobby             varchar(255),
    phobias           varchar(255),
    luggage           varchar(255),
    opened_card_types text,
    card_to_open      text,
    CONSTRAINT profession_fk FOREIGN KEY (profession) REFERENCES common_card (id),
    CONSTRAINT health_fk FOREIGN KEY (health) REFERENCES common_card (id),
    CONSTRAINT biology_fk FOREIGN KEY (biology) REFERENCES common_card (id),
    CONSTRAINT extra_skills_fk FOREIGN KEY (extra_skills) REFERENCES common_card (id),
    CONSTRAINT human_qualities_fk FOREIGN KEY (human_qualities) REFERENCES common_card (id),
    CONSTRAINT hobby_fk FOREIGN KEY (hobby) REFERENCES common_card (id),
    CONSTRAINT phobias_fk FOREIGN KEY (phobias) REFERENCES common_card (id),
    CONSTRAINT luggage_fk FOREIGN KEY (luggage) REFERENCES common_card (id)
);

CREATE TABLE player
(
    id                varchar(255),
    role              varchar(255) NOT NULL,
    status            varchar(255) NOT NULL,
    name              varchar(255) unique,
    session_id        varchar(255),
    character         varchar(255),
    can_vote          boolean,
    double_vote       boolean,
    can_cote_for      text,
    vote_for_yourself boolean,
    CONSTRAINT player_pkey PRIMARY KEY (id),
    CONSTRAINT character_fk FOREIGN KEY (character) REFERENCES character (id)
);

CREATE TABLE session
(
    id             varchar(255),
    current_player varchar(255),
    state          varchar(255) NOT NULL,
    сatastrophe    varchar(255),
    CONSTRAINT session_pkey PRIMARY KEY (id)
);


CREATE TABLE catastrophe
(
    id   varchar(255) PRIMARY KEY,
    text varchar(5000) not null,
    name varchar(255)  not null
);


CREATE TABLE special_card
(
    id          varchar(255) PRIMARY KEY,
    text        varchar(1000) not null,
    name        varchar(255)  not null,
    dtype       varchar(255)  not null,
    interaction varchar(255)  not null
);

CREATE TABLE bunker_card
(
    id   varchar(255) PRIMARY KEY,
    text varchar(255) not null,
    name varchar(255) not null
);

CREATE TABLE message
(
    id       varchar(255),
    content  varchar(255),
    receiver varchar(255),
    sender   varchar(255),
    type     varchar(255) NOT NULL,
    CONSTRAINT message_pkey PRIMARY KEY (id)
);

CREATE TABLE votes
(
    player_name       varchar(255) primary key,
    votes_number      integer,
    session_id        varchar(255),
    doubled_votes_for text
);

CREATE TABLE character_2_special_card
(
    character_id    varchar(255),
    special_card_id varchar(255),
    CONSTRAINT character_id_fk FOREIGN KEY (character_id) REFERENCES character (id),
    CONSTRAINT special_card_id_fk FOREIGN KEY (special_card_id) REFERENCES special_card (id)
);

CREATE TABLE session_to_bunker_card
(
    session_id     varchar(255),
    bunker_card_id varchar(255),
    CONSTRAINT session_id_fk FOREIGN KEY (session_id) REFERENCES session (id),
    CONSTRAINT bunker_card_id_fk FOREIGN KEY (bunker_card_id) REFERENCES bunker_card (id)
);

CREATE TABLE session_to_message
(
    session_id varchar(255),
    message_id varchar(255),
    CONSTRAINT session_id_fk FOREIGN KEY (session_id) REFERENCES session (id),
    CONSTRAINT message_id_fk FOREIGN KEY (message_id) REFERENCES message (id)
);

CREATE TABLE session_to_players
(
    session_id varchar(255),
    player_id  varchar(255),
    CONSTRAINT session_id_fk FOREIGN KEY (session_id) REFERENCES session (id),
    CONSTRAINT player_id_fk FOREIGN KEY (player_id) REFERENCES player (id)
);
