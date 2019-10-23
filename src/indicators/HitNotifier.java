package indicators;

/**
 * HitListener interface: registered to other objects as a notifier. knows to
 * add or remove an HitListener.
 *
 * @author ori29
 *
 */
public interface HitNotifier {

    /**
     * addHitListener: adds an HitListener.
     *
     * @param hl HitLestener.
     */
    void addHitListener(HitListener hl);

    /**
     * removeHitListener: removes an HitListener.
     *
     * @param hl HitLestener.
     */
    void removeHitListener(HitListener hl);

}
