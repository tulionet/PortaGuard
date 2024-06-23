package com.example.model.Dialog;

import com.example.model.nf;

import javafx.collections.ObservableList;

public interface DialogCallbackNF {
    void onDataReceived(ObservableList<nf> data);
}
