package Validators;

import Model.Order;
public class CantitateValidator implements Validator<Order> {
    private static final int MIN_CANTITATE = 1;
    private static final int MAX_CANTITATE = 10;
    public void validate(Order t) {

        if (t.getCantitate() < MIN_CANTITATE || t.getCantitate() > MAX_CANTITATE) {
            throw new IllegalArgumentException("Invalid cantity!");
        }
    }
}
