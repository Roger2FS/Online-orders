package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import Connection.ConnectionFactory;
import Model.Client;
public class ClientDAO {
    protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO Client (idClient,ClientName,email,address)"
            + " VALUES (?,?,?,?)";
    private final static String findStatementString = "SELECT * FROM Client where idClient = ?";
    private final static String deleteStatementString = "DELETE FROM Client where idClient = ?";
    private final static String editStatementString = "UPDATE client SET %s = ? WHERE idClient = ?";
    private final static String selectAllStatementString = "SELECT * FROM client";
    public static Client findById(int idClient) {
        Client toReturn = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setLong(1, idClient);
            rs = findStatement.executeQuery();
            rs.next();

            String ClientName = rs.getString("ClientName");
            String email = rs.getString("email");
            String address = rs.getString("address");
            toReturn = new Client(idClient, ClientName, email, address);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ClientDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        System.out.print(toReturn.getId() + " " + toReturn.getName() + " " + toReturn.getEmail() + " " + toReturn.getAddress());
        return toReturn;
    }
    public static int insert(Client client) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            Statement query = dbConnection.createStatement() ;

            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1,client.getId());
            insertStatement.setString(2, client.getName());
            insertStatement.setString(3, client.getEmail());
            insertStatement.setString(4, client.getAddress());
            insertStatement.executeUpdate();

            query.executeUpdate(insertStatementString) ;

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }
    public static void delete(int idClient) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement deleteStatement = null;
        try {
            Statement query = dbConnection.createStatement() ;

            deleteStatement = dbConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);
            deleteStatement.setInt(1, idClient);
            deleteStatement.executeUpdate();

            query.executeUpdate(deleteStatementString) ;

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }
    }
    public static void editName(String coloana, String nouaDenumire, int idClient) {
        Connection dbConnection = ConnectionFactory.getConnection();

        String editStatementStringWithColumn = String.format(editStatementString, coloana);
        PreparedStatement editStatement = null;
        try {
            Statement query = dbConnection.createStatement() ;

            editStatement = dbConnection.prepareStatement(editStatementStringWithColumn, Statement.RETURN_GENERATED_KEYS);
            editStatement.setString(1, nouaDenumire);
            editStatement.setInt(2, idClient);
            editStatement.executeUpdate();

            query.executeUpdate(editStatementString) ;

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:edit " + e.getMessage());
        } finally {
            ConnectionFactory.close(editStatement);
            ConnectionFactory.close(dbConnection);
        }
    }
    public static void editEmail(String coloana, String nouaDenumire, int idClient) {
        Connection dbConnection = ConnectionFactory.getConnection();

        String editStatementStringWithColumn = String.format(editStatementString, coloana);
        PreparedStatement editStatement = null;
        try {
            Statement query = dbConnection.createStatement() ;

            editStatement = dbConnection.prepareStatement(editStatementStringWithColumn, Statement.RETURN_GENERATED_KEYS);
            editStatement.setString(1, nouaDenumire);
            editStatement.setInt(2, idClient);
            editStatement.executeUpdate();

            query.executeUpdate(editStatementString) ;

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:edit " + e.getMessage());
        } finally {
            ConnectionFactory.close(editStatement);
            ConnectionFactory.close(dbConnection);
        }
    }
    public static void editAddress(String coloana, String nouaDenumire, int idClient) {
        Connection dbConnection = ConnectionFactory.getConnection();

        String editStatementStringWithColumn = String.format(editStatementString, coloana);
        PreparedStatement editStatement = null;
        try {
            Statement query = dbConnection.createStatement() ;

            editStatement = dbConnection.prepareStatement(editStatementStringWithColumn, Statement.RETURN_GENERATED_KEYS);
            editStatement.setString(1, nouaDenumire);
            editStatement.setInt(2, idClient);
            editStatement.executeUpdate();

            query.executeUpdate(editStatementString) ;

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:edit " + e.getMessage());
        } finally {
            ConnectionFactory.close(editStatement);
            ConnectionFactory.close(dbConnection);
        }
    }
    public static ArrayList<Client> selectAll() {
        Connection dbConnection = ConnectionFactory.getConnection();
        ArrayList<Client> array = new ArrayList<>() ;

        try {
            Statement query = dbConnection.createStatement();
            ResultSet resultSet = query.executeQuery(selectAllStatementString);

            while(resultSet.next()) {
                int idClient = resultSet.getInt("idClient");
                String clientName = resultSet.getString("clientName");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                array.add(new Client(idClient,clientName,email,address)) ;
            }

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:selectAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(dbConnection);
        }
        return array ;
    }
}
