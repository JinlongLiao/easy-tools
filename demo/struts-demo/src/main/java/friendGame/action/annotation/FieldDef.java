package friendGame.action.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * @author liaojinlong
 * @since 2021/1/3 13:47
*/
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldDef {
	/**
	 * 字段长度
	 * @return /
	 */
	int fieldLength();
}
