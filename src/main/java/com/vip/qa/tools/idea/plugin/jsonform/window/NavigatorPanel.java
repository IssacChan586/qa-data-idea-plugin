package com.vip.qa.tools.idea.plugin.jsonform.window;

import com.alibaba.fastjson.JSONArray;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionToolbar;
import com.intellij.openapi.actionSystem.DataProvider;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;
import com.vip.qa.tools.idea.plugin.jsonform.common.DataKeys;
import com.vip.qa.tools.idea.plugin.jsonform.common.PluginConfig;
import com.vip.qa.tools.idea.plugin.jsonform.service.TestDataService;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.File;

public class NavigatorPanel extends SimpleToolWindowPanel implements DataProvider {

	private JFileChooser dataFileChooser;
	private JBScrollPane contentScrollPanel;
	private JBTable dataTable;
	private JLabel dataStatusLabel;

	public NavigatorPanel(ToolWindow toolWindow, Project project) {
		super(Boolean.TRUE, Boolean.TRUE);
		final ActionManager actionManager = ActionManager.getInstance();

		ActionToolbar actionToolbar = actionManager.createActionToolbar("jsonform Toolbar",
				(DefaultActionGroup) actionManager.getAction("jsonform.NavigatorActionsToolbar"), true);
		actionToolbar.setTargetComponent(dataTable);
		setToolbar(actionToolbar.getComponent());

		dataFileChooser = new JFileChooser();
		dataFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		dataFileChooser.showDialog(new JLabel(), "Choose json data file");

		File file = dataFileChooser.getSelectedFile();
		PluginConfig.curDataFile = file.getAbsolutePath();
		Pair<String, JSONArray> dataContent = JsonDataFileUtils.readJsonFile(file);

		JPanel groupPanel = new JPanel();
		groupPanel.setLayout(new BoxLayout(groupPanel, BoxLayout.Y_AXIS));

		dataStatusLabel = new JLabel();
		dataTable = new JBTable();
		contentScrollPanel = new JBScrollPane(dataTable, JBScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JBScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		groupPanel.add(dataStatusLabel);
		groupPanel.add(contentScrollPanel);

		SimpleToolWindowPanel tablePanel = new SimpleToolWindowPanel(Boolean.TRUE, Boolean.TRUE);
		tablePanel.setContent(groupPanel);
		setContent(tablePanel);

		refreshDataContent(dataTable, dataStatusLabel, dataContent.getLeft(), dataContent.getRight());
	}

	public static void refreshDataContent(JBTable dataTable, JLabel dataStatusLabel, String dataStatus,
			JSONArray dataList) {
		dataStatusLabel.setText(dataStatus);
		dataStatusLabel.updateUI();
		dataTable.setModel(TestDataService.convertDataArray2DataTable(dataList));
		dataTable.updateUI();
	}

	@Override
	public Object getData(@NotNull String dataId) {
		if (DataKeys.JSON_FORM_DATA_TABLE.is(dataId)) {
			return dataTable;
		}
		if (DataKeys.JSON_FORM_DATA_STATUS.is(dataId)) {
			return dataStatusLabel;
		}
		return super.getData(dataId);
	}

}
