package com.springboot.vaadin.components;

import com.vaadin.data.Property;
import com.vaadin.data.Validator;
import com.vaadin.ui.Field;
import com.vaadin.ui.Table;
import org.jetbrains.annotations.NonNls;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maggouh on 13/02/17.
 */
public class RTLTable extends Table {

    private final ArrayList<Field> fields = new ArrayList<Field>();

    public RTLTable(@NonNls String debugId) {
        super();
        setDebugId(debugId + ".Table");
    }

    public void clearFields() {
        fields.clear();
    }

    public void registerFieldToValidate(Field field) {
        fields.add(field);
    }

    public void sort(Object propertyId, Boolean ascending) {
        sort(new Object[]{propertyId}, new boolean[]{ascending});
    }

    @Override
    protected String formatPropertyValue(Object rowId, Object colId, Property property) {
        try {
            if (property.getValue() != null) {
                return property.getValue().toString();
            }

            return super.formatPropertyValue(rowId, colId, property);
        } catch (Exception e) {
            return "";
        }
    }

    public void validateFields() throws Validator.InvalidValueException {
        for (Field field: this.fields) {
            try {
                field.validate();
            } catch (Validator.InvalidValueException ex) {
                throw new Validator.InvalidValueException(ex.getMessage());
            }
            field.requestRepaint();
        }

    }

    public boolean containsVisibleColumn(String colId) {
        boolean contains = false;
        Object[] columns = getVisibleColumns();
        for (Object column : columns) {
            if (column.toString().equals(colId)) {
                contains = true;
                break;
            }
        }
        if(!contains && this.getColumnGenerator(colId) != null) {
            contains = true;
        }

        return contains;
    }

    protected void removeVisibleColumn(String colId) {
        if(containsVisibleColumn(colId)) {
            this.setColumnExpandRatio(colId, -1);
            Object[] columns = getVisibleColumns();

            ArrayList<Object> visibleColumns = new ArrayList<Object>();
            ArrayList<String> headerColumns = new ArrayList<String>();
            ArrayList<Table.Align> alignmentColumns = new ArrayList<Table.Align>();

            for (Object column : columns) {
                if (!column.toString().equals(colId)) {
                    visibleColumns.add(column);
                    headerColumns.add(getColumnHeader(column));
                    alignmentColumns.add(getColumnAlignment(column));
                }
            }

            this.setVisibleColumns(visibleColumns.toArray());
            this.setColumnHeaders(headerColumns.toArray(new String[headerColumns.size()]));
            this.setColumnAlignments(alignmentColumns.toArray(new Table.Align[alignmentColumns.size()]));
        }
    }

    public void setColumnsByColumnDefinitionList(TableColumnDefinitionList tableColumnDefinitionList) {
        this.setVisibleColumns(tableColumnDefinitionList.getVisibleColumns());
        this.setColumnHeaders(tableColumnDefinitionList.getColumnHeaders());
        this.setColumnAlignments(tableColumnDefinitionList.getColumnAlignments());
        this.setColumnExpandRatios(tableColumnDefinitionList);
        this.setCollapsed(tableColumnDefinitionList);
    }

    public void setColumnExpandRatios(TableColumnDefinitionList tableColumnDefinitions) {
        for(TableColumnDefinition columnDefinition : tableColumnDefinitions.getTableColumnDefinitions()) {
            this.setColumnExpandRatio(columnDefinition.propertyName, columnDefinition.expandRatio);
        }
    }

    public void setCollapsed(TableColumnDefinitionList tableColumnDefinitions) {
        if (isColumnCollapsingAllowed()) {
            for(TableColumnDefinition columnDefinition : tableColumnDefinitions.getTableColumnDefinitions()) {
                this.setColumnCollapsed(columnDefinition.propertyName, columnDefinition.collapsed);
            }
        }
    }

    public List getCollapsedColumns() {
        if (isColumnCollapsingAllowed()) {
            List propertiIds = new ArrayList();
            for(Object colId : getVisibleColumns()) {
                if (isColumnCollapsed(colId)) {
                    propertiIds.add(colId);
                }
            }
            return propertiIds;
        }
        return null;
    }

    public ArrayList<Field> getFields() {
        return fields;
    }
}

