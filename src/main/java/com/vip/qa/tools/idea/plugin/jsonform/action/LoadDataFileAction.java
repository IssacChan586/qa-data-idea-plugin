package com.vip.qa.tools.idea.plugin.jsonform.action;

import com.alibaba.fastjson.JSONArray;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.vip.qa.tools.idea.plugin.jsonform.common.DataKeys;
import com.vip.qa.tools.idea.plugin.jsonform.common.PluginConfig;
import com.vip.qa.tools.idea.plugin.jsonform.service.FileService;
import com.vip.qa.tools.idea.plugin.jsonform.window.JsonFormWindowFactory;
import com.vip.qa.tools.idea.plugin.jsonform.window.NavigatorPanel;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.File;

public class LoadDataFileAction extends AnAction {

	@Override
	public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
		if (anActionEvent.getProject() == null) {
			return;
		}
		JFileChooser dataFileChooser = new JFileChooser();
		dataFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		dataFileChooser.showDialog(new JLabel(), "Choose json data file");

		File file = dataFileChooser.getSelectedFile();
		PluginConfig.curDataFile = file.getAbsolutePath();

		Pair<String, JSONArray> dataContent = FileService.readJsonFile(file);
		NavigatorPanel.refreshDataContent(
				JsonFormWindowFactory.getDataContext(anActionEvent.getProject()).getData(DataKeys.JSON_FORM_DATA_TABLE),
				JsonFormWindowFactory.getDataContext(anActionEvent.getProject())
						.getData(DataKeys.JSON_FORM_DATA_STATUS), dataContent.getLeft(), dataContent.getRight());

	}

}
