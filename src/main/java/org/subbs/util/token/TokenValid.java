package org.subbs.util.token;

import java.lang.annotation.*;

/**
 * token校验注解
 * Created with IntelliJ IDEA.
 * User: peng
 * Date: 10/22/19
 * Time: 8:39 PM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TokenValid{
}
