package ru.keyght.MeasurementConverter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

            //Set clipboard copying on click
            currentText.setOnClickListener(view -> {
                final Editable prevText = currentText.getText();

                TextToClipboard(prevText);

                currentText.clearComposingText();
            });

            //Set recalculation of all measures
            currentText.setOnFocusChangeListener((view, b) -> {
                if (!b) {
                    double value = AddMeasureName(currentText, caption);
                    if (Math.abs(value) >= 0) metricsData.OnAfterTextChanged(this, caption, value);
                }
            });
        }
    }

    private double DoubleOutOfBounds(String text) {
        double value;
        try {
            value = Double.parseDouble(text);
            return value;
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), R.string.text_max_num, Toast.LENGTH_LONG).show();
            return -1;
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
            Toast.makeText(getApplicationContext(), R.string.text_copied, Toast.LENGTH_SHORT).show();
        }
    }

    public double AddMeasureName(EditText editText, String caption) {
        String text = editText.getText().toString().replaceAll("[^.,\\d].*", "");
        String[] parts = (text.replaceAll(",", ".") + ".0").split("[.]");
        if (parts.length > 2) {
            text = String.join(".", parts[0], parts[1]);
            if (parts.length > 3) Toast.makeText(getApplicationContext(), R.string.text_too_many_dots, Toast.LENGTH_LONG).show();
        }
        int textLastIntValues = 0;
        if (!text.isEmpty()) {
            double buffer = DoubleOutOfBounds(parts[0]);
            textLastIntValues = (int) buffer % 1000;
        } else text = "0";
        if (textLastIntValues >= 0) {
            double value = DoubleOutOfBounds(text);
            if (value < 0) return -1;
            String currentMeasureName = getResources().getQuantityString(
                    getResources().getIdentifier(caption, "plurals", getPackageName()),
                    textLastIntValues, textLastIntValues).replaceAll("\\d", "");
            editText.setText(String.join(" ", MetricsData.FormatEditText(value), currentMeasureName));
            return  value;
        } else return -1;
    }
}
