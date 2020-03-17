package org.mambofish.spring.data.jsondb.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mapping.context.AbstractMappingContext;
import org.springframework.data.mapping.model.Property;
import org.springframework.data.mapping.model.SimpleTypeHolder;
import org.springframework.data.util.TypeInformation;

/**
 * @author vince
 */
public class JsonDBMappingContext extends AbstractMappingContext<JsonDBPersistentEntity<?>, JsonDBPersistentProperty> {

    private static final Logger logger = LoggerFactory.getLogger(JsonDBMappingContext.class);

    public JsonDBMappingContext() {
    }

    @Override
    protected <T> JsonDBPersistentEntity<?> createPersistentEntity(TypeInformation<T> typeInformation) {
        return new JsonDBPersistentEntity<>(typeInformation);
    }

    @Override
    protected JsonDBPersistentProperty createPersistentProperty(Property property, JsonDBPersistentEntity<?> jsonDBPersistentProperties, SimpleTypeHolder simpleTypeHolder) {
        return new JsonDBPersistentProperty(property, jsonDBPersistentProperties, simpleTypeHolder);
    }
}
