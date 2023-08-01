package Model;
public class Product {
    private int idProduct ;
    private String produsType ;
    private String produsName ;
    private int price ;
    private int stock ;
    public Product(int idProduct, String produsName, String produsType, int price, int stock) {
        this.idProduct = idProduct;
        this.produsType = produsType;
        this.produsName = produsName;
        this.price = price;
        this.stock = stock;
    }
    public int getIdProduct() {
        return idProduct;
    }
    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }
    public String getProdusType() {
        return produsType;
    }
    public void setProdusType(String produsType) {
        this.produsType = produsType;
    }
    public String getProdusName() {
        return produsName;
    }
    public void setProdusName(String produsName) {
        this.produsName = produsName;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
}
