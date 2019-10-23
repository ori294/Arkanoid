package utilities;

import java.io.File;
import java.io.IOException;

import biuoop.GUI;

/**
 * ExitGameTask: closes the game and saves the highscores before.
 * @author ori29
 *
 */
public class ExitGameTask implements Task<Void> {

    private GUI gui;
    private HighScoresTable highScores;
    private File filename;

    /**
     * ExitGameTask constructor.
     * @param g gui.
     * @param hs highscores table.
     * @param fn fine name.
     */
    public ExitGameTask(GUI g, HighScoresTable hs, File fn) {
        this.gui = g;
        this.highScores = hs;
        this.filename = fn;
    }
    /**
     * run: runs the task (closing the game).
     * @return null.
     */
    @Override
    public Void run() {
        try {
            this.highScores.save(this.filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.gui.close();
        System.exit(0);
        return null;
    }
}
