package com.noon.tawfiqyah;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
//import com.yahoo.mobile.client.android.util.rangeseekbar.RangeSeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class BottomSheetPriceFilter  extends BottomSheetDialogFragment {

    //RangeSeekBar rangeSeekBar;
    TextView maxBar;
    TextView minBar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.buttom_sheet_filter_price, container,false);

      //  rangeSeekBar = v.findViewById(R.id.price_range_seekbar);
         maxBar = v.findViewById(R.id.range_bar_max);
         minBar = v.findViewById(R.id.range_bar_min);

      //  rangeSeekBar.setSelectedMaxValue(100000);
      //  rangeSeekBar.setSelectedMinValue(10000);

//        rangeSeekBar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener() {
//            @Override
//            public void onRangeSeekBarValuesChanged(RangeSeekBar bar, Object minValue, Object maxValue) {
//
//                Number min_value = bar.getSelectedMinValue();
//                Number max_value = bar.getSelectedMaxValue();
//
//                int min = (int) min_value;
//                int max = (int) max_value;
//
//
//                Toast.makeText(getContext(), "here : " + min + "mas is " + max, Toast.LENGTH_SHORT).show();
//
//            }
//        });



        return v;
    }

}
