package com.vip.qa.tools.idea.plugin.jsonform.model;

import com.alibaba.fastjson.JSONObject;

public class TestCase {

	private String index;
	private JSONObject testcase;

	public TestCase(String index) {
		this.index = index;
	}

	public TestCase(String index, JSONObject testcase) {
		this.index = index;
		this.testcase = testcase;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public JSONObject getTestcase() {
		return testcase;
	}

	public void setTestcase(JSONObject testcase) {
		this.testcase = testcase;
	}
}
