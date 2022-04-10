package com.Ayush.BloodDonationAPP;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class marathi_text extends androidx.appcompat.widget.AppCompatTextView {
    public marathi_text(@NonNull Context context) {
        super(context);
        initTypeface(context);
    }

    public marathi_text(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initTypeface(context);

    }

    public marathi_text(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTypeface(context);

    }
    private void initTypeface(Context context)
    {
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "Samyak Devanagari Regular.ttf");
        this.setTypeface(typeface);
    }
}
