package Model;
public class Order {
    private int idComanda ;
    private String produsName ;
    private String ClientName ;
    private int cantitate ;
    public Order(int idComanda, int cantitate, String ClientName, String produsName) {
        this.idComanda = idComanda;
        this.cantitate = cantitate ;
        this.ClientName = ClientName ;
        this.produsName = produsName ;
    }
    public int getIdComanda() {
        return idComanda;
    }
    public void setIdComanda(int idComanda) {
        this.idComanda = idComanda;
    }
    public int getCantitate() {
        return cantitate;
    }
    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }
    public String getProdusName() {
        return produsName;
    }
    public void setProdusName(String produsName) {
        this.produsName = produsName;
    }
    public String getClientName() {
        return ClientName;
    }
    public void setClientName(String produsType) {
        this.ClientName = ClientName;
    }
}
