package ir.apend.slider.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;

import java.util.List;

import ir.apend.slider.model.Slide;
import ir.apend.slider.ui.Slider;
import ir.apend.sliderlibrary.R;

/**
 * Created by Farzad Farazmand on 28,June,2017 farzad.farazmand@gmail.com
 */

public class SliderAdapter extends PagerAdapter {

    private int targetWidth;
    private LayoutInflater layoutInflater;
    private AdapterView.OnItemClickListener itemClickListener;
    private List<Slide> items;

    public SliderAdapter(@NonNull Context context, List<Slide> items,
                         AdapterView.OnItemClickListener itemClickListener,
                         int targetWidth) {
        this.targetWidth = targetWidth;
        this.items = items;
        this.itemClickListener = itemClickListener;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        // The object returned by instantiateItem() is a key/identifier. This method checks whether
        // the View passed to it (representing the page) is associated with that key or not.
        // It is required by a PagerAdapter to function properly.
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = layoutInflater.inflate(R.layout.row_slider, container, false);
        ImageView sliderImage = view.findViewById(R.id.sliderImage);
        setImageWidth(view.findViewById(R.id.topChild));
        loadImage(sliderImage, items.get(position).getImageUri());
        View parent = view.findViewById(R.id.ripple);
        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickListener != null)
                    itemClickListener.onItemClick(null, null, position, 0);
            }
        });
        container.addView(view);
        return view;
    }

    private void setImageWidth(View view) {
        if (targetWidth > -1) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.width = targetWidth;
            view.setLayoutParams(layoutParams);
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Removes the page from the container for the given position. We simply removed object using removeView()
        // but could’ve also used removeViewAt() by passing it the position.
        try {
            // Remove the view from the container
            container.removeView((View) object);
            // Invalidate the object
            object = null;
        } catch (Exception e) {
            Log.w(Slider.TAG, "destroyItem: failed to destroy item and clear it's used resources", e);
        }
    }

    /**
     * Display the gallery image into the image view provided.
     */
    private void loadImage(ImageView imageView, Uri uri) {
        if (uri != null) {
            Glide.with(imageView.getContext()) // Bind it with the context of the actual view used
                    .load(uri) // Load the image
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .placeholder(R.mipmap.ic_image)
                    .transform(new CenterCrop())
                    .into(imageView);
        }
    }
}
