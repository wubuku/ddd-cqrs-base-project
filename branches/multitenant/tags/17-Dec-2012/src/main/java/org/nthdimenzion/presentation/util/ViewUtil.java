package org.nthdimenzion.presentation.util;

import com.google.common.collect.Lists;
import org.nthdimenzion.object.utils.UtilValidator;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.impl.InputElement;

import java.util.List;
import java.util.Set;

public final class ViewUtil {

	public static void clearComponents(Component parent) {
	if (parent == null) return;
	List<? extends Component> children = parent.getChildren();
	if (UtilValidator.isNotEmpty(children)) {
		children.clear();
	}
	}

	@SuppressWarnings("unchecked")
	public static <T> T getSelectedItem(Listbox listbox) {
	Listitem selectedItem = listbox.getSelectedItem();
	if (selectedItem != null) return (T) selectedItem.getValue();
	return null;
	}

	public static <T> List<T> getSelectedItems(Listbox listbox) {
	Set<Listitem> selectedItems = listbox.getSelectedItems();
	List<Object> selectedObjects = Lists.newArrayList();
	for (Listitem listitem : selectedItems)
		selectedObjects.add(listitem.getValue());
	return (List<T>) selectedObjects;
	}

	public static void validateEmail(Textbox textbox) {
	String email = textbox.getValue();
	if (UtilValidator.isNotEmpty(email) && !UtilValidator.validateEmail(email)) {
		throw new WrongValueException(textbox, "Invalid Email format");
	}
	}

	public static void setDisabled(boolean disabled, InputElement... elements) {
	for (InputElement element : elements) {
		element.setDisabled(disabled);
	}
	}
}
