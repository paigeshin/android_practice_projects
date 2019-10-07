package com.example.dialog_tutorial;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class ExampleDialog extends AppCompatDialogFragment {

    private EditText etName, etPassword;
    //🔵 Activity와 AppCompatDialogFragment가 값을 교환 할 수 있게 만듬.
    private ExampleDialogListener exampleDialogListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);
        builder.setView(view)
                .setTitle("Login")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //🔵 Activity와 AppCompatDialogFragment가 값을 교환 할 수 있게 만듬.
                        String username = etName.getText().toString();
                        String password = etPassword.getText().toString();
                        exampleDialogListener.applyText(username, password);
                    }
                });

        etName = view.findViewById(R.id.etName);
        etPassword = view.findViewById(R.id.etPassword);

        return builder.create();
    }

    //🔴 Dialog 와 값을 교환하려면 반드시 AppCompatDialogFragment에 있는 onAttach Method를 호출하자.
    //Fragment 내부에 정의된
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            exampleDialogListener = (ExampleDialogListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement ExampleDialogListener");
        }

    }

    //🔵 Activity와 AppCompatDialogFragment가 값을 교환 할 수 있게 만듬.
    public interface ExampleDialogListener {
        void applyText(String username, String password);
    }

}
