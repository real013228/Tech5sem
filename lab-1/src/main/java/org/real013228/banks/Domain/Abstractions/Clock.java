package org.real013228.banks.Domain.Abstractions;

import org.real013228.banks.Domain.Entities.Actions.Actions;

/***
 * Interface, that works with time
 */
public interface Clock {
    /***
     * getter merhod
     * @return actions of day changed subscribers
     */
    Actions getDayChangedSubscribers();

    /***
     * getter method
     * @return actions of month changed subscribers
     */
    Actions getMonthChangedSubscribers();
    /***
     * Changing day method
     */
    void ChangeDay();
}
