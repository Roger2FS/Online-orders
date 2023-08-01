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
import Model.Product;
public class ProductDAO {
    protected static final Logger LOGGER = Logger.getLogger(ProductDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO Product (idProduct,produsName,produsType,price,stock)"
            + " VALUES (?,?,?,?,?)";
    private final static String findStatementString = "SELECT * FROM Product where idProduct = ?";
    private final static String findStatementString2 = "SELECT * FROM product where produsName = ?";
    private final static String deleteStatementString = "DELETE FROM Product where idProduct = ?";
    private final static String editStatementString = "UPDATE product SET %s = ? WHERE idProduct = ?";
    private final static String selectAllStatementString = "SELECT * FROM product";
    public static Product findById(int idProduct) {
        Product toReturn = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setLong(1, idProduct);
            rs = findStatement.executeQuery();
            rs.next();

            String produsName = rs.getString("produsName");
            String produsType = rs.getString("produsType");
            int price = rs.getInt("price");
            int stock = rs.getInt("stock");
            toReturn = new Product(idProduct, produsName, produsType, price, stock);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ProductDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        System.out.print(toReturn.getIdProduct() + " " + toReturn.getProdusName() + " " + toReturn.getProdusType() + " " + toReturn.getPrice() + " " + toReturn.getStock());
        return toReturn;
    }
    public static Product findByName(String name) {
        Product toReturn = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString2);
            findStatement.setString(1, name);
            rs = findStatement.executeQuery();
            rs.next();

            int id = rs.getInt("idProduct");
            String produsType = rs.getString("produsType");
            int price = rs.getInt("price");
            int stock = rs.getInt("stock");
            toReturn = new Product(id, name, produsType, price, stock);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ProductDAO:findByName " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        System.out.print(toReturn.getIdProduct() + " " + toReturn.getProdusName() + " " + toReturn.getProdusType() + " " + toReturn.getPrice() + " " + toReturn.getStock());
        return toReturn;
    }
    public static int insert(Product product) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            Statement query = dbConnection.createStatement() ;

            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1,product.getIdProduct());
            insertStatement.setString(2, product.getProdusName());
            insertStatement.setString(3, product.getProdusType());
            insertStatement.setInt(4, product.getPrice());
            insertStatement.setInt(5,product.getStock());
            insertStatement.executeUpdate();

            query.executeUpdate(insertStatementString) ;

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }
    public static void delete(int idProduct) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement deleteStatement = null;
        try {
            Statement query = dbConnection.createStatement() ;

            deleteStatement = dbConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);
            deleteStatement.setInt(1, idProduct);
            deleteStatement.executeUpdate();

            query.executeUpdate(deleteStatementString) ;

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }
    }
    public static void editName(String coloana, String nouaDenumire, int idProduct) {
        Connection dbConnection = ConnectionFactory.getConnection();

        String editStatementStringWithColumn = String.format(editStatementString, coloana);
        PreparedStatement editStatement = null;
        try {
            Statement query = dbConnection.createStatement() ;

            editStatement = dbConnection.prepareStatement(editStatementStringWithColumn, Statement.RETURN_GENERATED_KEYS);
            editStatement.setString(1, nouaDenumire);
            editStatement.setInt(2, idProduct);
            editStatement.executeUpdate();

            query.executeUpdate(editStatementString) ;

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:edit " + e.getMessage());
        } finally {
            ConnectionFactory.close(editStatement);
            ConnectionFactory.close(dbConnection);
        }
    }
    public static void editType(String coloana, String nouaDenumire, int idProduct) {
        Connection dbConnection = ConnectionFactory.getConnection();

        String editStatementStringWithColumn = String.format(editStatementString, coloana);
        PreparedStatement editStatement = null;
        try {
            Statement query = dbConnection.createStatement() ;

            editStatement = dbConnection.prepareStatement(editStatementStringWithColumn, Statement.RETURN_GENERATED_KEYS);
            editStatement.setString(1, nouaDenumire);
            editStatement.setInt(2, idProduct);
            editStatement.executeUpdate();

            query.executeUpdate(editStatementString) ;

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:edit " + e.getMessage());
        } finally {
            ConnectionFactory.close(editStatement);
            ConnectionFactory.close(dbConnection);
        }
    }
    public static void editCantitate(String coloana, int cantitate, int idProduct) {
        Connection dbConnection = ConnectionFactory.getConnection();

        String editStatementStringWithColumn = String.format(editStatementString, coloana);
        PreparedStatement editStatement = null;
        try {
            Statement query = dbConnection.createStatement() ;

            editStatement = dbConnection.prepareStatement(editStatementStringWithColumn, Statement.RETURN_GENERATED_KEYS);
            editStatement.setInt(1, cantitate);
            editStatement.setInt(2, idProduct);
            editStatement.executeUpdate();

            query.executeUpdate(editStatementString) ;

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:edit " + e.getMessage());
        } finally {
            ConnectionFactory.close(editStatement);
            ConnectionFactory.close(dbConnection);
        }
    }
    public static ArrayList<Product> selectAll() {
        Connection dbConnection = ConnectionFactory.getConnection();
        ArrayList<Product> array = new ArrayList<>() ;

        try {
            Statement query = dbConnection.createStatement();
            ResultSet resultSet = query.executeQuery(selectAllStatementString);

            while(resultSet.next()) {
                int idProduct = resultSet.getInt("idProduct");
                String produsName = resultSet.getString("produsName");
                String produsType = resultSet.getString("produsType");
                int price = resultSet.getInt("price");
                int stock = resultSet.getInt("stock");
                array.add(new Product(idProduct,produsName,produsType,price,stock)) ;
            }

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:selectAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(dbConnection);
        }
        return array ;
    }
}
