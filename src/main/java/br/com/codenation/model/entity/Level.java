package br.com.codenation.model.entity;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum Level {

    ERROR('E'),
    WARNING('W'),
    INFO('I');

    private Character code;
    private static final Map<Character, Level> ENUM_MAPEAMENTO;


    Level (Character code) {
        this.code = code;
    }

    public Character getCode() {
        return this.code;
    }

    static {
        Map<Character,Level> mapa = new HashMap<Character,Level>();
        for (Level instance : Level.values()) {
            mapa.put(instance.getCode(),instance);
        }
        ENUM_MAPEAMENTO = Collections.unmodifiableMap(mapa);
    }

    public static Level getTipo(Character code) {
        if (ENUM_MAPEAMENTO.get(Character.toUpperCase(code)) == null) {
            throw new RuntimeException(String.format("Não existe implementado o tipo Level para o (%s)"));
        }
        return ENUM_MAPEAMENTO.get(code);
    }

}
