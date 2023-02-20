package com.xunlekj.jpa.naming;

import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

import java.util.Locale;

public class CustomNamingStrategy extends CamelCaseToUnderscoresNamingStrategy {

    protected Identifier getIdentifier(String name, final boolean quoted, final JdbcEnvironment jdbcEnvironment) {
        return new Identifier( name.toUpperCase( Locale.ROOT ), quoted );
    }

}
