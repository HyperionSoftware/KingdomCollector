package cat.udl.hyperion.appmobils.kingdomcollector.collection.views;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.concurrent.Executors;

import cat.udl.hyperion.appmobils.kingdomcollector.R;
import cat.udl.hyperion.appmobils.kingdomcollector.collection.admin.CardManager;
import cat.udl.hyperion.appmobils.kingdomcollector.collection.admin.FirestoreCard;
import cat.udl.hyperion.appmobils.kingdomcollector.collection.db.AppDatabase;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.Card;

public class addingcards extends AppCompatActivity {

    private CardManager cardManager;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addingcards);
        cardManager = new CardManager();

        Button refreshCardsButton = findViewById(R.id.button);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "general-cards-local").build();
        refreshCardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCards();
            }
        });
    }

    private void addCards() {
        //Cartas del equipo ElBarrio:
        addCardToDbAndManager("elbarrio1", R.drawable.adri_contreras,"Adri_Contreras", "Presidente", 9, 10, 7, 10);
        addCardToDbAndManager("elbarrio2", R.drawable.barrio_barnera_portero, "Barnera", "Portero", 3, 4, 4, 4);
        addCardToDbAndManager("elbarrio3", R.drawable.barrio_boada_delantero, "Boada", "Delantero", 6, 3, 2, 6);
        addCardToDbAndManager("elbarrio4", R.drawable.barrio_capilla_medio, "Capilla", "Medio", 5, 3, 5, 1);
        addCardToDbAndManager("elbarrio5", R.drawable.barrio_jacob_delantero, "Jacob", "Delantero", 2, 6, 3, 7);
        addCardToDbAndManager("elbarrio6", R.drawable.barrio_josejuan_11, "Jose_Juan", "11", 9, 10, 7, 9);
        addCardToDbAndManager("elbarrio7", R.drawable.barrio_mesa_defensa, "Mesa", "Defensa", 1, 4, 7, 6);
        addCardToDbAndManager("elbarrio8", R.drawable.barrio_olae_delantero, "Olae", "Delantero", 4, 4, 3, 2);
        addCardToDbAndManager("elbarrio9", R.drawable.barrio_pau_defensa, "Pau", "Defensa", 3, 4, 4, 1);
        addCardToDbAndManager("elbarrio10", R.drawable.barrio_ros_defensa, "Ros", "Defensa", 3, 3, 6, 7);
        addCardToDbAndManager("elbarrio11", R.drawable.barrio_ubon_medio, "Ubon", "Medio", 9, 10, 9, 9);

        //Cartas del equipo 1k:
        addCardToDbAndManager("1k1", R.drawable.k_bruno_defensa,"Bruno", "Defensa",5,3,5,3 );
        addCardToDbAndManager("1k2", R.drawable.k_gunter_delantero,"Gunter", "Delantero",7,3,1,5 );
        addCardToDbAndManager("1k3", R.drawable.k_iker_casillas_presidente,"Casillas", "Presidente",10,10,7,8 );
        addCardToDbAndManager("1k4", R.drawable.k_lautaro_delantero,"Lautaro", "Delantero",6,6,1,3 );
        addCardToDbAndManager("1k5", R.drawable.k_ledo_defensa,"Ledo", "Defensa",6,3,2,2 );
        addCardToDbAndManager("1k6", R.drawable.k_marino_delantero,"Marino", "Delantero",3,7,1,2 );
        addCardToDbAndManager("1k7", R.drawable.k_pluvins_medio,"Pluvins", "Medio",1,7,4,6 );
        addCardToDbAndManager("1k8", R.drawable.k_ricardo_portero,"Ricardo", "Portero",8,4,7,4 );
        addCardToDbAndManager("1k9", R.drawable.k_rivero_medio,"Rivero", "Medio",3,6,3,7 );
        addCardToDbAndManager("1k10", R.drawable.k_roman_defensa,"Roman", "Defensa",2,6,2,1 );
        addCardToDbAndManager("1k11", R.drawable.k_sauras_defensa,"Sauras", "Defensa",6,3,4,1 );
        addCardToDbAndManager("1k12", R.drawable.k_sorroche_delantero,"Sorroche", "Delantero",7,1,3,1 );

    }
    private void addCardToDbAndManager(final String id, final int drawable, final String name, final String role, final int powerArriba, final int powerIzquierda, final int powerAbajo, final int powerDerecha) {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                // Check if the card already exists
                Card existingCard = db.cardDao().getCardById(id);
                if (existingCard == null) {
                    // The card does not exist, so we can insert it
                    Card card = new Card(id, drawable, name, role, powerArriba, powerIzquierda, powerAbajo, powerDerecha);
                    cardManager.addCard(new FirestoreCard(id));
                    db.cardDao().insert(card);
                }
            }
        });
    }


}