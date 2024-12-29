package de.edlly.key.services;

import de.edlly.key.services.listener.IStoreListener;
import org.springframework.stereotype.Component;

@Component
public class DummyStoreListener implements IStoreListener {

    boolean called = false;

    @Override
    public void onAddData() {
        called = true;
    }

    public boolean isCalled() {
        return called;
    }
}
