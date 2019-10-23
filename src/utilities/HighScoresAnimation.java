package utilities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import game.Animation;
import utilities.HighScoresTable.ScoreInfo;
import biuoop.DrawSurface;

/**
 * HighScoresAnimation: shows the highscore table.
 * @author ori29
 */
public class HighScoresAnimation implements Animation {

    private HighScoresTable highScores;
    private List<ScoreInfo> scoreList;

    /**
     * HighScoresAnimation constructor.
     * @param scores HighScoreTable.
     */
     public HighScoresAnimation(HighScoresTable scores) {
         this.highScores = scores;
         this.scoreList = new ArrayList<ScoreInfo>();
         this.refreshHighScores();
     }

     /**
      * doOneFrame: plays one frame.
      * @param d a DrawSurface.
      */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.refreshHighScores();
        d.setColor(java.awt.Color.BLACK);
        d.drawText(100, 100, "High Scores", 42);
         for (int i = 0; i < this.scoreList.size(); i++) {
             d.drawText(100, (140 + 20) + 40 * i,
                     "Player Name: " + this.scoreList.get(i).getName(), 20);
             d.drawText(500, (140 + 20) + 40 * i,
                     "Score: " + this.scoreList.get(i).getScoreString(), 20);
         }
    }
    /**
     * refreshHighScores: loads the highscores again.
     */
    public void refreshHighScores() {
         this.scoreList.clear();
         File filename = new File("highscores.txt");
         try {
             if (filename.exists()) {
                 this.highScores = HighScoresTable.loadFromFile(filename);
             } else {
                 return;
             }
        } catch (ClassNotFoundException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         for (int i = 0; i < this.highScores.getHighScores().size(); i++) {
             this.scoreList.add(this.highScores.getHighScores().get(i));
         }
    }
    /**
     * this method isn't being used.
     * @return false.
     */
    @Override
    public boolean shouldStop() {
        return false;
    }
}
