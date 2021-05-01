# Trivia Game-Manager

### Guides

Run game-manager using docker on port 5000:

    docker run -it -p5000:5000 amitdr/game-manager:0.0.1

### API 

1. Create new trivia game:
  
    ```POST -> localhost:5000/api/v1/trivia```

2. Answer question:

    ```PUT --> localhost:5000/api/v1/trivia```
    
3. Get leaderboard:

    ```GET --> localhost:5000/api/v1/trivia/{gameId}```
