package BLL;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import Validators.*;
import Dao.ClientDAO;
import Model.Client;
public class ClientBLL {
    private List<Validator<Client>> validators;
    public ClientBLL() {
        validators = new ArrayList<Validator<Client>>();
        validators.add(new IdClientValidator());
        validators.add(new EmailValidator());
        validators.add(new NameValidator()) ;
        validators.add(new AddressValidator()) ;
    }
    public Client findClientById(int id) {
        Client st = ClientDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        return st;
    }
    public int insertClient(Client client) {
        for (Validator<Client> v : validators) {
            v.validate(client);
        }
        return ClientDAO.insert(client);
    }
    public void deleteClient(int id) {
        ClientDAO.delete(id);
    }
    public void editClientName(int id, String name) {
        ClientDAO.editName("ClientName",name,id);
    }
    public void editClientEmail(int id, String name) {
        ClientDAO.editEmail("email",name,id);
    }
    public void editClientAddress(int id, String name) {
        ClientDAO.editAddress("address",name,id);
    }
    public ArrayList<Client> selectAll(ArrayList<Client> array){
        array = ClientDAO.selectAll() ;
        return array ;
    }
}
