package com.alcode.registerandlogin;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.login.widget.LoginButton;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = SignupActivity.class.getSimpleName();


    /* *************************************
     *              FACEBOOK               *
     ***************************************/
    /* The login button for Facebook */
    private LoginButton mFacebookLoginButton;
    /* The callback manager for Facebook */
    private CallbackManager mFacebookCallbackManager;
    /* Used to track user logging in/out off Facebook */
    private AccessTokenTracker mFacebookAccessTokenTracker;


    private Button mPasswordLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_signup);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Register or Login");
        setSupportActionBar(toolbar);
        // Set the padding to match the Status Bar height
        toolbar.setPadding(0, getStatusBarHeight(), 0, 0);


        /* *************************************
         *              FACEBOOK               *
         ***************************************/
        /* Load the Facebook login button and set up the tracker to monitor access token changes */
        mFacebookCallbackManager = CallbackManager.Factory.create();
        mFacebookLoginButton = (LoginButton) findViewById(R.id.login_with_facebook);
        mFacebookAccessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                Log.i(TAG, "Facebook.AccessTokenTracker.OnCurrentAccessTokenChanged");
                SignupActivity.this.onFacebookAccessTokenChange(currentAccessToken);
            }
        };

        mPasswordLoginButton = (Button) findViewById(R.id.login_with_password);
        mPasswordLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginWithPassword();
            }
        });

    }

    // A method to find height of the status bar
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /* ************************************
   *             FACEBOOK               *
   **************************************
   */
    private void onFacebookAccessTokenChange(AccessToken token) {
//        if (token != null) {
//            mAuthProgressDialog.show();
//            mFirebaseRef.authWithOAuthToken("facebook", token.getToken(), new AuthResultHandler("facebook"));
//        } else {
//            // Logged out of Facebook and currently authenticated with Firebase using Facebook, so do a logout
//            if (this.mAuthData != null && this.mAuthData.getProvider().equals("facebook")) {
//                mFirebaseRef.unauth();
//                setAuthenticatedUser(null);
//            }
//        }
    }

    private void loginWithPassword() {
//        Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//        startActivity(intent);
//        overridePendingTransition(0, 0);
        Intent startIntent = new Intent(SignupActivity.this, LoginActivity.class);
        startIntent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(startIntent);
        overridePendingTransition(0, 0);

//        Bundle b = ActivityOptionsCompat
//                .makeSceneTransitionAnimation(SignupActivity.this,
//                        //new Pair<>(findViewById(R.id.logo), "logo"), //Or whatever other elements you don't want moving around
//                        new Pair<>(findViewById(R.id.activity_root), "activity_root"))
//                .toBundle();
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//            startActivity(startIntent, b);
//        }else startActivity(startIntent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.signup, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

       // unbindDrawables(findViewById(R.id.activity_root));
        System.gc();
    }

    private void unbindDrawables(View view) {
        if (view.getBackground() != null) {
            view.getBackground().setCallback(null);
        }
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                unbindDrawables(((ViewGroup) view).getChildAt(i));
            }
            ((ViewGroup) view).removeAllViews();
        }
    }

}
