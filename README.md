# Snake Game 🐍

A classic Snake game built with Java Swing, featuring multiple difficulty levels and smooth gameplay.

## Screenshots

| Game State          | Screenshot                                                                 |
|---------------------|----------------------------------------------------------------------------|
| **Main Menu**       | ![Start](https://github.com/user-attachments/assets/f8d39572-11e3-416b-ac46-3b1e28d40cbb)
 |
| **Game Paused**     | ![Paused](https://github.com/user-attachments/assets/0a25e7f3-7c01-4dc9-ab46-fe314dff5291)
 |
| **Game Over**       | ![Over](https://github.com/user-attachments/assets/1ffacbd2-10c5-4a6b-b65e-24ea3d6a15c0)
 |

## Features

- 🎮 Classic Snake gameplay with intuitive controls
- ⚙️ Three difficulty levels (Easy shown in menu)
- 🏆 Score tracking (visible in paused/game over screens)
- ⏸️ Pause/resume functionality (middle screenshot)
- 🔄 One-click restart after game over (last screenshot)
- 🎨 Clean retro-style UI

## How to Play

1. **From the Menu** (top screenshot):
   - Click "Play" to start
   - Change difficulty with "Level" button

2. **In-Game Controls**:
   - Arrow keys (↑↓←→) to move
   - `P` to pause (middle screenshot)
   - `Enter` to resume from pause

3. **After Game Over** (bottom screenshot):
   - `Space` to restart
   - See your final score and length

## Difficulty Levels

| Level  | Speed | Visual Indicator |
|--------|-------|------------------|
| Easy   | Slow  | Default in menu  |
| Medium | Medium| (Change via menu)|
| Hard   | Fast  | (Change via menu)|

## Installation & Running

```bash
# Clone the repository
git clone https://github.com/as-morat/Snake_Game_2.0-java.git

# Compile and run
cd snake-game-java
javac Main.java
java Main

