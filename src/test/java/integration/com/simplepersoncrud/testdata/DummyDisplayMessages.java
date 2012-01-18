package com.simplepersoncrud.testdata;

import org.nthdimenzion.presentation.infrastructure.IDisplayMessages;

public class DummyDisplayMessages implements IDisplayMessages<Object> {

    public String message;

    @Override
    public void showSuccessInPopUp(String message) {
    this.message = message;
    }

    @Override
    public void showSuccessInPopUp() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void showConfirmation(Object eventListener) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void showConfirmation(String message, Object eventListener) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void showSuccess() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void showSuccess(String message) {
        this.message = message;
    }

    @Override
    public void showMessage(String message) {
        this.message = message;
    }

    @Override
    public void showError(String message) {
        this.message = message;
    }

    @Override
    public void displayError(String message) {
        this.message = message;
    }

    @Override
    public void displaySuccess() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void displaySuccess(String message) {
        this.message = message;
    }

    @Override
    public void confirm(String message, String title, int buttons, String icon, Object eventListener) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void clearMessage() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String toString() {
        return message;
    }
}
