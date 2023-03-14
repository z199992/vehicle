package com.xunlekj.jpa.enhanced;

import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.TableGenerator;
import org.hibernate.id.factory.spi.CustomIdGeneratorCreationContext;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
import org.hibernate.type.descriptor.java.BigIntegerJavaType;
import org.hibernate.type.descriptor.jdbc.BigIntJdbcType;
import org.hibernate.type.internal.NamedBasicTypeImpl;

import java.io.Serializable;
import java.lang.reflect.Member;
import java.util.Properties;

public class CustomTableGenerator extends TableGenerator {
    private static final int initial = 100;
    private static final int interval = 1;
    private String prefix = "";
    private int length = 17;
    private String type;
    private CustomIdGeneratorCreationContext context;

    public CustomTableGenerator(com.xunlekj.jpa.annotations.CustomTableGenerator config,
                                Member annotatedMember,
                                CustomIdGeneratorCreationContext context) {
        this.length = config.length();
        this.type = config.type();
        this.context = context;
        if (config.prefix().length() > 0) {
            this.prefix = config.prefix();
        } else {
            this.prefix = this.type;
        }

        configure(new NamedBasicTypeImpl<>(new BigIntegerJavaType(), new BigIntJdbcType(), "bigint"), new Properties(), context.getServiceRegistry());
    }

    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
        if (this.type == null) {
            throw new MappingException("ID generator has no type set!");
        }

        params.put(TABLE_PARAM, "ID_SERIAL_NO");
        params.put(SEGMENT_COLUMN_PARAM, "ID");
        params.put(SEGMENT_VALUE_PARAM, this.type);
        params.put(VALUE_COLUMN_PARAM, "Num");
        params.put(INCREMENT_PARAM, interval);
        params.put(INITIAL_PARAM, initial);

        super.configure(type, params, serviceRegistry);
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object obj) {
        return String.format(prefix + "%0" + length + "d", super.generate(session, obj));
    }
}
