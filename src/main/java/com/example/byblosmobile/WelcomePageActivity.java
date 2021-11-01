package com.example.byblosmobile;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Query;

/**
 * A welcome page after successfully logged-in / signed-up
 */
public class WelcomePageActivity extends AppCompatActivity {

  private TextView welcomeText;
  private String username;
  private String userType;

  private final MyDatabase myDatabase = new MyDatabase();

  public void updateUserInfo(UserInfo userInfo) {
    this.username = userInfo.firstName + " " + userInfo.lastName;
    this.userType = userInfo.userType;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_welcome_page);


    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

//    final ArrayList<String> list = new ArrayList<>();

    String userEntryKey = myDatabase.getDbRef().child(currentUser.getUid()).getKey();
    Query queryResult = myDatabase.getDbRef().orderByKey().equalTo(userEntryKey);


    // TODO @kurtis: 10/8/21 I REALLY do not like this ad-hoc way of putting the listener here, fixme in the future i will
    //             possible solution: https://stackoverflow.com/a/10196709
    queryResult.get().addOnSuccessListener(dataSnapshot -> {

      for (DataSnapshot sh : dataSnapshot.getChildren()) {

        UserInfo userInfo = sh.getValue(UserInfo.class);

        // TODO @kurtis: 10/8/21 here, all the userInfo is loaded at this point
        // TODO @kurtis: 10/8/21 VERT IMPORTANT: this inner anon-class is executed ASYNCHRONOUSLY,
        //                so before we came up with another solution, we have to every thing inside here

//        list.add(userInfo.email);

        assert userInfo != null;
        updateUserInfo(userInfo);
        welcomeText = (TextView) findViewById((R.id.welcomeText));
        welcomeText.setText(String.format("Welcome, %s!\n\nYou are logged in as %s", username, userType));

        Log.d("wtf", userInfo.toString());
      }
    });

  }
}