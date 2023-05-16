package cat.udl.hyperion.appmobils.kingdomcollector.collection.admin;

import cat.udl.hyperion.appmobils.kingdomcollector.game.models.Card;

public class FirestoreCard {
    private String id;
    private int imageUrl;
    private String name;
    private String type;
    private int powerArriba;
    private int powerIzquierda;
    private int powerAbajo;
    private int powerDerecha;

    public FirestoreCard(String id, int imageUrl, String name, String type, int powerArriba, int powerIzquierda, int powerAbajo, int powerDerecha){
        this.id = id;
        this.imageUrl = imageUrl;
        this.name = name;
        this.type = type;
        this.powerArriba = powerArriba;
        this.powerIzquierda = powerIzquierda;
        this.powerAbajo = powerAbajo;
        this.powerDerecha = powerDerecha;
    }
    public FirestoreCard(){
        //Constructor buit per firebase.
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public Card toCard(){
        Card card = new Card(id,imageUrl, name, type, powerArriba, powerIzquierda, powerAbajo, powerDerecha);
        return card;
    }
}
