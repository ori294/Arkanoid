package utilities;

import java.util.ArrayList;
import java.util.List;

import game.AnimationRunner;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * MenuAnimation: shows the menu.
 * @author ori29
 *
 * @param <T> T == Task(PlayGame, ShowHighScores or ExitGame).
 */
public class MenuAnimation<T> implements Menu<T>, Task<T> {

    private KeyboardSensor keyboard;
    private AnimationRunner animation;
    private List<MenuSelection> selectionList;

    /**
     * MenuAnimation constructor.
     *
     * @param sensor    a KeyBoardSensor.
     * @param anime an Animation, a decorator to this class.
     */
    public MenuAnimation(KeyboardSensor sensor, AnimationRunner anime) {
        this.keyboard = sensor;
        this.animation = anime;
        this.selectionList = new ArrayList<MenuSelection>();
    }

    /**
     * doOneFrame: runs one frame.
     *
     * @param d a DrawSurface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {

        if (!this.shouldStop()) {
            d.setColor(java.awt.Color.BLACK);
            d.drawText(100, 100, "Main Menu", 58);

            for (int i = 0; i < this.selectionList.size(); i++) {
                d.drawText(100, (140 + 20) + 40 * i,
                        "(" + this.selectionList.get(i).getKey() + ") " + this.selectionList.get(i).getText(), 20);
            }
        }
    }

    /**
     * Asks the method if the animation should stop (checks if a key is pressed).
     * @return a boolean value.
     */
    @Override
    public boolean shouldStop() {

        for (int i = 0; i < this.selectionList.size(); i++) {
            if (this.keyboard.isPressed(this.selectionList.get(i).getKey())) {
                return true;
            }
        }
        return false;
    }

    /**
     * addSelection: adds a selection to the menu.
     * @param key the menu key.
     * @param message the message.
     * @param returnVal the return value (a Task).
     */
    @Override
    public void addSelection(String key, String message, T returnVal) {
        MenuSelection selection = new MenuSelection(key, message, returnVal);
        this.selectionList.add(selection);
    }

    /**
     * getStatus: gets the choosen task.
     * @return T (a Task).
     */
    @Override
    public T getStatus() {

        for (int i = 0; i < this.selectionList.size(); i++) {
            if (this.keyboard.isPressed(this.selectionList.get(i).getKey())) {
                return this.selectionList.get(i).getTask();
            }
        }
        return null;
    }
    /**
     * addSubMenu: adds a sub menu to the menu.
     * @param key the menu key.
     * @param message the message.
     * @param subMenu the return value (a subMenu).
     */
    @Override
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
        MenuSelection selection = new MenuSelection(key, message, subMenu);
        this.selectionList.add(selection);
    }
    /**
     * getStatus: run the choosen task.
     * @return T (null).
     */
    @Override
    public T run() {
        this.animation.run(this);
        @SuppressWarnings("unchecked")
        Task<T> task = (Task<T>) this.getStatus();
        if (task != null) {
            task.run();
        }
        return null;
    }
    /**
     * MenuSelection: hold the key, the message and the text of the
     *  selection in the menu.
     * @author ori29
     *
     */
    public class MenuSelection {

        private String key;
        private String selectionText;
        private T selectionTask;
        private Menu<T> subMenu;
        private boolean hasSubMenu;

        /**
         * MenuSelection constructor.
         * @param k the menu key.
         * @param txt the message.
         * @param returnVal the return value (a Task).
         */
        public MenuSelection(String k, String txt, T returnVal) {
            this.key = k;
            this.selectionText = txt;
            this.hasSubMenu = false;
            this.selectionTask = returnVal;
        }
        /**
         * MenuSelection constructor.
         * @param k the menu key.
         * @param txt the message.
         * @param sm the return value (a sub menu).
         */
        public MenuSelection(String k, String txt, Menu<T> sm) {
            this.key = k;
            this.selectionText = txt;
            this.hasSubMenu = true;
            this.subMenu = sm;
        }

        /**
         * getTask returns the task.
         * @return the task.
         */
        public T getTask() {
            return this.selectionTask;
        }
        /**
         * getKey returns the key.
         * @return the key.
         */
        public String getKey() {
            return this.key;
        }
        /**
         * getText returns the message.
         * @return the message.
         */
        public String getText() {
            return this.selectionText;
        }
        /**
         * returns true if the selection holds a sub menu.
         * @return boolean value.
         */
        public boolean hasSub() {
            return this.hasSubMenu;
        }
        /**
         * getSubMenu returns the sub menu.
         * @return the sub menu.
         */
        public Menu<T> getSubMenu() {
            return this.subMenu;
        }

    }
}
