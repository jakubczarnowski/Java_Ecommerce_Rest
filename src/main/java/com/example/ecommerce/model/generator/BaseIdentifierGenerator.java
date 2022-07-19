package com.example.ecommerce.model.generator;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.UUIDGenerator;

import java.io.Serializable;
import java.util.Objects;
import java.util.Random;

public class BaseIdentifierGenerator extends UUIDGenerator {
    private static final int NUMBER_OF_CHARS_IN_ID_PART = 5;

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object obj) throws HibernateException {
        // Generate a custom ID for the new entity
        final String uuid = super.generate(session, obj).toString();
        final long longTimeRandom = System.nanoTime() + System.currentTimeMillis() + new Random().nextLong() + Objects.hash(obj);
        final String timeHex = Long.toHexString(longTimeRandom);
        return timeHex.substring(0, NUMBER_OF_CHARS_IN_ID_PART) + uuid.substring(0, NUMBER_OF_CHARS_IN_ID_PART);
    }
}
