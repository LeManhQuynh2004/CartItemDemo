package fpoly.quynhlmph32353.demofirebase.Dao;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import fpoly.quynhlmph32353.demofirebase.Model.Author;
import fpoly.quynhlmph32353.demofirebase.Model.Book;

public class AuthorDao {
    DatabaseReference databaseReference;

    public AuthorDao() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        // Chỉnh sửa đường dẫn, sử dụng "Authors" thay vì "Book"
        databaseReference = db.getReference("Authors");
    }

    // Thêm một tác giả mới vào cơ sở dữ liệu
    public Task<Void> add(Author author) {
//        String authorId = databaseReference.push().getKey();
//        author.setId(authorId);
        return databaseReference.push().setValue(author);
    }

    // Cập nhật thông tin tác giả
    public Task<Void> update(String key, HashMap<String, Object> hashMap) {
        // Chỉnh sửa đường dẫn để trỏ đúng vào tác giả cần cập nhật
        return databaseReference.child(key).updateChildren(hashMap);
    }

    // Xóa tác giả
    public Task<Void> delete(String key) {
        // Chỉnh sửa đường dẫn để trỏ đúng vào tác giả cần xóa
        return databaseReference.child(key).removeValue();
    }

    // Lấy tất cả các tác giả
    public Task<DataSnapshot> getAll() {
        return databaseReference.get();
    }
}
