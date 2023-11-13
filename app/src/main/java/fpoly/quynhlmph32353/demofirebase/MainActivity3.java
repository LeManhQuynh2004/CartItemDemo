package fpoly.quynhlmph32353.demofirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        findViewById(R.id.bt_user).setOnClickListener(view -> {
            startActivity(new Intent(this,MainActivity.class));
        });
        findViewById(R.id.bt_post).setOnClickListener(view -> {
            startActivity(new Intent(this,MainActivity2.class));
        });
    }
}