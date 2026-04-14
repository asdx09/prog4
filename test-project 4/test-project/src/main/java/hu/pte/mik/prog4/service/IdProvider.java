package hu.pte.mik.prog4.service;

import java.util.concurrent.atomic.AtomicLong;

public class IdProvider {

    private static IdProvider instance;

    private final AtomicLong id = new AtomicLong(1L);

    private IdProvider() {}

    public static IdProvider getInstance() {
        if(instance == null) {
            instance = new IdProvider();
        }

        return instance;
    }

    public long nextId() {
        return this.id.getAndIncrement();
    }

}
