package teamtreehouse.com.nasaapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import teamtreehouse.com.nasaapp.R;
import teamtreehouse.com.nasaapp.model.Camera;

public class RoverCameraRecylerAdapter extends RecyclerView.Adapter<RoverCameraRecylerAdapter.ViewHolder>  {

    ArrayList<Camera> cameras;

    public RoverCameraRecylerAdapter(ArrayList<Camera> cameras) {
        this.cameras = cameras;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.camera_row,parent, false);
        return new  ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.photoText.setText(cameras.get(position).camDescription);
    }

    @Override
    public int getItemCount() {
        return cameras.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView photoText;

        public ViewHolder(View itemView) {
            super(itemView);
            photoText = itemView.findViewById(R.id.cameraTitle);
        }
    }
}
