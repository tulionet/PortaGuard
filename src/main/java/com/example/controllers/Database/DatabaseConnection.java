package com.example.controllers.Database;

import com.example.model.User;
import com.example.model.accesType;
import com.example.model.userDisplay;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlserver://HM83GZ3\\SQLEXPRESS;databaseName=Portaria;trustServerCertificate=true";
    private static final String USER = "Portaria";
    private static final String PASSWORD = "Portaria@2023";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public ObservableList<userDisplay> fillTableUsers() {
        ObservableList<userDisplay> data = FXCollections.observableArrayList();
        String query = "SELECT * FROM Usuarios";

        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery()) {

            // ResultSetMetaData rsmd = rs.getMetaData();
            // int columnsNumber = rsmd.getColumnCount();

            while (rs.next()) {
                String nome = rs.getString("Nome");
                String usuario = rs.getString("Usuario");
                
                int tipo = rs.getInt("tipo");
                String role = null; 
                accesType[] values = accesType.values();
                accesType perm = null;
                        for (accesType value : values) {
                            if (value.ordinal() + 1 == tipo) {
                                perm = value;
                                break;
                            }
                        }
                        if (perm != null) {
                            int nextIndex = (perm.ordinal()) % values.length;
                            accesType nextType = values[nextIndex];
                            role = nextType.name();
                        }
            
                userDisplay user = new userDisplay();
                user.UserDisplay(nome, usuario, role, false);
                
                // userDisplay UserDisplay = new userDisplay();
                // for (int i = 1; i <= columnsNumber; i++) {
                //     String columnName = rsmd.getColumnName(i);
                //     String columnValue = rs.getString(i);
                //     switch (columnName) {
                //         case "Usuario":
                //         UserDisplay.setUsuario(columnValue);
                //             System.out.println("Usuário: " + UserDisplay.getUsuario());
                //             break;
                //         case "Nome":
                //             UserDisplay.setNome(columnValue);
                //             System.out.println("Nome: " + UserDisplay.getNome());
                //             break;
                //         case "Role":
                //             UserDisplay.setRole(columnValue);
                //             System.out.println("Role: " + UserDisplay.getRole());
                //             break;
                //     }
                data.add(user);
            }
                
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public boolean auth(String username, String password) {
        String sql = "SELECT * FROM Usuarios WHERE Usuario = ? AND Senha = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
    
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
    
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("Nome");
                int role = resultSet.getInt("Tipo");
                accesType accesType = com.example.model.accesType.values()[role - 1];
                
                User User = new User(name, password, accesType);
                return true;
            } else {
                return false;
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean userRegister(String name, String user, String password,  int type) {
        String sql = "INSERT INTO Usuarios (Nome, Usuario, Senha, Tipo) VALUES (?, ?, ?, ?)";
    
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
    
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, user);
            preparedStatement.setString(3, password);
            preparedStatement.setInt(4, type);
    
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
    
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}



