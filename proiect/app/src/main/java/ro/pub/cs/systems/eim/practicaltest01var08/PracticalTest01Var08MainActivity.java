package ro.pub.cs.systems.eim.practicaltest01var08;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01Var08MainActivity extends AppCompatActivity {

    EditText answer, riddle;
    Button play;
    boolean serviceStatus = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var08_main);

        answer = findViewById(R.id.answer);
        riddle = findViewById(R.id.riddle);
        play = findViewById(R.id.play);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = answer.getText().toString();
                String r = riddle.getText().toString();

                if (serviceStatus == false) {
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01Var08Service.class);
                    intent.putExtra("a", a);
                    getApplicationContext().startService(intent);
                    serviceStatus = true;
                }


                if (!a.isEmpty() && !r.isEmpty()){
                    Intent intent = new Intent(PracticalTest01Var08MainActivity.this, PracticalTest01Var02PlayActivity.class);
                    intent.putExtra("a", a);
                    intent.putExtra("r", r);
                    Log.d("wow", "am trimis");
                    PracticalTest01Var08MainActivity.this.startActivity(intent);
                }
            }
        });



    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null){
            String a = savedInstanceState.getString("a");
            String r = savedInstanceState.getString("r");
            if (r != null){
                riddle.setText(r);
            }
            else{
                riddle.setText("");
            }
            if (a != null){
                answer.setText(a);
            }
            else{
                answer.setText("");
            }

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("a", answer.getText().toString());
        outState.putString("r", riddle.getText().toString());
    }
}
