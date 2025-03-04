package org.openmrs.module.basicmodule.Coverter;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;

import java.nio.file.Paths;

public class PathKeyDeserializer extends KeyDeserializer {

    @Override
    public Object deserializeKey(String key, DeserializationContext ctxt) {
        return Paths.get(key);
    }
}
