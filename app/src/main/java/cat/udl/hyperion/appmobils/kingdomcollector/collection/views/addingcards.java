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
        addCardToDbAndManager("Porcinos1", R.drawable.porcinos_sergi_portero,"Sergi", "Portero",3,6,3,7 );
        addCardToDbAndManager("Porcinos2", R.drawable.porcinos_lao_medio,"Lao", "Medio",3,3,5,4 );
        addCardToDbAndManager("Porcinos3", R.drawable.porcinos_uri_delantero,"Uri", "Delantero",5,4,7,5 );
        addCardToDbAndManager("Porcinos4", R.drawable.porcinos_blanco_delantero,"Blanco", "Delantero",3,6,3,6 );
        addCardToDbAndManager("Porcinos5", R.drawable.porcinos_kiliam_delantero,"Kiliam", "Delantero",8,3,8,2 );
        addCardToDbAndManager("Porcinos6", R.drawable.porcinos_carlitos_medio,"Carlitos", "Medio",2,5,1,3 );
        addCardToDbAndManager("Porcinos7", R.drawable.porcinos_toti_medio,"Toti", "Medio",7,3,8,8 );
        addCardToDbAndManager("Porcinos8", R.drawable.porcinos_david_defensa,"David", "Defensa",5,6,7,3 );
        addCardToDbAndManager("Porcinos9", R.drawable.porcinos_cichero_defensa,"Cichero", "Defensa",8,4,7,6 );
        addCardToDbAndManager("Porcinos10", R.drawable.porcinos_segovia_portero,"Segovia", "Portero",8,10,7,6 );
        addCardToDbAndManager("Porcinos11", R.drawable.porcinos_espinosa_jugador,"Espinosa", "Delantero",8,4,3,6 );
        addCardToDbAndManager("Porcinos12", R.drawable.porcinos_ibai_presidente,"Ibai", "Presidente",10,11,9,11 );


        //Cartas del equipo Rayo:
        addCardToDbAndManager("Rayo1", R.drawable.rayo_alex_delantero,"Alex", "Delantero",6,7,3,6 );
        addCardToDbAndManager("Rayo2", R.drawable.rayo_jonathan_medio,"Jonathan", "Medio",4,4,7,6 );
        addCardToDbAndManager("Rayo3", R.drawable.rayo_biboum_delantero,"Biboum", "Delantero",6,5,3,5 );
        addCardToDbAndManager("Rayo4", R.drawable.rayo_alejandro_defensa,"Alejandro", "Defensa",6,3,2,6 );
        addCardToDbAndManager("Rayo5", R.drawable.rayo_dani_medio,"Dani", "Medio",4,4,2,4 );
        addCardToDbAndManager("Rayo6", R.drawable.rayo_franky_medio,"Franky", "Medio",5,4,2,6 );
        addCardToDbAndManager("Rayo7", R.drawable.rayo_reyes_defensa,"Reyes", "Defensa",5,3,3,6 );
        addCardToDbAndManager("Rayo8", R.drawable.rayo_guillem_delantero,"Guillem", "Delantero",7,3,5,1 );
        addCardToDbAndManager("Rayo9", R.drawable.rayo_canet_portero,"Canet", "Portero",7,3,1,5 );
        addCardToDbAndManager("Rayo10", R.drawable.rayo_pelaz_medio,"Pelaz", "Medio",9,8,8,9 );
        addCardToDbAndManager("Rayo11", R.drawable.rayo_polotelli_jugador,"Polotelli", "Delantero",2,8,7,2 );
        addCardToDbAndManager("Rayo12", R.drawable.rayo_spursito_presidente,"Spursito", "Presidente",4,7,10,10 );



        //Cartas del equipo Sayans:
        addCardToDbAndManager("Sayans1", R.drawable.sayans_fajardo_portero,"Fajardo", "Portero",3,6,3,8 );
        addCardToDbAndManager("Sayans2", R.drawable.sayans_carbo_medio,"Carbo", "Medio",3,6,5,6 );
        addCardToDbAndManager("Sayans3", R.drawable.sayans_david_delantero,"David", "Delantero",6,6,6,8 );
        addCardToDbAndManager("Sayans4", R.drawable.sayans_feliu_medio,"Feliu", "Medio",5,5,3,8 );
        addCardToDbAndManager("Sayans5", R.drawable.sayans_torres_defensa,"Torres", "Defensa",6,7,7,4 );
        addCardToDbAndManager("Sayans6", R.drawable.sayans_valle_medio,"Valle", "Medio",5,7,5,3 );
        addCardToDbAndManager("Sayans7", R.drawable.sayans_temo_medio,"Temo", "Medio",6,7,8,8 );
        addCardToDbAndManager("Sayans8", R.drawable.sayans_campu_defensa,"Campu", "Defensa",6,7,3,5 );
        addCardToDbAndManager("Sayans9", R.drawable.sayans_gio_delantero,"Gio", "Delantero",7,9,9,7 );
        addCardToDbAndManager("Sayans10", R.drawable.sayans_briones_portero,"Briones", "Portero",10,9,9,9 );
        addCardToDbAndManager("Sayans11", R.drawable.sayans_pol_jugador,"Pol", "Delantero",7,8,7,9 );
        addCardToDbAndManager("Sayans12", R.drawable.sayans_thegrefg_presidente,"TheGrefg", "Presidente",10,11,11,9 );


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
                else if (existingCard!=null){
                    db.cardDao().getCardById(id).setImageResource(drawable);
                }
            }
        });
    }


}