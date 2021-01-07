package io.x12fd16b.assignment.week11.assignment04.count;

/**
 * Distribute Counter.
 *
 * @author David Liu
 */
public interface DistributeCounter {

    /**
     * get quantity.
     *
     * @param resourceKey quantity resource key
     * @return the resource quantity
     */
    Integer get(String resourceKey);

    /**
     * decrement resource quantity.
     *
     * @param resourceKey quantity resource key
     * @param quantity    decrement quantity
     */
    void decrement(String resourceKey, Integer quantity);

    /**
     * increment resource quantity.
     *
     * @param resourceKey quantity resource key
     * @param quantity    increment quantity
     */
    void increment(String resourceKey, Integer quantity);
}
