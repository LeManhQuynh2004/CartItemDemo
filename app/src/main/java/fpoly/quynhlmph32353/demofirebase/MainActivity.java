package fpoly.quynhlmph32353.demofirebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    EditText ed_username, ed_password;
    UserDao userDao;
    ArrayList<User> list = new ArrayList<>();
    RecyclerView recyclerView_User;
    UserAdapter userAdapter;

    String username, password;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseReference = FirebaseDatabase.getInstance().getReference("User");
        userDao = new UserDao();
        ed_username = findViewById(R.id.ed_username);
        ed_password = findViewById(R.id.ed_password);
        recyclerView_User = findViewById(R.id.RecyclerView_User);
        userDao.getAll().addOnSuccessListener(dataSnapshot -> {
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                User user_date = snapshot.getValue(User.class);
                user_date.setKey(snapshot.getKey());
                if (user_date != null) {
                    list.add(user_date);
                    userAdapter.notifyDataSetChanged();
                }
            }
        }).addOnFailureListener(er -> {
            Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
        });
        userAdapter = new UserAdapter(this, list);
        recyclerView_User.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_User.setAdapter(userAdapter);

        findViewById(R.id.btn_login).setOnClickListener(view -> {
            username = ed_username.getText().toString().trim();
            password = ed_password.getText().toString().trim();
            User user = new User(username, password);
            userDao.add(user).addOnSuccessListener(suc -> {
                Toast.makeText(this, "Add success", Toast.LENGTH_SHORT).show();
                list.add(user);
                ed_username.setText("");
                ed_password.setText("");
                userAdapter.notifyDataSetChanged();
            }).addOnFailureListener(er -> {
                Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
            });
        });
//        findViewById(R.id.btn_Delete).setOnClickListener(view -> {
//            userDao.delete(username.).addOnSuccessListener(suc -> {
//                Toast.makeText(this, "Delete success", Toast.LENGTH_SHORT).show();
//            }).addOnFailureListener(er -> {
//                Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
//            });
//        });
//        findViewById(R.id.u).setOnClickListener(view -> {
//            HashMap<String, Object> hashMap = new HashMap<>();
//            hashMap.put("username", username);
//            hashMap.put("password", password);
//            userDao.update("-NiEK1C3Duodtw-ENWdA", hashMap).addOnSuccessListener(suc -> {
//                Toast.makeText(this, "update success", Toast.LENGTH_SHORT).show();
//            }).addOnFailureListener(er -> {
//                Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
//            });
//        });
    }
}