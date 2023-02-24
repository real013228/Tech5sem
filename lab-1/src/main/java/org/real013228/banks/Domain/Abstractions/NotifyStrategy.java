package org.real013228.banks.Domain.Abstractions;

/***
 * Interface, that client use to be notified
 */
public interface NotifyStrategy {
    /***
     * Notifying client method
     * @param msg message, that client will see, when he will be notified
     */
    void notify(String msg);
}
