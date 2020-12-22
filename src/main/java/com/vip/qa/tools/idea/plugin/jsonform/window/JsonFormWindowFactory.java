// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package com.vip.qa.tools.idea.plugin.jsonform.window;

import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class JsonFormWindowFactory implements ToolWindowFactory {

	public static String ID = "JsonForm";

	public static void updateTitle(@NotNull Project project, String dataFileName) {
		ToolWindow jsonFormWindow = ToolWindowManager.getInstance(project).getToolWindow(ID);
		jsonFormWindow
				.setTitle(StringUtils.isBlank(dataFileName) ? StringUtils.EMPTY : String.format("[%s]", dataFileName));
	}

	public static DataContext getDataContext(@NotNull Project project) {
		ToolWindow jsonFormWindow = ToolWindowManager.getInstance(project).getToolWindow(ID);
		return DataManager.getInstance()
				.getDataContext(jsonFormWindow.getContentManager().getContent(0).getComponent());
	}

	/**
	 * Create the tool window content.
	 *
	 * @param project    current project
	 * @param toolWindow current tool window
	 */
	public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
		ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
		JComponent navigatorPanel = new NavigatorPanel(toolWindow, project);
		Content content = contentFactory.createContent(navigatorPanel, StringUtils.EMPTY, false);
		toolWindow.getContentManager().addContent(content);
	}

}
