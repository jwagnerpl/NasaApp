package teamtreehouse.com.nasaapp.utilities;

import android.app.Application;
import android.content.Context;
import android.provider.Settings;

public class GlobalContext extends Application{


        private static GlobalContext instance;

        public static GlobalContext getInstance() {
            return instance;
        }

        public static Context getContext(){
            return instance;
            // or return instance.getApplicationContext();
        }

        @Override
        public void onCreate() {
            instance = this;
            super.onCreate();
        }
}
