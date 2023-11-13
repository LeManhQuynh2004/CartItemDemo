package fpoly.quynhlmph32353.demofirebase.Dao;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import fpoly.quynhlmph32353.demofirebase.Model.Book;

public class BookDao {
    DatabaseReference databaseReference;

    public BookDao() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference("Book");
    }
    public Task<Void> add(Book book){
//        String bookId = databaseReference.push().getKey();
//        book.setId(bookId);
        return databaseReference.push().setValue(book);
    }
    public Task<Void> update(String key , HashMap<String, Object> hashMap ){
        return databaseReference.child(key).updateChildren(hashMap);
    }
    public Task<Void> delete(String key) {
        return databaseReference.child(key).removeValue();
    }
    public Task<DataSnapshot> getAll(){
        return databaseReference.get();
    }
}
