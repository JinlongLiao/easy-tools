/*
 * Copyright 2020-2021.
 * 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
 package io.github.jinlonghliao.common.core.lang.test.bean;

import java.io.Serializable;
import java.util.Objects;

/**
 * 
 * @author 质量过关
 *
 */
public class ExamInfoDict implements Serializable {
	private static final long serialVersionUID = 3640936499125004525L;
	
	// 主键
	private Integer id; // 可当作题号
	// 试题类型 客观题 0主观题 1
	private Integer examType;
	// 试题是否作答
	private Integer answerIs;

	public Integer getId() {
		return id;
	}
	public Integer getId(Integer defaultValue) {
		return this.id == null ? defaultValue : this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getExamType() {
		return examType;
	}
	public void setExamType(Integer examType) {
		this.examType = examType;
	}

	public Integer getAnswerIs() {
		return answerIs;
	}
	public void setAnswerIs(Integer answerIs) {
		this.answerIs = answerIs;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ExamInfoDict that = (ExamInfoDict) o;
		return Objects.equals(id, that.id) && Objects.equals(examType, that.examType) && Objects.equals(answerIs, that.answerIs);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, examType, answerIs);
	}

	@Override
	public String toString() {
		return "ExamInfoDict{" + "id=" + id + ", examType=" + examType + ", answerIs=" + answerIs + '}';
	}
}
