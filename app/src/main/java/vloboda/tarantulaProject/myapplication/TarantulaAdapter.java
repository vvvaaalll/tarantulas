package vloboda.tarantulaProject.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

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

        //Picasso.get().load(tarantula.imgURL).into(holder.imageView);      NE RADI, GASI APK


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

        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            species = itemView.findViewById(R.id.tvSpecies);
            name = itemView.findViewById(R.id.tvName);
            origin = itemView.findViewById(R.id.tvOrigin);
            temper = itemView.findViewById(R.id.temperGroup);
            venom = itemView.findViewById(R.id.venomGroup);
            hairs = itemView.findViewById(R.id.urticatingCheckBox);
            imageView = itemView.findViewById(R.id.imageView);

            temper1 = itemView.findViewById(R.id.temper1);
            temper2 = itemView.findViewById(R.id.temper2);
            temper3 = itemView.findViewById(R.id.temper3);
            temper4 = itemView.findViewById(R.id.temper4);
            temper5 = itemView.findViewById(R.id.temper5);
            temper6 = itemView.findViewById(R.id.temper6);

            venom1 = itemView.findViewById(R.id.venom1);
            venom2 = itemView.findViewById(R.id.venom2);
            venom3 = itemView.findViewById(R.id.venom3);

        }


    }

}
