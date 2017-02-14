package com.springboot.vaadin.components;

import com.vaadin.ui.Table;
import org.jetbrains.annotations.NonNls;

/**
 * Created by maggouh on 13/02/17.
 */
public class TableColumnDefinition {
    public final String propertyName;
    public final String caption;
    public final Table.Align alignment;
    public boolean collapsed;
    public float expandRatio;

    public TableColumnDefinition(@NonNls String propertyName, String caption, Table.Align alignment) {
        this.propertyName = propertyName;
        this.caption = caption;
        this.alignment = alignment;
    }

    public TableColumnDefinition(@NonNls String propertyName, String caption, Table.Align alignment, boolean collapsed) {
        this.propertyName = propertyName;
        this.caption = caption;
        this.alignment = alignment;
        this.collapsed = collapsed;
    }

    public TableColumnDefinition(String propertyName, String caption, Table.Align alignment, boolean collapsed, float expandRatio) {
        this.propertyName = propertyName;
        this.caption = caption;
        this.alignment = alignment;
        this.collapsed = collapsed;
        this.expandRatio = expandRatio;
    }

    public TableColumnDefinition(String propertyName, String caption, Table.Align alignment, float expandRatio) {
        this.propertyName = propertyName;
        this.caption = caption;
        this.alignment = alignment;
        this.expandRatio = expandRatio;
    }
}

