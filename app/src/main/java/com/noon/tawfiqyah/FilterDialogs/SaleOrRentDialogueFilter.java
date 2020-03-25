package com.noon.tawfiqyah.FilterDialogs;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.noon.tawfiqyah.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SaleOrRentDialogueFilter extends BottomSheetDialogFragment {

    SaleOrRent saleOrRent;
    private View forSale;
    private View forRent;
    ImageView checkMarkSale, checkMarkRent;
    String choice = "sale";



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sale_rent_filter, container, false);

        forSale = view.findViewById(R.id.filter_for_sale);
        forRent = view.findViewById(R.id.filter_for_rent);
        checkMarkSale = view.findViewById(R.id.check_mark_sale);
        checkMarkRent = view.findViewById(R.id.check_mark_rent);

        if (choice.equals("rent")){
            checkMarkRent.setVisibility(View.VISIBLE);
            checkMarkSale.setVisibility(View.GONE);
        }else {
            checkMarkSale.setVisibility(View.VISIBLE);
            checkMarkRent.setVisibility(View.GONE);
        }


        forSale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choice = "sale";
                saleOrRent.onSaleOrRentChoosing(choice);
                dismiss();
            }
        });

        forRent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choice = "rent";
                saleOrRent.onSaleOrRentChoosing(choice);
                dismiss();
            }
        });

        return view;
    }

    public interface SaleOrRent {
         void onSaleOrRentChoosing (String choice);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            saleOrRent = (SaleOrRent) context;

        }catch (ClassCastException e){
            throw new ClassCastException(context.toString());
        }    }
}
