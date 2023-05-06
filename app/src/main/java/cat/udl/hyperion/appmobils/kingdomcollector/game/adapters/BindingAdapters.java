package cat.udl.hyperion.appmobils.kingdomcollector.game.adapters;

import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import cat.udl.hyperion.appmobils.kingdomcollector.R;
import cat.udl.hyperion.appmobils.kingdomcollector.game.models.Card;


public class BindingAdapters {

    @BindingAdapter("card")
    public static void setCardImage(ImageButton imageButton, Card card) {
        if (card == null) {
            return;
        }

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.card_placeholder)
                .error(R.drawable.card_placeholder);

        Glide.with(imageButton.getContext())
                .setDefaultRequestOptions(requestOptions)
                .load(card.getImageResource())
                .into(imageButton);
    }

    @BindingAdapter("app:srcCompat")
    public static void setSrcCompat(ImageView view, int resourceId) {
        view.setImageResource(resourceId);
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, Integer imageResource) {
        if (imageResource != null) {
            imageView.setImageResource(imageResource);
        } else {
            imageView.setImageResource(R.drawable.card_placeholder);
        }
    }


    @BindingAdapter("android:src")
    public static void setImageResource(ImageView imageView, int resource){
        imageView.setImageResource(resource);
    }

}
