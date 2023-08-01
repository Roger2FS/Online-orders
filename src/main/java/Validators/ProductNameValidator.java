package Validators;

import java.util.regex.Pattern;
import Model.Product;
public class ProductNameValidator implements Validator<Product> {
    private static final String NAME_PATTERN = "^[A-Z][a-z]*( [A-Z][a-z]*)*$";
    public void validate(Product t) {
        Pattern pattern = Pattern.compile(NAME_PATTERN);
    }
}
