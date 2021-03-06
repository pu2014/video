package top.yinjinbiao.video.common.interceptor;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import top.yinjinbiao.video.common.annotation.*;
import top.yinjinbiao.video.common.util.UserLocalUtil;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Properties;

@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class,
        Object.class})})
public class BaseColumnInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];

        // 获取 SQL
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();

        // 获取参数
        Object parameter = invocation.getArgs()[1];

        // 获取成员变量
        Field[] declaredFields = parameter.getClass().getSuperclass().getDeclaredFields();

        for (Field field : declaredFields) {
            if (field.getAnnotation(CreateTime.class) != null) {
                if (SqlCommandType.INSERT.equals(sqlCommandType)) {
                    // insert语句插入createTime
                    field.setAccessible(true);
                    // 这里设置时间，当然时间格式可以自定。比如转成String类型
                    field.set(parameter, new Date());
                }
            } else if (field.getAnnotation(UpdateTime.class) != null) {

                if (SqlCommandType.INSERT.equals(sqlCommandType)
                        || SqlCommandType.UPDATE.equals(sqlCommandType)) {
                    // insert 或update语句插入updateTime
                    field.setAccessible(true);
                    field.set(parameter, new Date());
                }
            } else if (field.getAnnotation(CreateBy.class) != null) {
                if (SqlCommandType.INSERT.equals(sqlCommandType)) {
                    // insert语句插入CreatedBy
                    field.setAccessible(true);
                    // 这里设置登陆人
                    field.set(parameter, UserLocalUtil.getCurrentUserId());
                }
            } else if (field.getAnnotation(UpdateBy.class) != null) {

                if (SqlCommandType.INSERT.equals(sqlCommandType)
                        || SqlCommandType.UPDATE.equals(sqlCommandType)) {
                    // insert 或update语句插入更新人
                    field.setAccessible(true);
                    field.set(parameter, UserLocalUtil.getCurrentUserId());
                }
            } else if (field.getAnnotation(Delete.class) != null) {
                if(SqlCommandType.INSERT.equals(sqlCommandType)){
                    field.setAccessible(true);
                    field.set(parameter, false);
                }
            }
        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
