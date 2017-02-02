package com.scorpio.dhwani;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.AsyncTask;

import com.scorpio.dhwani.MainActivity;

/**
 * Created by Ayush Bansal on 02-02-2017.
 */

public class PlaySound extends AsyncTask<Double,Void,Void>
{

    @Override
    protected Void doInBackground(Double ...note) {
        final int duration = 100; // Seconds
        final int sampleRate = 22050; // Hz (maximum frequency is 7902.13Hz (B8))
        final int numSamples = duration * sampleRate;
        final double samples[] = new double[numSamples];
        final short buffer[] = new short[numSamples];

        for (int i = 0; i < numSamples; ++i) {
            samples[i] = Math.sin(2 * Math.PI * i / (sampleRate / note[0])); // Sine wave
            buffer[i] = (short) (samples[i] * Short.MAX_VALUE);  // Higher amplitude increases volume
        }

        final AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
                sampleRate, AudioFormat.CHANNEL_OUT_MONO,
                AudioFormat.ENCODING_PCM_16BIT, buffer.length,
                AudioTrack.MODE_STATIC);
        MainActivity.soundTrack = audioTrack;

        audioTrack.write(buffer, 0, buffer.length);
        audioTrack.play();
        return null;
    }
}