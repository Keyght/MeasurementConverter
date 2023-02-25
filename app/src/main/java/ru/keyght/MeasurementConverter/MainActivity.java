package ru.keyght.MeasurementConverter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Field;

import ru.keyght.MeasurementConverter.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    private ViewGroup measurements;
    private MetricsData metricsData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        metricsData = new MetricsData();
        measurements = binding.Measurments;

        for (int i = 0; i < measurements.getChildCount(); i++) {
            View currentMeasure = measurements.getChildAt(i);
            String caption = metricsData.getText_view_captions().get(i);

            EditText currentText = (EditText)((ViewGroup)currentMeasure).getChildAt(1);
            metricsData.AddEditText(currentText);

            currentText.setOnClickListener(view -> {
                final Editable prevText = currentText.getText();

                TextToClipboard(prevText);

                currentText.clearComposingText();
            });

            currentText.setOnFocusChangeListener((view, b) -> {
                if (!b) {
                    metricsData.OnAfterTextChanged();
                    String currentMeasureName = getResources().getQuantityString(getResources().getIdentifier(caption, "plurals", getPackageName()), Integer.parseInt(currentText.getText().toString()));
                    currentText.setText(String.join(" ", currentText.getText().toString(), currentMeasureName));
                }
            });

            /*currentText.addTextChangedListener(new TextWatcher() {
                CharSequence buffer = currentText.getText();
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    Log.d("CCCCC", "Here");
                    metricsData.OnAfterTextChanged();
                    currentText.setText(String.join(" ", charSequence.toString(), currentMeasureName.getText().toString()));
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (!buffer.equals(charSequence))
                    {
                        buffer = charSequence;
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {}
            });*/
        }
    }

    private void TextToClipboard(Editable text) {
        int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(text);
            Toast.makeText(getApplicationContext(), R.string.text_copied, Toast.LENGTH_SHORT).show();
        } else {
            ClipboardManager clipboard = (ClipboardManager) getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("TAG",text);
            clipboard.setPrimaryClip(clip);
            Toast.makeText(getApplicationContext(),R.string.text_copied, Toast.LENGTH_SHORT).show();
        }
    }
}
