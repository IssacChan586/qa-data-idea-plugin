// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package com.vip.qa.tools.idea.plugin.jsonform.window;

import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class JsonFormWindow {

	private JPanel jsonFormWindowContent;
	private JTable dataTable;
	private JLabel dataFile;
	private JScrollPane dataTableScroll;
	private JButton loadFileButton;
	private JButton saveFileButton;

	public JsonFormWindow(ToolWindow toolWindow) {
		toolWindow.setTitle("Config.json");
		loadFileButton.addActionListener(e -> {

		});
		saveFileButton.addActionListener(e -> {

		});
		// dataTable.getModel().getValueAt(0,1)
		// 监听表格表更
		// 增加一键还原，保存，增加列，行按钮
	}

	private void loadFile() {

	}

	private void saveFile() {

	}

	public JPanel getContent() {
		return jsonFormWindowContent;
	}

	private void createUIComponents() {
		dataFile = new JLabel();
		dataFile.setText("TestData Demo");

		String[] columnNames = {"caseIndex", "caseName", "description", "key1", "key2"};
		Object[][] rowData = new Object[][]{{1, "case1", "description1", "value11", "value21"},
				{2, "case2", "description2", "value12", "value22"}};
		dataTable = new JBTable(new DefaultTableModel(rowData, columnNames));

		dataTableScroll = new JBScrollPane();
		dataTableScroll.setViewportView(dataTable);
	}

}
