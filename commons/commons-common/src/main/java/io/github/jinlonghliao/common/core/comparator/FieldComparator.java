/**
 * Copyright 2020-2021 JinlongLiao
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.jinlonghliao.common.core.comparator;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Comparator;

import io.github.jinlonghliao.common.core.util.ClassUtil;
import io.github.jinlonghliao.common.core.util.ObjectUtil;
import io.github.jinlonghliao.common.core.util.ReflectUtil;
import io.github.jinlonghliao.common.core.util.StrUtil;

/**
 * Bean字段排序器<br>
 * 参阅feilong-core中的PropertyComparator
 * 
 * @author Looly
 *
 * @param <T> 被比较的Bean
 */
public class FieldComparator<T> implements Comparator<T>, Serializable {
	private static final long serialVersionUID = 9157326766723846313L;

	private final Field field;

	/**
	 * 构造
	 * 
	 * @param beanClass Bean类
	 * @param fieldName 字段名
	 */
	public FieldComparator(Class<T> beanClass, String fieldName) {
		this.field = ClassUtil.getDeclaredField(beanClass, fieldName);
		if(this.field == null){
			throw new IllegalArgumentException(StrUtil.format("Field [{}] not found in Class [{}]", fieldName, beanClass.getName()));
		}
	}

	@Override
	public int compare(T o1, T o2) {
		if (o1 == o2) {
			return 0;
		} else if (null == o1) {// null 排在后面
			return 1;
		} else if (null == o2) {
			return -1;
		}

		Comparable<?> v1;
		Comparable<?> v2;
		try {
			v1 = (Comparable<?>) ReflectUtil.getFieldValue(o1, this.field);
			v2 = (Comparable<?>) ReflectUtil.getFieldValue(o2, this.field);
		} catch (Exception e) {
			throw new ComparatorException(e);
		}

		return compare(o1, o2, v1, v2);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private int compare(T o1, T o2, Comparable fieldValue1, Comparable fieldValue2) {
		int result = ObjectUtil.compare(fieldValue1, fieldValue2);
		if(0 == result) {
			//避免TreeSet / TreeMap 过滤掉排序字段相同但是对象不相同的情况
			result = CompareUtil.compare(o1, o2, true);
		}
		return result;
	}
}
