package com.noon.tawfiqyah.FilterDialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.noon.tawfiqyah.R;
import com.yahoo.mobile.client.android.util.rangeseekbar.RangeSeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PriceFilter extends BottomSheetDialogFragment {

    RangeSeekBar rangeSeekBar;
    TextView maxBar;
    TextView minBar;
    TextView priceFilterApply;

    PriceSheetListner priceSheetListner;

    String priceMinValue;
    String priceMaxValue;
    boolean filterGotClicked = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.price_dialogu, container, false);

        rangeSeekBar = view.findViewById(R.id.price_rangebar);
        maxBar = view.findViewById(R.id.range_max);
        minBar = view.findViewById(R.id.range_min);
        priceFilterApply = view.findViewById(R.id.price_filter_apply);

        rangeSeekBar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar bar, Object minValue, Object maxValue) {
                priceMinValue = minValue.toString();
                priceMaxValue = maxValue.toString();


                Toast.makeText(getContext(), "here : " + priceMinValue + "mas is " + priceMaxValue, Toast.LENGTH_SHORT).show();

            }
        });

        priceFilterApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filterGotClicked = true;
                priceSheetListner.onButtomClicked(priceMaxValue, priceMinValue, filterGotClicked);
                //filterGotClicked = false;
                dismiss();
            }
        });

        return view;
    }

    public interface PriceSheetListner {
        void onButtomClicked (String minValue, String maxValue, boolean filterGotClicked);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            priceSheetListner = (PriceSheetListner) context;

        }catch (ClassCastException e){
            throw new ClassCastException(context.toString());
        }
    }
}
