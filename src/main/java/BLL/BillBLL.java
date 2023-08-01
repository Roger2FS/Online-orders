package BLL;

import Dao.BillDao;
import Model.Bill;

import java.util.ArrayList;
public class BillBLL {
    public int insertBill(Bill bill) {
        return BillDao.insert(bill);
    }
    public ArrayList<Bill> selectAll(ArrayList<Bill> array){
        array = BillDao.selectAll() ;
        return array ;
    }
}
