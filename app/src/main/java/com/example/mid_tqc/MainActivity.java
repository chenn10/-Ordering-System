package com.example.mid_tqc;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.app.Dialog;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView show;
    private  String[] meal = {"漢堡", "薯條", "雞塊", "可樂", "冰淇淋"};
    private boolean[] mealSeclect = {false, false, false, false, false};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();

    }

    public void showDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("您點的餐點有：");

        builder.setMultiChoiceItems(meal, mealSeclect, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                mealSeclect[which] = isChecked;
            }
        });

        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                StringBuilder selectedItems = new StringBuilder();
                for (int i = 0; i < mealSeclect.length; i++) {
                    if (mealSeclect[i]) {
                        selectedItems.append(meal[i]).append("\n");
                    }
                }
                show.setText(selectedItems.toString());
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Close the dialog and return to the main screen
                dialog.dismiss();
            }

        });

        builder.show();
    }
    private void findViews() {
        show = findViewById(R.id.tvshow);
    }
}