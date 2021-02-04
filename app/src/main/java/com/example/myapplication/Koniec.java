package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;

public class Koniec extends AppCompatActivity {
    private SoundPool dzwieki;
    private int click, wrong, correct, victory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koniec);
        AudioAttributes atrybuty = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        dzwieki = new SoundPool.Builder()
                .setMaxStreams(6)
                .setAudioAttributes(atrybuty)
                .build();
        click = dzwieki.load(this,R.raw.click,1);
        wrong = dzwieki.load(this,R.raw.wrong,1);
        correct = dzwieki.load(this,R.raw.correct,1);
        victory = dzwieki.load(this,R.raw.victory,1);
    }

    public void newgame(View view) {
        Intent intent = new Intent(getApplicationContext(), Pytanie.class);
        startActivity(intent);
        dzwieki.play(click,1,1,0,0,1);
    }

    public void menubutton(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        dzwieki.play(click,1,1,0,0,1);
    }
}