package cat.udl.hyperion.appmobils.kingdomcollector.collection.admin;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cat.udl.hyperion.appmobils.kingdomcollector.game.models.Card;

public class SharedPreferencesManager {

    private static final String SELECTED_CARDS_KEY = "selected_cards_key";

    private SharedPreferences sharedPreferences;
    private Gson gson;

    public SharedPreferencesManager(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        gson = new Gson();
    }

    public void storeSelectedCards(List<Card> selectedCards) {
        String json = gson.toJson(selectedCards);
        sharedPreferences.edit().putString(SELECTED_CARDS_KEY, json).apply();
    }

    public List<Card> getSelectedCards() {
        String json = sharedPreferences.getString(SELECTED_CARDS_KEY, null);
        if (json == null) {
            return new ArrayList<>();
        } else {
            Type type = new TypeToken<List<Card>>() {}.getType();
            return gson.fromJson(json, type);
        }
    }
}
