package br.com.codenation.model.entity.converter;

import br.com.codenation.model.entity.Level;

import javax.persistence.AttributeConverter;

public class LevelJPAConverter implements AttributeConverter<Level,Character> {

    @Override
    public Character convertToDatabaseColumn(Level attribute) {
        return attribute.getCode();
    }

    @Override
    public Level convertToEntityAttribute(Character dbData) {
        return Level.getTipo(dbData);
    }
}
