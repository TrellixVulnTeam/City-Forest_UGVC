package com.zehava.cityforest;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Locale;

import static com.zehava.cityforest.Constants.RC_SIGN_IN;

public class SignInActivity extends AppCompatActivity{

    private GoogleApiClient mGoogleApiClient;
    private SignInButton sign_in_button;
    private ProgressDialog mProgress;
    private FirebaseAuth mAuth;
    private TextView title_txt;
    private TextView snippet_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sign_in);

        String languageToLoad  = "he"; // your language
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());




        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        mAuth = FirebaseAuth.getInstance();

        sign_in_button = (SignInButton) findViewById(R.id.sign_in_button);
        sign_in_button.setOnClickListener(new ClickListener());


        mProgress = new ProgressDialog(this);
        mProgress.setMessage("Signing in...");

        title_txt = (TextView)findViewById(R.id.titleTxt);
        snippet_txt = (TextView)findViewById(R.id.snippetTxt);
        Typeface typeFaceBold=Typeface.createFromAsset(getAssets(),"fonts/Alef-Bold.ttf");
        Typeface typeFaceReg=Typeface.createFromAsset(getAssets(),"fonts/Alef-Regular.ttf");
        title_txt.setTypeface(typeFaceBold);
        snippet_txt.setTypeface(typeFaceReg);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null)
            updateUI(currentUser);
    }

    private class ClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if(v.getId() == sign_in_button.getId()){
                mProgress.show();
                sign_in_button.setEnabled(false);
                signIn();
            }

        }
    }

    /*Method sends a sign in intent to Google Sign In API, result will handled
    * in method onActivityResult*/
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            /*Getting the result and sending it to method handleSignInResult*/
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        mProgress.hide();
        if (result.isSuccess()) {
            // Google Sign In was successful, authenticate with Firebase
            GoogleSignInAccount account = result.getSignInAccount();
            firebaseAuthWithGoogle(account);
        } else {
            updateUI(null);
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Intent intent = getIntent();
                            if (intent != null){
                                setResult(53, intent);
                                finish();
                            }else {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUI(user);
                            }
                        } else {
                            updateUI(null);
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        if(user == null){
            Toast.makeText(SignInActivity.this, "Authentication failed",
                    Toast.LENGTH_SHORT).show();

            sign_in_button.setEnabled(true);
        }
        else{
            String userUid = user.getUid();
            if(userUid.equals(getResources().getString(R.string.permitted_editor)) ||
                    userUid.equals(getResources().getString(R.string.permitted_editor2))||(userUid.equals("64xkNhmPOvXlmJaXeleTI5XT1zA2"))){
                Intent i = new Intent(SignInActivity.this, EditorHomeActivity.class);
                startActivity(i);
            }
            else{
                Intent i = new Intent(SignInActivity.this, Home.class);
                startActivity(i);
            }

        }

    }

    /*Exit app dialog if the user clicked back button*/
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.dialog_exit_app_title)
                .setMessage(R.string.dialog_exit_app_body)
                .setPositiveButton(R.string.dialog_yes, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        /*Exits the application*/
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                })
                .setNegativeButton(R.string.dialog_cancel, null)
                .show();
    }

}
