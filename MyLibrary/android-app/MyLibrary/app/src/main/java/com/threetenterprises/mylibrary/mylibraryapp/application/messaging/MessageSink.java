package com.threetenterprises.mylibrary.mylibraryapp.application.messaging;

/**
 * Created by david on 7/17/17.
 */

public interface MessageSink<T extends AppMessage> {
    void onMessageReceived(T message);
}
