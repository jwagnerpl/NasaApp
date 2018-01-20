package teamtreehouse.com.nasaapp.ui.fragments;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v13.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

import teamtreehouse.com.nasaapp.R;
import teamtreehouse.com.nasaapp.adapters.ItemClickListener;
import teamtreehouse.com.nasaapp.adapters.RoverImageRecyclerAdapter;
import teamtreehouse.com.nasaapp.photo_model.Photo;
import teamtreehouse.com.nasaapp.ui.activities.MainActivity;
import teamtreehouse.com.nasaapp.utilities.Utilities;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.support.v4.content.PermissionChecker.checkSelfPermission;

public class RoverImageRecyclerFragment extends android.app.Fragment implements ItemClickListener{

    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;
    private static final int REQUEST_PERMISSION = 1;
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
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                TextView tv = view.findViewById(R.id.imageOverlayText);
                tv.setText(input.getText());
                tv.setDrawingCacheEnabled(true);
                tv.buildDrawingCache();
                Bitmap tvCacheBm = tv.getDrawingCache();
                ImageView iv = view.findViewById(R.id.roverImage);
                BitmapDrawable bm = (BitmapDrawable)iv.getDrawable();
                Bitmap bitmap = bm.getBitmap();
                Bitmap bmCombined = combineImages(bitmap,tvCacheBm);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                        && ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            REQUEST_PERMISSION);
                    return;
                }

                shareBitmap(bmCombined, "mybitmap");

                String pathofBmp = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bmCombined,"title", null);
                Uri bmpUri = Uri.parse(pathofBmp);

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto",email.getText().toString(), null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Awesome Mars Photo");
                emailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Hey there, check out my attachment of a great Mars photo I picked for you thanks to the iNeedSpace app. Enjoy!");
                emailIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
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

    URI createBitmapFile(String filename, Bitmap bmp){
        File directory = getActivity().getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,"profile.jpg");

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(mypath);
            Log.d(TAG, mypath.toString());
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
        return mypath.toURI();
    }

    private void shareBitmap (Bitmap bitmap,String fileName) {
        try {
            File file = new File(getActivity().getCacheDir(), fileName + ".png");
            FileOutputStream fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();
            file.setReadable(true, false);
            final Intent intent = new Intent(     android.content.Intent.ACTION_SEND);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
            intent.setType("image/png");
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
