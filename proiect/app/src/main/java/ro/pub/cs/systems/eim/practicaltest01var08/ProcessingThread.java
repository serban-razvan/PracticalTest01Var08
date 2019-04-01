package ro.pub.cs.systems.eim.practicaltest01var08;


import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Random;

public class ProcessingThread extends Thread {

    private Context context = null;
    private String a;
    private boolean isRunning = true;
    private Random random = new Random();

    public ProcessingThread(Context context, String a){
        this.context = context;
        this.a = a;
    }

    @Override
    public void run() {
        while (isRunning) {
            sendMessage();
            sleep();
        }
    }

    private void sendMessage() {
        Intent intent = new Intent();

        intent.setAction("1");

        int q = random.nextInt(a.length()) ;
        StringBuilder x = new StringBuilder();
        for (int i =0;i<a.length();i++){
            if (i == q){
                x.append(a.charAt(q));
            }
            else{
                x.append("*");
            }
        }

        intent.putExtra("message", x.toString());
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }


}
