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
package io.github.jinlonghliao.common.core.lang.tree;

import io.github.jinlonghliao.common.core.collection.CollUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * 通用树测试
 *
 * @author liangbaikai
 */
public class TreeTest {
	// 模拟数据
	static List<TreeNode<String>> nodeList = CollUtil.newArrayList();

	static {
		// 模拟数据
		nodeList.add(new TreeNode<>("1", "0", "系统管理", 5));
		nodeList.add(new TreeNode<>("11", "1", "用户管理", 222222));
		nodeList.add(new TreeNode<>("111", "11", "用户添加", 0));

		nodeList.add(new TreeNode<>("2", "0", "店铺管理", 1));
		nodeList.add(new TreeNode<>("21", "2", "商品管理", 44));
		nodeList.add(new TreeNode<>("221", "2", "商品管理2", 2));
	}


	@Test
	public void sampleTree() {
		List<Tree<String>> treeList = TreeUtil.build(nodeList, "0");
		for (Tree<String> tree : treeList) {
			Assert.assertNotNull(tree);
			Assert.assertEquals("0", tree.getParentId());
		}

		// 测试通过子节点查找父节点
		final Tree<String> rootNode0 = treeList.get(0);
		final Tree<String> parent = rootNode0.getChildren().get(0).getParent();
		Assert.assertEquals(rootNode0, parent);
	}

	@Test
	public void tree() {

		//配置
		TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
		// 自定义属性名 都要默认值的
		treeNodeConfig.setWeightKey("order");
		treeNodeConfig.setIdKey("rid");
		treeNodeConfig.setDeep(3);

		//转换器
		List<Tree<String>> treeNodes = TreeUtil.build(nodeList, "0", treeNodeConfig,
				(treeNode, tree) -> {
					tree.setId(treeNode.getId());
					tree.setParentId(treeNode.getParentId());
					tree.setWeight(treeNode.getWeight());
					tree.setName(treeNode.getName());
					// 扩展属性 ...
					tree.putExtra("extraField", 666);
					tree.putExtra("other", new Object());
				});

		Assert.assertEquals(treeNodes.size(), 2);
	}
}
