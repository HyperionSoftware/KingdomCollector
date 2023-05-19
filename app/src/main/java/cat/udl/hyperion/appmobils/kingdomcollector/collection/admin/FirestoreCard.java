package cat.udl.hyperion.appmobils.kingdomcollector.collection.admin;

public class FirestoreCard {
    private String id;

    public FirestoreCard(String id){
        this.id = id;
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
}
