package teamtreehouse.com.nasaapp.adapters;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import teamtreehouse.com.nasaapp.R;
import teamtreehouse.com.nasaapp.photo_model.Photo;
import teamtreehouse.com.nasaapp.ui.activities.MainActivity;

public class RoverImageRecyclerAdapter extends RecyclerView.Adapter<RoverImageRecyclerAdapter.ViewHolder> {
    List<Photo> photos;
    private static final String TAG = "RoverImageRecyclerAdapt";
    ItemClickListener itemClickListener;

    public RoverImageRecyclerAdapter(List<Photo> photos) {
        this.photos = photos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_row, parent, false);
        return new RoverImageRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Photo photo = photos.get(position);
        if(!photo.getImgSrc().contains("https")){
        photo.setImgSrc(photo.getImgSrc().replaceAll("http","https"));}
        Picasso.Builder builder = new Picasso.Builder(MainActivity.context);
        builder.listener(new Picasso.Listener() {
            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                Log.d(TAG, exception.toString());
            }
        });

        String uri = photos.get(position).getImgSrc();
        builder.build().load(Uri.parse(uri)).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener

    {
        TextView imageTitle;
        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imageTitle = itemView.findViewById(R.id.imageTitle);
            imageView = itemView.findViewById(R.id.roverImage);
        }

        @Override
        public void onClick(View view) {
            if(itemClickListener != null){
                itemClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
}
