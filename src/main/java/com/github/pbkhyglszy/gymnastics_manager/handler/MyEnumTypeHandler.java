package com.github.pbkhyglszy.gymnastics_manager.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.asm.Type;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyEnumTypeHandler<E extends Enum<E>> extends BaseTypeHandler<E> {
    private final Class<E> type;

    public MyEnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, toValue(parameter));
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int ordinal = rs.getInt(columnName);
        if (ordinal == 0 && rs.wasNull()) {
            return null;
        }
        return toEnum(ordinal);
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int ordinal = rs.getInt(columnIndex);
        if (ordinal == 0 && rs.wasNull()) {
            return null;
        }
        return toEnum(ordinal);
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int ordinal = cs.getInt(columnIndex);
        if (ordinal == 0 && cs.wasNull()) {
            return null;
        }
        return toEnum(ordinal);
    }

    private E toEnum(int ordinal) {
        try {
            //noinspection unchecked
            return (E) type.getMethod("fromValue", int.class).invoke(null, ordinal);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Cannot convert " + ordinal + " to " + type.getSimpleName(), ex);
        }
    }

    private int toValue(E data) {try {
        return (int) type.getMethod("getValue").invoke(data);
    } catch (Exception ex) {
        throw new IllegalArgumentException("Cannot convert " + data + " to int", ex);
    }
    }
}
