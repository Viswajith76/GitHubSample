package com.boxme.githubsample.githhubsample.CustomComponents;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;

import com.boxme.githubsample.githhubsample.R;


/**
 * Created by viswajith on 8/9/16.
 */

public class CustomEditText extends AppCompatEditText {
    public CustomEditText(Context context) {
        super(context);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context, attrs);
        initView();
        registerEvents();

    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setCustomFont(context, attrs);
        initView();
        registerEvents();
    }

    private void initView(){

    }

    private void setCustomFont(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs,  R.styleable.CustomTextView);
        String customFont = typedArray.getString(R.styleable.CustomTextView_font);
        setCustomFont(context, customFont);
        typedArray.recycle();
    }

    public boolean setCustomFont(Context ctx, String asset) {
        Typeface typeface = null;
        try {
            typeface = Typeface.createFromAsset(ctx.getAssets(), asset);
        } catch (Exception e) {
            Log.e("error", "Could not get typeface: " + e.getMessage());
            return false;
        }

        setTypeface(typeface);
        return true;

    }

    private void registerEvents() {
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) {
                    if (getParent() instanceof TextInputLayout) {
                        TextInputLayout textInputLayout = (TextInputLayout) getParent();
                        textInputLayout.setError(null);
                        textInputLayout.setErrorEnabled(false);
                    }
                }
            }
        });
    }

}
