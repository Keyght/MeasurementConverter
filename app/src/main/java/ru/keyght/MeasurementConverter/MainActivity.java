package ru.keyght.MeasurementConverter;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import ru.keyght.MeasurementConverter.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    private int lower = 2;
    private int upper = 15;
    private ViewGroup measurements;
    private MetricsData metricsData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (getIntent().getExtras() != null) {
            lower = getIntent().getExtras().getInt("lower", 2);
            upper = getIntent().getExtras().getInt("upper", 15);
        }

        metricsData = new MetricsData();
        measurements = binding.Measurments;
        RemoveExtraMeasures();

    }

    private void RemoveExtraMeasures() {
        int metricSize = metricsData.getText_view_captions().size();
        List<View> toRemove = new ArrayList<View>();
        for (int i = 0; i < metricSize; i++) {
            if (i < lower || i > upper)  {
                toRemove.add(measurements.getChildAt(i));
            }
        }
        for (View view : toRemove) {
            measurements.removeView(view);
        }
    }

    private void SetMeasuresSize() {
        for (int i = 0; i < measurements.getChildCount(); i++) {
            View currentView = measurements.getChildAt(i);
            currentView.la
        }
    }
}
