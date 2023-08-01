package GUI;

import BLL.BillBLL;
import BLL.ClientBLL;
import BLL.OrderBLL;
import BLL.ProductBLL;
import Dao.OrderDAO;
import Model.Bill;
import Model.Client;
import Model.Order;
import Model.Product;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Types.NULL;
public class GUI2 extends JFrame {
    private JPanel panel1;
    private JTable table1;
    private JTable table2;
    private JTable table3;
    private javax.swing.JPanel JPanel;
    private JLabel clientName;
    private JLabel ClientID;
    private JLabel clientEmail;
    private JLabel clientAddress;
    private JTextField nameField;
    private JTextField IDfield;
    private JTextField emailField;
    private JTextField addressField;
    private JButton adaugaClientButton;
    private JButton cautaClient;
    private JButton modificaClientButton;
    private JTextField cautaID1;
    private JLabel IdCauta;
    private JLabel IDLabel;
    private JLabel numeProdusLabel;
    private JLabel tipProdusLabel;
    private JLabel pretLabel;
    private JTextField idField;
    private JTextField prodField;
    private JTextField pretProdus;
    private JTextField stockField;
    private JTextField produsType;
    private JButton adaugaProdusButton;
    private JButton cautaProdusButton;
    private JButton REFRESHButton;
    private JButton modificaProdusButton;
    private JTextField cautaID3;
    private JLabel afisareRez2;
    private JLabel stock;
    private JTextField idcomanda;
    private JTextField cantitateComanda;
    private JTextField numeClientComanda;
    private JTextField numeProd;
    private JButton cautaComandaButton;
    private JButton eliminaComandaButton;
    private JButton REFRESHButton1;
    private JButton modificaComandaButton;
    private JButton adaugaComandaButton;
    private JLabel afisare;
    private JTextField ID5;
    private JButton eliminaProdusButton;
    private JButton eliminaClientButton;
    private JButton refresh;
    private JLabel afisareRez;
    private JSplitPane tabelClient;
    private ArrayList<Client> clients = new ArrayList<>() ;
    private ArrayList<Product> products = new ArrayList<>() ;
    private ArrayList<Order> orders = new ArrayList<>() ;
    private ArrayList<BILL> bills = new ArrayList<>() ;
    private ArrayList<Integer> preturi = new ArrayList<>() ;
    private clientiTable c ;
    private produsTable d ;
    private comandaTable ee ;
    public GUI2() {

        ClientBLL clientBLL = new ClientBLL();
        ProductBLL productBLL = new ProductBLL();
        OrderBLL orderBLL = new OrderBLL();
        BillBLL billBLL = new BillBLL();
        adaugaClientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = IDfield.getText() ;
                int ID = Integer.parseInt(id) ;
                String name = nameField.getText() ;
                String email = emailField.getText() ;
                String address = addressField.getText() ;
                boolean verificaID = false;
                boolean verificaNume = false ;

                for(Client afisare : clients){
                    if(afisare.getId() == ID){
                        verificaID = true ;
                    }
                }

                for(Client afisare : clients){
                    if(afisare.getName().equals(name)){
                        verificaNume = true ;
                    }
                }

                if(!verificaID && !verificaNume){
                    clients.add(new Client(ID,name,email,address)) ;

                    c = new clientiTable(clients) ;
                    c.fireTableDataChanged();
                    table1.setModel(c);
                    table1.setAutoCreateRowSorter(true);

                    Client client = new Client(ID, name, email, address);
                    clientBLL.insertClient(client) ;
                    verificaID = false ;
                    verificaNume = false ;
                }
                else{
                    System.out.println("Nume sau ID invalide!");
                    verificaID = false ;
                    verificaNume = false ;
                }
            }
        });
        cautaClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = cautaID1.getText() ;
                int ID = Integer.parseInt(id) ;
                boolean ok = false ;

                for(int i = 0 ; i < clients.size() ; i++ ){
                    if( i == ID - 1 ){
                        afisareRez.setText(clients.get(i).getId() + " " + clients.get(i).getName() + " " + clients.get(i).getEmail() + " " + clients.get(i).getAddress() );
                        ok = true ;
                    }
                }
                if( ok == false ){
                    afisareRez.setText("Clientul cautat nu exista!");
                }
                ok = false ;
            }
        });
        eliminaClientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = cautaID1.getText() ;
                int ID = Integer.parseInt(id) ;
                int pozitie = -1;

                for(int i = 0 ; i < clients.size() ; i++ ){
                    if( clients.get(i).getId() == ID ){
                        pozitie = i ;
                    }
                }

                boolean verificaNume = false ;
                for(Order cautare : orders){
                    if(cautare.getClientName().equals(clients.get(pozitie).getName())){
                        verificaNume = true ;
                    }
                }

                if(!verificaNume){
                    clients.remove(pozitie) ;

                    c.fireTableDataChanged();
                    table1.setModel(c);
                    table1.setAutoCreateRowSorter(true);

                    clientBLL.deleteClient(ID);
                    verificaNume = false ;
                }
                else{
                    System.out.println("Clientul nu poate fi eliminat, acesta are o comanda!");
                    verificaNume = false ;
                }
            }
        });
        modificaClientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = cautaID1.getText() ;
                int ID = Integer.parseInt(id) ;
                String name = nameField.getText() ;
                String email = emailField.getText() ;
                String address = addressField.getText() ;
                boolean namee = false ;
                boolean emaill = false ;
                boolean addresss = false ;

                if(name.equals("")){
                    namee = false ;
                }
                else{
                    namee = true ;
                }
                if(email.equals("")){
                    emaill = false ;
                }
                else{
                    emaill = true ;
                }
                if(address.equals("")){
                    addresss = false ;
                }
                else{
                    addresss = true ;
                }

                if(namee){
                    clientBLL.editClientName(ID,name);

                    clients = clientBLL.selectAll(clients);
                    c = new clientiTable(clients) ;

                    c.fireTableDataChanged();
                    table1.setModel(c);
                    table1.setAutoCreateRowSorter(true);
                    namee = false ;
                }
                if(emaill){
                    clientBLL.editClientEmail(ID,email);

                    clients = clientBLL.selectAll(clients);
                    c = new clientiTable(clients) ;

                    c.fireTableDataChanged();
                    table1.setModel(c);
                    table1.setAutoCreateRowSorter(true);
                    emaill = false ;
                }
                if(addresss){
                    clientBLL.editClientAddress(ID,address);

                    clients = clientBLL.selectAll(clients);
                    c = new clientiTable(clients) ;

                    c.fireTableDataChanged();
                    table1.setModel(c);
                    table1.setAutoCreateRowSorter(true);
                    addresss = false ;
                }
            }
        });
        refresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clients = clientBLL.selectAll(clients);
                c = new clientiTable(clients) ;

                c.fireTableDataChanged();
                table1.setModel(c);
                table1.setAutoCreateRowSorter(true);
            }
        });
        adaugaProdusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText() ;
                int ID = Integer.parseInt(id) ;
                String name = prodField.getText() ;
                String type = produsType.getText() ;
                String price = pretProdus.getText() ;
                int PRICE = Integer.parseInt(price);
                String stock = stockField.getText() ;
                int STOCK = Integer.parseInt(stock);
                boolean verificaID = false ;
                boolean verificaNume = false ;

                for(Product cautare : products){
                    if( cautare.getIdProduct() == ID ){
                        verificaID = true ;
                    }
                }

                for(Product cautare : products){
                    if( cautare.getProdusName().equals(name) ){
                        verificaNume = true ;
                    }
                }

                if(!verificaID && !verificaNume){
                    products.add(new Product(ID,name,type,PRICE,STOCK)) ;

                    d = new produsTable(products) ;
                    d.fireTableDataChanged();
                    table2.setModel(d);
                    table2.setAutoCreateRowSorter(true);

                    Product product = new Product(ID, name, type, PRICE,STOCK);
                    productBLL.insertProduct(product);
                    verificaID = false ;
                    verificaNume = false ;
                }
                else{
                    System.out.println("Nume sau ID invalide!");
                    verificaID = false ;
                    verificaNume = false ;
                }
            }
        });
        cautaProdusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = cautaID3.getText() ;
                int ID = Integer.parseInt(id) ;
                boolean ok = false ;

                for(int i = 0 ; i < products.size() ; i++ ){
                    if( i == ID - 1 ){
                        afisareRez2.setText(products.get(i).getIdProduct() + " " + products.get(i).getProdusName() + " " + products.get(i).getProdusType() + " " + products.get(i).getPrice() );
                        ok = true ;
                    }
                }
                if( ok == false ){
                    afisareRez2.setText("Produsul cautat nu exista!");
                }
                ok = false ;
            }
        });
        eliminaProdusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = cautaID3.getText() ;
                int ID = Integer.parseInt(id) ;
                int pozitie = -1 ;

                for(int i = 0 ; i < products.size() ; i++ ){
                    if(products.get(i).getIdProduct() == ID){
                        pozitie = i ;
                    }
                }

                boolean verificaNume = false ;
                for(Order cautare : orders){
                    if(cautare.getProdusName().equals(products.get(pozitie).getProdusName())){
                        verificaNume = true ;
                    }
                }

                if(!verificaNume){
                    products.remove(pozitie) ;

                    d.fireTableDataChanged();
                    table2.setModel(d);
                    table2.setAutoCreateRowSorter(true);

                    productBLL.deleteProduct(ID);
                    verificaNume = false ;
                }
                else{
                    System.out.println("Produsul nu poate fi eliminat, exista in comanda unui client!");
                    verificaNume = false ;
                }
            }
        });
        REFRESHButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                products = productBLL.selectAll(products);
                d = new produsTable(products) ;

                d.fireTableDataChanged();
                table2.setModel(d);
                table2.setAutoCreateRowSorter(true);
            }
        });
        modificaProdusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = cautaID3.getText() ;
                int ID = Integer.parseInt(id) ;
                String name = prodField.getText() ;
                String type = produsType.getText() ;
                String price = pretProdus.getText() ;

                boolean namee = false ;
                boolean typee = false ;

                if(name.equals("")){
                    namee = false ;
                }
                else{
                    namee = true ;
                }
                if(type.equals("")){
                    typee = false ;
                }
                else{
                    typee = true ;
                }

                if(namee){
                    productBLL.editProductName(ID,name);

                    products = productBLL.selectAll(products);
                    d = new produsTable(products) ;

                    d.fireTableDataChanged();
                    table2.setModel(d);
                    table2.setAutoCreateRowSorter(true);
                    namee = false ;
                }
                if(typee){
                    productBLL.editProductType(ID,type);

                    products = productBLL.selectAll(products);
                    d = new produsTable(products) ;

                    d.fireTableDataChanged();
                    table2.setModel(d);
                    table2.setAutoCreateRowSorter(true);
                    typee = false ;
                }
            }
        });
        adaugaComandaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = idcomanda.getText() ;
                int ID = Integer.parseInt(id) ;
                String cantitate = cantitateComanda.getText() ;
                int Cantitate = Integer.parseInt(cantitate);
                String numeClient = numeClientComanda.getText() ;
                String numeProdus = numeProd.getText() ;
                boolean clientGasit = false, produsGasit = false, verificaID = false, ok = false ;

                for(Client cauta : clients){
                    if( cauta.getName().equals(numeClient) ){
                        clientGasit = true ;
                    }
                }
                for(Product cauta : products){
                    if( cauta.getProdusName().equals(numeProdus) ){
                        produsGasit = true ;
                    }
                }

                for(Order cauta : orders){
                    if( cauta.getIdComanda() == ID ){
                        verificaID = true ;
                    }
                }

                int retine = -1, retineID = 0 ;
                for(int i = 0 ; i < products.size() ; i++ ){
                    if(products.get(i).getProdusName().equals(numeProdus)){
                        if(products.get(i).getStock() >= Cantitate){
                            retineID = products.get(i).getIdProduct() ;
                            retine = i ;
                            ok = true ;
                        }
                    }
                }

                if( clientGasit && produsGasit && !verificaID && ok ){

                    orders.add(new Order(ID,Cantitate,numeClient,numeProdus)) ;
                    productBLL.editProductCantitate(retineID,products.get(retine).getStock()-Cantitate);

                    ee = new comandaTable(orders) ;
                    ee.fireTableDataChanged();

                    table3.setModel(ee);
                    table3.setAutoCreateRowSorter(true);

                    Order order = new Order(ID, Cantitate, numeClient, numeProdus);
                    orderBLL.insertOrder(order) ;

                    Bill bill = new Bill(ID,numeClient,numeProdus,productBLL.findByName(OrderDAO.findById(ID).getProdusName()).getPrice()) ;
                    billBLL.insertBill(bill) ;

                    clientGasit = false ;
                    produsGasit = false ;
                    verificaID = false ;
                    ok = false ;
                }
                else{
                    System.out.println("Client, produs, ID sau stock insuficient invalide!");
                    clientGasit = false ;
                    produsGasit = false ;
                    verificaID = false ;
                    ok = false ;
                }
            }
        });
        cautaComandaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = ID5.getText() ;
                int ID = Integer.parseInt(id) ;
                boolean ok = false ;

                for(int i = 0 ; i < orders.size() ; i++ ){
                    if( i == ID - 1 ){
                        afisare.setText(orders.get(i).getIdComanda() + " " + orders.get(i).getClientName() + " " + orders.get(i).getProdusName() + " " + orders.get(i).getCantitate() );
                        ok = true ;
                    }
                }
                if( ok == false ){
                    afisare.setText("Comanda cautata nu exista!");
                }
                ok = false ;
            }
        });
        eliminaComandaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = ID5.getText() ;
                int ID = Integer.parseInt(id) ;
                int pozitie = -1 ;

                for(int i = 0 ; i < orders.size() ; i++ ){
                    if(orders.get(i).getIdComanda() == ID){
                        pozitie = i ;
                    }
                }

                orders.remove(pozitie) ;

                //ee.fireTableDataChanged();
                table3.setModel(ee);
                table3.setAutoCreateRowSorter(true);

                orderBLL.deleteOrder(ID);
            }
        });
        REFRESHButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                orders = orderBLL.selectAll(orders);
                ee = new comandaTable(orders) ;

                ee.fireTableDataChanged();
                table3.setModel(ee);
                table3.setAutoCreateRowSorter(true);
            }
        });
        modificaComandaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = ID5.getText() ;
                int ID = Integer.parseInt(id) ;
                String cantitate = cantitateComanda.getText() ;
                int CANTITATE = Integer.parseInt(cantitate);
                String nameClient = numeClientComanda.getText() ;
                String nameProd = numeProd.getText() ;
                boolean namec = false ;
                boolean namep = false ;
                boolean cant = false ;

                if(nameClient.equals("")){
                    namec = false ;
                }
                else{
                    namec = true ;
                }
                if(nameProd.equals("")){
                    namep = false ;
                }
                else{
                    namep = true ;
                }
                if(CANTITATE == NULL){
                    cant = false ;
                }
                else{
                    cant = true ;
                }

                if(namec){
                    orderBLL.editOrderClientName(ID,nameClient);

                    orders = orderBLL.selectAll(orders);
                    ee = new comandaTable(orders) ;

                    ee.fireTableDataChanged();
                    table3.setModel(ee);
                    table3.setAutoCreateRowSorter(true);
                    namec = false ;
                }
                if(namep){
                    orderBLL.editOrderProdusName(ID,nameProd);

                    orders = orderBLL.selectAll(orders);
                    ee = new comandaTable(orders) ;

                    ee.fireTableDataChanged();
                    table3.setModel(ee);
                    table3.setAutoCreateRowSorter(true);
                    namep = false ;
                }
                if(cant){
                    orderBLL.editOrderCantitate(ID,CANTITATE);

                    orders = orderBLL.selectAll(orders);
                    ee = new comandaTable(orders) ;

                    ee.fireTableDataChanged();
                    table3.setModel(ee);
                    table3.setAutoCreateRowSorter(true);
                    cant = false ;
                }
            }
        });

        Font font = new Font("Arial", Font.PLAIN, 14);

        adaugaClientButton.setFont(font);
        cautaClient.setFont(font);
        modificaClientButton.setFont(font);
        eliminaClientButton.setFont(font);
        adaugaProdusButton.setFont(font);
        cautaProdusButton.setFont(font);
        modificaProdusButton.setFont(font);
        eliminaProdusButton.setFont(font);
        adaugaComandaButton.setFont(font);
        cautaComandaButton.setFont(font);
        modificaComandaButton.setFont(font);
        eliminaComandaButton.setFont(font);

        clientName.setForeground(Color.BLUE);
        ClientID.setForeground(Color.BLUE);
        clientEmail.setForeground(Color.BLUE);
        clientAddress.setForeground(Color.BLUE);
        IdCauta.setForeground(Color.BLUE);
        IDLabel.setForeground(Color.BLUE);
        numeProdusLabel.setForeground(Color.BLUE);
        tipProdusLabel.setForeground(Color.BLUE);
        pretLabel.setForeground(Color.BLUE);
        stock.setForeground(Color.BLUE);

        adaugaClientButton.setBackground(Color.GREEN);
        cautaClient.setBackground(Color.CYAN);
        modificaClientButton.setBackground(Color.ORANGE);
        eliminaClientButton.setBackground(Color.RED);
        adaugaProdusButton.setBackground(Color.GREEN);
        cautaProdusButton.setBackground(Color.CYAN);
        modificaProdusButton.setBackground(Color.ORANGE);
        eliminaProdusButton.setBackground(Color.RED);
        adaugaComandaButton.setBackground(Color.GREEN);
        cautaComandaButton.setBackground(Color.CYAN);
        modificaComandaButton.setBackground(Color.ORANGE);
        eliminaComandaButton.setBackground(Color.RED);

        table1.setBackground(Color.LIGHT_GRAY);
        table2.setBackground(Color.LIGHT_GRAY);
        table3.setBackground(Color.LIGHT_GRAY);

        afisare.setForeground(Color.RED);
        afisareRez.setForeground(Color.RED);
        afisareRez2.setForeground(Color.RED);

        panel1.setBackground(Color.PINK);
        JPanel.setBackground(Color.YELLOW);

        tabelClient.setDividerLocation(0.33);
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Proiect") ;
        frame.setContentPane(new GUI2().tabelClient);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    private static class clientiTable extends AbstractTableModel {
        private final String[] COLUMNS = {"idClient","ClientName","email","address"} ;
        private List<Client> client ;
        private clientiTable(List<Client> client){
            this.client = client ;
        }
        public int getRowCount() {
            return client.size() ;
        }
        public int getColumnCount() {
            return COLUMNS.length ;
        }
        public Object getValueAt(int rowIndex, int columnIndex) {
            return switch (columnIndex){
                case 0 -> client.get(rowIndex).getId() ;
                case 1 -> client.get(rowIndex).getName() ;
                case 2 -> client.get(rowIndex).getEmail() ;
                case 3 -> client.get(rowIndex).getAddress() ;
                default -> "-" ;
            };
        }
        public String getColumnName(int column){
            return COLUMNS[column] ;
        }
        public Class<?> getColumnClass(int columnIndex){
            if(getValueAt(0,columnIndex) != null){
                return getValueAt(0,columnIndex).getClass() ;
            }
            else{
                return Object.class ;
            }
        }
    }
    private static class produsTable extends AbstractTableModel{
        private final String[] COLUMNS = {"idProduct","produsName","produsType","price","stock"} ;
        private List<Product> produs ;
        private produsTable(List<Product> produs){
            this.produs = produs ;
        }
        public int getRowCount() {
            return produs.size() ;
        }
        public int getColumnCount() {
            return COLUMNS.length ;
        }
        public Object getValueAt(int rowIndex, int columnIndex) {
            return switch (columnIndex){
                case 0 -> produs.get(rowIndex).getIdProduct() ;
                case 1 -> produs.get(rowIndex).getProdusName() ;
                case 2 -> produs.get(rowIndex).getProdusType() ;
                case 3 -> produs.get(rowIndex).getPrice() ;
                case 4 -> produs.get(rowIndex).getStock() ;
                default -> "-" ;
            };
        }
        public String getColumnName(int column){
            return COLUMNS[column] ;
        }
        public Class<?> getColumnClass(int columnIndex){
            if(getValueAt(0,columnIndex) != null){
                return getValueAt(0,columnIndex).getClass() ;
            }
            else{
                return Object.class ;
            }
        }
    }
    private static class comandaTable extends AbstractTableModel{
        private final String[] COLUMNS = {"idComanda","cantitate","ClientName","produsName"} ;
        private List<Order> order ;
        private comandaTable(List<Order> order){
            this.order = order ;
        }
        public int getRowCount() {
            return order.size() ;
        }
        public int getColumnCount() {
            return COLUMNS.length ;
        }
        public Object getValueAt(int rowIndex, int columnIndex) {
            return switch (columnIndex){
                case 0 -> order.get(rowIndex).getIdComanda() ;
                case 1 -> order.get(rowIndex).getCantitate() ;
                case 2 -> order.get(rowIndex).getClientName() ;
                case 3 -> order.get(rowIndex).getProdusName() ;
                default -> "-" ;
            };
        }
        public String getColumnName(int column){
            return COLUMNS[column] ;
        }
        public Class<?> getColumnClass(int columnIndex){
            if(getValueAt(0,columnIndex) != null){
                return getValueAt(0,columnIndex).getClass() ;
            }
            else{
                return Object.class ;
            }
        }
    }
}

