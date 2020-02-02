package com.noon.tawfiqyah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.noon.tawfiqyah.netwroksync.CheckInternetConnection;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private LottieAnimationView tv_no_item;
    private FirebaseRecyclerAdapter adapter;

    //Getting reference to Firebase Database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mDatabaseReference = database.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //check Internet Connection
        new CheckInternetConnection(this).checkConnection();


        //Initializing our Recyclerview
        recyclerView = findViewById(R.id.my_recycler_view);
        tv_no_item = findViewById(R.id.tv_no_cards);


        if (recyclerView != null) {
            //to enable optimization of recyclerview
            recyclerView.setHasFixedSize(true);
        }
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        fetch();
    }



    private void fetch() {
        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("Apartments");

        FirebaseRecyclerOptions<Apartment> options =
                new FirebaseRecyclerOptions.Builder<Apartment>()
                        .setQuery(query, new SnapshotParser<Apartment>() {
                            @NonNull
                            @Override
                            public Apartment parseSnapshot(@NonNull DataSnapshot snapshot) {
                                return new Apartment(
                                        snapshot.child("id").getValue().toString(),
                                        snapshot.child("apartmentImage").getValue().toString(),
                                        snapshot.child("apartmentDate").getValue().toString(),
                                        snapshot.child("apartmentPrice").getValue().toString(),
                                        snapshot.child("apartmentLocation").getValue().toString(),
                                        snapshot.child("apartmentNOBath").getValue().toString(),
                                        snapshot.child("apartmentNOBeds").getValue().toString(),
                                        snapshot.child("apartmentSpace").getValue().toString(),
                                        snapshot.child("apartmentNumber").getValue().toString(),
                                        snapshot.child("apartmentNote").getValue().toString(),
                                        snapshot.child("apartmentGPS").getValue().toString(),
                                        snapshot.child("apartmentFloor").getValue().toString(),
                                        snapshot.child("apartmentFinishing").getValue().toString(),
                                        snapshot.child("apartmentView").getValue().toString(),
                                        snapshot.child("apartmentPaymentMethod").getValue().toString(),
                                        snapshot.child("apartmentDescreption").getValue().toString());
                            }
                        })
                        .build();

        adapter = new FirebaseRecyclerAdapter<Apartment, ViewHolder>(options) {
            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.raw_items, parent, false);

                return new ViewHolder(view);
            }


            @Override
            protected void onBindViewHolder(ViewHolder holder, final int position, Apartment model) {
                if (tv_no_item.getVisibility() == View.VISIBLE) {
                    tv_no_item.setVisibility(View.GONE);
                }
                Picasso.get().load(model.getApartmentImage()).into(holder.apartment_image);
                holder.apartment_date.setText(model.getApartmentAddingDate());
                holder.apartment_price.setText(model.getApartmentPrice());
                holder.apartment_location.setText(model.getApartmentLocation());
                holder.apartment_space.setText(model.getApartmentSpace());
                holder.apartment_beds_number.setText(model.getApartmentNOBeds());
                holder.apartment_bath_number.setText(model.getApartmentNOBath());


                holder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this, ApartmentDetailes.class);
                        intent.putExtra("Apartment", getItem(position));
                        startActivity(intent);
                    }
                });
            }

        };
        recyclerView.setAdapter(adapter);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView apartment_image;
        TextView apartment_date;
        TextView apartment_price;
        TextView apartment_location;
        TextView apartment_space;
        TextView apartment_beds_number;
        TextView apartment_bath_number;
        View apartment_phone;
        View apartment_note;
        CardView cardView;
        View mView;

        public ViewHolder(View v) {
            super(v);
            mView = v;
            apartment_image = v.findViewById(R.id.apartment_image);
            apartment_date = v.findViewById(R.id.apartment_date);
            apartment_price = v.findViewById(R.id.apartment_price);
            apartment_location = v.findViewById(R.id.apartment_location);
            apartment_space = v.findViewById(R.id.apartment_space);
            apartment_beds_number = v.findViewById(R.id.apartment_beds_number);
            apartment_bath_number = v.findViewById(R.id.apartment_bath_number);
            apartment_phone = v.findViewById(R.id.apartment_phone);
            apartment_note = v.findViewById(R.id.apartment_note);
            cardView = v.findViewById(R.id.apartment_cardView);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
