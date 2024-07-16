package com.example.home_work_2_4;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private Button buttonLogin;
    private TextView textViewTitle, textViewWelcome, textViewSubtitle;
    private View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootView = findViewById(R.id.main);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewWelcome = findViewById(R.id.textViewWelcome);
        textViewSubtitle = findViewById(R.id.textViewSubtitle);

        ViewCompat.setOnApplyWindowInsetsListener(rootView, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateButtonBackground();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };

        editTextEmail.addTextChangedListener(textWatcher);
        editTextPassword.addTextChangedListener(textWatcher);

        buttonLogin.setOnClickListener(v -> {
            String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();

            if (email.equals("admin") && password.equals("admin")) {
                Toast.makeText(MainActivity.this, "Вы успешно зарегистрировались", Toast.LENGTH_SHORT).show();
                showWelcomeMessage();
            } else  {
                Toast.makeText(MainActivity.this, "Неправильный логин и пароль", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateButtonBackground() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        if (!email.isEmpty() && !password.isEmpty()) {
            buttonLogin.setBackgroundColor(getResources().getColor(R.color.orange));
        } else {
            buttonLogin.setBackgroundColor(getResources().getColor(R.color.grey));
        }
    }

    private void showWelcomeMessage() {
        textViewTitle.setVisibility(View.GONE);
        editTextEmail.setVisibility(View.GONE);
        editTextPassword.setVisibility(View.GONE);
        buttonLogin.setVisibility(View.GONE);
        textViewSubtitle.setVisibility(View.GONE);
        textViewWelcome.setVisibility(View.VISIBLE);
    }

    @Override
    public void onResume() {
        super.onResume();
        editTextEmail.clearFocus();
        editTextPassword.clearFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(rootView.getWindowToken(), 0);
    }
}