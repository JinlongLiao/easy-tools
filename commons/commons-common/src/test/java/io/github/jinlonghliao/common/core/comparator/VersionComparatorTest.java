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

import io.github.jinlonghliao.common.core.comparator.VersionComparator;
import org.junit.Assert;
import org.junit.Test;

/**
 * 版本比较单元测试
 * 
 * @author looly
 *
 */
public class VersionComparatorTest {
	
	@Test
	public void versionComparatorTest1() {
		int compare = VersionComparator.INSTANCE.compare("1.2.1", "1.12.1");
		Assert.assertTrue(compare < 0);
	}
	
	@Test
	public void versionComparatorTest2() {
		int compare = VersionComparator.INSTANCE.compare("1.12.1", "1.12.1c");
		Assert.assertTrue(compare < 0);
	}
	
	@Test
	public void versionComparatorTest3() {
		int compare = VersionComparator.INSTANCE.compare(null, "1.12.1c");
		Assert.assertTrue(compare < 0);
	}
	
	@Test
	public void versionComparatorTest4() {
		int compare = VersionComparator.INSTANCE.compare("1.13.0", "1.12.1c");
		Assert.assertTrue(compare > 0);
	}
	
	@Test
	public void versionComparatorTest5() {
		int compare = VersionComparator.INSTANCE.compare("V1.2", "V1.1");
		Assert.assertTrue(compare > 0);
	}
	
	@Test
	public void versionComparatorTes6() {
		int compare = VersionComparator.INSTANCE.compare("V0.0.20170102", "V0.0.20170101");
		Assert.assertTrue(compare > 0);
	}

	@Test
	public void equalsTest(){
		VersionComparator first = new VersionComparator();
		VersionComparator other = new VersionComparator();
		Assert.assertFalse(first.equals(other));
	}
}
