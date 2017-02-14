package com.springboot.vaadin.components;

import com.vaadin.ui.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maggouh on 13/02/17.
 */
public class TableColumnDefinitionList {
    private final ArrayList<TableColumnDefinition> tableColumnDefinitions = new ArrayList<TableColumnDefinition>();

    public ArrayList<TableColumnDefinition> getTableColumnDefinitions() {
        return tableColumnDefinitions;
    }

    public void add(TableColumnDefinition tableColumnDefinition) {
        tableColumnDefinitions.add(tableColumnDefinition);
    }

    public void add(int index, TableColumnDefinition tableColumnDefinition) {
        tableColumnDefinitions.add(index, tableColumnDefinition);
    }

    public int size() {
        return tableColumnDefinitions.size();
    }

    public Object[] getVisibleColumns() {
        Object[] visibleColumns = new Object[tableColumnDefinitions.size()];
        int i = 0;
        for(TableColumnDefinition tableColumnDefinition : tableColumnDefinitions) {
            visibleColumns[i] = tableColumnDefinition.propertyName;
            i++;
        }
        return visibleColumns;
    }

    public String[] getColumnHeaders() {
        String[] columnHeaders = new String[tableColumnDefinitions.size()];
        int i = 0;
        for(TableColumnDefinition tableColumnDefinition : tableColumnDefinitions) {
            columnHeaders[i] = tableColumnDefinition.caption;
            i++;
        }
        return columnHeaders;
    }

    public float[] getColumnExpandRatios() {
        float[] ratioColumns = new float[tableColumnDefinitions.size()];
        int i = 0;
        for(TableColumnDefinition tableColumnDefinition : tableColumnDefinitions) {
            ratioColumns[i] = tableColumnDefinition.expandRatio;
            i++;
        }
        return ratioColumns;
    }

    public Table.Align[] getColumnAlignments() {
        Table.Align[] columnAlignments = new Table.Align[tableColumnDefinitions.size()];
        int i = 0;
        for(TableColumnDefinition tableColumnDefinition : tableColumnDefinitions) {
            columnAlignments[i] = tableColumnDefinition.alignment;
            i++;
        }
        return columnAlignments;
    }

    public void setCollapsed(List collapsedColumns) {
        for(TableColumnDefinition tableColumnDefinition : tableColumnDefinitions) {
            tableColumnDefinition.collapsed = collapsedColumns.contains(tableColumnDefinition.propertyName);
        }
    }
}
