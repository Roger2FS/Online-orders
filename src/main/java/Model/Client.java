package Model;
public class Client {
    private int idClient ;
    private String ClientName ;
    private String email ;
    private String address ;
    public Client(int idClient, String ClientName, String email, String address){
        this.idClient = idClient ;
        this.ClientName = ClientName ;
        this.email = email ;
        this.address = address ;
    }
    public int getId() {
        return idClient;
    }
    public void setId(int idClient) {
        this.idClient = idClient;
    }
    public String getName() {
        return ClientName;
    }
    public void setName(String ClientName) {
        this.ClientName = ClientName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
