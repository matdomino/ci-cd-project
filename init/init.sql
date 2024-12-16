CREATE TABLE Game (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    title VARCHAR(255) NOT NULL CHECK (length(title) > 0),
    category VARCHAR(255) NOT NULL CHECK (length(category) > 0),
    release_date TIMESTAMP NOT NULL
);

CREATE TABLE Achievement (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    type VARCHAR(255) NOT NULL CHECK (length(type) > 0),
    description VARCHAR(255) NOT NULL CHECK (length(description) > 0),
    achievement_date TIMESTAMP NOT NULL,
    game_id UUID NOT NULL,
    CONSTRAINT fk_game FOREIGN KEY (game_id) REFERENCES Game(id) ON DELETE CASCADE
);
