package levels;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import blocksreader.BlocksDefinitionReader;
import blocksreader.BlocksFromSymbolsFactory;
import blocksreader.ColorsParser;
import game.Background;
import game.Block;
import game.ImageSprite;
import game.LevelInformation;
import game.Rectangle;
import game.Velocity;

/**
 * LevelFromIO: gets lines of texts and building itself into LevelInformation.
 * implements: LevelInformation.
 * @author ori29
 */
public class LevelFromIO implements LevelInformation {

    private int width = 800;
    private int height = 600;
    private int ballsNum;
    private List<Velocity> velList;
    private List<Block> blockList;
    private int padSpeed;
    private int padWidth;
    private String levelName;
    private int blockToRemove;
    private Background back;
    private String blockDef;
    private int blockStartX;
    private int blockStartY;
    private int rowHight;
    private BlocksFromSymbolsFactory factory;
    private int rowNum;

    /**
     * LevelFromIO consturctor.
     */
    public LevelFromIO() {
        this.blockList = new ArrayList<Block>();
        this.rowNum = 0;
        this.ballsNum = 0;
    }
    /**
     * numberOfBalls: returns the num of balls.
     * @return the ball number.
     */
    @Override
    public int numberOfBalls() {
        return this.ballsNum;
    }
    /**
     * initialBallVelocities: return the ball velocities.
     * @return the velocities.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        return this.velList;
    }
    /**
     * paddleSpeed: return the paddle speed.
     * @return the paddle speed.
     */
    @Override
    public int paddleSpeed() {
        return this.padSpeed;
    }
    /**
     * paddleWidth: return the paddle width.
     * @return the paddle width.
     */
    @Override
    public int paddleWidth() {
        return this.padWidth;
    }
    /**
     * levelName: return the level name.
     * @return the level name.
     */
    @Override
    public String levelName() {
        return this.levelName;
    }

    /**
     * getBackground: returns the Background.
     * @return background.
     */
    @Override
    public Background getBackground() {
        return this.back;
    }

    /**
     * blocks: returns a list of the level blocks.
     * @return the list of the blocks.
     */
    @Override
    public List<Block> blocks() {
        return this.blockList;
    }

    /**
     * numberOfBlocksToRemove: return the number of blocks to remove.
     * @return the number of blocks to remove.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return this.blockToRemove;
    }

    /**
     * ballColor: returns the color of the ball.
     * @return the ball color.
     */
    @Override
    public Color ballColor() {
        return java.awt.Color.WHITE;
    }

    /**
     * addVelocity: adds velocities from a string line.
     * @param line a string.
     */
    public void addVelocity(String line) {

        this.velList = new ArrayList<Velocity>();

        String vel = line.replaceFirst("ball_velocities:", "");
        String[] velocities = vel.split(" ");

        for (String str : velocities) {
            String[] angleSpeed = str.split(",");
            int angle = Integer.parseInt(angleSpeed[0]);
            int speed = Integer.parseInt(angleSpeed[1]);
            Velocity ballVel = Velocity.fromAngleAndSpeed(angle, speed);
            this.velList.add(ballVel);
            this.ballsNum += 1;
        }
    }

    /**
     * setPaddleSpeed: sets paddle speed from a line.
     * @param line a string line.
     */
    public void setPaddleSpeed(String line) {
        String padSpeed1 = line.replaceFirst("paddle_speed:", "");
        this.padSpeed = Integer.parseInt(padSpeed1);
    }

    /**
     * setpaddleWidth: sets the paddle width from a line.
     * @param line a string line.
     */
    public void setpaddleWidth(String line) {
        String padW = line.replaceFirst("paddle_width:", "");
        this.padWidth = Integer.parseInt(padW);
    }

    /**
     * setLvlName: sets level name from a line.
     * @param line a string line.
     */
    public void setLvlName(String line) {
        String lvlName = line.replaceFirst("level_name:", "");
        this.levelName = lvlName;
    }

    /**
     * setNumOfBlock: sets the number of blocks from a line.
     * @param line a string line.
     */
    public void setNumOfBlock(String line) {
        String num = line.replaceFirst("num_blocks:", "");
        this.blockToRemove = Integer.parseInt(num);
    }
    /**
     * blockX: sets the block starting X pos from a line.
     * @param line a string line.
     */
    public void blockX(String line) {
        String num = line.replaceFirst("blocks_start_x:", "");
        this.blockStartX = Integer.parseInt(num);
    }
    /**
     * blockY: sets the block starting Y pos from a line.
     * @param line a string line.
     */
    public void blockY(String line) {
        String num = line.replaceFirst("blocks_start_y:", "");
        this.blockStartY = Integer.parseInt(num);
    }
    /**
     * rowsHights: sets the rows hight.
     * @param line a string line.
     */
    public void rowsHights(String line) {
        String num = line.replaceFirst("row_height:", "");
        this.rowHight = Integer.parseInt(num);
    }
    /**
     * blockDefenitions: sets the block definition file from a line.
     * @param line a string line.
     */
    public void blockDefenitions(String line) {
        String txt = line.replaceFirst("block_definitions:", "");
        this.blockDef = txt;

        BufferedReader reader = null;
        InputStream fis = null;

        fis = ClassLoader.getSystemClassLoader().getResourceAsStream(this.blockDef);
        reader = new BufferedReader(new InputStreamReader(fis));

        try {
            this.factory = BlocksDefinitionReader.fromReader(reader);
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * addBlocks: adds a row of block from the read line.
     * @param line a line (string).
     */
    public void addBlocks(String line) {
        char[] chars = line.toCharArray();
        this.rowNum++;

        for (int i = 0; i < chars.length; i++) {
            String s = String.valueOf(chars[i]);
            int space = 0;

            if (this.factory.isSpaceSymbol(s)) {
                space += this.factory.getSpaceWidth(s);

            } else if (this.factory.isBlockSymbol(s)) {
                int wd = this.factory.getBlockWidth(s);
                this.blockList.add(this.factory.getBlock(s, this.blockStartX + wd * i + space + i,
                        this.blockStartY + (this.rowHight + 1) * this.rowNum + 1));
                space = 0;
            }
        }
    }
    /**
     * sets the background from a line.
     * @param line string.
     */
    public void background(String line) {

        if (line.contains("color(RGB")) {

            this.back = new Background();

            String trimmed1 = line.substring(line.indexOf("(") + 5, line.indexOf(")"));
            String[] colors = trimmed1.split(",");

            int red = Integer.parseInt(colors[0]);
            int green = Integer.parseInt(colors[1]);
            int blue = Integer.parseInt(colors[2]);
            java.awt.Color backColor = new java.awt.Color(red, green, blue);

            Rectangle backg = new Rectangle(0, 0, this.width, this.height);
            Block backR = new Block(backg, backColor, backColor , 0);
            this.back.addToBackGround(backR);

        } else if (line.contains("color")) {
            this.back = new Background();

            String trimmed1 = line.substring(line.indexOf("(") + 1, line.indexOf(")"));
            Rectangle backg = new Rectangle(0, 0, this.width, this.height);
            java.awt.Color backColor = ColorsParser.stringToColor(trimmed1);
            Block backR = new Block(backg, backColor, backColor , 0);
            this.back.addToBackGround(backR);

        } else if (line.contains("image")) {

            this.back = new Background();

            String trimmed1 = line.substring(line.indexOf("(") + 1, line.indexOf(")"));
            ImageSprite img = new ImageSprite(trimmed1, 0, 0);
            this.back.addToBackGround(img);
        }
    }
}
