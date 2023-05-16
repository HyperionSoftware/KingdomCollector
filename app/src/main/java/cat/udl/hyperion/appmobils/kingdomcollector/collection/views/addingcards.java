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
        cardManager.addCard(new FirestoreCard("elbarrio1", R.drawable.adri_contreras,"Adri_Contreras", "Presidente", 9, 10, 7, 10));
        cardManager.addCard(new FirestoreCard("elbarrio2", R.drawable.barrio_barnera_portero, "Barnera", "Portero", 3, 4, 4, 4));
        cardManager.addCard(new FirestoreCard("elbarrio3", R.drawable.barrio_boada_delantero, "Boada", "Delantero", 6, 3, 2, 6));
        cardManager.addCard(new FirestoreCard("elbarrio4", R.drawable.barrio_capilla_medio, "Capilla", "Medio", 5, 3, 5, 1));
        cardManager.addCard(new FirestoreCard("elbarrio5", R.drawable.barrio_jacob_delantero, "Jacob", "Delantero", 2, 6, 3, 7));
        cardManager.addCard(new FirestoreCard("elbarrio6", R.drawable.barrio_josejuan_11, "Jose_Juan", "11", 9, 10, 7, 9));
        cardManager.addCard(new FirestoreCard("elbarrio7", R.drawable.barrio_mesa_defensa, "Mesa", "Defensa", 1, 4, 7, 6));
        cardManager.addCard(new FirestoreCard("elbarrio8", R.drawable.barrio_olae_delantero, "Olae", "Delantero", 4, 4, 3, 2));
        cardManager.addCard(new FirestoreCard("elbarrio9", R.drawable.barrio_pau_defensa, "Pau", "Defensa", 3, 4, 4, 1));
        cardManager.addCard(new FirestoreCard("elbarrio10", R.drawable.barrio_ros_defensa, "Ros", "Defensa", 3, 3, 6, 7));
        cardManager.addCard(new FirestoreCard("elbarrio11", R.drawable.barrio_ubon_medio, "Ubon", "Medio", 9, 10, 9, 9));

        //Cartas del equipo 1k:
        cardManager.addCard(new FirestoreCard("1k1",R.drawable.k_bruno_defensa,"Bruno", "Defensa",5,3,5,3 ));
        cardManager.addCard(new FirestoreCard("1k2",R.drawable.k_gunter_delantero,"Gunter", "Delantero",7,3,1,5 ));
        cardManager.addCard(new FirestoreCard("1k3",R.drawable.k_iker_casillas_presidente,"Casillas", "Presidente",10,10,7,8 ));
        cardManager.addCard(new FirestoreCard("1k4",R.drawable.k_lautaro_delantero,"Lautaro", "Delantero",6,6,1,3 ));
        cardManager.addCard(new FirestoreCard("1k5",R.drawable.k_ledo_defensa,"Ledo", "Defensa",6,3,2,2 ));
        cardManager.addCard(new FirestoreCard("1k6",R.drawable.k_marino_delantero,"Marino", "Delantero",3,7,1,2 ));
        cardManager.addCard(new FirestoreCard("1k7",R.drawable.k_pluvins_medio,"Pluvins", "Medio",1,7,4,6 ));
        cardManager.addCard(new FirestoreCard("1k8",R.drawable.k_ricardo_portero,"Ricardo", "Portero",8,4,7,4 ));
        cardManager.addCard(new FirestoreCard("1k9",R.drawable.k_rivero_medio,"Rivero", "Medio",3,6,3,7 ));
        cardManager.addCard(new FirestoreCard("1k10",R.drawable.k_roman_defensa,"Roman", "Defensa",2,6,2,1 ));
        cardManager.addCard(new FirestoreCard("1k11",R.drawable.k_sauras_defensa,"Sauras", "Defensa",6,3,4,1 ));
        cardManager.addCard(new FirestoreCard("1k12",R.drawable.k_sorroche_delantero,"Sorroche", "Delantero",7,1,3,1 ));
    }
}