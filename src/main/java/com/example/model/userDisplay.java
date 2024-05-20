package com.example.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class userDisplay {
    private SimpleStringProperty usuario;
    private SimpleStringProperty nome;
    private SimpleStringProperty role;
    private SimpleBooleanProperty selected;

    public userDisplay(String nome, String usuario, String role, Boolean selected) {
        this.usuario = new SimpleStringProperty(usuario);
        this.nome = new SimpleStringProperty(nome);
        this.role = new SimpleStringProperty(role);
        this.selected = new SimpleBooleanProperty(false);
    }
    

    // public void UserDisplay(String nome, String usuario, String role, Boolean selected) {
    //     this.usuario = new SimpleStringProperty(usuario);
    //     this.nome = new SimpleStringProperty(nome);
    //     this.role = new SimpleStringProperty(role);
    //     this.selected = new SimpleBooleanProperty(false);
    // }

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

    public SimpleBooleanProperty selectedProperty() {
        return selected;
    }

    public boolean isSelected() {
        return selected.get();
    }

    public void setSelected(boolean selected) {
        this.selected.set(selected);
    }

}