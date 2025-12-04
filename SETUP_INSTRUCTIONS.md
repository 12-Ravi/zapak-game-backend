# Zapak Game Backend - Player Management & Leaderboard System

## 📋 Overview
Complete backend application for player registration, progress tracking, and leaderboard management built with Spring Boot, MySQL, and Docker.

## 🚀 Quick Start

### Prerequisites
- Docker & Docker Compose installed
- Java 17+ (only for local development without Docker)
- Maven 3.8+ (only for local development without Docker)

### Run with Docker (Recommended)

```bash
# Clone or download the project
cd zapak-game-backend

# Build and start all services
docker-compose up --build

# Stop services
docker-compose down

# Stop and remove all data
docker-compose down -v
```

The application will be available at: **http://localhost:8080**

## 📁 Project Structure

```
zapak-game-backend/
├── src/
│   └── main/
│       ├── java/com/reliancegames/zapak/
│       │   ├── ZapakGameBackendApplication.java
│       │   ├── config/
│       │   │   └── CacheConfig.java
│       │   ├── controller/
│       │   │   ├── PlayerController.java
│       │   │   └── LeaderboardController.java
│       │   ├── dto/
│       │   │   ├── PlayerRegistrationRequest.java
│       │   │   ├── PlayerProgressRequest.java
│       │   │   ├── ScoreSubmissionRequest.java
│       │   │   ├── LeaderboardEntry.java
│       │   │   └── ApiResponse.java
│       │   ├── entity/
│       │   │   ├── Player.java
│       │   │   ├── PlayerProgress.java
│       │   │   └── PlayerScore.java
│       │   ├── repository/
│       │   │   ├── PlayerRepository.java
│       │   │   ├── PlayerProgressRepository.java
│       │   │   └── PlayerScoreRepository.java
│       │   └── service/
│       │       ├── PlayerService.java
│       │       └── LeaderboardService.java
│       └── resources/
│           └── application.properties
├── docker-compose.yml
├── Dockerfile
├── init.sql
└── pom.xml
```

## 🎯 API Endpoints Documentation

### Base URL: `http://localhost:8080/api/v1`

---

### 1️⃣ Player Registration & Progress APIs

#### Register New Player
**POST** `/players/register`

Request Body:
```json
{
  "deviceId": "DEVICE_12345",
  "username": "player_john",
  "platform": "Android",
  "country": "US"
}
```

Response:
```json
{
  "success": true,
  "message": "Player registered successfully",
  "data": {
    "id": 1,
    "deviceId": "DEVICE_12345",
    "username": "player_john",
    "platform": "Android",
    "creationDate": "2024-12-04T10:30:00",
    "lastActiveTime": "2024-12-04T10:30:00",
    "country": "US"
  }
}
```

#### Save Player Progress
**POST** `/players/progress`

Request Body:
```json
{
  "playerId": 1,
  "level": 15,
  "rank": 3,
  "gold": 5000,
  "cash": 2500,
  "gems": 100,
  "rewardName": "Daily Bonus",
  "rewardId": "REWARD_001",
  "country": "US"
}
```

Response:
```json
{
  "success": true,
  "message": "Progress saved successfully",
  "data": {
    "id": 1,
    "playerId": 1,
    "level": 15,
    "rank": 3,
    "gold": 5000,
    "cash": 2500,
    "gems": 100,
    "rewardName": "Daily Bonus",
    "rewardId": "REWARD_001",
    "lastActiveTime": "2024-12-04T11:00:00",
    "country": "US",
    "updatedAt": "2024-12-04T11:00:00"
  }
}
```

#### Get Player Progress
**GET** `/players/{playerId}/progress`

Example: `/players/1/progress`

Response:
```json
{
  "success": true,
  "message": "Success",
  "data": {
    "id": 1,
    "playerId": 1,
    "level": 15,
    "rank": 3,
    "gold": 5000,
    "cash": 2500,
    "gems": 100,
    "rewardName": "Daily Bonus",
    "rewardId": "REWARD_001",
    "country": "US"
  }
}
```

#### Get Player Details
**GET** `/players/{playerId}`

Example: `/players/1`

Response:
```json
{
  "success": true,
  "message": "Success",
  "data": {
    "id": 1,
    "deviceId": "DEVICE_12345",
    "username": "player_john",
    "platform": "Android",
    "creationDate": "2024-12-04T10:30:00",
    "lastActiveTime": "2024-12-04T11:00:00",
    "country": "US"
  }
}
```

