package teamtreehouse.com.nasaapp.ui.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import teamtreehouse.com.nasaapp.R;
import teamtreehouse.com.nasaapp.adapters.ItemClickListener;
import teamtreehouse.com.nasaapp.adapters.RoverCameraRecylerAdapter;
import teamtreehouse.com.nasaapp.adapters.RoverImageRecyclerAdapter;
import teamtreehouse.com.nasaapp.photo_model.Photo;
import teamtreehouse.com.nasaapp.ui.activities.MainActivity;
import teamtreehouse.com.nasaapp.utilities.Utilities;

public class RoverImageRecyclerFragment extends android.app.Fragment implements ItemClickListener{

    RecyclerView recyclerView;
    RoverImageRecyclerAdapter adapter;
    ArrayList<Photo> photos;
    int columns;
    private static final String TAG = "RoverImageRecyclerFragm";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rover_image_recycler, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        photos = MainActivity.photoList;
        Log.d(TAG,photos.toString());
        adapter = new RoverImageRecyclerAdapter(photos);
        adapter.setItemClickListener(this);
        recyclerView = view.findViewById(R.id.imageRecyclerView);
        columns = Utilities.calculateNoOfColumns(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), columns));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Log.d(TAG, photos.get(position).getImgSrc());
        Context context = getActivity();
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("Type to create text overlay");
        alertDialog.setNegativeButton("Cancel", null);

        final LinearLayout ll = new LinearLayout(context);
        final EditText input = new EditText(context);
        final EditText email = new EditText(context);

        ll.setOrientation(LinearLayout.VERTICAL);
        ll.addView(input);
        ll.addView(email);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        email.setHint("Recipient Email Address");
        input.setHint("Your Image Text Here");
        input.setLayoutParams(lp);
        email.setLayoutParams(lp);

        alertDialog.setView(ll);
        alertDialog.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getActivity(),"Message sent",Toast.LENGTH_LONG);
                TextView tv = view.findViewById(R.id.imageOverlayText);
                tv.setText(input.getText());
                tv.setDrawingCacheEnabled(true);
                tv.buildDrawingCache();
                Bitmap tvCacheBm = tv.getDrawingCache();
                ImageView iv = view.findViewById(R.id.roverImage);
                BitmapDrawable bm = (BitmapDrawable)iv.getDrawable();
                Bitmap bitmap = bm.getBitmap();
                Bitmap bmCombined = combineImages(bitmap,tvCacheBm);
                createBitmapFile("21331212",bmCombined);

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","abc@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });
        alertDialog.show();
    }

    Bitmap combineImages(Bitmap background, Bitmap foreground){
        int width = 0, height = 0;
        Bitmap cs;

        width = getActivity().getWindowManager().getDefaultDisplay().getWidth();
        height = getActivity().getWindowManager().getDefaultDisplay().getHeight();

        cs = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas comboImage = new Canvas(cs);
        background = Bitmap.createScaledBitmap(background, width, height, true);
        comboImage.drawBitmap(background, 0, 0, null);
        comboImage.drawBitmap(foreground, 0,0, null);
        return cs;
    }

    void createBitmapFile(String filename, Bitmap bmp){
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filename);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
            // PNG is a lossless format, the compression factor (100) is ignored
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
