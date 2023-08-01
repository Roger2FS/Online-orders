package Model;
public record Bill(int idComanda, String ClientName, String ProdusName, int Pret) {
    public Bill {
        if( idComanda < 1 ){
            throw new IllegalArgumentException("idComanda invalid!");
        }
        if( ClientName.isEmpty() ){
            throw new IllegalArgumentException("Nume invalid!");
        }
        if( ProdusName.isEmpty() ){
            throw new IllegalArgumentException("Produs invalid!");
        }
        if( Pret < 1 ){
            throw new IllegalArgumentException("Pret invalid!");
        }
    }
}
