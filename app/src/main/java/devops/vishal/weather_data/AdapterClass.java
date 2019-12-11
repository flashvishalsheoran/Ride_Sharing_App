package devops.vishal.weather_data;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.ViewHolder> {

    private List<TempData> list_data;

    public AdapterClass(List<TempData> data) {
        this.list_data = data;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        TempData Data=list_data.get(i);

        holder.fromaddr.setText(Data.getFrom());
        holder.toaddr.setText(Data.getTo());
        holder.fromdatetime.setText(Data.getFrom_time());
        holder.todatetime.setText(Data.getTo_time());
        holder.currency.setText(Data.getCurrency_symbol());

        Log.d("----ADAPTER----", String.valueOf(Data.getValue()));

        holder.totalamount.setText(Data.getValue());
        holder.totalridetime.setText(Data.getTrip_duration_in_mins());


    }


    @Override
    public int getItemCount() {
        return list_data.size();
    }






    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView fromaddr;
        public TextView toaddr;
        public TextView fromdatetime;
        public TextView todatetime;
        public TextView currency;
        public TextView totalamount;
        public TextView totalridetime;



        public ViewHolder(View itemView) {
            super(itemView);
            fromaddr =  itemView.findViewById(R.id.fromaddr);
            toaddr =  itemView.findViewById(R.id.toaddr);
            fromdatetime =  itemView.findViewById(R.id.fromdatetime);
            todatetime =  itemView.findViewById(R.id.totimedate);
            currency =  itemView.findViewById(R.id.currency);
            totalamount =  itemView.findViewById(R.id.totalamount);
            totalridetime =  itemView.findViewById(R.id.totalridetime);




        }

    }




}
