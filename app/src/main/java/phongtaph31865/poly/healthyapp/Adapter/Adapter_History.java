package phongtaph31865.poly.healthyapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import phongtaph31865.poly.healthyapp.Model.History;
import phongtaph31865.poly.healthyapp.R;

public class Adapter_History extends RecyclerView.Adapter<Adapter_History.ViewHolder> {
    private List<History> histories;
    public Adapter_History(List<History> histories){
        this.histories = histories;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        History history = histories.get(position);
        holder.id.setText(history.get_id());
        holder.time.setText(history.getTime());
        holder.date.setText(history.getDate());
    }

    @Override
    public int getItemCount() {
        if (histories != null){
            return histories.size();
        }
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView id, time, date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.tv_item_id_history);
            time = itemView.findViewById(R.id.tv_item_time_his);
            date = itemView.findViewById(R.id.tv_item_date_his);
        }
    }
}
