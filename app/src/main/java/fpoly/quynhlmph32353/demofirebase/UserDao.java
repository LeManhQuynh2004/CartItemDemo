package fpoly.quynhlmph32353.demofirebase;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

public class UserDao {
    DatabaseReference databaseReference;
    public UserDao() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference("User");
    }
    public Task<Void> add(User user){
        return databaseReference.push().setValue(user);
    }
    public  Task<Void> update(String key , HashMap<String, Object> hashMap ){
        return databaseReference.child(key).updateChildren(hashMap);
    }
    public Task<Void> delete(String key) {
        return databaseReference.child(key).removeValue();
    }
    public Task<DataSnapshot> getAll(){
        return databaseReference.get();
    }
}
