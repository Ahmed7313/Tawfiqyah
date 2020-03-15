package com.noon.tawfiqyah.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.noon.tawfiqyah.FilterDialogs.FilterDialog;
import com.noon.tawfiqyah.FilterDialogs.PriceFilter;
import com.noon.tawfiqyah.R;
import com.noon.tawfiqyah.netwroksync.CheckInternetConnection;
import com.noon.tawfiqyah.pojo.Apartment;
import com.noon.tawfiqyah.usersession.UserSession;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements PriceFilter.PriceSheetListner {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private LottieAnimationView tv_no_item;
//    private FirebaseRecyclerAdapter adapter;
    private UserSession session;
    private DatabaseReference mDatabaseReference;
    private FirebaseAuth mAuth;
    private DatabaseReference userRef;
    private FirebaseFirestore mFireStore;
    private FirebaseUser user;
    private int heartAnimationIsPressed = 0;
    private ImageView searchApartment;
    ArrayList<Apartment> apartmentList;
    ApartmentAdapter adapter;
    private View priceFilter;

    private String priceMinValue;
    private String priceMaxValue;
    private boolean priceFilterIsClicked;

    Apartment apartment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //check Internet Connection
        new CheckInternetConnection(this).checkConnection();


        //Initializing our Recyclerview
        recyclerView = findViewById(R.id.my_recycler_view);
        tv_no_item = findViewById(R.id.tv_no_cards);
        searchApartment = findViewById(R.id.search_apartment);

        if (recyclerView != null) {
            //to enable optimization of recyclerview
            recyclerView.setHasFixedSize(true);
        }
        apartmentList = new ArrayList<Apartment>();

        mAuth = FirebaseAuth.getInstance();
        //get firebase instance
        //initializing database reference
        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Apartments");
        //SharedPreference for Cart Value
        session = new UserSession(getApplicationContext());

        //validating session
        session.isLoggedIn();

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        fetch();

        searchApartment.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                FilterDialog filterDialog = new FilterDialog();
                filterDialog.show(getSupportFragmentManager(), "testClass");
            }

        });
        priceFilter = findViewById(R.id.price_filter);
        priceFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PriceFilter priceFilter = new PriceFilter();
                priceFilter.show(getSupportFragmentManager(), "Price Filter");
            }
        });
    }

    private void fetch (){

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshots: dataSnapshot.getChildren()){
                    Apartment apartment= dataSnapshots.getValue(Apartment.class);
                    apartmentList.add(apartment);
                }
                adapter = new ApartmentAdapter(apartmentList, MainActivity.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onButtomClicked(String minValue, String maxValue, boolean filterGotClicked) {
        priceMinValue = minValue;
        priceMaxValue = maxValue;
        priceFilterIsClicked = filterGotClicked;

        if (priceFilterIsClicked){
            mDatabaseReference.orderByChild("apartmentPrice").startAt(priceMinValue).
                    endAt(priceMaxValue).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    adapter.clear();
                    recyclerView.setAdapter(null);
                    for (DataSnapshot dataSnapshots: dataSnapshot.getChildren()){
                        Apartment apartment= dataSnapshots.getValue(Apartment.class);
                        apartmentList.add(apartment);
                    }
                    adapter = new ApartmentAdapter(apartmentList, MainActivity.this);
                    recyclerView.setAdapter(adapter);
                    priceFilterIsClicked = false;
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        } else {
            fetch();
        }
    }
}
