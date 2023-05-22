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
        addCardToDbAndManager("elbarrio1", R.drawable.barrio_adri_contreras_presidente,"Adri_Contreras", "Presidente", 9, 10, 7, 10);
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

        //Cartas del equipo Aniquiladores (12)
        addCardToDbAndManager("Aniquiladores1", R.drawable.ani_andres_defensa,"Andres", "Defensa",5,3,5,2 );
        addCardToDbAndManager("Aniquiladores2", R.drawable.ani_bernat_medio,"Bernat", "Medio",1,5,1,4 );
        addCardToDbAndManager("Aniquiladores3", R.drawable.ani_daniel_portero,"Daniel", "Medio",7,7,6,6 );
        addCardToDbAndManager("Aniquiladores4", R.drawable.ani_gilles_medio,"Gilles", "Medio",4,2,5,4 );
        addCardToDbAndManager("Aniquiladores5", R.drawable.ani_glavaly_medio,"Glavaly", "Medio",3,7,1,2 );
        addCardToDbAndManager("Aniquiladores6", R.drawable.ani_hernandez_11,"Hernandez", "Delantero",3,7,4,6 );
        addCardToDbAndManager("Aniquiladores7", R.drawable.ani_jadir_defensa,"Jadir", "Defensa",6,7,6,2 );
        addCardToDbAndManager("Aniquiladores8", R.drawable.ani_jorquera_defensa,"Jorquera", "Defensa",2,4,4,1 );
        addCardToDbAndManager("Aniquiladores9", R.drawable.ani_juan_guarnizo_presidente,"Juan Guarnizo", "Presidente",10,10,9,9 );
        addCardToDbAndManager("Aniquiladores10", R.drawable.ani_morales_portero,"Morales", "Portero",6,3,6,4 );
        addCardToDbAndManager("Aniquiladores11", R.drawable.ani_pau_delantero,"Pau", "Delantero",6,2,1,1 );
        addCardToDbAndManager("Aniquiladores12", R.drawable.ani_roger_medio,"Roger", "Medio",7,2,4,7 );

        //Cartas del equipo Jijantes:
        addCardToDbAndManager("Jijantes1", R.drawable.jij_alsina_medio,"Alsina", "Medio",7,1,3,1 );
        addCardToDbAndManager("Jijantes2", R.drawable.jij_arnau_defensa,"Arnau", "Defensa",2,5,1,4 );
        addCardToDbAndManager("Jijantes3", R.drawable.jij_dorado_delantero,"Dorado", "Delantero",3,5,1,2 );
        addCardToDbAndManager("Jijantes4", R.drawable.jij_gerard_romero_presidente,"Gerard Romero", "Presidente",3,4,10,10 );
        addCardToDbAndManager("Jijantes5", R.drawable.jij_golobart_jugador,"Golobart", "Delantero",2,6,2,1 );
        addCardToDbAndManager("Jijantes6", R.drawable.jij_ivan_delantero,"Ivan", "Delantero",3,3,5,4 );
        addCardToDbAndManager("Jijantes7", R.drawable.jij_jay_defensa,"Jay", "Defensa",5,4,3,3 );
        addCardToDbAndManager("Jijantes8", R.drawable.jij_maca_medio,"Maca", "Medio",5,5,2,3 );
        addCardToDbAndManager("Jijantes9", R.drawable.jij_mario_portero,"Mario", "Portero",4,2,7,4 );
        addCardToDbAndManager("Jijantes10", R.drawable.jij_noel_defensa,"Noel", "Defensa",4,2,5,4 );
        addCardToDbAndManager("Jijantes11", R.drawable.jij_pol_portero,"Pol", "Portero",6,3,2,2 );
        addCardToDbAndManager("Jijantes12", R.drawable.jij_quero_medio,"Quero", "Medio",6,3,4,1 );

        //Cartas del equipo KuniSports:
        addCardToDbAndManager("Kun1", R.drawable.kun_coro_11,"Coro", "Delantero",10,9,4,9 );
        addCardToDbAndManager("Kun2", R.drawable.kun_guerrero_defensa,"Guerrero", "Defensa",6,9,4,5 );
        addCardToDbAndManager("Kun3", R.drawable.kun_hidaldo_delantero,"Hidaldo", "Delantero",2,7,6,3 );
        addCardToDbAndManager("Kun4", R.drawable.kun_joan_medio,"Joan", "Medio",6,9,8,5 );
        addCardToDbAndManager("Kun5", R.drawable.kun_kun_aguero_presidente,"Kun Aguero", "Presidente",10,10,10,10 );
        addCardToDbAndManager("Kun6", R.drawable.kun_lluc_delantero,"Lluc", "Delantero",7,4,4,4 );
        addCardToDbAndManager("Kun7", R.drawable.kun_miki_defensa,"Miki", "Defensa",4,7,2,6 );
        addCardToDbAndManager("Kun8", R.drawable.kun_nacho_defensa,"Nacho", "Defensa",7,5,3,2 );
        addCardToDbAndManager("Kun9", R.drawable.kun_regerri_defensa,"Regerri", "Defensa",4,4,3,6 );
        addCardToDbAndManager("Kun10", R.drawable.kun_rode_portero,"Rode", "Portero",10,9,8,9 );
        addCardToDbAndManager("Kun11", R.drawable.kun_torrentbo_delantero,"Torrentbo", "Delantero",9,6,8,7 );
        addCardToDbAndManager("Kun12", R.drawable.kun_victor_portero,"Victor", "Portero",6,5,7,3 );


        //Cartas del equipo LosTroncos:
        addCardToDbAndManager("Troncos1", R.drawable.troncos_arus_portero,"Arus", "Portero",5,7,4,5 );
        addCardToDbAndManager("Troncos2", R.drawable.troncos_nuevo_medio,"Nuevo", "Medio",3,1,2,10 );
        addCardToDbAndManager("Troncos3", R.drawable.troncos_dorkis_delantero,"Dorkis", "Delantero",6,3,7,2 );
        addCardToDbAndManager("Troncos4", R.drawable.troncos_martinez_delantero,"Martinez", "Delantero",6,3,7,3 );
        addCardToDbAndManager("Troncos5", R.drawable.troncos_molas_medio,"Molas", "Medio",4,7,2,4 );
        addCardToDbAndManager("Troncos6", R.drawable.troncos_moel_medio,"Moel", "Medio",5,4,2,6 );
        addCardToDbAndManager("Troncos7", R.drawable.troncos_molina_medio,"Molina", "Medio",6,2,2,2 );
        addCardToDbAndManager("Troncos8", R.drawable.troncos_soriano_defensa,"Soriano", "Defensa",7,1,3,1 );
        addCardToDbAndManager("Troncos9", R.drawable.troncos_lechuga_portero,"Lechuga", "Portero",2,7,2,1 );
        addCardToDbAndManager("Troncos10", R.drawable.troncos_alvaro_delantero,"Alvaro", "Delantero",8,7,9,10 );
        addCardToDbAndManager("Troncos11", R.drawable.troncos_planas_jugador,"Planas", "Delantero",7,6,9,8 );
        addCardToDbAndManager("Troncos12", R.drawable.troncos_perxita_presidente,"Perxita", "Presidente",9,9,10,11 );



        //Cartas del equipo PIO:
        addCardToDbAndManager("Pio1", R.drawable.pio_santiago_defensa,"Santiago", "Defensa",5,6,2,4 );
        addCardToDbAndManager("Pio2", R.drawable.pio_ropero_medio,"Ropero", "Medio",1,7,4,5 );
        addCardToDbAndManager("Pio3", R.drawable.pio_cokita_medio,"Cokita", "Medio",2,3,6,7 );
        addCardToDbAndManager("Pio4", R.drawable.pio_adri_delantero,"Adri", "Delantero",9,2,8,4 );
        addCardToDbAndManager("Pio5", R.drawable.pio_turner_defensa,"Turner", "Defensa",4,9,8,4 );
        addCardToDbAndManager("Pio6", R.drawable.pio_juarez_medio,"Juarez", "Medio",7,8,6,7 );
        addCardToDbAndManager("Pio7", R.drawable.pio_cabello_delantero,"Cabello", "Delantero",5,3,3,6 );
        addCardToDbAndManager("Pio8", R.drawable.pio_pau_medio,"Pau", "Medio",5,3,7,2 );
        addCardToDbAndManager("Pio9", R.drawable.pio_carlitos_defensa,"Carlitos", "Defensa",1,7,7,8 );
        addCardToDbAndManager("Pio10", R.drawable.pio_jorge_portero,"Jorge", "Portero",10,8,2,7 );
        addCardToDbAndManager("Pio11", R.drawable.pio_lopo_jugador,"Lopo", "Delantero",7,2,4,8 );
        addCardToDbAndManager("Pio12", R.drawable.pio_rivers_presidente,"Rivers", "Presidente",10,4,10,9 );



        //Cartas del equipo Porcinos:

        //Cartas del equipo Rayo:

        //Cartas del equipo Sayans:

        //Cartas del equipo UltimateMostoles:

        //Cartas del equipo XBuyer:

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