package utilities;

import java.io.File;
import java.io.IOException;

import game.AnimationRunner;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

/**
 * Ass7Game: the main of the game.
 * @author ori29
 *
 */
public class Ass7Game {

    /**
     * the main of the class, runs the game.
     * @param args a list of numbers which indicates what levels should be played.
     */
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", 800, 600);
        Sleeper sleeper = new Sleeper();

        File filename = new File("highscores.txt");
        HighScoresTable highscoresTable = null;

        if (filename.exists()) {
            try {
                highscoresTable = HighScoresTable.loadFromFile(filename);
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                filename.createNewFile();
                highscoresTable = new HighScoresTable(9);
                highscoresTable.save(filename);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        KeyboardSensor keyboard = gui.getKeyboardSensor();
        AnimationRunner animation = new AnimationRunner(gui, sleeper);
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>(keyboard, animation);
        Menu<Task<Void>> subMenu = new MenuAnimation<Task<Void>>(keyboard, animation);

        String path = null;
        if (args.length < 0) {
            path = args[0];
        }

        subMenu.addSelection("e", "Play the game - easy mode", new PlayGameTask(animation, keyboard, gui,
                sleeper, highscoresTable, 1, path));
        subMenu.addSelection("h", "Play the game - hard mode", new PlayGameTask(animation, keyboard, gui,
                sleeper, highscoresTable, 2, path));
        subMenu.addSelection("o", "Play the game - Ori's levels", new PlayGameTask(animation, keyboard, gui,
                sleeper, highscoresTable, 3, path));
        menu.addSelection("s", "Play the game", (Task<Void>) subMenu);
        menu.addSelection("h", "Hall of fame", new ShowHiScoresTask(animation,
                new HighScoresAnimation(highscoresTable), keyboard));
        menu.addSelection("q", "Exit game", new ExitGameTask(gui, highscoresTable, filename));

        while (true) {
            animation.run(menu);
            Task<Void> task = menu.getStatus();

            if (task != null) {
                task.run();

            } else if (task == null) {
                System.out.print("Task is null");
            }
        }
    }
}