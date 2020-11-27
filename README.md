![Схема БД](https://github.com/elvyyy/jwd-core-final/blob/master/img/jwd-db.png)

```mysql
CREATE SCHEMA IF NOT EXISTS jwd;
USE jwd;

DROP TABLE IF EXISTS crew_members;
CREATE TABLE crew_members(
	id SERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    rank ENUM('TRAINEE', 'SECOND_OFFICER', 'FIRST_OFFICER', 'CAPTAIN') NOT NULL,
    role ENUM('MISSION_SPECIALIST', 'FLIGHT_ENGINEER', 'PILOT', 'COMMANDER') NOT NULL,
    is_ready_for_next_mission BOOL NOT NULL DEFAULT TRUE
) COMMENT = 'CrewMembers';   

DROP TABLE IF EXISTS spaceships;
CREATE TABLE spaceships(
	id SERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    flight_distance BIGINT UNSIGNED ZEROFILL NOT NULL,
    is_ready_for_next_missions BOOL NOT NULL DEFAULT TRUE
);

DROP TABLE IF EXISTS shaceship_crews;
CREATE TABLE shaceship_crews(
	id SERIAL PRIMARY KEY,
	spaceship_id BIGINT UNSIGNED NOT NULL,
    role ENUM('MISSION_SPECIALIST', 'FLIGHT_ENGINEER', 'PILOT', 'COMMANDER') NOT NULL,
    number_of_crew_members SMALLINT UNSIGNED NOT NULL,
    FOREIGN KEY (spaceship_id) REFERENCES spaceships(id)
);

DROP TABLE IF EXISTS flight_missions;
CREATE TABLE flight_missions(
	id SERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    distance BIGINT UNSIGNED ZEROFILL NOT NULL,
    assigned_spaceship_id BIGINT UNSIGNED NOT NULL,
    mission_result ENUM('CANCELLED', 'FAILED', 'PLANNED', 'IN_PROGRESS', 'COMPLETED') NOT NULL,
    started_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    ended_at DATETIME NOT NULL
);

DROP TABLE IF EXISTS assigned_crew;
CREATE TABLE assigned_crew(
	id SERIAL PRIMARY KEY,
	flight_mission_id BIGINT UNSIGNED NOT NULL,
    crew_member_id BIGINT UNSIGNED NOT NULL,
    FOREIGN KEY (flight_mission_id) REFERENCES flight_missions(id),
    FOREIGN KEY (crew_member_id) REFERENCES crew_members(id)
);
```