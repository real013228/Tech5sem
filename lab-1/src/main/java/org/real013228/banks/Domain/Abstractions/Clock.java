package org.real013228.banks.Domain.Abstractions;

import org.real013228.banks.Domain.Entities.Actions.Actions;

/***
 * Interface, that works with time
 */
public interface Clock {
    Actions getDayChangedSubscribers();
    Actions getMonthChangedSubscribers();
    /***
     * Changing day method
     */
    void ChangeDay();
}
