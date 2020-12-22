package com.vip.qa.tools.idea.plugin.jsonform.action;

import com.alibaba.fastjson.JSONArray;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;
import com.intellij.ui.table.JBTable;
import com.vip.qa.tools.idea.plugin.jsonform.common.DataKeys;
import com.vip.qa.tools.idea.plugin.jsonform.common.PluginConfig;
import com.vip.qa.tools.idea.plugin.jsonform.service.TestDataService;
import com.vip.qa.tools.idea.plugin.jsonform.window.JsonDataFileUtils;
import com.vip.qa.tools.idea.plugin.jsonform.window.JsonFormWindowFactory;
import org.jetbrains.annotations.NotNull;

public class SaveDataFileAction extends AnAction {

	@Override
	public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
		if (anActionEvent.getProject() == null) {
			return;
		}
		JBTable dataTable = JsonFormWindowFactory.getDataContext(anActionEvent.getProject())
				.getData(DataKeys.JSON_FORM_DATA_TABLE);
		if (dataTable == null) {
			return;
		}
		JSONArray dataArray = TestDataService.convertDataTable2DataArray(dataTable.getModel());
		JsonDataFileUtils.writeJsonFile(dataArray, PluginConfig.curDataFile);
		Messages.showInfoMessage(String.format("Save [%s] successfully", PluginConfig.curDataFile), "Save!");
	}

}
