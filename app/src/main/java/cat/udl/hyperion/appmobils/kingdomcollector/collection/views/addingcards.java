package cat.udl.hyperion.appmobils.kingdomcollector.collection.views;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import cat.udl.hyperion.appmobils.kingdomcollector.R;
import cat.udl.hyperion.appmobils.kingdomcollector.collection.admin.CardManager;
import cat.udl.hyperion.appmobils.kingdomcollector.collection.admin.FirestoreCard;

public class addingcards extends AppCompatActivity {

    private CardManager cardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addingcards);
        cardManager = new CardManager();

        Button refreshCardsButton = findViewById(R.id.button);
        refreshCardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCards();
            }
        });
    }

    private void addCards() {

        //Cartas del equipo ElBarrio:
        cardManager.addCard(new FirestoreCard("elbarrio1", "https://firebasestorage.googleapis.com/v0/b/hyperion-843ad.appspot.com/o/elbarrio%2Fbarrio_adri_contreras_presidente.png?alt=media&token=83a46a3b-f33d-4384-a3fa-e3077bc4fdbd","Adri_Contreras", "Presidente", 9, 10, 7, 10));
        cardManager.addCard(new FirestoreCard("elbarrio2", "https://firebasestorage.googleapis.com/v0/b/hyperion-843ad.appspot.com/o/elbarrio%2Fbarrio_barnera_portero.png?alt=media&token=cd54753a-bf64-4c23-aa3f-9c3000970b69", "Barnera", "Portero", 3, 4, 4, 4));
        cardManager.addCard(new FirestoreCard("elbarrio3", "https://firebasestorage.googleapis.com/v0/b/hyperion-843ad.appspot.com/o/elbarrio%2Fbarrio_boada_delantero.png?alt=media&token=9962817e-df32-40d8-93b3-f6eef898eeec", "Boada", "Delantero", 6, 3, 2, 6));
        cardManager.addCard(new FirestoreCard("elbarrio4", "https://firebasestorage.googleapis.com/v0/b/hyperion-843ad.appspot.com/o/elbarrio%2Fbarrio_capilla_medio.png?alt=media&token=afca0ef0-380e-49ca-b651-82728a07a5bc", "Capilla", "Medio", 5, 3, 5, 1));
        cardManager.addCard(new FirestoreCard("elbarrio5", "https://firebasestorage.googleapis.com/v0/b/hyperion-843ad.appspot.com/o/elbarrio%2Fbarrio_jacob_delantero.png?alt=media&token=bff28308-ddfe-47ea-808e-093dbddde284", "Jacob", "Delantero", 2, 6, 3, 7));
        cardManager.addCard(new FirestoreCard("elbarrio6", "https://firebasestorage.googleapis.com/v0/b/hyperion-843ad.appspot.com/o/elbarrio%2Fbarrio_josejuan_11.png?alt=media&token=2dfda1b1-213e-470d-bcc6-f06c13105f80", "Jose_Juan", "11", 9, 10, 7, 9));
        cardManager.addCard(new FirestoreCard("elbarrio7", "https://firebasestorage.googleapis.com/v0/b/hyperion-843ad.appspot.com/o/elbarrio%2Fbarrio_mesa_defensa.png?alt=media&token=4997f44f-700a-4bad-a73b-7c40da93eacf", "Mesa", "Defensa", 1, 4, 7, 6));
        cardManager.addCard(new FirestoreCard("elbarrio8", "https://firebasestorage.googleapis.com/v0/b/hyperion-843ad.appspot.com/o/elbarrio%2Fbarrio_olae_delantero.png?alt=media&token=3108d1e1-ef20-417e-97cc-b90761fdb401", "Olae", "Delantero", 4, 4, 3, 2));
        cardManager.addCard(new FirestoreCard("elbarrio9", "https://firebasestorage.googleapis.com/v0/b/hyperion-843ad.appspot.com/o/elbarrio%2Fbarrio_pau_defensa.png?alt=media&token=6645313a-b7dd-44e4-a379-f4d2a7cb6b3a", "Pau", "Defensa", 3, 4, 4, 1));
        cardManager.addCard(new FirestoreCard("elbarrio10", "https://firebasestorage.googleapis.com/v0/b/hyperion-843ad.appspot.com/o/elbarrio%2Fbarrio_ros_defensa.png?alt=media&token=60409c89-03e5-42cc-a595-0cdc29e146cf", "Ros", "Defensa", 3, 3, 6, 7));
        cardManager.addCard(new FirestoreCard("elbarrio11", "https://firebasestorage.googleapis.com/v0/b/hyperion-843ad.appspot.com/o/elbarrio%2Fbarrio_ubon_medio.png?alt=media&token=cecd6abf-2e69-4453-83c3-e1cba5273b5d", "Ubon", "Medio", 9, 10, 9, 9));

        //Cartas del equipo ...:
        //(String id, String imageUrl, String name, String type, int powerArriba, int powerIzquierda, int powerAbajo, int powerDerecha) {
        //cardManager.addCard(new FirestoreCard("1k1", "URL", "NombreJugador", "delantero", 2,1,4,5));
        cardManager.addCard(new FirestoreCard("1k1","https://firebasestorage.googleapis.com/v0/b/hyperion-843ad.appspot.com/o/1k%2F1k_bruno_defensa.png?alt=media&token=38550727-aea6-4958-ba91-539290355376","Bruno", "Defensa",5,3,5,3 ));
    }
}