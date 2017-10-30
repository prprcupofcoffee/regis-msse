package com.threetenterprises.mylibrary.mylibraryapp.application.messaging;

import java.util.ArrayList;

/**
 * Maintains a list of objects interested in receiving {@link AppMessage} messages.
 */

public class Messenger<T extends AppMessage> {
    private final ArrayList<MessageSink<T>> mSubscribers = new ArrayList<>();

    /**
     * Add a listener for {@link AppMessage} messages.
     * @param messageSink The new listener
     */
    public void addListener(MessageSink<T> messageSink) {
        mSubscribers.add(messageSink);
    }

    /**
     * Send an {@link AppMessage} object to any interested listeners.
     * @param message The message to send
     */
    public void sendMessage(T message) {
        for (MessageSink<T> sink : mSubscribers) {
            sink.onMessageReceived(message);
        }
    }
}
