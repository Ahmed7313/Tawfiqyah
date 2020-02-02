package com.noon.tawfiqyah;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.smarteist.autoimageslider.SliderViewAdapter;

import org.w3c.dom.Text;

import java.util.List;

public class ApartmentDetailes extends AppCompatActivity {

    @BindView(R.id.apartment_date_details)
    TextView apartmentDate;
    @BindView(R.id.apartment_price_details)
    TextView apartmentPrice;
    @BindView(R.id.apartment_location_details)
    TextView apartmentLocation;
    @BindView(R.id.apartment_space_details)
    TextView apartmentSpace;
    @BindView(R.id.apartment_beds_number_details)
    TextView apartmentBedsN;
    @BindView(R.id.apartment_bath_number_details)
    TextView apartmentBathN;
    @BindView(R.id.show_location_on_map)
    TextView showLocationOnMap;
    @BindView(R.id.apartment_floor)
    TextView apartmentFloor;
    @BindView(R.id.apartment_finish)
    TextView apartmentFinish;
    @BindView(R.id.apartment_view)
    TextView apartmentView;
    @BindView(R.id.apartment_payment_method)
    TextView apartmentPaymentM;
    @BindView(R.id.expandable_text)
    TextView apartmentDesc;
    @BindView(R.id.apartment_image_slider)
    SliderView sliderView;
    @BindView(R.id.add_to_wishlist_details)
    LottieAnimationView addToWishlist;
    Apartment apartment;
    private FirebaseAuth mAuth;
    private DatabaseReference userRef;
    private FirebaseFirestore mFireStore;
    private FirebaseUser user;
    private DatabaseReference mDatabaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apartment_detailes);
        ButterKnife.bind(this);

        apartment = (Apartment) getIntent().getSerializableExtra("Apartment");

        mAuth = FirebaseAuth.getInstance();
        userRef = FirebaseDatabase.getInstance().getReference().child("Users");
        mFireStore = FirebaseFirestore.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();


        instantiateTheSlider();

        apartmentDate.setText(apartment.getApartmentAddingDate());
        apartmentPrice.setText(apartment.getApartmentPrice());
        apartmentLocation.setText(apartment.getApartmentLocation());
        apartmentSpace.setText(apartment.getApartmentSpace());
        apartmentBedsN.setText(apartment.getApartmentNOBeds());
        apartmentBathN.setText(apartment.getApartmentNOBath());
        apartmentFloor.setText(apartment.getApartmentFloor());
        apartmentFinish.setText(apartment.getApartmentFinishing());
        apartmentView.setText(apartment.getApartmentView());
        apartmentPaymentM.setText(apartment.getApartmentPaymentMethod());
        apartmentDesc.setText(apartment.getApartmentDescreption());

        showLocationOnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mape view show the exact location of the place using intent
                Uri location = Uri.parse(apartment.getApartmentGPS());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);

                // Verify it resolves
                PackageManager packageManager = getPackageManager();
                List<ResolveInfo> activities = packageManager.queryIntentActivities(mapIntent, 0);
                boolean isIntentSafe = activities.size() > 0;

                // Start an activity if it's safe
                if (isIntentSafe) {
                    startActivity(mapIntent);
                }
            }
        });
    }

    private void instantiateTheSlider (){
        SliderAdapterExample adapter = new SliderAdapterExample(this);

        sliderView.setSliderAdapter(adapter);

        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();
    }

    public class SliderAdapterExample extends SliderViewAdapter<SliderAdapterExample.SliderAdapterVH> {

        private Context context;

        public SliderAdapterExample(Context context) {
            this.context = context;
        }

        @Override
        public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_item, null);
            return new SliderAdapterVH(inflate);
        }

        @Override
        public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {

            switch (position) {
                case 0:
                    Glide.with(viewHolder.itemView)
                            .load(apartment.getApartmentImage())
                            .into(viewHolder.imageViewBackground);
                    break;
                case 1:
                    Glide.with(viewHolder.itemView)
                            .load("https://images.pexels.com/photos/747964/pexels-photo-747964.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260")
                            .into(viewHolder.imageViewBackground);
                    break;
                case 2:
                    Glide.with(viewHolder.itemView)
                            .load("https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")
                            .into(viewHolder.imageViewBackground);
                    break;
                default:
                    Glide.with(viewHolder.itemView)
                            .load("https://images.pexels.com/photos/218983/pexels-photo-218983.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")
                            .into(viewHolder.imageViewBackground);
                    break;

            }

        }

        @Override
        public int getCount() {
            //slider view count could be dynamic size
            return 4;
        }

        class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

            View itemView;
            ImageView imageViewBackground;

            public SliderAdapterVH(View itemView) {
                super(itemView);
                imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
                //textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider);
                this.itemView = itemView;
            }
        }
    }



    public void addToWishList(View view) {

        addToWishlist.playAnimation();
        String currentUserID = mAuth.getCurrentUser().getUid();
        //mDatabaseReference.child("Users").child(currentUserID).child("wishList").push().setValue(getProductObject());
    }
}
