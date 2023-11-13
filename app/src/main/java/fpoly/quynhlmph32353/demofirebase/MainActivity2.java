package fpoly.quynhlmph32353.demofirebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

import fpoly.quynhlmph32353.demofirebase.Dao.UserDao;
import fpoly.quynhlmph32353.demofirebase.Model.User;

public class MainActivity2 extends AppCompatActivity {

    RecyclerView recyclerView;
    EditText ed_title, ed_content;
    Spinner spinner;
    UserDao userDao;
    ArrayList<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        userDao = new UserDao();
        spinner = findViewById(R.id.spinner);

        // Bạn có thể gọi setAdapter ở đây để đảm bảo rằng Spinner đã có dữ liệu khi hiển thị
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                list
        );
        findViewById(R.id.btn_Out_User).setOnClickListener(view -> {
            finish();
        });
        findViewById(R.id.btn_Add_User).setOnClickListener(view -> {

        });
        spinner.setAdapter(adapter);

        // Gọi hàm selectAll để lấy dữ liệu từ Firebase
        selectAll();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity2.this, list.get(i) + "", Toast.LENGTH_SHORT).show();
                Log.d("TAG", "onItemSelected: " + i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void selectAll() {
        userDao.getAll().addOnSuccessListener(dataSnapshot -> {
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                User user_data = snapshot.getValue(User.class);
                if (user_data != null) {
                    String key = user_data.getKey();
                    list.add(key);
                }
            }

            // Bạn có thể gọi notifyDataSetChanged() để thông báo cho Adapter cập nhật dữ liệu
            ArrayAdapter<String> adapter = (ArrayAdapter<String>) spinner.getAdapter();
            adapter.notifyDataSetChanged();
        }).addOnFailureListener(er -> {
            Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }
}
