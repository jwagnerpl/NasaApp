package teamtreehouse.com.nasaapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import teamtreehouse.com.nasaapp.R;
import teamtreehouse.com.nasaapp.model.Camera;

public class RoverCameraRecylerAdapter extends RecyclerView.Adapter<RoverCameraRecylerAdapter.ViewHolder> {

    ArrayList<Camera> cameras;
    private static final String TAG = "RoverCameraRecylerAdapt";

    public RoverCameraRecylerAdapter(ArrayList<Camera> cameras) {
        this.cameras = cameras;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.camera_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.photoText.setText(cameras.get(position).camDescription);

        if (Camera.selectedRover == 1 || Camera.selectedRover == 2) {
            switch (cameras.get(position).getCamAbbreviation()) {
                case "fhaz": holder.imageView.setImageResource(R.drawable.spirit_front_hazcam);
                break;
                case "rhaz": holder.imageView.setImageResource(R.drawable.spirit_rear_hazcam);
                break;
                case "navcam": holder.imageView.setImageResource(R.drawable.spirit_navcam);
                break;
                case "pancam": holder.imageView.setImageResource(R.drawable.spirit_panoramic);
                break;
                case "minitest": holder.imageView.setImageResource(R.drawable.spirit_micro);
                break;
            }
        }

        else{
            Log.d(TAG, cameras.get(position).getCamAbbreviation());
            switch(cameras.get(position).getCamAbbreviation()){

                case "fhaz": holder.imageView.setImageResource(R.drawable.curiosity_front_hazcam);
                break;
                case "rhaz": holder.imageView.setImageResource(R.drawable.curiosity_rear_hazcam);
                break;
                case "mast": holder.imageView.setImageResource(R.drawable.curiosity_mast_cam);
                break;
                case "chemcam": holder.imageView.setImageResource(R.drawable.curiosity_chemcam);
                break;
                case "mahli": holder.imageView.setImageResource(R.drawable.curiosity_mahli);
                break;
                case "mardi": holder.imageView.setImageResource(R.drawable.curiosity_mardi);
                break;
                case "navcam": holder.imageView.setImageResource(R.drawable.curiosity_navcam);
                break;
            }

        }

    }

    @Override
    public int getItemCount() {
        return cameras.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView photoText;
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            photoText = itemView.findViewById(R.id.cameraTitle);
            imageView = itemView.findViewById(R.id.cameraImage);
        }
    }
}
