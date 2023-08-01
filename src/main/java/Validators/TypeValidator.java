package Validators;

import Model.Product;
public class TypeValidator implements Validator<Product> {
    public void validate(Product t) {

        if (t.getProdusType().equals("Lactate") || t.getProdusType().equals("Mezeluri") || t.getProdusType().equals("Cosmetice") || t.getProdusType().equals("Haine") || t.getProdusType().equals("Medicamente")) {
            throw new IllegalArgumentException("This type does not exist!");
        }
    }
}
