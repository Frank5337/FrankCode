package com.bugkai.config;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.lang.reflect.Field;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;


/*
 *       参考代码，绝逼可以使用在项目。
 * */

/**
 * @param <E> E 当像sql传入值（#{XXX}）时，他的类型为Object，没有卵用
 *            当将返回结果自动类型转换时，他是枚举类型，这个代码可以升级至匹配大部分类型转换。有待尝试
 * @author 凡小云
 */

public class MyEnumTypeHandler<E extends Enum<E>> extends BaseTypeHandler<E> {

    private Class<E> type;

//    private List<String> attributes;
//
//    public void setAttributes(List<String> attributes) {
//        this.attributes = attributes;
//    }

    /**
     * type 这个是传来的E的字节码对象。
     *
     * @param type
     */
    public MyEnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
    }

    /**
     * @param ps        预编译对象，详情请参照jdbc编程
     * @param i         这个值应该是预编译对象后，拼接参数的参数位置。暂时没发现乱用
     * @param parameter 预编译需要的从代码那边传来的参数。
     * @param jdbcType  表示jdbc即数据库表字段的类型。一般是由#{XXX,jdbcType=XXXXX}传来的。
     * @throws SQLException
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        Object obj = null;
        try {
            Field field = parameter.getClass().getDeclaredField("code");
            field.setAccessible(true);
//            for (String attribute : attributes) {
//                field = parameter.getClass().getDeclaredField(attribute);
//                field.setAccessible(true);
//            }
            obj = field.get(parameter);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        if (jdbcType == null) {
            ps.setString(i, obj != null ? String.valueOf(obj) : parameter.name());
        } else {
            ps.setObject(i, obj != null ? String.valueOf(obj) : parameter.name(), jdbcType.TYPE_CODE); // see r3589
        }
    }

    /**
     * @param rs         执行结果集。
     * @param columnName 执行结果的列名。
     * @return
     * @throws SQLException
     */

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String s = rs.getString(columnName);
        E[] es = type.getEnumConstants();
        E result = null;
        for (E e : es) {
            Object o = null;
            try {
                //code是我在枚举中定义的一个属性，用来枚举值跟数据库里数据做对对应的。
                Field field = type.getDeclaredField("code");
                field.setAccessible(true);
                o = field.get(e);
            } catch (IllegalAccessException | NoSuchFieldException ex) {
                ex.printStackTrace();
            }
            if (Objects.equals(o, s)) {
                result = e;
                break;
            }
        }
        return result != null ? result : s == null ? null : Enum.valueOf(type, s);
    }

    /**
     * 这个东西貌似debug没见走过。 功能同上
     *
     * @param rs          结果集
     * @param columnIndex 列索引
     * @return
     * @throws SQLException
     */
    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String s = rs.getString(columnIndex);
        E[] es = type.getEnumConstants();
        E result = null;
        for (E e : es) {
            Object o = null;
            try {
                Field field = type.getDeclaredField("code");
                field.setAccessible(true);
                o = field.get(e);
            } catch (IllegalAccessException | NoSuchFieldException ex) {
                ex.printStackTrace();
            }
            if (Objects.equals(o, s)) {
                result = e;
                break;
            }
        }
        return result != null ? result : s == null ? null : Enum.valueOf(type, s);
    }

    /**
     * 同上
     *
     * @param cs          结果集
     * @param columnIndex 列索引
     * @return
     * @throws SQLException
     */
    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String s = cs.getString(columnIndex);
        E[] es = type.getEnumConstants();
        E result = null;
        for (E e : es) {
            Object o = null;
            try {
                Field field = type.getDeclaredField("code");
                field.setAccessible(true);
                o = field.get(e);
            } catch (IllegalAccessException | NoSuchFieldException ex) {
                ex.printStackTrace();
            }
            if (Objects.equals(o, s)) {
                result = e;
                break;
            }
        }
        return result != null ? result : s == null ? null : Enum.valueOf(type, s);
    }
}
