package BLL;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import Dao.ProductDAO;
import Model.Product;
import Validators.*;
public class ProductBLL {
    private List<Validator<Product>> validators;
    public ProductBLL() {
        validators = new ArrayList<Validator<Product>>();
        validators.add(new IdProductValidator());
        validators.add(new TypeValidator());
        validators.add(new ProductNameValidator()) ;
        validators.add(new PriceValidator()) ;
        validators.add(new StockValidator()) ;
    }
    public Product findProductById(int id) {
        Product st = ProductDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        return st;
    }
    public Product findByName(String name) {
        Product st = ProductDAO.findByName(name);
        return st;
    }
    public int insertProduct(Product product) {
        return ProductDAO.insert(product);
    }
    public void deleteProduct(int id) {
        ProductDAO.delete(id);
    }
    public void editProductName(int id, String name) {
        ProductDAO.editName("produsName",name,id);
    }
    public void editProductType(int id, String name) {
        ProductDAO.editType("produsType",name,id);
    }
    public void editProductCantitate(int id, int cantitate) {
        ProductDAO.editCantitate("stock",cantitate,id);
    }
    public ArrayList<Product> selectAll(ArrayList<Product> array){
        array = ProductDAO.selectAll() ;
        return array ;
    }
}
