package Validators;

import Model.Product;
public class StockValidator implements Validator<Product> {
    private static final int MIN_STOCK = 1;
    private static final int MAX_STOCK = 100;
    public void validate(Product t) {

        if (t.getStock() < MIN_STOCK || t.getStock() > MAX_STOCK) {
            throw new IllegalArgumentException("Stock is not valid!");
        }
    }
}
