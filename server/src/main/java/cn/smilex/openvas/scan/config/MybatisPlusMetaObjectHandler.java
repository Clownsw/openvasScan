package cn.smilex.openvas.scan.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author smilex
 * @date 2022/9/30/6:29
 * @since 1.0
 */
@Component
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        setFieldValByName("lastLogin", LocalDateTime.now(), metaObject);
        setFieldValByName("gmtCreated", LocalDateTime.now(), metaObject);
        setFieldValByName("gmtModified", LocalDateTime.now(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("gmtModified", LocalDateTime.now(), metaObject);
    }

    @Override
    public MetaObjectHandler setFieldValByName(String fieldName, Object fieldVal, MetaObject metaObject) {
        if (metaObject.hasSetter(fieldName)) {
            return MetaObjectHandler.super.setFieldValByName(fieldName, fieldVal, metaObject);
        }

        return this;
    }
}
