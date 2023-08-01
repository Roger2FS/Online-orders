package Validators;

import Model.Order;
import java.util.regex.Pattern;
public class DateFacturareValidator implements Validator<Order> {
    private static final String ADDRESS_PATTERN = "^[a-zA-Z\\s]+ nr\\.\\s*\\d+$";
    public void validate(Order t) {
        Pattern pattern = Pattern.compile(ADDRESS_PATTERN);
    }
}
