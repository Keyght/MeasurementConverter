package ru.keyght.MeasurementConverter;

import android.util.Log;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MetricsData {
    private List<String> text_view_captions;
    private List<Double> metre_to_caption;
    private List<EditText> texts;

    public  MetricsData() {
        text_view_captions = new ArrayList<>();
        metre_to_caption = new ArrayList<>();
        texts = new ArrayList<>();

        FillCaptions();
    }

    private void FillCaptions() {
        text_view_captions.add("et_inch");          metre_to_caption.add(39.3701);
        text_view_captions.add("et_yard");          metre_to_caption.add(1.09361);
        text_view_captions.add("et_foot");          metre_to_caption.add(3.28084);
        text_view_captions.add("et_mile");          metre_to_caption.add(0.000621371);
        text_view_captions.add("et_yottametre");    metre_to_caption.add(0.000000000000000000000001);
        text_view_captions.add("et_zettametre");    metre_to_caption.add(0.000000000000000000001);
        text_view_captions.add("et_exametre");      metre_to_caption.add(0.000000000000000001);
        text_view_captions.add("et_petametre");     metre_to_caption.add(0.000000000000001);
        text_view_captions.add("et_terametre");     metre_to_caption.add(0.000000000001);
        text_view_captions.add("et_gigametre");     metre_to_caption.add(0.000000001);
        text_view_captions.add("et_megametre");     metre_to_caption.add(0.000001);
        text_view_captions.add("et_kilometre");     metre_to_caption.add(0.001);
        text_view_captions.add("et_hectometre");    metre_to_caption.add(0.01);
        text_view_captions.add("et_decametre");     metre_to_caption.add(0.1);
        text_view_captions.add("et_metre");         metre_to_caption.add(1.0);
        text_view_captions.add("et_decimetre");     metre_to_caption.add(10.0);
        text_view_captions.add("et_centimetre");    metre_to_caption.add(100.0);
        text_view_captions.add("et_millimetre");    metre_to_caption.add(1000.0);
        text_view_captions.add("et_micrometre");    metre_to_caption.add(1000000.0);
        text_view_captions.add("et_nanometre");     metre_to_caption.add(1000000000.0);
        text_view_captions.add("et_picometre");     metre_to_caption.add(1000000000000.0);
        text_view_captions.add("et_femtometre");    metre_to_caption.add(1000000000000000.0);
        text_view_captions.add("et_attometre");     metre_to_caption.add(1000000000000000000.0);
        text_view_captions.add("et_zeptometre");    metre_to_caption.add(1000000000000000000000.0);
        text_view_captions.add("et_yoctometre");    metre_to_caption.add(1000000000000000000000000.0);
    }

    public void OnAfterTextChanged(MainActivity mainActivity, String caption, double value) {
        double metreValue = CaptionToMetre(caption, value);
        for (int i = 0; i < texts.size(); i++) {
            double currValue;
            EditText currText = texts.get(i);
            String currCaption = text_view_captions.get(i);

            if (currCaption.equals("et_metre")) currValue = metreValue;
            else currValue = metreValue * metre_to_caption.get(i);
            currText.setText(FormatEditText(currValue));
            mainActivity.AddMeasureName(currText, currCaption);
        }
    }

    public static String FormatEditText(double value) {
        if (Math.abs(value) < 1) return String.format("%.25f", value);
        else return String.format("%.1f", value);
    }

    private double CaptionToMetre(String caption, double value) {
        int i = text_view_captions.indexOf(caption);
        value = value / metre_to_caption.get(i);
        return  value;
    }

    public List<String> getText_view_captions() {
        return text_view_captions;
    }

    public List<EditText> getTexts() {
        return texts;
    }

    public void AddEditText(EditText item) {
        if (!texts.contains(item)) texts.add(item);
    }
}
