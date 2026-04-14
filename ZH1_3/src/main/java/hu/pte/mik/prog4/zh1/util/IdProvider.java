package hu.pte.mik.prog4.zh1.util;

import java.util.concurrent.atomic.AtomicLong;

public class IdProvider {

    private static final IdProvider ID_PROVIDER = new IdProvider();

    private final AtomicLong id = new AtomicLong(1L);

    private IdProvider() {
    }

    public static IdProvider getInstance() {
        return ID_PROVIDER;
    }

    public Long getNewId() {
        return this.id.getAndIncrement();
    }

}
