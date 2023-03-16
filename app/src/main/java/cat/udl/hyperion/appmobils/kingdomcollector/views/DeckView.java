package cat.udl.hyperion.appmobils.kingdomcollector.views;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cat.udl.hyperion.appmobils.kingdomcollector.R;

public class DeckView extends Fragment {

    public DeckView() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Infla el layout para este fragment
        return inflater.inflate(R.layout.fragment_deck, container, false);
    }
}
