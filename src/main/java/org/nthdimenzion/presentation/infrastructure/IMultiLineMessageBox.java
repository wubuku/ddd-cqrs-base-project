package org.nthdimenzion.presentation.infrastructure;

public interface IMultiLineMessageBox<T> {

    int show(String message, String title, int buttons, String icon, boolean padding)
			throws InterruptedException;

    int show(String message, String title, int buttons, String icon, boolean padding,
			T listener) throws InterruptedException;
}
