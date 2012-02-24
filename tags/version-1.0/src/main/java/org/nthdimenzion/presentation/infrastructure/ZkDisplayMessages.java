package org.nthdimenzion.presentation.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.sys.ExecutionsCtrl;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;


public class ZkDisplayMessages implements IDisplayMessages<EventListener> {


    public static final String STYLE = "font-size:14px;color:%s;line-height:40px;font-weight:bold";
    private final String title;
    protected static final Logger logger = LoggerFactory.getLogger(ZkDisplayMessages.class);

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
    public void showSuccessInPopUp(String message) {
        display(message, Messagebox.OK, Messagebox.INFORMATION, true);
    }

    @Override
    public void showSuccessInPopUp() {
        showSuccess(sucessfulOperationMessage);
    }


    @Override
    public void showMessage(String message) {
        display(message, Messagebox.OK, Messagebox.EXCLAMATION);
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
            logger.warn("Error in display messages",e);

        }
    }

    @Override
    public void clearMessage() {
        final Label l = getHeaderLabel();
        l.setValue(null);
    }

    private int display(String message, int buttons, String icon, boolean isPopup) {
        if (isErrorMessage(icon) || isSuccessMessageLabelNotAvailable() || isPopup) {
            final Label l = getHeaderLabel();
            l.setValue(message);
            l.setStyle(getStyleForLabel("red"));
            return 0;
        } else {
            final Label l = getHeaderLabel();
            l.setValue(message);
            l.setStyle(getStyleForLabel("#00AA00"));
            return 0;
        }
    }

    public String getStyleForLabel(String color) {
        return String.format(STYLE, color);
    }

    private Label getHeaderLabel() {
        return (Label) ExecutionsCtrl.getCurrentCtrl().getCurrentPage().getFellow(successsMessageLabelId, true);
    }

    private int display(String message, int buttons, String icon) {
        return display(message, buttons, icon, false);
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