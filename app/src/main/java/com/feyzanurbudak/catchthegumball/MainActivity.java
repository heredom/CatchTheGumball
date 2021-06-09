package com.feyzanurbudak.catchthegumball;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView scoreText;
    TextView timeText;
    int score;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView [] imageArray;
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initilaize(burada bir kere başlatıyoruz)
        scoreText=findViewById(R.id.scoreText);
        timeText=findViewById(R.id.timeText);
        imageView1=findViewById(R.id.imageView1);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);
        imageView9=findViewById(R.id.imageView9);

        imageArray =new ImageView[]{imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};
        hideImages();
        score=0;

        new CountDownTimer(10000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timeText.setText("Time:"+millisUntilFinished/1000);
            }


            @Override
            public void onFinish() {
                timeText.setText("TİME OFF");
                handler.removeCallbacks(runnable);//runnable durdur
                for(ImageView image :imageArray){
                    image.setVisibility(View.INVISIBLE);//gumballı komple sakla
                }
                AlertDialog.Builder alert= new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("RESTART?");
                alert.setMessage("Are you sure restart game?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=getIntent();
                        finish();
                        startActivity(intent);

                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"Game Over!",Toast.LENGTH_LONG).show();
                    }
                });
                alert.show();//toastu göster
            }
        }.start();
    }
    public void skoruArttır(View view){
        score++;
        scoreText.setText("Score:"+score);

    }
    public void hideImages(){
        handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                for(ImageView image :imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                Random random=new Random();
                int i=random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this,500);//runnableı çalıştır ve istediğim periyodda çalıştır
            }
        };
        handler.post(runnable);//başlatmak için
    }

}
