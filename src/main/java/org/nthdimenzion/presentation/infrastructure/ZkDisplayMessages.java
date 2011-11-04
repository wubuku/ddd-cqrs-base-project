package org.nthdimenzion.presentation.infrastructure;

import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.sys.ExecutionsCtrl;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;


public class ZkDisplayMessages implements IDisplayMessages<EventListener> {


    private final String title;

    private String sucessfulOperationMessage = "Operation Completed Successfully";

    private String confirmationMessage = "Do you want to proceed ?";

    public String successsMessageLabelId = "successMsg";

    private IMultiLineMessageBox multiLineMessageBox;

    public ZkDisplayMessages(String title, IMultiLineMessageBox multiLineMessageBox) {
        this.title = title;
        this.multiLineMessageBox = multiLineMessageBox;
    }


    @Override
    public void showConfirmation(EventListener eventListener) {
        showConfirmation(confirmationMessage, eventListener);
    }

    @Override
    public void showConfirmation(String message, EventListener eventListener) {
        confirm(message, title, Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, eventListener);
    }

    @Override
    public void showSuccess() {
        showSuccess(sucessfulOperationMessage);
    }

    @Override
    public void showSuccess(String message) {
        display(message, Messagebox.OK, Messagebox.INFORMATION);
    }

    @Override
    public void showMessage(String message) {
        display(message, Messagebox.OK, Messagebox.EXCLAMATION);
    }

    @Override
    public void showError(String message) {
        display(message, ZkMultiLineMessageBox.OK, Messagebox.ERROR);
    }

    @Override
    public void displayError(String message) {
        display(message, ZkMultiLineMessageBox.OK, ZkMultiLineMessageBox.ERROR);
    }

    @Override
    public void displaySuccess() {
        displaySuccess(sucessfulOperationMessage);
    }

    @Override
    public void displaySuccess(String message) {
        display(message, ZkMultiLineMessageBox.OK, ZkMultiLineMessageBox.INFORMATION);
    }

    @Override
    public void confirm(String message, String title, int buttons, String icon, EventListener eventListener) {
        try {
            Messagebox.show(message, title, buttons, icon, eventListener);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clearMessage() {
        final Label l = (Label) ExecutionsCtrl.getCurrentCtrl().getCurrentPage().getFellow(successsMessageLabelId, true);
        l.setValue(null);
    }

    private int display(String message, int buttons, String icon) {
        if (isErrorMessage(icon) || isSuccessMessageLabelNotAvailable()) {
            try {
                return multiLineMessageBox.show(message, title, buttons, icon, true);
            } catch (InterruptedException e) {
                throw new RuntimeException("Contact Administrator", e);
            }
        } else {
            final Label l = (Label) ExecutionsCtrl.getCurrentCtrl().getCurrentPage().getFellow(successsMessageLabelId, true);
            l.setValue(message);
            return 0;
        }
    }

    private boolean isSuccessMessageLabelNotAvailable() {
        return !isSuccessMessageLabelAvailable();
    }

    private boolean isSuccessMessageLabelAvailable() {
        return ExecutionsCtrl.getCurrentCtrl().getCurrentPage().getFellowIfAny(successsMessageLabelId, true) != null;
    }

    private boolean isErrorMessage(String icon) {
        return icon != null && icon.equals(ZkMultiLineMessageBox.ERROR);
    }

    public void setSucessfulOperationMessage(String sucessfulOperationMessage) {
        this.sucessfulOperationMessage = sucessfulOperationMessage;
    }

    public void setConfirmationMessage(String confirmationMessage) {
        this.confirmationMessage = confirmationMessage;
    }

    public void setSuccesssMessageLabelId(String successsMessageLabelId) {
        this.successsMessageLabelId = successsMessageLabelId;
    }

    public void setMultiLineMessageBox(IMultiLineMessageBox multiLineMessageBox) {
        this.multiLineMessageBox = multiLineMessageBox;
    }
}