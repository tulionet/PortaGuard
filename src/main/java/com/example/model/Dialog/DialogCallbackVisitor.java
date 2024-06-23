package com.example.model.Dialog;

import com.example.model.visitor;

import javafx.collections.ObservableList;

public interface DialogCallbackVisitor {
    void onDataReceived(ObservableList<visitor> data);
}
