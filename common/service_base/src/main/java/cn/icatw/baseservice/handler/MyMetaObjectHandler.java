package cn.icatw.baseservice.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 元对象处理程序
 *
 * @author icatw
 * @date 2022/09/20
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
 @Override
 public void insertFill(MetaObject metaObject) {
 this.setFieldValByName("gmtCreate", new Date(), metaObject);
 this.setFieldValByName("gmtModified", new Date(), metaObject);
 }
 @Override
 public void updateFill(MetaObject metaObject) {
 this.setFieldValByName("gmtModified", new Date(), metaObject);
 }
}
