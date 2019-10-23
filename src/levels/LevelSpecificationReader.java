package levels;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import game.LevelInformation;

/**
 * LevelSpecificationReader: reads the level definitions from a given txt file.
 * @author ori29
 */
public class LevelSpecificationReader {

    private List<LevelInformation> levels;

    /**
     * LevelSpecificationReader constructor.
     */
    public LevelSpecificationReader() {
        this.levels = new ArrayList<LevelInformation>();
    }

    /**
     * fromReader: reads the level definitions, and returns a list of the levels.
     * @param fileReader a BufferedReader.
     * @return List of levels.
     */
    public List<LevelInformation> fromReader(BufferedReader fileReader) {

        BufferedReader br = fileReader;

        try {

            String line;

            while ((line = br.readLine()) != null) {
                if (line.contains("START_LEVEL")) {
                    LevelFromIO level = new LevelFromIO();

                    while ((line = br.readLine()) != null) {
                        if (line.contains("START_BLOCKS")) {
                            while (!line.contains("END_BLOCKS")) {
                                line = br.readLine();
                                level.addBlocks(line);
                            }
                        } else if (line.contains("level_name")) {
                            level.setLvlName(line);
                        } else if (line.contains("ball_velocities")) {
                            level.addVelocity(line);
                        } else if (line.contains("background")) {
                            level.background(line);
                        } else if (line.contains("paddle_speed")) {
                            level.setPaddleSpeed(line);
                        } else if (line.contains("paddle_width")) {
                            level.setpaddleWidth(line);
                        } else if (line.contains("block_definitions")) {
                            level.blockDefenitions(line);
                        } else if (line.contains("blocks_start_x")) {
                            level.blockX(line);
                        } else if (line.contains("blocks_start_y")) {
                            level.blockY(line);
                        } else if (line.contains("row_height")) {
                            level.rowsHights(line);
                        } else if (line.contains("num_blocks")) {
                            level.setNumOfBlock(line);
                        } else if (line.contains("END_LEVEL")) {
                            this.levels.add(level);
                            break;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.levels;
    }
}
