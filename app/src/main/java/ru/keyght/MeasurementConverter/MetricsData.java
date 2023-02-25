package ru.keyght.MeasurementConverter;

import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MetricsData {
    private Map<Integer, String> text_view_captions;
    private List<EditText> texts;

    public  MetricsData() {
        text_view_captions = new HashMap<>();
        texts = new ArrayList<>();

        FillCaptions();
    }

    private void FillCaptions() {
        text_view_captions.put(0, "et_inch");
        text_view_captions.put(1, "et_yard");
        text_view_captions.put(2, "et_foot");
        text_view_captions.put(3, "et_mile");
        text_view_captions.put(4, "et_yottametre");
        text_view_captions.put(5, "et_zettametre");
        text_view_captions.put(6, "et_exametre");
        text_view_captions.put(7, "et_petametre");
        text_view_captions.put(8, "et_terametre");
        text_view_captions.put(9, "et_gigametre");
        text_view_captions.put(10, "et_megametre");
        text_view_captions.put(11, "et_kilometre");
        text_view_captions.put(12, "et_hectometre");
        text_view_captions.put(13, "et_decametre");
        text_view_captions.put(14, "et_metre");
        text_view_captions.put(15, "et_decimetre");
        text_view_captions.put(16, "et_centimetre");
        text_view_captions.put(17, "et_millimetre");
        text_view_captions.put(18, "et_micrometre");
        text_view_captions.put(19, "et_nanometre");
        text_view_captions.put(20, "et_picometre");
        text_view_captions.put(21, "et_femtometre");
        text_view_captions.put(22, "et_attometre");
        text_view_captions.put(23, "et_zeptometre");
        text_view_captions.put(24, "et_yoctometre");
    }

    public void OnAfterTextChanged() {
        return;
    }

    public Map<Integer, String> getText_view_captions() {
        return text_view_captions;
    }

    public List<EditText> getTexts() {
        return texts;
    }

    public void AddEditText(EditText item) {
        if (!texts.contains(item)) texts.add(item);
    }
}
