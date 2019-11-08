package com.example.phonebook.othersToBeSort;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.DialogFragment;

import com.example.phonebook.R;

import java.util.Objects;

public class ConfirmDeletion extends DialogFragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_confirm_deletion, container, false);
        Button mCancelDeletion = view.findViewById(R.id.buttonCancelDeletion);
        Button mConfirmDeletion = view.findViewById(R.id.buttonConfirmDeletion);

        Objects.requireNonNull(Objects.requireNonNull(getDialog()).getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        mCancelDeletion.setOnClickListener(v -> dismiss());

        mConfirmDeletion.setOnClickListener(v -> {
            Objects.requireNonNull(getActivity()).getIntent().putExtra("ITEM_POSITION", getActivity().getIntent().getIntExtra("ITEM_POSITION", 0));
            getActivity().setResult(MainActivity.RESULT_DELETE, getActivity().getIntent());
            getActivity().finish();
        });
        return view;
    }

}