package ro.pub.cs.systems.eim.practicaltest01var08;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01Var02PlayActivity extends AppCompatActivity {

    EditText riddle2, answer2;
    Button check, back;
    String r;

    private IntentFilter intentFilter = new IntentFilter();

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, intent.getStringExtra("message"), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var02_play);

        intentFilter.addAction("1");

        riddle2 = findViewById(R.id.riddle2);
        answer2 = findViewById(R.id.answer2);
        check = findViewById(R.id.check);
        back = findViewById(R.id.back);

        Intent intent = this.getIntent();
        String a = intent.getStringExtra("a");
        r = intent.getStringExtra("r");
        riddle2.setText(r);

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (riddle2.getText().toString().equals(r)){
                    Log.d("wow", riddle2.getText().toString());
                    Log.d("wow", answer2.getText().toString());
                    Toast.makeText(PracticalTest01Var02PlayActivity.this, "Bine ba, bun raspuns", Toast.LENGTH_SHORT).show();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PracticalTest01Var02PlayActivity.this, PracticalTest01Var08MainActivity.class);
                PracticalTest01Var02PlayActivity.this.startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, PracticalTest01Var08Service.class);
        stopService(intent);
        super.onDestroy();
    }
}
