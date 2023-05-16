package cat.udl.hyperion.appmobils.kingdomcollector.collection.admin;

public class FirestoreCard {
    private String id;
    private int imageUrl;
    private String name;

    private String type;
    private int powerArriba;
    private int powerIzquierda;
    private int powerAbajo;
    private int powerDerecha;

    public FirestoreCard(String id, int imageUrl, String name, String type, int powerArriba, int powerIzquierda, int powerAbajo, int powerDerecha) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.name = name;
        this.type = type;
        this.powerArriba = powerArriba;
        this.powerIzquierda = powerIzquierda;
        this.powerAbajo = powerAbajo;
        this.powerDerecha = powerDerecha;
    }

    public FirestoreCard() {
    }

    public int getImageUrl() {
        return imageUrl;
    }

    public int getPowerAbajo() {
        return powerAbajo;
    }

    public int getPowerDerecha() {
        return powerDerecha;
    }

    public int getPowerIzquierda() {
        return powerIzquierda;
    }

    public int getPowerArriba() {
        return powerArriba;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setPowerAbajo(int powerAbajo) {
        this.powerAbajo = powerAbajo;
    }

    public void setPowerArriba(int powerArriba) {
        this.powerArriba = powerArriba;
    }

    public void setPowerDerecha(int powerDerecha) {
        this.powerDerecha = powerDerecha;
    }

    public void setPowerIzquierda(int powerIzquierda) {
        this.powerIzquierda = powerIzquierda;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }
}