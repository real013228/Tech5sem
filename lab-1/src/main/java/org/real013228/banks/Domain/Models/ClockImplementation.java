package org.real013228.banks.Domain.Models;

import org.real013228.banks.Domain.Abstractions.Clock;
import org.real013228.banks.Domain.Entities.Actions.Actions;

public class ClockImplementation implements Clock {
    private int day;

    public ClockImplementation() {
        this.day = 0;
        this.dayChangedSubscribers = new Actions();
        this.monthChangedSubscribers = new Actions();
    }

    private Actions dayChangedSubscribers;
    private Actions monthChangedSubscribers;

    @Override
    public Actions getDayChangedSubscribers() {
        return dayChangedSubscribers;
    }

    @Override
    public Actions getMonthChangedSubscribers() {
        return monthChangedSubscribers;
    }

    @Override
    public void ChangeDay() {
        day = (day + 1) % 30;
        dayChangedSubscribers.invoke(true);
        if (day % 30 == 0)
            monthChangedSubscribers.invoke(true);
    }

}
