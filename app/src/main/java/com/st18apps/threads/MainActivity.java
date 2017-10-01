package com.st18apps.threads;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ProgressBar progressBar;
    private Button mButtonAsync;
    private Button mButtonThread;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView1 = (ImageView) findViewById(R.id.imageView1);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageView3 = (ImageView) findViewById(R.id.imageView3);
        imageView4 = (ImageView) findViewById(R.id.imageView4);
        imageView5 = (ImageView) findViewById(R.id.imageView5);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        progressBar.setVisibility(View.INVISIBLE);

        mButtonAsync = (Button) findViewById(R.id.buttonAsync);
        mButtonThread = (Button) findViewById(R.id.buttonThread);

        mHandler = new Handler();

        mButtonAsync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mButtonThread.setEnabled(false);

                MyAsyncTask myAsyncTask1 = new MyAsyncTask();
                MyAsyncTask myAsyncTask2 = new MyAsyncTask();
                MyAsyncTask myAsyncTask3 = new MyAsyncTask();
                MyAsyncTask myAsyncTask4 = new MyAsyncTask();
                MyAsyncTask myAsyncTask5 = new MyAsyncTask();

                myAsyncTask1.execute(1);
                myAsyncTask2.execute(2);
                myAsyncTask3.execute(3);
                myAsyncTask4.execute(4);
                myAsyncTask5.execute(5);

                mButtonThread.setEnabled(true);

            }
        });

        mButtonThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mButtonAsync.setEnabled(false);

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView1.setImageResource(R.drawable.bmw1);
                    }
                });

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView2.setImageResource(R.drawable.bmw2);
                    }
                });

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView3.setImageResource(R.drawable.bmw3);
                    }
                });

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView4.setImageResource(R.drawable.bmw4);
                    }
                });

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView5.setImageResource(R.drawable.bmw5);
                    }
                });

                mButtonAsync.setEnabled(true);

            }
        });
    }

    public void setImageToImageView(int number) {

        switch (number) {
            case 1:
                imageView5.setImageResource(R.drawable.bmw1);
                break;

            case 2:
                imageView4.setImageResource(R.drawable.bmw2);
                break;

            case 3:
                imageView3.setImageResource(R.drawable.bmw3);
                break;

            case 4:
                imageView2.setImageResource(R.drawable.bmw4);
                break;

            case 5:
                imageView1.setImageResource(R.drawable.bmw5);
                break;

        }
    }

    public class MyAsyncTask extends AsyncTask<Integer, Void, Integer> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Integer aVoid) {
            super.onPostExecute(aVoid);

            setImageToImageView(aVoid);

            if (progressBar.getVisibility() == View.INVISIBLE)
                progressBar.setVisibility(View.VISIBLE);
            else
                progressBar.setVisibility(View.INVISIBLE);

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            progressBar.setVisibility(View.VISIBLE);

        }

        @Override
        protected Integer doInBackground(Integer... voids) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return voids[0];
        }
    }
}
