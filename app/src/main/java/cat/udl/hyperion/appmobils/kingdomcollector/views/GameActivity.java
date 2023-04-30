package cat.udl.hyperion.appmobils.kingdomcollector.views;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cat.udl.hyperion.appmobils.kingdomcollector.R;
import cat.udl.hyperion.appmobils.kingdomcollector.adapter.CardSelectedAdapter;
import cat.udl.hyperion.appmobils.kingdomcollector.models.Card;
import cat.udl.hyperion.appmobils.kingdomcollector.viewmodels.CardSelectedViewModel;

public class GameActivity extends AppCompatActivity {

    private CardSelectedAdapter selectedCardAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        List<Card> selectedCards1 = (List<Card>) getIntent().getSerializableExtra("selectedCards");

        RecyclerView selectedRecyclerView = findViewById(R.id.selected_recycler_view);
        selectedRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        selectedCardAdapter = new CardSelectedAdapter(null);
        selectedRecyclerView.setAdapter(selectedCardAdapter);

        CardSelectedViewModel selectedViewModel = new ViewModelProvider(this).get(CardSelectedViewModel.class);
        selectedViewModel.getSelectedCardsLiveData().observe(this, selectedCards -> selectedCardAdapter.setSelectedCards(selectedCards));

        // Configura el RecyclerView del tablero de juego
        RecyclerView gameBoardRecyclerView = findViewById(R.id.game_board_recycler_view);
        gameBoardRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        GameBoardAdapter gameBoardAdapter = new GameBoardAdapter();
        gameBoardRecyclerView.setAdapter(gameBoardAdapter);

        // Cargar las cartas seleccionadas y actualizar el CardSelectedViewModel
        List<Card> selectedCards = loadSelectedCards();
        CardSelectedViewModel cardSelectedViewModel = new ViewModelProvider(this).get(CardSelectedViewModel.class);
        cardSelectedViewModel.setSelectedCardsLiveData(selectedCards);
    }

    private static class GameBoardAdapter extends RecyclerView.Adapter<GameBoardViewHolder> {

        @NonNull
        @Override
        public GameBoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_board_item, parent, false);
            return new GameBoardViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull GameBoardViewHolder holder, int position) {
            // Configura el fondo del espacio del tablero de juego
            holder.bind(R.drawable.reverse_card);
        }

        @Override
        public int getItemCount() {
            return 9;
        }
    }

    private static class GameBoardViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;

        public GameBoardViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
        }

        public void bind(int image) {
            imageView.setImageResource(image);
        }
    }
    private List<Card> loadSelectedCards() {
        SharedPreferences sharedPreferences = getSharedPreferences("selected_cards", MODE_PRIVATE);
        String json = sharedPreferences.getString("cards", null);

        if (json != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Card>>() {}.getType();
            return gson.fromJson(json, type);
        } else {
            return new ArrayList<>();
        }
    }

}
