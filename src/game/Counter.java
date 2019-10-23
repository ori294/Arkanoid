package game;

/**
 * A simple counter class that can count anything.
 * @author ori29
 *
 */
public class Counter {

    private int count;

    /**
     * constructor with number.
     * @param number a number.
     */
    public Counter(int number) {
        this.count = number;
    }

    /**
     * constructor without number: sets to zero.
     */
    public Counter() {
        this.count = 0;
    }

    /**
     * increase: adds a number to the counter.
     * @param number a number(int).
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * decrease: decrease a number from the counter.
     * @param number a number(int).
     */
    public void decrease(int number) {
        this.count -= number;
    }

    /**
     * a value getter.
     * @return the value in the counter.
     */
    public int getValue() {
        return this.count;
    }
}
