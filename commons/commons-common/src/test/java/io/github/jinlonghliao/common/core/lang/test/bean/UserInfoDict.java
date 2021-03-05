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
package io.github.jinlonghliao.common.core.lang.test.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * 用户信息
 * @author 质量过关
 *
 */
public class UserInfoDict implements Serializable {
	private static final long serialVersionUID = -936213991463284306L;
	// 用户Id
	private Integer id;
	// 要展示的名字
	private String realName;
	// 头像地址
	private String photoPath;
	private List<ExamInfoDict> examInfoDict;
	private UserInfoRedundCount userInfoRedundCount;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPhotoPath() {
		return photoPath;
	}
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public List<ExamInfoDict> getExamInfoDict() {
		return examInfoDict;
	}
	public void setExamInfoDict(List<ExamInfoDict> examInfoDict) {
		this.examInfoDict = examInfoDict;
	}

	public UserInfoRedundCount getUserInfoRedundCount() {
		return userInfoRedundCount;
	}
	public void setUserInfoRedundCount(UserInfoRedundCount userInfoRedundCount) {
		this.userInfoRedundCount = userInfoRedundCount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		UserInfoDict that = (UserInfoDict) o;
		return Objects.equals(id, that.id) && Objects.equals(realName, that.realName) && Objects.equals(photoPath, that.photoPath) && Objects.equals(examInfoDict, that.examInfoDict);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, realName, photoPath, examInfoDict);
	}

	@Override
	public String toString() {
		return "UserInfoDict [id=" + id + ", realName=" + realName + ", photoPath=" + photoPath + ", examInfoDict=" + examInfoDict + ", userInfoRedundCount=" + userInfoRedundCount + "]";
	}
}
