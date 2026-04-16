package com.example.hello;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import android.graphics.Color;
import android.text.TextUtils;

public class MainActivity extends Activity {

    private LinearLayout chatContainer;
    private EditText input;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setBackgroundColor(Color.parseColor("#121212"));

        TextView title = new TextView(this);
        title.setText("🤖 AR.AI Chat");
        title.setTextSize(20);
        title.setTextColor(Color.WHITE);
        title.setBackgroundColor(Color.parseColor("#075E54"));
        title.setGravity(Gravity.CENTER);
        title.setPadding(20, 40, 20, 20);
        mainLayout.addView(title);

        scrollView = new ScrollView(this);
        scrollView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 0, 1));
        chatContainer = new LinearLayout(this);
        chatContainer.setOrientation(LinearLayout.VERTICAL);
        chatContainer.setPadding(20, 20, 20, 20);
        scrollView.addView(chatContainer);
        mainLayout.addView(scrollView);

        LinearLayout inputLayout = new LinearLayout(this);
        inputLayout.setOrientation(LinearLayout.HORIZONTAL);
        inputLayout.setPadding(10, 10, 10, 10);
        inputLayout.setBackgroundColor(Color.parseColor("#1E1E1E"));

        input = new EditText(this);
        input.setHint("اكتب رسالة...");
        input.setHintTextColor(Color.GRAY);
        input.setTextColor(Color.WHITE);
        input.setBackgroundColor(Color.parseColor("#2A2A2A"));
        input.setSingleLine(true);

        Button sendBtn = new Button(this);
        sendBtn.setText("إرسال");
        sendBtn.setBackgroundColor(Color.parseColor("#0F9D58"));
        sendBtn.setTextColor(Color.WHITE);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

        inputLayout.addView(input, new LinearLayout.LayoutParams(0, -2, 1));
        inputLayout.addView(sendBtn);

        mainLayout.addView(inputLayout);
        setContentView(mainLayout);

        addBotMessage("مرحباً بك! كيف يمكنني مساعدتك؟");
    }

    private void sendMessage() {
        String msg = input.getText().toString().trim();
        if (TextUtils.isEmpty(msg)) return;
        addUserMessage(msg);
        input.setText("");
        addBotMessage("تم استلام: " + msg);
    }

    private void addUserMessage(String text) {
        TextView tv = new TextView(this);
        tv.setText("أنت: " + text);
        styleMessage(tv, true);
        chatContainer.addView(tv);
        scrollDown();
    }

    private void addBotMessage(String text) {
        TextView tv = new TextView(this);
        tv.setText("AR.AI: " + text);
        styleMessage(tv, false);
        chatContainer.addView(tv);
        scrollDown();
    }

    private void styleMessage(TextView tv, boolean isUser) {
        tv.setTextSize(16);
        tv.setTextColor(Color.WHITE);
        tv.setBackgroundColor(isUser ? Color.parseColor("#0F9D58") : Color.parseColor("#1E1E1E"));
        tv.setPadding(20, 12, 20, 12);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(-2, -2);
        p.setMargins(isUser ? 60 : 10, 10, isUser ? 10 : 60, 10);
        tv.setLayoutParams(p);
    }

    private void scrollDown() {
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(View.FOCUS_DOWN);
            }
        });
    }
}
