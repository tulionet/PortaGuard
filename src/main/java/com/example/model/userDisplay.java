package com.example.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class userDisplay {
    private StringProperty usuario;
    private StringProperty nome;
    private StringProperty role;
    private BooleanProperty selected;

    public void UserDisplay(String nome, String usuario, String role, Boolean selected) {
        this.usuario = new SimpleStringProperty(usuario);
        this.nome = new SimpleStringProperty(nome);
        this.role = new SimpleStringProperty(role);
        this.selected = new SimpleBooleanProperty(false);
    }

    public String getUsuario() {
        return usuario.get();
    }

    public void setUsuario(String usuario) {
        this.usuario.set(usuario);
    }

    public String getNome() {
        return nome.get();
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public String getRole() {
        return role.get();
    }

    public BooleanProperty selectedProperty() {
        return selected;
    }

    public boolean isSelected() {
        return selected.get();
    }

    public void setSelected(boolean selected) {
        this.selected.set(selected);
    }

}