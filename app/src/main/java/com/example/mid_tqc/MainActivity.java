package com.example.mid_tqc;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 使用更清晰的變數命名
    private TextView orderDisplayTextView;
    private final String[] menuItems = {"花生厚片", "巧克力厚片", "草莓厚片", "奶酥厚片", "藍莓厚片"};
    private final boolean[] selectedItems = {false, false, false, false, false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
    }

    /**
     * 初始化視圖組件
     */
    private void initializeViews() {
        orderDisplayTextView = findViewById(R.id.tvshow);
        // 設置初始狀態
        updateOrderDisplay();
    }

    /**
     * 顯示點餐對話框
     * 對應原始的 showDialog 方法
     */
    public void showOrderDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("請選擇您要的餐點：");
        builder.setIcon(R.drawable.ic_restaurant); // 添加圖標

        builder.setMultiChoiceItems(menuItems, selectedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                selectedItems[which] = isChecked;
                // 添加音效反饋
                playClickSound();
            }
        });

        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                updateOrderDisplay();
                showConfirmationToast();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 重置選擇狀態
                resetSelections();
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "已取消點餐", Toast.LENGTH_SHORT).show();
            }
        });

        // 添加中性按鈕用於清除所有選擇
        builder.setNeutralButton("全部清除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                clearAllSelections();
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "已清除所有選擇", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * 更新訂單顯示
     */
    private void updateOrderDisplay() {
        StringBuilder selectedItemsText = new StringBuilder();
        int selectedCount = 0;

        for (int i = 0; i < selectedItems.length; i++) {
            if (selectedItems[i]) {
                selectedItemsText.append("• ").append(menuItems[i]).append("\n");
                selectedCount++;
            }
        }

        if (selectedCount == 0) {
            orderDisplayTextView.setText("尚未選擇任何餐點");
            orderDisplayTextView.setTextColor(getResources().getColor(android.R.color.darker_gray));
        } else {
            orderDisplayTextView.setText(selectedItemsText.toString().trim());
            orderDisplayTextView.setTextColor(getResources().getColor(android.R.color.black));
        }
    }

    /**
     * 顯示確認提示
     */
    private void showConfirmationToast() {
        int selectedCount = getSelectedCount();
        if (selectedCount > 0) {
            String message = "已選擇 " + selectedCount + " 項餐點";
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "您沒有選擇任何餐點", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 獲取已選擇的餐點數量
     */
    private int getSelectedCount() {
        int count = 0;
        for (boolean selected : selectedItems) {
            if (selected) count++;
        }
        return count;
    }

    /**
     * 重置所有選擇
     */
    private void resetSelections() {
        for (int i = 0; i < selectedItems.length; i++) {
            selectedItems[i] = false;
        }
        updateOrderDisplay();
    }

    /**
     * 清除所有選擇
     */
    private void clearAllSelections() {
        resetSelections();
    }

    /**
     * 播放點擊音效（可選功能）
     */
    private void playClickSound() {
        // 可以在這裡添加音效
        // MediaPlayer.create(this, R.raw.click_sound).start();
    }

    /**
     * 保存應用程式狀態
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBooleanArray("selectedItems", selectedItems);
    }

    /**
     * 恢復應用程式狀態
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        boolean[] savedSelections = savedInstanceState.getBooleanArray("selectedItems");
        if (savedSelections != null) {
            System.arraycopy(savedSelections, 0, selectedItems, 0, selectedItems.length);
            updateOrderDisplay();
        }
