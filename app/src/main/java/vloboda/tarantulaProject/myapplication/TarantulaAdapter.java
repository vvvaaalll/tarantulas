package vloboda.tarantulaProject.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        holder.species.setText(tarantula.mSpecies);
        holder.name.setText(tarantula.mName);
        holder.origin.setText(tarantula.mOrigin);
       /* holder.temper.setText(tarantula.getmTemper());
        holder.venom.setText(tarantula.getmVenom());
        holder.hairs.setText(tarantula.getmHairs());*/
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView species, name, origin;
       // RadioButton temper, venom;
        //CheckBox hairs;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            species = itemView.findViewById(R.id.tvSpecies);
            name = itemView.findViewById(R.id.tvName);
            origin = itemView.findViewById(R.id.tvOrigin);
           /* temper = itemView.findViewById(R.id.temperGroup);
            venom = itemView.findViewById(R.id.venomGroup);
            hairs = itemView.findViewById(R.id.urticatingCheckBox);*/

        }


    }

}
