CREATE DATABASE IF NOT EXISTS zapak_game_db;
USE zapak_game_db;

GRANT ALL PRIVILEGES ON zapak_game_db.* TO 'zapak_user'@'%';
FLUSH PRIVILEGES;