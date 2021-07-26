package vloboda.tarantulaProject.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TarantulaAdapter extends RecyclerView.Adapter<TarantulaAdapter.MyViewHolder> {

    Context context;
    ArrayList<Tarantula> list;



    public TarantulaAdapter(Context context, ArrayList<Tarantula> list) {
        this.context = context;
        this.list = list;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.tarantula,parent, false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Tarantula tarantula = list.get(position);

            holder.species.setText(tarantula.species);
            holder.name.setText(tarantula.name);
            holder.origin.setText(tarantula.origin);
            holder.tarantula = tarantula;


        switch((int)tarantula.temper) {
            case 1:
                holder.temper1.setChecked(true);
                break;
            case 2:
                holder.temper2.setChecked(true);
                break;
            case 3:
                holder.temper3.setChecked(true);
                break;
            case 4:
                holder.temper4.setChecked(true);
                break;
            case 5:
                holder.temper5.setChecked(true);
                break;
            case 6:
                holder.temper6.setChecked(true);
                break;
        }

        switch((int)tarantula.venom){
            case 1:
                holder.venom1.setChecked(true);
                break;
            case 2:
                holder.venom2.setChecked(true);
                break;
            case 3:
                holder.venom3.setChecked(true);
                break;



        }

            if (tarantula.hairs == 1) {
                holder.hairs.setChecked(true);
            }



            if(!TextUtils.isEmpty(tarantula.imgName)) {

                FirebaseStorage fStorage = FirebaseStorage.getInstance();
                StorageReference refImage = fStorage.getReference()
                        .child("tarantulas")
                        .child(tarantula.imgName);

                refImage.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(holder.imageView);
                    }
                });


            }




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView species, name, origin;
        RadioGroup temper, venom;
        RadioButton temper1,temper2,temper3,temper4,temper5,temper6, venom1, venom2, venom3;
        CheckBox hairs;
        ImageView imageView;
        Tarantula tarantula;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);



            species = itemView.findViewById(R.id.tvSpecies);
            name = itemView.findViewById(R.id.tvName);
            origin = itemView.findViewById(R.id.tvOrigin);
            temper = itemView.findViewById(R.id.temperGroup);
            venom = itemView.findViewById(R.id.venomGroup);
            hairs = itemView.findViewById(R.id.urticatingCheckBox);
            imageView = itemView.findViewById(R.id.tarantulaImage);

            temper1 = itemView.findViewById(R.id.temper1);
            temper2 = itemView.findViewById(R.id.temper2);
            temper3 = itemView.findViewById(R.id.temper3);
            temper4 = itemView.findViewById(R.id.temper4);
            temper5 = itemView.findViewById(R.id.temper5);
            temper6 = itemView.findViewById(R.id.temper6);

            venom1 = itemView.findViewById(R.id.venom1);
            venom2 = itemView.findViewById(R.id.venom2);
            venom3 = itemView.findViewById(R.id.venom3);


            itemView.findViewById(R.id.RW_deletebtn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
                    builder.setTitle("Do you want to delete this tarantula?");

                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //DELETE FROM FIRESTORE AND FIRESTORAGE
                            String userID = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
                            try {
                                if (tarantula.imgName.isEmpty()) {
                                    throw new Exception();
                                    }
                                else{
                                    FirebaseStorage.getInstance().getReference()
                                            .child("tarantulas")
                                            .child(tarantula.imgName).delete();


                                }
                            }catch (Exception e){
                                Log.e("",e.getMessage());

                            }

                                FirebaseFirestore.getInstance().collection("users").document(userID)
                                        .collection("tarantulas").document(tarantula.getTarantulaID()).delete();


                            itemView.getContext().startActivity(new Intent(itemView.getContext(), Tarantulas.class));
                            //startActivity(new Intent(getApplicationContext(),Tarantulas.class));
                            Toast.makeText(itemView.getContext(), "Succesfully deleted tarantula" , Toast.LENGTH_SHORT).show();

                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog ad = builder.create();
                    ad.show();
                }
            });

        }




    }

}
