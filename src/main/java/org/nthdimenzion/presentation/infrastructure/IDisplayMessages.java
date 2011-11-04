package org.nthdimenzion.presentation.infrastructure;

public interface IDisplayMessages<T> {
    

    void showConfirmation(T eventListener);

	void showConfirmation(String message, T eventListener);

	void showSuccess();

	void showSuccess(String message);

	void showMessage(String message);

	void showError(String message);

	void displayError(String message);

	void displaySuccess();

	void displaySuccess(String message);

	void confirm(String message, String title, int buttons, String icon, T eventListener);

    void clearMessage();
}
