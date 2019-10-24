package com.example.quran;

import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;

public class recite extends AppCompatActivity {

    public WebView webView;
    public ProgressBar progressBar;

    public int position;

    public Context context;

    private String CHANNEL_ID="Quran";

    public String surahname="";

    public String url="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_recite);
        webView = (WebView) findViewById(R.id.webView);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);







// Create an Intent for the activity you want to start
        Intent resultIntent = new Intent(this, MainActivity.class);
// Create the TaskStackBuilder and add the intent, which inflates the back stack
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntentWithParentStack(resultIntent);
// Get the PendingIntent containing the entire back stack
       final PendingIntent  contentIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);



        Bundle b = getIntent().getExtras();
        int value = -1; // or other values
        if(b != null){
            value = b.getInt("surah_title");


        }





        if(numlength(value+1)==1){

            String valuestr= value+1+"";
            url = "https://www.al-hamdoulillah.com/coran/mp3/files/ahmed-ajmi/00"+valuestr+".mp3";
        }
        else if(numlength(value+1)==2){
            String valuestr= value+1+"";
            url = "https://www.al-hamdoulillah.com/coran/mp3/files/ahmed-ajmi/0"+valuestr+".mp3";

        }
        if(numlength(value+1)==3){
            String valuestr= value+1+"";
            url = "https://www.al-hamdoulillah.com/coran/mp3/files/ahmed-ajmi/"+valuestr+".mp3";

        }






        try {
            JSONObject obj = new JSONObject(readJSONFromAsset());
            JSONArray surats=obj.getJSONArray("sura");




            for (int i=0;i<surats.length();i++){
                JSONObject jsonObjec=surats.getJSONObject(i);


                if(i==value){

                surahname+=" سورة "    ;
                surahname+= jsonObjec.getString("name");}


            }}


            catch (JSONException e) {
                e.printStackTrace();
            }







        webView = findViewById(R.id.webView);
        progressBar = findViewById(R.id.progress_bar);


        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                progressBar.setVisibility(View.VISIBLE);
                view.loadUrl(url);

                return true;
            }

            @Override
            public void onPageFinished(WebView view, final String url) {

               /* NotificationCompat.Builder notification = new NotificationCompat.Builder(recite.this, CHANNEL_ID)
                        .setContentTitle("Quran Reciting")
                        .setLargeIcon(BitmapFactory.decodeResource(recite.this.getResources(),
                                R.drawable.ic_notification))
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText("It's "+ surahname +" is playing" ));
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(recite.this);
                int notificationId = (int) (System.currentTimeMillis() / 4);
                notificationManager.notify(notificationId, notification.build());

                */


               NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(recite.this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_notification)
                        .setLargeIcon(BitmapFactory.decodeResource(recite.this.getResources(),
                                R.mipmap.ic_launcher))
                        .setContentTitle("Quran Reciting")
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText("It's "+ surahname +" is playing" ))
                        //.setSound(defaultSoundUri)
                        .setAutoCancel(true)
                        .setContentIntent(contentIntent);

                android.app.NotificationManager notificationManager =
                        (android.app.NotificationManager) recite.this.getSystemService(Context.NOTIFICATION_SERVICE);


                int notificationId = (int) (System.currentTimeMillis() / 4);

                notificationManager.notify(notificationId, notificationBuilder.build());


               // notificationBuilder




                progressBar.setVisibility(View.GONE);
            }
        });

        webView.loadUrl(url);





    }


    static int numlength(int n)
    {
        if (n == 0) return 1;
        int l;
        n=Math.abs(n);
        for (l=0;n>0;++l)
            n/=10;
        return l;
    }


        public String readJSONFromAsset() {
            String json = null;
            try {
                InputStream is = getAssets().open("quran.json");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                json = new String(buffer, "UTF-8");
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
            return json;
        }



}
