package com.example.marian.vecsproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.marian.vecsproject.models.MainRresponse;
import com.example.marian.vecsproject.models.UserModel;
import com.example.marian.vecsproject.retrofit.API;
import com.example.marian.vecsproject.retrofit.WebService;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class LoginActivity extends Activity
{
    @BindView(R.id.user_name_et)
    EditText UserName;
    @BindView(R.id.password_et)
    EditText Password;
    @BindView(R.id.login_btn)
    Button LoginBtn;

//    private EditText UserName;
//    private EditText Password;
//    private Button LoginBtn;

    API API;




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
//        UserName = findViewById(R.id.user_name_et);
//        Password = findViewById(R.id.password_et);
//        LoginBtn = findViewById(R.id.login_btn);



       // API = VECSapi.getUserService();

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String userName = UserName.getText().toString();
                String password = Password.getText().toString();
                validateLogin(userName,password);
                doLogin();

            }
        });
    }


    private Boolean validateLogin(String userName, String password)
    {
        if(userName==null|| userName.trim().length()== 0)
        {
            Toast.makeText(this,"UserName is Required",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password==null|| password.trim().length()== 0)
        {
            Toast.makeText(this,"Password is Required",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void doLogin()
    {
        UserModel user = new UserModel();
        user.username = UserName.getText().toString();
        user.password = Password.getText().toString();

        WebService.getInstance().getApi().login(user).enqueue(new Callback<MainRresponse>()
        {
            @Override
            public void onResponse(Call<MainRresponse> call, Response<MainRresponse> response)
            {
                if (response.body().status == 2) {
                    Toast.makeText(LoginActivity.this, response.body().message, Toast.LENGTH_SHORT).show();

                } else if (response.body().status == 1) {
                    Toast.makeText(LoginActivity.this, response.body().message, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, response.body().message, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<MainRresponse> call, Throwable t) {
                Log.e(TAG, t.getLocalizedMessage());

            }
        });

    }


//        Call<UserModel> call = API.login(login);
//        call.enqueue(new Callback<UserModel>() {
//            @Override
//            public void onResponse(Call<UserModel> call, Response<UserModel> response)
//            {
//                if (response.isSuccessful())
//                {
//                    UserModel userModel=response.body();
//                    if (userModel.getUsername().equals("kero") && userModel.getPassword().equals("123") )
//                    {
//                        Intent intent= new Intent(LoginActivity.this,MainActivity.class);
////                        intent.putExtra("userName",userName);
//                        startActivity(intent);
//                    }
//
//                    else
//                    {
//                        Toast.makeText(LoginActivity.this,"Error in password or username ",Toast.LENGTH_SHORT).show();
//                    }
//                }
//                else
//                    {
//
//                    Toast.makeText(LoginActivity.this, "Error! Please try again", Toast.LENGTH_SHORT).show();
//                    }


//
//            @Override
//            public void onFailure(Call<UserModel> call, Throwable t)
//            {
//                Toast.makeText(LoginActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
//
//
//            }
//        });


//    }

}
