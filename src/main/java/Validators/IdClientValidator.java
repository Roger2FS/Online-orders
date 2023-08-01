package Validators;

import Model.Client;
public class IdClientValidator implements Validator<Client> {
    private static final int MIN_ID = 1;
    private static final int MAX_ID = 100;
    public void validate(Client t) {

        if (t.getId() < MIN_ID || t.getId() > MAX_ID) {
            throw new IllegalArgumentException("Invalid ID!");
        }
    }
}
