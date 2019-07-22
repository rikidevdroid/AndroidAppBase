package com.example.amir.base.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amir.base.MVVM.models.CallBooking;
import com.example.amir.base.MVVM.models.DoctorsResponse;
import com.example.amir.base.MVVM.models.OldDoctors;
import com.example.amir.base.R;
import com.example.amir.base.dagger.module.RetrofitModule;
import com.example.amir.base.dagger.qualifier.ApplicationContext;
import com.example.amir.base.dagger.scopes.ApplicationScope;
import com.example.amir.base.retrofit.APIInterface;
import com.example.amir.base.utils.RetrofitClientInstance;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.Provides;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.amir.base.utils.Configurations.base_URL;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<DoctorsResponse.Result.Doctor> data;
    private RecyclerViewAdapter.ClickListener clickListener;

    private SharedPreferences sharedPreferences;
    private String patientsPhoneNumber;


    public APIInterface apiInterface;

    private Context context;

    @Inject
    public RecyclerViewAdapter(ClickListener clickListener) {
        this.clickListener = clickListener;
        data = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();


        sharedPreferences =  PreferenceManager
                .getDefaultSharedPreferences(context);

        apiInterface = RetrofitClientInstance.getRetrofitInstance().create(APIInterface.class);



        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.doctors_list_row, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.doctorName.setText(data.get(position).firstName + " "+ data.get(position).lastName);
        holder.doctorResaaCode.setText(data.get(position).subscriberNumber);
        holder.doctorSpecialty.setText(data.get(position).specialty.title);
        Picasso.get().load(base_URL+data.get(position).imagePath).placeholder(R.drawable.ic_person_black_24dp).error(R.drawable.ic_tag_faces_black_24dp).into(holder.doctorImage);

        holder.callDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sharedPreferences =  PreferenceManager
                        .getDefaultSharedPreferences(context);

                patientsPhoneNumber = sharedPreferences.getString("patientsPhoneNumber" , "");

                apiInterface.callBooking(data.get(position).subscriberNumber , patientsPhoneNumber).enqueue(new Callback<CallBooking>() {
                    @Override
                    public void onResponse(Call<CallBooking> call, Response<CallBooking> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(context,"Done!",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<CallBooking> call, Throwable t) {

                        Toast.makeText(context,t.getMessage(),Toast.LENGTH_LONG).show();

                    }
                });

            }
        });

    }

    @Override
    public int getItemCount() {
        if(data.size()>10){
            return 10;
        }else{
            return data.size();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.id_doctor_image)
        public CircleImageView doctorImage;

        @BindView(R.id.id_doctor_name)
        public TextView doctorName;

        @BindView(R.id.id_resaa_code)
        public TextView doctorResaaCode;

        @BindView(R.id.id_doctor_specialty)
        public TextView doctorSpecialty;

        @BindView(R.id.id_call_doctor)
        public ImageView callDoctor;




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

    public void setData(List<DoctorsResponse.Result.Doctor> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }
}
