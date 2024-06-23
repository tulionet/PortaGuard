package com.example.controllers.Database;

import com.example.model.Alerts;
import com.example.model.Driver;
import com.example.model.User;
import com.example.model.UserManager;
import com.example.model.accesType;
import com.example.model.nf;
import com.example.model.userDisplay;
import com.example.model.visitor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlserver://#####\\SQLEXPRESS;databaseName=######;trustServerCertificate=true";
    private static final String USER = "#####";
    private static final String PASSWORD = "######";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private PreparedStatement prepareStatement(String sql, Object... parameters) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        
        for (int i = 0; i < parameters.length; i++) {
            preparedStatement.setObject(i + 1, parameters[i]);
        }
        
        return preparedStatement;
    }
    

    public ObservableList<userDisplay> fillTableUsers() {
        ObservableList<userDisplay> data = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Usuarios";

        // System.out.println("SELECT * FROM Usuarios");

        try (PreparedStatement preparedStatement = prepareStatement(sql)) {
                ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String nome = rs.getString("Nome");
                String usuario = rs.getString("Usuario");
                
                int tipo = rs.getInt("Tipo");
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
                Boolean isEnable = rs.getBoolean("isEnable");
                String isEnableFinal = null;
                if (isEnable == true) {
                    isEnableFinal = "SIM";
                } else {isEnableFinal = "NÃO";
                    }
            
                userDisplay user = new userDisplay(nome, usuario, role, false, isEnableFinal);
                
                data.add(user);
            }  
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public ObservableList<Driver> fillTableDrivers() {
        ObservableList<Driver> data = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Motoristas";

        // System.out.println("SELECT * FROM Motoristas");

        try (PreparedStatement preparedStatement = prepareStatement(sql)) {
                ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String driverName = rs.getString("NomeMotorista");
                String cpf = rs.getString("CPF");
                String carPlate = rs.getString("PlacaVeiculo");
                String companyName = rs.getString("NomeEmpresa");
                String telephone = rs.getString("telefone");
            
                Driver driver = new Driver(driverName, cpf, carPlate, companyName, telephone);
                
                data.add(driver);
            }  
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public ObservableList<visitor> fillTableVisitorsRegistered() {
        ObservableList<visitor> data = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Visitantes_Cadastrados";

        // System.out.println("SELECT * FROM Visitantes_Cadastrados");

        try (PreparedStatement preparedStatement = prepareStatement(sql)) {
                ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String visitorName = rs.getString("Nome");
                String cpf = rs.getString("CPF");
                String companyName = rs.getString("Empresa");
            
                visitor visitor = new visitor(visitorName, cpf, companyName);

                data.add(visitor);
            }  
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public ObservableList<visitor> fillTableVisitors(int flg) {
        ObservableList<visitor> data = FXCollections.observableArrayList();
        String sql = "SELECT a.DataHoraEntrada, a.DataHoraSaida, b.Nome AS 'Nome Visitante', b.CPF, b.Empresa, a.Motivo , c.Nome AS 'Porteiro Resp.' " +
            "FROM visitantes a " +
            "JOIN visitantes_cadastrados b ON a.IDVisitante = b.ID " +
            "JOIN usuarios c ON a.IDPorteiro = c.ID " +
            "WHERE a.flgIN = ?";

        // System.out.println("SELECT a.DataHoraEntrada, a.DataHoraSaida, b.Nome AS 'Nome Visitante', b.CPF, b.Empresa, a.Motivo , c.Nome AS 'Porteiro Resp.' " +
        // "FROM visitantes a " +
        // "JOIN visitantes_cadastrados b ON a.IDVisitante = b.ID " +
        // "JOIN usuarios c ON a.IDPorteiro = c.ID " +
        // "WHERE a.flgIN = " + flg);

        try (PreparedStatement preparedStatement = prepareStatement(sql, flg)) {
                ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String entranceHour = rs.getString("DataHoraEntrada");
                String exitHour = rs.getString("DataHoraSaida");
                String visitorName = rs.getString("Nome Visitante");
                String cpf = rs.getString("CPF");
                String companyName = rs.getString("Empresa");
                String user = rs.getString("Porteiro Resp.");
                String reason = rs.getString("Motivo");
            
                visitor visitor = new visitor(entranceHour, exitHour, visitorName, cpf, companyName, reason, user);
                
                data.add(visitor);
            }  
        } catch (SQLException e) {
             e.printStackTrace();
        }
        return data;
    }

    public ObservableList<nf> fillTableNF() {
        ObservableList<nf> data = FXCollections.observableArrayList();
        String sql = "SELECT a.datahora AS datahora, a.situacao AS situacao, a.nf AS nf, b.NomeMotorista AS NomeMotorista, b.CPF AS CPF, b.NomeEmpresa AS NomeEmpresa, b.PlacaVeiculo AS PlacaVeiculo, b.telefone, c.Nome AS Nome FROM nfs a JOIN Motoristas b ON a.MotoristaID = b.ID JOIN usuarios c ON a.porteiroResp = c.ID";

        // System.out.println("SELECT a.datahora AS datahora, a.situacao AS situacao, a.nf AS nf, b.NomeMotorista AS NomeMotorista, b.CPF AS CPF, b.NomeEmpresa AS NomeEmpresa, b.PlacaVeiculo AS PlacaVeiculo, b.telefone, c.Nome AS Nome FROM nfs a JOIN Motoristas b ON a.MotoristaID = b.ID JOIN usuarios c ON a.porteiroResp = c.ID");

        try (PreparedStatement preparedStatement = prepareStatement(sql)) {
                ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String datahora = rs.getString("datahora");
                String situacao = rs.getString("situacao");
                Double nf = rs.getDouble("nf");
                String driverName = rs.getString("NomeMotorista");
                String driverCPF = rs.getString("CPF");
                String companyName = rs.getString("NomeEmpresa");
                String driverPlate = rs.getString("PlacaVeiculo");
                String telephone = rs.getString("telefone");
                String user = rs.getString("Nome");
                nf NFClass = new nf(datahora, situacao, nf, driverName, driverCPF, companyName, driverPlate, telephone, user);                
                data.add(NFClass);
            }  
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    

    public boolean auth(String username, String password) {
        String sql = "SELECT * FROM Usuarios WHERE Usuario = ? AND Senha = ? AND isEnable = 1";

        // System.out.println("SELECT * FROM Usuarios WHERE Usuario = " + username + " AND Senha = " + password + "AND isEnable = 1");

        try (PreparedStatement preparedStatement = prepareStatement(sql, username, password)) {
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("Nome");
                int role = rs.getInt("Tipo");
                accesType accesType = com.example.model.accesType.values()[role - 1];
                User user = new User(id, name, username, password, accesType);
                UserManager.setUser(user);

                return true;
            } 
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean userRegister(String name, String user, String password,  int type) {
        String sql = "INSERT INTO Usuarios (Nome, Usuario, Senha, Tipo, isEnable) VALUES (?, ?, ?, ?, 1)";
    
        // System.out.println("INSERT INTO Usuarios (Nome, Usuario, Senha, Tipo, isEnable) VALUES ('" + name + "', '" + user + "', '" + password + "', " + type + ", 1)");

        try (PreparedStatement preparedStatement = prepareStatement(sql, name, user, password, type)) {
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void editUser(String name, String user, String password, int type) {
        String sql = "UPDATE Usuarios SET Nome = ?, Senha = ?, Tipo = ? WHERE Usuario = ?";

        // System.out.println("UPDATE Usuarios SET Nome =" + name +", Senha = "+password+", Tipo = "+type+" WHERE Usuario = "+user);

        try (PreparedStatement preparedStatement = prepareStatement(sql, name, password, type, user)) {
            preparedStatement.executeUpdate();
            System.out.println("UPDATE Usuarios SET Nome =" + name +", Senha = "+password+", Tipo = "+type+" WHERE Usuario = "+user);
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }

    public void editUser2(String name, String user, int type) {
        String sql = "UPDATE Usuarios SET Nome = ?, Tipo = ? WHERE Usuario = ?";

        // System.out.println("UPDATE Usuarios SET Nome =" + name +", Tipo = "+type+" WHERE Usuario = "+user);

        try (PreparedStatement preparedStatement = prepareStatement(sql, name, type, user)) {
            preparedStatement.executeUpdate();
            System.out.println("UPDATE Usuarios SET Nome =" + name +", Tipo = "+type+" WHERE Usuario = "+user);
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }

    public boolean enableDisableUser(String username, String enable) {
        String sql = "UPDATE usuarios SET isEnable = ? WHERE Usuario = ?";

        // System.out.println("UPDATE usuarios SET isEnable = " + enable + " WHERE Usuario = " + username);

        int isEnable = enable.equals("SIM") ? 0 : 1;
        try (PreparedStatement preparedStatement = prepareStatement(sql, isEnable, username)) {
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editDriver(String carPlate, String companyName, String telephone, String cpf) {
        String sql = "UPDATE Motoristas SET PlacaVeiculo = ?, NomeEmpresa = ?, telefone = ? WHERE CPF = ?";

        // System.out.println("UPDATE Motoristas SET PlacaVeiculo = " + carPlate + ", NomeEmpresa = " + companyName + ", telefone = " + telephone + " WHERE CPF = " + cpf);

        try (PreparedStatement preparedStatement = prepareStatement(sql, carPlate, companyName, telephone, cpf)) {
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editVisitor(String visitorName, String companyName, String cpf) {
        String sql = "UPDATE Visitantes_Cadastrados SET Nome = ?, Empresa = ? WHERE CPF = ?";

        // System.out.println("UPDATE Visitantes_Cadastrados SET Nome =" + visitorName +", Empresa = "+companyName+" WHERE CPF = "+cpf);

        try (PreparedStatement preparedStatement = prepareStatement(sql, visitorName, companyName, cpf)) {
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean nfEnter(String CPFQuery, String nf, String situacao, int porteiro) {
        String sql = "INSERT INTO NFs (Situacao, NF, porteiroResp, MotoristaID, DataHora) VALUES (?, ?, ?, (SELECT iD FROM Motoristas WHERE CPF = ?), GETDATE())";
   
        // System.out.println("INSERT INTO NFs (Situacao, NF, porteiroResp, MotoristaID, DataHora) VALUES ('" 
        // + situacao + "', '" 
        // + nf + "', '" 
        // + porteiro + "', (SELECT iD FROM Motoristas WHERE CPF = '" 
        // + CPFQuery + "'), GETDATE())");
    
        try (PreparedStatement preparedStatement = prepareStatement(sql, situacao, nf, porteiro, CPFQuery)) {
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean visitorEnter(String cpf, String reason, int porteiro) {
        String sql = "IF NOT EXISTS (SELECT 1 FROM visitantes a JOIN Visitantes_Cadastrados b ON a.IDVisitante = b.ID WHERE CPF = ? AND flgIN = 1) BEGIN INSERT INTO visitantes (IDPorteiro, IDVisitante, Motivo, DataHoraEntrada, flgIN) VALUES (?, (SELECT ID FROM Visitantes_Cadastrados WHERE CPF = ?), ?, GETDATE(), 1) END";
        // System.out.println("IF NOT EXISTS (SELECT 1 FROM visitantes a JOIN Visitantes_Cadastrados b ON a. IDVisitante = b.ID WHERE CPF = " + cpf + " AND flgIN = 1) BEGIN INSERT INTO visitantes (IDPorteiro, IDVisitante, Motivo, DataHoraEntrada, flgIN) VALUES ('" + porteiro + "', (SELECT ID FROM Visitantes_Cadastrados WHERE CPF = '" + cpf + "'), '" + reason + "', GETDATE(), 1) END");
        try (PreparedStatement preparedStatement = prepareStatement(sql, cpf, porteiro, cpf, reason)) {
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public Driver filterDriverByCPF(String Cpf) {
        String sql = "SELECT * FROM Motoristas WHERE CPF = ?";

        // System.out.println("SELECT * FROM Motoristas WHERE CPF = " + Cpf);
        Driver driver = new Driver();
        try (PreparedStatement preparedStatement = prepareStatement(sql, Cpf)) {
            ResultSet rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    driver.setName(rs.getString("NomeMotorista"));
                    driver.setCompanyName(rs.getString("NomeEmpresa"));
                    driver.setCpf(rs.getString("CPF"));
                    driver.setCarPlate(rs.getString("PlacaVeiculo"));
                    driver.setTelephone(rs.getString("telefone"));
                    return driver;
            }  else { 
                new Alerts().showAlertError("Motorista não encontrado no sistema.", null);
                return null;
            }
        } catch (SQLException e) {
            // e.printStackTrace();
        }
        return null;
    }

    public visitor filterVisitorByCPF(String cpf) {
        String sql = "SELECT * FROM Visitantes_Cadastrados WHERE CPF = ?";

        // System.out.println("SELECT * FROM Visitantes_Cadastrados WHERE CPF = " + cpf);
        visitor visitor = new visitor();
        try (PreparedStatement preparedStatement = prepareStatement(sql, cpf)) {
            ResultSet rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    visitor.setVisitorName(rs.getString("Nome"));
                    visitor.setCompanyName(rs.getString("Empresa"));
            }  else { 
                new Alerts().showAlertError("Visitante não encontrado no sistema.", null);
                return null;
            }
        } catch (SQLException e) {
            // e.printStackTrace();
        }
        return visitor;
    }
    public ObservableList<nf> filterNF(LocalDate localDate, String situacaoFilter, String nfFilter, String nameFilter, String cpfFilter, String companyNameFilter, String carPlateFilter, String telephoneFilter, String userFilter) {
        ObservableList<nf> data = FXCollections.observableArrayList();
        String sql = "SELECT a.datahora AS datahora, a.situacao AS situacao, a.nf AS nf, b.NomeMotorista AS NomeMotorista, b.CPF AS CPF, b.NomeEmpresa AS NomeEmpresa, b.PlacaVeiculo AS PlacaVeiculo, b.telefone AS Telefone, c.Nome AS Nome " +
            "FROM nfs a JOIN Motoristas b ON a.MotoristaID = b.ID JOIN usuarios c ON a.porteiroResp = c.ID " +
            "WHERE CONVERT(DATE, a.datahora) LIKE ? AND a.situacao LIKE ? AND a.nf LIKE ? AND b.NomeMotorista LIKE ? AND b.CPF LIKE ? AND b.NomeEmpresa LIKE ? AND b.PlacaVeiculo LIKE ? AND b.telefone LIKE ? AND c.Nome LIKE ?";
        String SQLdate = "";
        if (localDate != null) {
            SQLdate = ("%" + localDate + "%");
        } else {
            SQLdate = "%";
        }
        String SQLsituacao = ("%" + situacaoFilter + "%");
        String SQLnf = ("%" + nfFilter + "%");
        String SQLname = ("%" + nameFilter + "%");
        String SQLcpf = ("%" + cpfFilter + "%");
        String SQLcompanyName = ("%" + companyNameFilter + "%");
        String SQLcarPlate = ("%" + carPlateFilter + "%");
        String SQLtelefone = ("%" + telephoneFilter + "%");
        String  SQLporteiro = ("%" + userFilter + "%");
        
        // System.out.println("Select a.datahora AS datahora, a.situacao AS situacao, a.nf AS nf, b.NomeMotorista AS NomeMotorista, b.CPF AS CPF, b.NomeEmpresa AS NomeEmpresa, b.PlacaVeiculo AS PlacaVeiculo, b.telefone AS Telefone, c.Nome AS Nome " +
        //     "FROM nfs a JOIN Motoristas b ON a.MotoristaID = b.ID JOIN usuarios c ON a.porteiroResp = c.ID " +
        //     "WHERE CONVERT(DATE, a.datahora) LIKE '" + SQLdate + "' AND a.situacao LIKE '" + SQLsituacao + "' AND a.nf LIKE '" + SQLnf + "' AND b.NomeMotorista LIKE '" + SQLname + "' AND b.CPF LIKE '" + SQLcpf + "' AND b.NomeEmpresa LIKE '" + SQLcompanyName + "' AND b.PlacaVeiculo LIKE '" + SQLcarPlate + "' AND b.telefone LIKE '" + SQLtelefone + "' AND c.Nome LIKE '" + SQLporteiro + "'");
        try (PreparedStatement preparedStatement = prepareStatement(sql, SQLdate, SQLsituacao, SQLnf, SQLname, SQLcpf, SQLcompanyName, SQLcarPlate, SQLtelefone, SQLporteiro)) {
                ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String datahora = rs.getString("datahora");
                String situacao = rs.getString("situacao");
                Double nf = rs.getDouble("nf");
                String driverName = rs.getString("NomeMotorista");
                String driverCPF = rs.getString("CPF");
                String companyName = rs.getString("NomeEmpresa");
                String driverPlate = rs.getString("PlacaVeiculo");
                String telephone = rs.getString("telefone");
                String user = rs.getString("Nome");
                System.out.println(datahora + " " + situacao + " " + nf + " " + driverName + " " + driverCPF + " " + companyName + " " + driverPlate + " " + telephone + " " + user);
                nf NFClass = new nf(datahora, situacao, nf, driverName, driverCPF, companyName, driverPlate, telephone, user);                
                data.add(NFClass);
            }  
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        return data;
    }

    public ObservableList<visitor> filterVisitor(LocalDate enterHourQuery, LocalDate exitHourQuery, String visitorNameQuery, String visitorCPFQuery, String companyNameQuery, String reasonQuery, String userQuery) {
        ObservableList<visitor> data = FXCollections.observableArrayList();
        String sql = "SELECT a.DataHoraEntrada, a.DataHoraSaida, b.Nome AS 'Nome Visitante', b.CPF, b.Empresa, a.Motivo , c.Nome AS 'Porteiro Resp.' " +
            "FROM visitantes a " +
            "JOIN visitantes_cadastrados b ON a.IDVisitante = b.ID " +
            "JOIN usuarios c ON a.IDPorteiro = c.ID " +
            "WHERE CONVERT(DATE, a.DataHoraEntrada) LIKE ? AND CONVERT(DATE, a.DataHoraSaida) LIKE ? AND b.Nome LIKE ? AND b.CPF LIKE ? AND b.Empresa LIKE ? AND a.Motivo LIKE ? AND c.Nome LIKE ? AND a.FlgIN = 0";
        String SQLdatahoraEntrada = ("%" + enterHourQuery + "%");
        if (enterHourQuery != null) {
            SQLdatahoraEntrada = ("%" + enterHourQuery + "%");
        } else {
            SQLdatahoraEntrada = "%";
        }
        String SQLdatahoraSaida = ("%" + exitHourQuery + "%");
        if (exitHourQuery != null) {
            SQLdatahoraSaida = ("%" + exitHourQuery + "%");
        } else {
            SQLdatahoraSaida = "%";
        }
        String SQLvisitanteName = ("%" + visitorNameQuery + "%");
        String SQLvisitanteCPF = ("%" + visitorCPFQuery + "%");
        String SQLcompanyName = ("%" + companyNameQuery + "%");
        String SQLreason = ("%" + reasonQuery + "%");
        String SQLporteiro = ("%" + userQuery + "%");
        
        // System.out.println("Select a.DataHoraEntrada, a.DataHoraSaida, b.Nome AS 'Nome Visitante', b.CPF, b.Empresa, a.Motivo , c.Nome AS 'Porteiro Resp.' " +
        //     "FROM visitantes a " +
        //     "JOIN visitantes_cadastrados b ON a.IDVisitante = b.ID " +
        //     "JOIN usuarios c ON a.IDPorteiro = c.ID " +
        //     "WHERE CONVERT(DATE, a.DataHoraEntrada) LIKE '" + SQLdatahoraEntrada + "' AND CONVERT(DATE, a.DataHoraSaida) LIKE '" + SQLdatahoraSaida + "' AND b.Nome LIKE '" + SQLvisitanteName + "' AND b.CPF LIKE '" + SQLvisitanteCPF + "' AND b.Empresa LIKE '" + SQLcompanyName + "' AND a.Motivo LIKE '" + SQLreason + "' AND c.Nome LIKE '" + SQLporteiro + "'");
        try (PreparedStatement preparedStatement = prepareStatement(sql, SQLdatahoraEntrada, SQLdatahoraSaida, SQLvisitanteName, SQLvisitanteCPF, SQLcompanyName, SQLreason, SQLporteiro)) {
                ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String entranceHour = rs.getString("DataHoraEntrada");
                String exitHour = rs.getString("DataHoraSaida");
                String visitorName = rs.getString("Nome Visitante");
                String cpf = rs.getString("CPF");
                String companyName = rs.getString("Empresa");
                String user = rs.getString("Porteiro Resp.");
                String reason = rs.getString("Motivo");
            
                visitor visitor = new visitor(entranceHour, exitHour, visitorName, cpf, companyName, reason, user);
                
                data.add(visitor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        return data;
    }

    public boolean driverRegister(String Cpf, String carPlate, String name,  String companyName, String telephone) {
        String sql = "IF NOT EXISTS (SELECT 1 FROM Motoristas WHERE CPF = ?) BEGIN INSERT INTO Motoristas (NomeMotorista, CPF, PlacaVeiculo, NomeEmpresa, telefone) VALUES (?, ?, ?, ?, ?) END";
    
        System.out.println("IF NOT EXISTS (SELECT 1 FROM Motoristas WHERE CPF = '" + Cpf + "') BEGIN INSERT INTO Motoristas (NomeMotorista, CPF, PlacaVeiculo, NomeEmpresa, telefone) VALUES ('" + name + "', '" + Cpf + "', '" + carPlate + "', '" + companyName + "', '" + telephone + "') END");
    
        try (PreparedStatement preparedStatement = prepareStatement(sql, Cpf, name, Cpf, carPlate, companyName, telephone)) {
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean visitorRegister(String cpf, String name, String companyName) {
        String sql = "INSERT INTO Visitantes_Cadastrados (CPF, Nome, Empresa) VALUES (?, ?, ?)";
    
        // System.out.println("INSERT INTO Visitantes_Cadastrados (CPF, Nome, Empresa) VALUES (" 
        // + "'" + cpf + "', " 
        // + "'" + name + "', " 
        // + "'" + companyName);
    
        try (PreparedStatement preparedStatement = prepareStatement(sql, cpf, name, companyName)) {
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void exitVisitor(String cpf) {
        String sql = "UPDATE a\r\n" +
                "SET a.flgIN = 0, a.DataHoraSaida = GETDATE()\r\n" +
                "FROM visitantes a \r\n" +
                "INNER JOIN visitantes_cadastrados b on a.IDVisitante = b.ID\r\n" +
                "WHERE a.flgIN = 1 AND b.CPF = ?";

        // System.out.println("UPDATE a\r\n" +
        //     "SET a.flgIN = 0, a.DataHoraSaida = GETDATE()\r\n" +
        //     "FROM visitantes a \r\n" +
        //     "INNER JOIN visitantes_cadastrados b on a.IDVisitante = b.ID\r\n" +
        //     "WHERE a.flgIN = 1 AND b.CPF = " + cpf);
        try (PreparedStatement preparedStatement = prepareStatement(sql, cpf)) {
            preparedStatement.executeUpdate();
            System.out.println("UPDATE visitantes SET flgIN = 0, DataHoraSaida = GETDATE() WHERE CPF = "+cpf);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 

}

