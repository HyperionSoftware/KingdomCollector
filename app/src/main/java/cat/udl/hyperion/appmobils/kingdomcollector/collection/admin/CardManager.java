package cat.udl.hyperion.appmobils.kingdomcollector.collection.admin;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class CardManager {
    private FirebaseFirestore db;

    public CardManager() {
        db = FirebaseFirestore.getInstance();
    }

    public void addCard(FirestoreCard card) {
        db.collection("general_cards").document(card.getId()).set(card)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("CardManager", "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("CardManager", "Error writing document", e);
                    }
                });
    }

}

