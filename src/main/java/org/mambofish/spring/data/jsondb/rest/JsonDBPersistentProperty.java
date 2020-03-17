package org.mambofish.spring.data.jsondb.rest;

import io.jsondb.annotation.Id;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mapping.Association;
import org.springframework.data.mapping.model.AnnotationBasedPersistentProperty;
import org.springframework.data.mapping.model.Property;
import org.springframework.data.mapping.model.SimpleTypeHolder;

/**
 * @author vince
 */
public class JsonDBPersistentProperty extends AnnotationBasedPersistentProperty<JsonDBPersistentProperty> {

	private static final Logger logger = LoggerFactory.getLogger(JsonDBPersistentProperty.class);

    public JsonDBPersistentProperty(Property property, JsonDBPersistentEntity<?> jsonDBPersistentProperties, SimpleTypeHolder simpleTypeHolder) {
        super(property, jsonDBPersistentProperties, simpleTypeHolder);
    }

	@Override
	public boolean isIdProperty() {
		return isAnnotationPresent(Id.class);
	}

	@Override
	public boolean isVersionProperty() {
		logger.debug("[property].isVersionProperty() returns false"); // by design
		return false;
	}

	/**
	 * Overridden to force field access as opposed to getter method access for simplicity.
	 *
	 * @see org.springframework.data.mapping.model.AnnotationBasedPersistentProperty#usePropertyAccess()
	 */
	@Override
	public boolean usePropertyAccess() {
		logger.debug("[property].usePropertyAccess() returns false");
		return false;
	}

	@Override
	public boolean isAssociation() {
        logger.debug("[property].isAssociation() returns false");
        return false;
	}

	@Override
	protected Association<JsonDBPersistentProperty> createAssociation() {
		return new Association<>(this, null);
	}
}
