package BLL;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import Dao.OrderDAO;
import Model.Order;
import Validators.*;
public class OrderBLL {
    private List<Validator<Order>> validators;
    public OrderBLL() {
        validators = new ArrayList<Validator<Order>>();
        validators.add(new IdOrderValidator()) ;
        validators.add(new DateFacturareValidator());
        validators.add(new CantitateValidator()) ;
    }
    public Order findOrderById(int id) {
        Order st = OrderDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The order with id =" + id + " was not found!");
        }
        return st;
    }
    public int insertOrder(Order order) {
        for (Validator<Order> v : validators) {
            v.validate(order);
        }
        return OrderDAO.insert(order);
    }
    public void deleteOrder(int id) {
        OrderDAO.delete(id);
    }
    public void editOrderClientName(int id,String name) {
        OrderDAO.editClientName("ClientName",name,id);
    }
    public void editOrderProdusName(int id,String name) {
        OrderDAO.editProdusName("produsName",name,id);
    }
    public void editOrderCantitate(int id,int cantitate) {
        OrderDAO.editCantitate("cantitate",cantitate,id);
    }
    public ArrayList<Order> selectAll(ArrayList<Order> array){
        array = OrderDAO.selectAll() ;
        return array ;
    }
}
