package com.noon.tawfiqyah.FilterDialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.noon.tawfiqyah.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class FullScreanSearchDialog  extends DialogFragment implements View.OnClickListener {

    private Callback callback;

    static FullScreanSearchDialog newInstance() {
        return new FullScreanSearchDialog();
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullscreenDialogTheme);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_bottom_sheet, container, false);
        ImageButton close = view.findViewById(R.id.fullscreen_dialog_close);
        Button action = view.findViewById(R.id.filter_apply);
       // View rangePrices = view.findViewById(R.id.filter_price);


        close.setOnClickListener(this);
        action.setOnClickListener(this);
       // rangePrices.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {

            case R.id.fullscreen_dialog_close:
                dismiss();
                break;

            case R.id.filter_apply:
                callback.onActionClick("Whatever");
                dismiss();
                break;


        }

    }



    public interface Callback {

        void onActionClick(String name);

    }

}