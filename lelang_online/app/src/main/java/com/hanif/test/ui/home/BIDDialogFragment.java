package com.hanif.test.ui.home;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.hanif.test.DetailAuctionStuffFragment;
import com.hanif.test.R;

public class BIDDialogFragment extends DialogFragment implements View.OnClickListener {

    Button btnBid, btnClose;
    EditText edtBid;
    OnOptionDialogListener optionDialogListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_biddialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnBid = view.findViewById(R.id.btn_bid);
        btnBid.setOnClickListener(this);
        btnClose = view.findViewById(R.id.btn_close);
        btnClose.setOnClickListener(this);
        edtBid = view.findViewById(R.id.edt_bid);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Fragment fragment = getParentFragment();
        if (fragment instanceof DetailAuctionStuffFragment) {
            DetailAuctionStuffFragment detailCategoryFragment = (DetailAuctionStuffFragment) fragment;
            this.optionDialogListener = detailCategoryFragment.optionDialogListener;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.optionDialogListener = null;
    }

    @Override
    public void onClick(View view) {
        String price = null;
        if (view.getId() == R.id.btn_close){
            getDialog().cancel();
        } else if(view.getId() == R.id.btn_bid){
            price = edtBid.getText().toString().trim();
        }

        if (optionDialogListener != null) {
            optionDialogListener.onOptionChosen(price);
        }
        getDialog().dismiss();
    }

    public interface OnOptionDialogListener {
        void onOptionChosen(String text);
    }
}
