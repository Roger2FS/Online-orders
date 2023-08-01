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
import Model.Order;
public class OrderDAO {
    protected static final Logger LOGGER = Logger.getLogger(OrderDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO comanda (idComanda,cantitate,ClientName,produsName)"
            + " VALUES (?,?,?,?)";
    private final static String findStatementString = "SELECT * FROM comanda where idComanda = ?";
    private final static String deleteStatementString = "DELETE FROM comanda where idComanda = ?";
    private final static String editStatementString = "UPDATE comanda SET %s = ? WHERE idComanda = ?";
    private final static String selectAllStatementString = "SELECT * FROM comanda";
    public static Order findById(int idComanda) {
        Order toReturn = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setLong(1, idComanda);
            rs = findStatement.executeQuery();
            rs.next();

            int cantitate = rs.getInt("cantitate");
            String ClientName = rs.getString("ClientName");
            String produsName = rs.getString("produsName");
            toReturn = new Order(idComanda, cantitate, ClientName, produsName);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"OrderDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        System.out.print(toReturn.getIdComanda() + " " + toReturn.getCantitate() + " " + toReturn.getClientName() + " " + toReturn.getProdusName());
        return toReturn;
    }
    public static int insert(Order order) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            Statement query = dbConnection.createStatement() ;

            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1,order.getIdComanda());
            insertStatement.setInt(2, order.getCantitate());
            insertStatement.setString(3, order.getClientName());
            insertStatement.setString(4, order.getProdusName());
            insertStatement.executeUpdate();

            query.executeUpdate(insertStatementString) ;

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }
    public static void delete(int idComanda) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement deleteStatement = null;
        try {
            Statement query = dbConnection.createStatement() ;

            deleteStatement = dbConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);
            deleteStatement.setInt(1, idComanda);
            deleteStatement.executeUpdate();

            query.executeUpdate(deleteStatementString) ;

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderDAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }
    }
    public static void editCantitate(String coloana, int nouaCantitate, int idOrder) {
        Connection dbConnection = ConnectionFactory.getConnection();

        String editStatementStringWithColumn = String.format(editStatementString, coloana);
        PreparedStatement editStatement = null;
        try {
            Statement query = dbConnection.createStatement() ;

            editStatement = dbConnection.prepareStatement(editStatementStringWithColumn, Statement.RETURN_GENERATED_KEYS);
            editStatement.setInt(1, nouaCantitate);
            editStatement.setInt(2, idOrder);
            editStatement.executeUpdate();

            query.executeUpdate(editStatementString) ;

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderDAO:edit " + e.getMessage());
        } finally {
            ConnectionFactory.close(editStatement);
            ConnectionFactory.close(dbConnection);
        }
    }
    public static void editClientName(String coloana, String noulNume, int idOrder) {
        Connection dbConnection = ConnectionFactory.getConnection();

        String editStatementStringWithColumn = String.format(editStatementString, coloana);
        PreparedStatement editStatement = null;
        try {
            Statement query = dbConnection.createStatement() ;

            editStatement = dbConnection.prepareStatement(editStatementStringWithColumn, Statement.RETURN_GENERATED_KEYS);
            editStatement.setString(1, noulNume);
            editStatement.setInt(2, idOrder);
            editStatement.executeUpdate();

            query.executeUpdate(editStatementString) ;

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderDAO:edit " + e.getMessage());
        } finally {
            ConnectionFactory.close(editStatement);
            ConnectionFactory.close(dbConnection);
        }
    }
    public static void editProdusName(String coloana, String noulNume, int idOrder) {
        Connection dbConnection = ConnectionFactory.getConnection();

        String editStatementStringWithColumn = String.format(editStatementString, coloana);
        PreparedStatement editStatement = null;
        try {
            Statement query = dbConnection.createStatement() ;

            editStatement = dbConnection.prepareStatement(editStatementStringWithColumn, Statement.RETURN_GENERATED_KEYS);
            editStatement.setString(1, noulNume);
            editStatement.setInt(2, idOrder);
            editStatement.executeUpdate();

            query.executeUpdate(editStatementString) ;

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderDAO:edit " + e.getMessage());
        } finally {
            ConnectionFactory.close(editStatement);
            ConnectionFactory.close(dbConnection);
        }
    }
    public static ArrayList<Order> selectAll() {
        Connection dbConnection = ConnectionFactory.getConnection();
        ArrayList<Order> array = new ArrayList<>() ;

        try {
            Statement query = dbConnection.createStatement();
            ResultSet resultSet = query.executeQuery(selectAllStatementString);

            while(resultSet.next()) {
                int idComanda = resultSet.getInt("idComanda");
                int cantitate = resultSet.getInt("cantitate");
                String ClientName = resultSet.getString("ClientName");
                String produsName = resultSet.getString("produsName");
                array.add(new Order(idComanda,cantitate,ClientName,produsName)) ;
            }

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderDAO:selectAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(dbConnection);
        }
        return array ;
    }
}
