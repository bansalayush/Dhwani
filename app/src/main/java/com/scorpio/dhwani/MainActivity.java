package com.scorpio.dhwani;

import android.media.AudioTrack;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText enterfrequency;
    public static AudioTrack soundTrack = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enterfrequency = (EditText)findViewById(R.id.enterFrequency);
    }

    public void play(View v){

        double scale = 1;
        int playState = 0;

        if(soundTrack != null)
            playState = soundTrack.getPlayState();

        if(playState!= AudioTrack.PLAYSTATE_PLAYING)
            if(!enterfrequency.getText().toString().equals("")){

                double note = Double.parseDouble(enterfrequency.getText().toString());
                if(2 <= note && note<=2000)
                    new PlaySound().execute(note*scale);
                else
                    Toast.makeText(getApplicationContext(),"frequency should be in range",Toast.LENGTH_SHORT).show();


            }
            else{
                Toast.makeText(getApplicationContext(),"Please enter a frequency",Toast.LENGTH_SHORT).show();
            }

    }

    public void pause(View view) {
        if(soundTrack!=null)
            soundTrack.pause();
    }
}
