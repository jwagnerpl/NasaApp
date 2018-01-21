package teamtreehouse.com.nasaapp.ui.fragments;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.FragmentTransaction;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.easyandroidanimations.library.Animation;
import com.easyandroidanimations.library.AnimationListener;
import com.easyandroidanimations.library.BounceAnimation;
import com.easyandroidanimations.library.ExplodeAnimation;
import com.easyandroidanimations.library.FadeInAnimation;
import com.easyandroidanimations.library.PathAnimation;
import com.easyandroidanimations.library.RotationAnimation;

import java.util.ArrayList;

import teamtreehouse.com.nasaapp.R;
import teamtreehouse.com.nasaapp.api.ApiCall;
import teamtreehouse.com.nasaapp.date_model.CraftDates;
import teamtreehouse.com.nasaapp.ui.activities.MainActivity;


public class StartScreenFragment extends android.app.Fragment {

    public static ArrayList<CraftDates> craftDates;
    Button launchButton;
    private static final String TAG = "StartScreenFragment";
    ImageView logo;
    TextView title;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start_screen, container, false);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        logo = view.findViewById(R.id.rocketImageView);
        title = view.findViewById(R.id.title);

        //new FadeInAnimation(logo).setDuration(2000).animate();
        final MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.orbit);
        //mp.start();
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(50,100));
        points.add(new Point(50,-150));

        new FadeInAnimation(logo).setDuration(2500).animate();
        new FadeInAnimation(title).setDuration(2500).setListener(new AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {

                new RotationAnimation(logo).setPivot(RotationAnimation.PIVOT_CENTER).setDegrees(-45).setListener(new AnimationListener() {
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        new PathAnimation(logo).setPoints(points).setDuration(2500).setListener(new AnimationListener() {
                            @Override
                            public void onAnimationEnd(Animation animation) {
                                new ExplodeAnimation(title).setDuration(250).animate();
                            }
                        }).animate();
                    }
                }).animate();
            }
        }).animate();

        ApiCall apiCall = new ApiCall();
        apiCall.getDates();

        Handler handler = new Handler();
        waitFiveSeconds(handler);
    }

        void waitFiveSeconds(Handler handler){
        handler.postDelayed(new Runnable(){
            @Override
            public void run() {
                if(MainActivity.craftDates != null) {
                    startFragment();
                }

                else{waitFiveSeconds(handler);}

            }
        }, 6000);
    }


        void startFragment(){
        android.app.Fragment mainFragment = new MainFragment();
        android.app.FragmentManager fm = getActivity().getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        //fragmentTransaction.setCustomAnimations(fade_in, fade_out);
        fragmentTransaction.replace(R.id.placeHolder, mainFragment).commit();
    }


}
