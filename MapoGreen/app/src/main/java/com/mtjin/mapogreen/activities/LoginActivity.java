package com.mtjin.mapogreen.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
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
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.mtjin.mapogreen.R;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {
    // 구글로그인 result 상수
    private static final int CODE_SIGN_IN = 1000;
    // 구글api클라이언트
    private GoogleApiClient mGoogleApiClient; //구글인증에필요
    // 파이어베이스 인증 객체 생성
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    // 구글  로그인 버튼
    private SignInButton googleButton;
    //사용자
    private FirebaseUser mCurrentUser;
    //xml


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //권한요청
        tedPermission();


        googleButton = findViewById(R.id.login_btn_google);
        mAuth = FirebaseAuth.getInstance(); //firebaseAuth 객체의 공유 인스턴스를 가져옵니다.

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                mCurrentUser = firebaseAuth.getCurrentUser();
                if (mCurrentUser != null) {
                    //자동구글로그인시필요
                    /*//이 유저 로그인시
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);*/
                } else {
                    //해당 로그아웃시
                }
            }
        };


        //구글로그인관련 소스
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this) //기본으로 세팅해줌
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        findViewById(R.id.login_btn_google).setOnClickListener(this);
        findViewById(R.id.login_btn_forgot_pw).setOnClickListener(this);
        findViewById(R.id.login_btn_login).setOnClickListener(this);
        findViewById(R.id.login_btn_signup).setOnClickListener(this);
    }

    //구글인증연결 실패시
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    //구글로그인버튼 눌렀을 때 처리
    @Override
    public void onClick(View v) {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        overridePendingTransition(R.anim.fade_in_splash, R.anim.fade_out_splash);
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) { //task에서 다양한 정보를 담고있기 때문에 잘 사용하면된다.
                        if (!task.isSuccessful()) { //실패했다면
                            Toast.makeText(LoginActivity.this, "인증 실패하였습니다. 이미 가입된 이메일일수도 있습니다.", Toast.LENGTH_LONG).show();

                        } else { //성공했으면 다시 로그인액티비티에서 프로필액티비티로 가게해주면된다.
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            overridePendingTransition(R.anim.fade_in_splash, R.anim.fade_out_splash);
                        }
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == CODE_SIGN_IN) { //구글로그인버튼 누르고 응답결과
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) { //로그인 성공시
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                Toast.makeText(this, "구글 로그인을 실패하였습니다", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //위치 사용 권한 요청
    private void tedPermission() {

        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                // 권한 요청 성공
                //Toast.makeText(LoginActivity.this, "권한 성공", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                // 권한 요청 실패
               // Toast.makeText(LoginActivity.this, "권한 실패", Toast.LENGTH_SHORT).show();
            }
        };

        TedPermission.with(this)
                .setPermissionListener(permissionListener)
                .setRationaleMessage(getResources().getString(R.string.permission_2))
                .setDeniedMessage(getResources().getString(R.string.permission_1))
                .setPermissions(Manifest.permission.ACCESS_FINE_LOCATION)
                .check();
    }

    @Override //When initializing your Activity, check to see if the user is currently signed in.
    protected void onStart() {
        super.onStart();
        mCurrentUser = mAuth.getCurrentUser();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
