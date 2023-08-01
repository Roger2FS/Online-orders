package Validators;

import Model.Order;
public class IdOrderValidator implements Validator<Order> {
    private static final int MIN_ID = 1;
    private static final int MAX_ID = 1000;
    public void validate(Order t) {

        if (t.getIdComanda() < MIN_ID || t.getIdComanda() > MAX_ID) {
            throw new IllegalArgumentException("Invalid ID!");
        }
    }
}
