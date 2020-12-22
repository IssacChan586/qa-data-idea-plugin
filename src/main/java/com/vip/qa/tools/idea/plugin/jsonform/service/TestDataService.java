package com.vip.qa.tools.idea.plugin.jsonform.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.List;

public class TestDataService {

	public static DefaultTableModel convertDataArray2DataTable(JSONArray dataList) {
		List<String> columnNameList = Lists.newArrayList();
		for (int i = 0; i < dataList.size(); i++) {
			JSONObject data = dataList.getJSONObject(i);
			for (String key : data.keySet()) {
				if (columnNameList.contains(key)) {
					continue;
				}
				columnNameList.add(key);
			}
		}

		Object[][] rowData = new Object[dataList.size()][columnNameList.size()];
		for (int i = 0; i < dataList.size(); i++) {
			for (int j = 0; j < columnNameList.size(); j++) {
				JSONObject data = dataList.getJSONObject(i);
				rowData[i][j] = data.getOrDefault(columnNameList.get(j), StringUtils.EMPTY);
			}
		}
		return new DefaultTableModel(rowData, columnNameList.toArray());
	}

	public static JSONArray convertDataTable2DataArray(TableModel tableModel) {
		List<String> columnNameList = Lists.newArrayList();
		for (int i = 0; i < tableModel.getColumnCount(); i++) {
			columnNameList.add(tableModel.getColumnName(i));
		}
		JSONArray dataArray = new JSONArray();
		for (int i = 0; i < tableModel.getRowCount(); i++) {
			JSONObject data = new JSONObject();
			for (int j = 0; j < columnNameList.size(); j++) {
				data.put(columnNameList.get(j), tableModel.getValueAt(i, j));
			}
			dataArray.add(data);
		}
		return dataArray;
	}

}
