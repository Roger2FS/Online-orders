package Validators;

import Model.Product;
public class IdProductValidator implements Validator<Product> {
    private static final int MIN_ID = 1;
    private static final int MAX_ID = 1000;
    public void validate(Product t) {

        if (t.getIdProduct() < MIN_ID || t.getIdProduct() > MAX_ID) {
            throw new IllegalArgumentException("Invalid ID!");
        }
    }
}
