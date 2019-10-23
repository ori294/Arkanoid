package utilities;

import game.Animation;

/**
 * Menu<T> : menu which holding tasks.
 * @author ori29
 * @param <T> Tasks (PlayGame, ShowHighScores, ExitGame).
 */
public interface Menu<T> extends Animation {

    /**
     * addSelection: adds a new selection to the menu.
     * @param key selection key.
     * @param message selection message.
     * @param returnVal the value of return once choosen.
     */
       void addSelection(String key, String message, T returnVal);
       /**
        * getStatus: returns the choosen task.
        * @return T == Task(PlayGame, ShowHighScores or ExitGame).
        */
       T getStatus();
        /**
         * addSubMenu: adds a new selection to the menu(with sub-menu).
         * @param key selection key.
         * @param message selection message.
         * @param subMenu the value of return once choosen.
         */
       void addSubMenu(String key, String message, Menu<T> subMenu);
    }
