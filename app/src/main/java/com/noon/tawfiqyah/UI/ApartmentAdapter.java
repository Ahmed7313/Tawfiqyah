package com.noon.tawfiqyah.UI;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.noon.tawfiqyah.R;
import com.noon.tawfiqyah.pojo.Apartment;
import com.noon.tawfiqyah.usersession.UserSession;
import com.squareup.picasso.Picasso;
import com.webianks.easy_feedback.EasyFeedback;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ApartmentAdapter extends RecyclerView.Adapter<ApartmentAdapter.ViewHolder> {

    private int heartAnimationIsPressed = 0;
    private FirebaseAuth mAuth;
    private DatabaseReference userRef;
    private FirebaseFirestore mFireStore;
    private DatabaseReference mDatabaseReference;
    private FirebaseUser user;
    private Context context;
    private UserSession session;
    private ArrayList<Apartment> apartmentList = new ArrayList<>();

    public ApartmentAdapter(ArrayList<Apartment> apartmentList, Context context){
        this.apartmentList = apartmentList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.raw_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Picasso.get().load(apartmentList.get(position).getApartmentImage()).into(holder.apartment_image);
        holder.apartment_date.setText(apartmentList.get(position).getApartmentAddingDate());
        holder.apartment_price.setText(apartmentList.get(position).getApartmentPrice());
        holder.apartment_location.setText(apartmentList.get(position).getApartmentLocation());
        holder.apartment_space.setText(apartmentList.get(position).getApartmentSpace());
        holder.apartment_beds_number.setText(apartmentList.get(position).getApartmentNOBeds());
        holder.apartment_bath_number.setText(apartmentList.get(position).getApartmentNOBath());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ApartmentDetailes.class);
                intent.putExtra("Apartment", apartmentList.get(position));
                context.startActivity(intent);
            }
        });

        holder.apartment_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                String message = "tel:" + apartmentList.get(position).getApartmentNumber();
                intent.setData(Uri.parse(message));
                context.startActivity(intent);
            }
        });

        holder.addToWishList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                mAuth = FirebaseAuth.getInstance();
                //get firebase instance
                //initializing database reference
                mDatabaseReference = FirebaseDatabase.getInstance().getReference();
                //SharedPreference for Cart Value
                session = new UserSession(context);
                if (heartAnimationIsPressed == 0){
                    holder.heartAnimation.playAnimation();
                    String currentUserID = mAuth.getCurrentUser().getUid();
                    mDatabaseReference.child("Users").child(currentUserID).child("wishList").push()
                            .setValue(getProductObject(apartmentList.get(position)));
                    session.increaseWishlistValue();
                    heartAnimationIsPressed = 1;
                }else if (heartAnimationIsPressed == 1){
                    holder.heartAnimation.setProgress(0);
                    String currentUserID = mAuth.getCurrentUser().getUid();
                    mDatabaseReference.child("Users").child(currentUserID).child("wishList").setValue("");
                    heartAnimationIsPressed = 0;
                }
            }
        });

        holder.apartment_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new EasyFeedback.Builder(context)
                        .withEmail("ahmedrabie7313@gmail.com")
                        .withSystemInfo()
                        .build()
                        .start();
            }
        });

        holder.shareApartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "شاليه لقطة " + apartmentList.get(position).getApartmentDescreption() + "على تطبيق التوفيقية للعقارات";
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                context.startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });

    }

    @Override
    public int getItemCount() {
        return apartmentList.size();
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
        View addToWishList;
        LottieAnimationView heartAnimation;
        View shareApartment;


        public ViewHolder(View v) {
            super(v);
            context = v.getContext();
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
            addToWishList = v.findViewById(R.id.apartment_wishList);
            heartAnimation = v.findViewById(R.id.add_to_wishlist);
            shareApartment = v.findViewById(R.id.share_apartment);

        }
    }

    public void clear() {
        int size = apartmentList.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                apartmentList.remove(0);
            }

            notifyItemRangeRemoved(0, size);
        }
    }

    public Apartment getProductObject(Apartment model) {
        return new Apartment(
                model.getApartmentId(),
                model.getApartmentImage(),
                model.getApartmentAddingDate(),
                model.getApartmentPrice(),
                model.getApartmentLocation(),
                model.getApartmentNOBath(),
                model.getApartmentNOBeds(),
                model.getApartmentSpace(),
                model.getApartmentNumber(),
                model.getApartmentNote(),
                model.getApartmentGPS(),
                model.getApartmentFloor(),
                model.getApartmentFinishing(),
                model.getApartmentView(),
                model.getApartmentPaymentMethod(),
                model.getApartmentDescreption(),
                model.getApartmentSaleOrRent());

    }
}