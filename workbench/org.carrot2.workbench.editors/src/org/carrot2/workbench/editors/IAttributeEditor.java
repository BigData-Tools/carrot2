package org.carrot2.workbench.editors;

import org.carrot2.util.attribute.AttributeDescriptor;
import org.eclipse.swt.widgets.Composite;

/**
 * Attribute editor is a control, that is used to edit and display value of given
 * attribute.
 * 
 * The lifecycle of a attribute editor is as follows:
 * <ol>
 * <li>call editor.init(descriptor), if exception is thrown -> stop.
 * <li>call editor.createEditor(parent), if exception -> goto 5
 * <li>call editor.setValue(currentValue), if exception -> goto 5
 * <li>call editor.getValue()
 * <li>call editor.dispose()
 * <ol>
 */
public interface IAttributeEditor
{

    void init(AttributeDescriptor descriptor);

    void createEditor(Composite parent);

    void setValue(Object currentValue);

    Object getValue();

    void dispose();

}
