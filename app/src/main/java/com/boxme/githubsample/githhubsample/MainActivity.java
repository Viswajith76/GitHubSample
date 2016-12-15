package com.boxme.githubsample.githhubsample;

import android.animation.Animator;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.boxme.githubsample.githhubsample.CustomComponents.CustomButton;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.boxme.githubsample.githhubsample.CustomComponents.CustomEditText;
import com.boxme.githubsample.githhubsample.Utils.AnimationUtil;

public class MainActivity extends AppCompatActivity{

    private CardView mCardUserName;
    private CustomEditText mEdtUserName;
    private CustomButton mBtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    // Initialize all the views used in layout
    private void initViews() {
        mCardUserName = (CardView) findViewById(R.id.card_username);
        mEdtUserName = (CustomEditText) findViewById(R.id.edt_username);
        mBtnLogin = (CustomButton) findViewById(R.id.btn_login);
    }

    // Handle the click event of user login button
    public void onLoginClick(View view) {
        if (mCardUserName.getVisibility() == View.VISIBLE) {
            if (!isUserNameEmpty()) {
                Intent intent = new Intent(this, HomeActivity.class);
                intent.putExtra("userName", mEdtUserName.getText().toString());
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(this, view, getString(R.string.animation_name));
                startActivity(intent, options.toBundle());
            } else {
                Snackbar.make(mCardUserName, getString(R.string.username_empty_error), Snackbar.LENGTH_SHORT).show();
            }
        } else {
            mBtnLogin.setVisibility(View.GONE);
            mCardUserName.setVisibility(View.VISIBLE);
            AnimationUtil.scaleHeightAnimation(mCardUserName , mBtnLogin);
        }
    }

    // Checks whether the username field is empty
    private boolean isUserNameEmpty() {
        boolean isEmpty = false;
        if (TextUtils.isEmpty(mEdtUserName.getText())) {
            isEmpty = true;
        }
        return isEmpty;
    }
}
