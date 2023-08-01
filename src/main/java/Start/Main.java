package Start;

import BLL.ClientBLL;
import BLL.OrderBLL;
import BLL.ProductBLL;
import Model.Client;
import Model.Order;
import Model.Product;
public class Main {
    public static void main(String[] args) {

        Client client = new Client(1,"Darius", "darius_gherghina@yahoo.ro", "Alverna 71-73");

        Product product = new Product(1,"Lapte","Lactate",6,5) ;

        Order order = new Order(1,5,"Darius","Lapte") ;

        ClientBLL clientBLL = new ClientBLL() ;
        //clientBLL.insertClient(client) ;
        //clientBLL.deleteClient(1);
        //clientBLL.findClientById(1);
        //clientBLL.editClient(1);

        ProductBLL productBLL = new ProductBLL() ;
        //productBLL.insertProduct(product) ;
        //productBLL.deleteProduct(2);
        //productBLL.findProductById(1);
        //productBLL.editProduct(1);

        OrderBLL orderBLL = new OrderBLL() ;
        //orderBLL.insertOrder(order);
        //orderBLL.deleteOrder(1);
        //orderBLL.findOrderById(1) ;
        //orderBLL.editOrder(1);
    }
}
