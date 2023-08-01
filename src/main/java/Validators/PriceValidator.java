package Validators;

import Model.Product;
public class PriceValidator implements Validator<Product> {
    private static final int MIN_PRICE = 1;
    private static final int MAX_PRICE = 1000;
    public void validate(Product t) {

        if (t.getPrice() < MIN_PRICE || t.getPrice() > MAX_PRICE) {
            throw new IllegalArgumentException("Price is to higher!");
        }
    }
}
