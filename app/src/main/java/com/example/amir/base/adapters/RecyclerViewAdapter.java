package com.example.amir.base.adapters;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.amir.base.MVVM.models.Doctors;
import com.example.amir.base.R;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<Doctors.Doctor> data;
    private RecyclerViewAdapter.ClickListener clickListener;

    @Inject
    public RecyclerViewAdapter(ClickListener clickListener) {
        this.clickListener = clickListener;
        data = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_list_row, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtName.setText(data.get(position).firstName);
        holder.txtBirthYear.setText(data.get(position).lastName);
    }

    @Override
    public int getItemCount() {
        System.out.println(data.size()+"AAAAAAAAAAAAAAAAAAAAAAAAAAA");
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtName)
        public TextView txtName;

        @BindView(R.id.txtBirthYear)
        public TextView txtBirthYear;

        @BindView(R.id.constraintLayout)
        public ConstraintLayout constraintLayoutContainer;

        ViewHolder(View itemView) {
            super(itemView);

//            txtName = itemView.findViewById(R.id.txtName);
//            txtBirthYear = itemView.findViewById(R.id.txtBirthYear);
//            constraintLayoutContainer = itemView.findViewById(R.id.constraintLayout);

            ButterKnife.bind(this,itemView);



//            constraintLayoutContainer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                clickListener.launchIntent(data.get(getAdapterPosition()).films.get(0));
//            }
//        });
    }

//        @OnClick(R.id.constraintLayout)
//        public void onClick(View v) {
//            clickListener.launchIntent(data.get(getAdapterPosition()).films.get(0));
//        }
    }

    public interface ClickListener {
        void launchIntent(String filmName);
    }

    public void setData(List<Doctors.Doctor> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }
}
