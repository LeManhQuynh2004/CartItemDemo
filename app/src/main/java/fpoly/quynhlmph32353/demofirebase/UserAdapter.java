package fpoly.quynhlmph32353.demofirebase;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import fpoly.quynhlmph32353.demofirebase.Dao.UserDao;
import fpoly.quynhlmph32353.demofirebase.Model.User;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    Context context;
    ArrayList<User> list;
    UserDao userDao;

    EditText ed_username,ed_password;
    public UserAdapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
        userDao = new UserDao();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.tv_username.setText(list.get(position).getUsername());
        holder.tv_password.setText(list.get(position).getPassword());
        holder.itemView.setOnClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(context, view);
            popupMenu.getMenuInflater().inflate(R.menu.item_menu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(item ->{
                if(item.getItemId() == R.id.menu_delete){
                    DeleteItem(position);
                }else{
                    UpdateItem(position);
                }
                return false;
            });
            popupMenu.show();
        });
    }

    private void UpdateItem(int position) {
        User user = list.get(position);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_user,null);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();

        ed_username = dialogView.findViewById(R.id.ed_update_username);
        ed_password = dialogView.findViewById(R.id.ed_update_password);

        ed_username.setText(user.getUsername());
        ed_password.setText(user.getPassword());

        dialogView.findViewById(R.id.btn_Update).setOnClickListener(view -> {
            String username = ed_username.getText().toString().trim();
            String password = ed_password.getText().toString().trim();
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("username", username);
            hashMap.put("password", password);
            user.setUsername(username);
            user.setPassword(password);
            Log.d("TAG", "UpdateItem: "+user.getKey());
            if(user.getKey() != null){
                userDao.update(user.getKey(), hashMap).addOnSuccessListener(suc -> {
                    Toast.makeText(context, "update success", Toast.LENGTH_SHORT).show();
                    list.set(position,user);
                    notifyDataSetChanged();
                    alertDialog.dismiss();
                }).addOnFailureListener(er -> {
                    Toast.makeText(context, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
        });
        alertDialog.show();
    }

    private void DeleteItem( int position){
        User user = list.get(position);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete");
        builder.setMessage("Do you want delete item ?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               if(user.getKey() != null){
                   userDao.delete(user.getKey()).addOnSuccessListener(suc ->{
                       Toast.makeText(context, "Delete Success", Toast.LENGTH_SHORT).show();
                       list.remove(user);
                       notifyDataSetChanged();
                   }).addOnFailureListener(er -> {
                       Toast.makeText(context, er.getMessage()+"", Toast.LENGTH_SHORT).show();
                   });
               }
            }
        });
        builder.setNegativeButton("CanCle",null);
        builder.show();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        TextView tv_username, tv_password;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_username = itemView.findViewById(R.id.tv_username);
            tv_password = itemView.findViewById(R.id.tv_password);
        }
    }
}
