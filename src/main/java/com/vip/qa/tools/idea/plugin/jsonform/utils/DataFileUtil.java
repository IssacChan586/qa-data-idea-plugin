package com.vip.qa.tools.idea.plugin.jsonform.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONValidator;
import com.intellij.openapi.vfs.VirtualFile;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.io.IOException;

public class DataFileUtil {

	public static Pair<String, JSONArray> readJsonFile(VirtualFile virtualFile) {
		if (virtualFile == null) {
			return Pair.of("File not exist", new JSONArray());
		}
		return readJsonFile(new File(virtualFile.getPath()));
	}

	public static Pair<String, JSONArray> readJsonFile(String filePath) {
		return readJsonFile(new File(filePath));
	}

	public static Pair<String, JSONArray> readJsonFile(File file) {
		if (file == null) {
			return Pair.of("File is null", new JSONArray());
		}

		if (!file.exists() || !file.isFile()) {
			return Pair.of(String.format("File [%s] not a valid file", file.getAbsolutePath()), new JSONArray());
		}

		String description = String.format("Load file [%s] successfully", file.getAbsolutePath());
		JSONArray displayDataArray = new JSONArray();
		try {
			String dataString = FileUtils.readFileToString(file, "UTF-8");
			switch (JSONValidator.from(dataString).getType()) {
				case Object:
					JSONObject dataJson = JSON.parseObject(dataString);
					description += ", type: JSONObject.";
					displayDataArray.add(dataJson);
					break;
				case Array:
					displayDataArray = JSON.parseArray(dataString);
					description += ", type: JSONArray.";
					break;
				case Value:
					JSONObject dataValue = new JSONObject();
					dataValue.put("value", dataString);
					displayDataArray.add(dataValue);
					description += ", type: value.";
					break;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.err.println(String.format("Exception while reading file: %s", file.getAbsolutePath()));
			return Pair.of(String.format("Load file [%s] error", file.getAbsolutePath()), new JSONArray());
		}
		return Pair.of(description, displayDataArray);
	}

	public static void writeJsonFile(JSONArray dataList, String file) {
		writeJsonFile(dataList, new File(file));
	}

	public static void writeJsonFile(JSONArray dataList, File file) {
		if (file == null || !file.exists() || !file.isFile()) {
			return;
		}
		try {
			FileUtils.write(file, JSON.toJSONString(dataList, true), "UTF-8", false);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println(String.format("Exception while saving file: %s", file.getAbsolutePath()));
		}
	}

}
