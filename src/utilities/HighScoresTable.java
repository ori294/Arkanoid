package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * HighScoresTable: holds the table with the highscores.
 * @author ori29
 *
 */
public class HighScoresTable implements Serializable {

    private static final long serialVersionUID = 1L;
    private int tableSize;
    private List<ScoreInfo> scoreList;

    /**
     * HighScoresTable constructor.
     * @param size the size of the table.
     */
    public HighScoresTable(int size) {
        this.tableSize = size;
        this.scoreList = new ArrayList<ScoreInfo>();
    }

    /**
     * add: adds a score to the table.
     * @param score ScoreInfo.
     */
    public void add(ScoreInfo score) {
        this.scoreList.add(score);
        this.sortHighScores();
    }

    /**
     * size: return the size of the table.
     * @return the size of the table.
     */
    public int size() {
        return this.tableSize;

    }

    /**
     * getHighScores: returns the highscores table.
     * @return the List of the scores.
     */
    public List<ScoreInfo> getHighScores() {
        return this.scoreList;
    }

    /**
     * isInHallOfFame: checks if a score is worthy getting in the highscores.
     * @param score a score, int.
     * @return boolean value.
     */
    public boolean isInHallOfFame(int score) {

        if (this.scoreList.size() < this.tableSize) {
            return true;
        }

        for (int i = 0; i < this.tableSize && i < this.scoreList.size(); i++) {
            if (this.scoreList.get(i).playerScore < score) {
                return true;
            }
        }
        return false;
    }
    /**
     * sortHighScores: sorts the highscores.
     */
    public void sortHighScores() {

        for (int j = 0; j < this.scoreList.size(); j++) {
            for (int i = 0; i < this.scoreList.size() - 1; i++) {

                if (this.scoreList.get(i).getScore() < this.scoreList.get(i + 1).getScore()) {
                    Collections.swap(scoreList, i, i + 1);
                }
            }

            if (j > this.tableSize) {
                this.scoreList.remove(j);
            }
        }
    }

    /**
     * clear: clears the highscores.
     */
    public void clear() {
        this.scoreList.clear();
    }

    /**
     * load: loads the highscores from a file.
     * @param filename the file name.
     * @throws IOException - reading problems.
     * @throws ClassNotFoundException - class not found.
     */
    public void load(File filename) throws IOException, ClassNotFoundException {

        List<ScoreInfo> tempList = new ArrayList<ScoreInfo>();
        try {
            FileInputStream fis = new FileInputStream(filename.getName());
            ObjectInputStream ois = new ObjectInputStream(fis);
            tempList = ((HighScoresTable) ois.readObject()).getHighScores();

            ois.close();
            fis.close();

        } catch (IOException e) {
            System.out.println("Failed to read");
            e.printStackTrace();
        } finally {
            this.scoreList.clear();
            for (int i = 0; i < tempList.size(); i++) {
                this.scoreList.add(tempList.get(i));
            }
        }
    }
    /**
     * save: saves the highscores into a file.
     * @param filename the file name.
     * @throws IOException - throws when there's a problem saving.
     */
    public void save(File filename) throws IOException {

        try {
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject((HighScoresTable) this);
            out.close();
            fileOut.close();

        } catch (IOException ex) {
            System.out.println("Failed to save");
            ex.printStackTrace();
        }
    }
    /**
     * loadFromFile: loads HighScores from a file.
     * @param filename the file name.
     * @return HighScoresTable.
     * @throws IOException - reading problems.
     * @throws ClassNotFoundException - class not found.
     */
    public static HighScoresTable loadFromFile(File filename) throws IOException, ClassNotFoundException {

        FileInputStream fis = new FileInputStream(filename.getName());
        ObjectInputStream ois = new ObjectInputStream(fis);
        HighScoresTable highScores = (HighScoresTable) ois.readObject();

        ois.close();
        fis.close();

        return highScores;
    }

    /**
     * ScoreInfo class: holds info about a score (player name, score).
     * @author ori29
     *
     */
    public class ScoreInfo implements Serializable {

        private static final long serialVersionUID = 1L;
        private String playerName;
        private int playerScore;

        /**
         * ScoreInfo constructor.
         * @param name player name.
         * @param score player's score.
         */
        public ScoreInfo(String name, int score) {
            this.playerName = name;
            this.playerScore = score;
        }

        /**
         * getName: returns the player name.
         * @return player name.
         */
        public String getName() {
            return this.playerName;
        }

        /**
         * getScore: returns the player score.
         * @return the score.
         */
        public int getScore() {
            return this.playerScore;
        }
        /**
         * getScoreString: converts the score to string.
         * @return a string with the player score.
         */
        public String getScoreString() {
            return String.valueOf(playerScore);
        }
    }
}