---

### 2️⃣ Leaderboard & Scoring APIs

#### Submit Player Score
**POST** `/leaderboard/scores`

Request Body:
```json
{
  "playerId": 1,
  "gameId": "GAME_RACING_01",
  "score": 95000,
  "country": "US"
}
```

Response:
```json
{
  "success": true,
  "message": "Score submitted successfully",
  "data": {
    "id": 1,
    "playerId": 1,
    "gameId": "GAME_RACING_01",
    "score": 95000,
    "timestamp": "2024-12-04T12:00:00",
    "country": "US"
  }
}
```

#### Get Global Leaderboard
**GET** `/leaderboard/global/{gameId}?limit=10`

Example: `/leaderboard/global/GAME_RACING_01?limit=10`

Response:
```json
{
  "success": true,
  "message": "Success",
  "data": [
    {
      "position": 1,
      "playerId": 5,
      "username": "pro_gamer",
      "score": 150000,
      "country": "US"
    },
    {
      "position": 2,
      "playerId": 3,
      "username": "speed_king",
      "score": 125000,
      "country": "UK"
    },
    {
      "position": 3,
      "playerId": 1,
      "username": "player_john",
      "score": 95000,
      "country": "US"
    }
  ]
}
```

#### Get Country Leaderboard
**GET** `/leaderboard/country/{gameId}/{country}?limit=10`

Example: `/leaderboard/country/GAME_RACING_01/US?limit=10`

Response:
```json
{
  "success": true,
  "message": "Success",
  "data": [
    {
      "position": 1,
      "playerId": 5,
      "username": "pro_gamer",
      "score": 150000,
      "country": "US"
    },
    {
      "position": 2,
      "playerId": 1,
      "username": "player_john",
      "score": 95000,
      "country": "US"
    }
  ]
}
```

---

## 🧪 Testing the APIs

### Using cURL

#### 1. Register a Player
```bash
curl -X POST http://localhost:8080/api/v1/players/register \
  -H "Content-Type: application/json" \
  -d '{
    "deviceId": "DEVICE_001",
    "username": "test_player",
    "platform": "iOS",
    "country": "IN"
  }'
```

#### 2. Save Progress
```bash
curl -X POST http://localhost:8080/api/v1/players/progress \
  -H "Content-Type: application/json" \
  -d '{
    "playerId": 1,
    "level": 10,
    "rank": 5,
    "gold": 1000,
    "cash": 500,
    "gems": 50,
    "country": "IN"
  }'
```

#### 3. Submit Score
```bash
curl -X POST http://localhost:8080/api/v1/leaderboard/scores \
  -H "Content-Type: application/json" \
  -d '{
    "playerId": 1,
    "gameId": "GAME_001",
    "score": 5000,
    "country": "IN"
  }'
```

#### 4. Get Global Leaderboard
```bash
curl http://localhost:8080/api/v1/leaderboard/global/GAME_001?limit=10
```

#### 5. Get Country Leaderboard
```bash
curl http://localhost:8080/api/v1/leaderboard/country/GAME_001/IN?limit=10
```

### Using Postman
1. Import the following base URL: `http://localhost:8080/api/v1`
2. Create requests for each endpoint as documented above
3. Set Content-Type to `application/json` for POST requests

---

## 🗄️ Database Schema

### Tables Created Automatically

#### `players` Table
| Column | Type | Description |
|--------|------|-------------|
| id | BIGINT (PK) | Auto-increment player ID |
| device_id | VARCHAR(255) | Unique device identifier |
| username | VARCHAR(255) | Player username |
| platform | VARCHAR(255) | iOS/Android/Web |
| creation_date | DATETIME | Registration timestamp |
| last_active_time | DATETIME | Last activity timestamp |
| country | VARCHAR(255) | Player country |

#### `player_progress` Table
| Column | Type | Description |
|--------|------|-------------|
| id | BIGINT (PK) | Auto-increment ID |
| player_id | BIGINT | Foreign key to players |
| level | INT | Current level |
| rank | INT | Current rank |
| gold | BIGINT | Gold currency |
| cash | BIGINT | Cash currency |
| gems | BIGINT | Gems currency |
| reward_name | VARCHAR(255) | Last reward name |
| reward_id | VARCHAR(255) | Last reward ID |
| last_active_time | DATETIME | Last activity |
| country | VARCHAR(255) | Country |
| updated_at | DATETIME | Last update time |

