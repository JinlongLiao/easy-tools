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
package io.github.jinlonghliao.common.core.lang.tree.parser;

import io.github.jinlonghliao.common.core.lang.tree.TreeNode;
import io.github.jinlonghliao.common.core.lang.tree.Tree;
import io.github.jinlonghliao.common.core.map.MapUtil;

import java.util.Map;

/**
 * 默认的简单转换器
 *
 * @param <T> ID类型
 * @author liangbaikai
 */
public class DefaultNodeParser<T> implements NodeParser<TreeNode<T>, T> {

	@Override
	public void parse(TreeNode<T> treeNode, Tree<T> tree) {
		tree.setId(treeNode.getId());
		tree.setParentId(treeNode.getParentId());
		tree.setWeight(treeNode.getWeight());
		tree.setName(treeNode.getName());

		//扩展字段
		final Map<String, Object> extra = treeNode.getExtra();
		if(MapUtil.isNotEmpty(extra)){
			extra.forEach(tree::putExtra);
		}
	}
}
