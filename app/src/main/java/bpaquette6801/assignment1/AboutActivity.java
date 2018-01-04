package bpaquette6801.assignment1;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;

public class AboutActivity extends AppCompatActivity implements OnClickListener {

    private Button downloadButton;
    private Button callButton;
    private Button websiteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


        downloadButton = (Button) findViewById(R.id.downloadButton);
        downloadButton.setOnClickListener(this);

        callButton = (Button) findViewById(R.id.callButton);
        callButton.setOnClickListener(this);

        websiteButton = (Button) findViewById(R.id.websiteButton);
        websiteButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.callButton:
                Uri number = Uri.parse("tel:5195055577");
                Intent intent = new Intent(Intent.ACTION_DIAL, number);
                startActivity(intent);
                break;

            case R.id.websiteButton:
                Intent intent2 = new Intent();
                intent2.setAction(Intent.ACTION_VIEW);
                intent2.addCategory(Intent.CATEGORY_BROWSABLE);
                intent2.setData(Uri.parse("http://www.forever-abroad.com"));
                startActivity(intent2);
                break;
            case R.id.downloadButton:

                new DownloadImageTask((ImageView) findViewById(R.id.downloadImage))
                        .execute("https://img.buzzfeed.com/buzzfeed-static/static/2016-07/22/18/campaign_images/buzzfeed-prod-fastlane01/michael-scott-quotes-that-are-still-hilarious-2-10277-1469225647-3_dblbig.jpg");

                break;
        }
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
