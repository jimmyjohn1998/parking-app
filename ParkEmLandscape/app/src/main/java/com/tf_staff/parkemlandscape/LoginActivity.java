package com.tf_staff.parkemlandscape;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.JsonObject;
import com.tf_staff.parkemlandscape.RetrofitExtension.MyRetrofitClient;
import com.tf_staff.parkemlandscape.app_user.NavigationEntities.AppUserNearByParkingActivity;
import com.tf_staff.parkemlandscape.area_admin.NavigationEntities.AreaAdminProfileActivity;
import com.tf_staff.parkemlandscape.utils.AppConstants;
import com.tf_staff.parkemlandscape.webservice.WebServiveAPIs;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    EditText loginUserName, loginPassword;
    Button loginButton;
    ImageView registerImageView;
    String fcmId = "";

    TextView textViewForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_bak);
        loginUserName = (EditText) findViewById(R.id.login_username_editText);
        loginPassword = (EditText) findViewById(R.id.login_password_editText);
        registerImageView = (ImageView) findViewById(R.id.login_register_imageView);
        textViewForgotPassword = (TextView) findViewById(R.id.textViewForgotPassword);
        textViewForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(LoginActivity.this);
                dialog.setContentView(R.layout.forgot_password);
                dialog.show();
                final EditText editTextPasswordResetUsername = (EditText) dialog.findViewById(R.id.editTextPasswordResetUsername);
                Button buttonPasswordReset = (Button) dialog.findViewById(R.id.buttonPasswordReset);
                buttonPasswordReset.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String username = editTextPasswordResetUsername.getText().toString().trim();
                        resetPassword(username, dialog);
                    }
                });
            }
        });
        registerImageView.setOnClickListener(this);
        loginButton = (Button) findViewById(R.id.login_submit_button);
        loginButton.setOnClickListener(this);
    }

    private void resetPassword(String username, Dialog dialog) {
        Retrofit retrofit = new MyRetrofitClient().getRetrofit();
        WebServiveAPIs webServiveAPIs = retrofit.create(WebServiveAPIs.class);
        final ProgressDialog pDialog = new ProgressDialog(LoginActivity.this);
        pDialog.setMessage("waiting for response from server");
        dialog.dismiss();
        pDialog.show();
        webServiveAPIs.resetPassword(username).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                pDialog.dismiss();
                String msg = response.body() + "";
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
//                SharedPreferences sharedPreferences = getSharedPreferences(AppConstants.SHARED_PREF, MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                if (userType.trim().equals("\"padmin\"")) {
//                    editor.putString(AppConstants.USER_NAME, loginUserName.getText().toString());
//                    editor.apply();
//                    Intent intent = new Intent(LoginActivity.this, AreaAdminProfileActivity.class);
//                    startActivity(intent);
//                    finish();
//                } else if (userType.trim().equals("\"user\"")) {
//                    editor.putString(AppConstants.USER_NAME, loginUserName.getText().toString());
//                    editor.apply();
//                    Intent intent = new Intent(LoginActivity.this, AppUserNearByParkingActivity.class);
//                    startActivity(intent);
//                    finish();
//                }
//                onBackPressed();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                pDialog.dismiss();
                Toast.makeText(getApplicationContext(), "failed to reset password", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.login_submit_button) {
            fcmId = FirebaseInstanceId.getInstance().getToken();
            boolean flag = true;
            if (loginUserName.getText().toString().equals("")) {
                loginUserName.setError("Enter username");
                flag = false;
            }
            if (loginPassword.getText().toString().equals("")) {
                loginPassword.setError("Enter password");
                flag = false;
            }
            if (flag == true) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("username", loginUserName.getText().toString());
                    jsonObject.put("password", loginPassword.getText().toString());
                    jsonObject.put("fcm", fcmId);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Retrofit retrofit = new MyRetrofitClient().getRetrofit();
                WebServiveAPIs webServiveAPIs = retrofit.create(WebServiveAPIs.class);
                final ProgressDialog pDialog = new ProgressDialog(LoginActivity.this);
                pDialog.setMessage("waiting for response from server");
                pDialog.show();
                webServiveAPIs.login(jsonObject).enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        pDialog.show();
                        JsonObject jsonObjectGetUserType = response.body();
                        String userType = "";
                        userType = String.valueOf(jsonObjectGetUserType.get("userType"));
                        Toast.makeText(getApplicationContext(), userType, Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences = getSharedPreferences(AppConstants.SHARED_PREF, MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        if (userType.trim().equals("\"padmin\"")) {
                            editor.putString(AppConstants.USER_NAME, loginUserName.getText().toString());
                            editor.apply();
                            Intent intent = new Intent(LoginActivity.this, AreaAdminProfileActivity.class);
                            startActivity(intent);
                            finish();
                        } else if (userType.trim().equals("\"user\"")) {
                            editor.putString(AppConstants.USER_NAME, loginUserName.getText().toString());
                            editor.apply();
                            Intent intent = new Intent(LoginActivity.this, AppUserNearByParkingActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        pDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                        t.printStackTrace();
                    }
                });
            }
        } else if (view.getId() == R.id.login_register_imageView) {
            Intent intent = new Intent(this, CommonRegistrationActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        finish();
    }
}
