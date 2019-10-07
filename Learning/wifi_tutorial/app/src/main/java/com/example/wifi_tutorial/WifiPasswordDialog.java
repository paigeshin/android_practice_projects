package com.example.wifi_tutorial;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDialogFragment;

//이 클래스를 사용하는 activity에 password 값만 넘겨주면 된다.

public class WifiPasswordDialog extends AppCompatDialogFragment {

    private TextView tvName;
    private EditText etPassword;
    private WifiPasswordDialogListener wifiPasswordDialogListener;

    private String wifiName;
    private String wifiPassword;

    WifiPasswordDialog(String wifiName) {
        this.wifiName = wifiName;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.wifi_password, null);
        builder.setView(view)
                .setTitle("와이파이 비밀번호를 입력해주세요.")
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        wifiPassword = etPassword.getText().toString();
                        wifiPasswordDialogListener.applyPassword(wifiPassword);
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        tvName = view.findViewById(R.id.tvWifiTitle);
        //activity에서 넘어온 값
        tvName.setText(wifiName);
        //넘겨줄 값
        etPassword = view.findViewById(R.id.tvWifiPassword);

        //dialog design 바꾸기
        final AlertDialog dialog = builder.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface arg0) {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
                dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
            }
        });

        return dialog;


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            wifiPasswordDialogListener = (WifiPasswordDialogListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement WifiPasswordDialogListener");
        }
    }

    //넘겨줄 값
    public interface WifiPasswordDialogListener {
        void applyPassword(String password);
    }
}