#### `player_scores` Table
| Column | Type | Description |
|--------|------|-------------|
| id | BIGINT (PK) | Auto-increment ID |
| player_id | BIGINT | Foreign key to players |
| game_id | VARCHAR(255) | Game identifier |
| score | BIGINT | Player score |
| timestamp | DATETIME | Score submission time |
| country | VARCHAR(255) | Player country |

---

## ⚙️ Technical Features

### ✅ Implemented
- ✅ Spring Boot 3.2.1 with Java 17
- ✅ Spring Data JPA for database operations
- ✅ MySQL 8.0 for persistence
- ✅ Caffeine Cache for performance optimization
- ✅ Docker & Docker Compose for easy deployment
- ✅ RESTful API design
- ✅ Input validation
- ✅ Proper error handling
- ✅ Database indexing for optimal queries
- ✅ Connection pooling (HikariCP)
- ✅ Automatic database schema creation

### 📦 Caching Strategy
- **Player Progress**: 10 minutes TTL
- **Global Leaderboard**: 10 minutes TTL
- **Country Leaderboard**: 10 minutes TTL

---

## 🔧 Configuration

### Database Configuration
Edit `docker-compose.yml` to change database credentials:
```yaml
environment:
  MYSQL_DATABASE: zapak_game_db
  MYSQL_USER: zapak_user
  MYSQL_PASSWORD: zapak_password_2024
```

### Application Port
Change in `docker-compose.yml`:
```yaml
ports:
  - "8080:8080"  # Change first port for external access
```

---

## 🛠️ Development

### Run Locally (Without Docker)

1. Start MySQL locally:
```bash
mysql -u root -p
CREATE DATABASE zapak_game_db;
CREATE USER 'zapak_user'@'localhost' IDENTIFIED BY 'zapak_password_2024';
GRANT ALL PRIVILEGES ON zapak_game_db.* TO 'zapak_user'@'localhost';
```

2. Update `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/zapak_game_db
```

3. Run application:
```bash
mvn clean install
mvn spring-boot:run
```

### Build JAR Only
```bash
mvn clean package
java -jar target/zapak-game-backend.jar
```

---

## 📊 Monitoring & Logs

### View Application Logs
```bash
docker-compose logs -f app
```

### View MySQL Logs
```bash
docker-compose logs -f mysql
```

### Check Database
```bash
docker exec -it zapak-mysql mysql -uzapak_user -pzapak_password_2024 zapak_game_db
```

---

## 🔍 Troubleshooting

### Port Already in Use
```bash
# Change ports in docker-compose.yml
ports:
  - "8081:8080"  # Use 8081 instead
  - "3307:3306"  # Use 3307 for MySQL
```

### Database Connection Issues
```bash
# Restart MySQL container
docker-compose restart mysql

# Check MySQL is healthy
docker-compose ps
```

### Clear All Data and Restart
```bash
docker-compose down -v
docker-compose up --build
```

---

## 📝 API Summary

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/v1/players/register` | Register new player |
| POST | `/api/v1/players/progress` | Save player progress |
| GET | `/api/v1/players/{id}/progress` | Get player progress |
| GET | `/api/v1/players/{id}` | Get player details |
| POST | `/api/v1/leaderboard/scores` | Submit score |
| GET | `/api/v1/leaderboard/global/{gameId}` | Global leaderboard |
| GET | `/api/v1/leaderboard/country/{gameId}/{country}` | Country leaderboard |

---

## 🎯 Requirements Checklist

### ✅ Objective 1: Player Registration & Progress
- ✅ Player registration API with device ID, username, platform, creation date
- ✅ Save progress API with level, rank, currencies (gold, cash, gems)
- ✅ Reward tracking (name and ID)
- ✅ Last active time and country tracking

### ✅ Objective 2: Leaderboard System
- ✅ Submit scores API
- ✅ Global leaderboard (top X players per game)
- ✅ Country-based leaderboard (top X players per country per game)
- ✅ Ranking system with positions

### ✅ Tech Stack Compliance
- ✅ Spring Boot (Latest stable with Java 17)
- ✅ Spring Data JPA
- ✅ MySQL for persistence
- ✅ Gradle alternative: Maven used (both supported)
- ✅ Caffeine caching mechanism

---

## 👨‍💻 Author
Built for **Zapak (Reliance Games)** Assignment

## 📄 License
This project is for assignment evaluation purposes.

---

## 🎉 You're All Set!

Start the application and test all APIs. Everything is containerized and ready to use! 🚀