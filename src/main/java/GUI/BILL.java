package GUI;

import BLL.BillBLL;
import BLL.OrderBLL;
import BLL.ProductBLL;
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
public class BILL extends JFrame {
    private JPanel panel1;
    private JTable table1;
    private JTextField textField1;
    private JButton refreshButton;
    private JSplitPane da;
    private JLabel total;
    private ArrayList<Client> clients = new ArrayList<>() ;
    private ArrayList<Product> products = new ArrayList<>() ;
    private ArrayList<Integer> preturi = new ArrayList<>() ;
    private ArrayList<Order> orders = new ArrayList<>() ;
    private ArrayList<Bill> bills = new ArrayList<>() ;
    private BILL.facturaTable c ;
    public BILL() {
        ProductBLL productBLL = new ProductBLL();
        OrderBLL orderBLL = new OrderBLL();
        BillBLL billBLL = new BillBLL();

        refreshButton.setBackground(new Color(92, 184, 92));
        refreshButton.setForeground(Color.WHITE);
        textField1.setBackground(new Color(255, 255, 210));
        textField1.setForeground(Color.BLACK);
        refreshButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = textField1.getText();
                int ID = Integer.parseInt(id);

                products = productBLL.selectAll(products);
                orders = orderBLL.selectAll(orders);
                bills = billBLL.selectAll(bills);

                for (int i = 0; i < orders.size(); i++) {
                    preturi.add(productBLL.findByName(orders.get(i).getProdusName()).getPrice());
                }

                ArrayList<Order> orderss = new ArrayList<>();
                for (int i = 0; i < orders.size(); i++) {
                    if (orders.get(i).getIdComanda() == ID) {
                        orderss.add(orders.get(i));
                    }
                }

                if (!id.isEmpty()) {
                    for (int i = 0; i < bills.size(); i++) {
                        if (bills.get(i).idComanda() == ID) {
                            c = new BILL.facturaTable(orderss, preturi);

                            c.fireTableDataChanged();
                            table1.setModel(c);
                            table1.setAutoCreateRowSorter(true);

                            String pret = Integer.toString(preturi.get(0));
                            StringBuilder s = new StringBuilder();
                            s.append(pret).append(" lei");
                            total.setText(s.toString());
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(panel1, "Please enter an ID.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JFrame frame = new JFrame("Factura");
        frame.setContentPane(new BILL().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    private static class facturaTable extends AbstractTableModel {
        private final String[] COLUMNS = {"idComanda", "ClientName", "ProdusName", "Pret"};
        private List<Order> comenzi;
        private List<Product> produs;
        private List<Integer> price;

        private facturaTable(List<Order> comenzi, List<Integer> price) {
            this.comenzi = comenzi;
            this.price = price;
        }

        public int getRowCount() {
            return comenzi.size();
        }

        public int getColumnCount() {
            return COLUMNS.length;
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            return switch (columnIndex) {
                case 0 -> comenzi.get(rowIndex).getIdComanda();
                case 1 -> comenzi.get(rowIndex).getClientName();
                case 2 -> comenzi.get(rowIndex).getProdusName();
                case 3 -> price.get(rowIndex);
                default -> "-";
            };
        }

        public String getColumnName(int column) {
            return COLUMNS[column];
        }

        public Class<?> getColumnClass(int columnIndex) {
            if (getValueAt(0, columnIndex) != null) {
                return getValueAt(0, columnIndex).getClass();
            } else {
                return Object.class;
            }
        }
    }
}
