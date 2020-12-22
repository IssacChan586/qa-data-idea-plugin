package com.vip.qa.tools.idea.plugin.jsonform.common;

import com.intellij.openapi.actionSystem.DataKey;
import com.intellij.ui.table.JBTable;

import javax.swing.*;

public class DataKeys {

	public static final DataKey<JLabel> JSON_FORM_DATA_STATUS = DataKey.create("JSON_FORM_DATA_STATUS");
	public static final DataKey<JBTable> JSON_FORM_DATA_TABLE = DataKey.create("JSON_FORM_DATA_TABLE");

}
