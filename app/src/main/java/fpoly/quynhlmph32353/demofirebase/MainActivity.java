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

import fpoly.quynhlmph32353.demofirebase.Dao.AuthorDao;
import fpoly.quynhlmph32353.demofirebase.Dao.BookDao;
import fpoly.quynhlmph32353.demofirebase.Dao.UserDao;
import fpoly.quynhlmph32353.demofirebase.Model.Author;
import fpoly.quynhlmph32353.demofirebase.Model.Book;
import fpoly.quynhlmph32353.demofirebase.Model.User;

public class MainActivity extends AppCompatActivity {

    private BookDao bookDao;
    private AuthorDao authorDao;
    RecyclerView recyclerView;
    UserDao dao;
    ArrayList<User> list = new ArrayList<>();
    UserAdapter userAdapter;
    EditText ed_username, ed_password;

    FirebaseDatabase database;
    DatabaseReference usersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed_username = findViewById(R.id.ed_username);
        ed_password = findViewById(R.id.ed_password);
        dao = new UserDao();
        recyclerView = findViewById(R.id.RecyclerView);
        selectAll();
        database = FirebaseDatabase.getInstance();
        usersRef = database.getReference("User");
        userAdapter = new UserAdapter(this, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(userAdapter);

        findViewById(R.id.btn_Out_User).setOnClickListener(view -> {
            finish();
        });
        findViewById(R.id.btn_Add_User).setOnClickListener(view -> {
            String username = ed_username.getText().toString().trim();
            String password = ed_password.getText().toString().trim();
            User newUser = new User(username, password);

            DatabaseReference newUserRef = usersRef.push();
            String userId = newUserRef.getKey();
            newUser.setKey(userId);
            newUserRef.setValue(newUser).addOnSuccessListener(suc -> {
                Toast.makeText(this, "Add Success", Toast.LENGTH_SHORT).show();
                list.add(newUser);
                userAdapter.notifyDataSetChanged();
            }).addOnFailureListener(er -> {
                Toast.makeText(this, er.getMessage() + "", Toast.LENGTH_SHORT).show();
            });
        });
    }
    private void selectAll() {
        dao.getAll().addOnSuccessListener(dataSnapshot -> {
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                User user_date = snapshot.getValue(User.class);
                if (user_date != null) {
                    list.add(user_date);
                    userAdapter.notifyDataSetChanged();
                }
            }
        }).addOnFailureListener(er -> {
            Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }
}
