package com.example.byblosmobile;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyDatabase {

  private String dbPath = "Users";
  private FirebaseDatabase db = FirebaseDatabase.getInstance();
  private DatabaseReference dbRef;

  /**
   * Default is to reference the top-level entry of the current database, "User".
   */
  public MyDatabase() {
    setReference(this.dbPath);
  }

  public DatabaseReference getDbRef() {
    return dbRef;
  }

  /**
   * Change the database reference by $dbPath.
   *
   * @param dbPath a string pointing to the database's inner structure
   */
  public void setReference(String dbPath) {
    this.dbPath = dbPath;
    this.dbRef = db.getReference(this.dbPath);
  }

  /**
   * Add $fbUser to the database.
   *
   * @param fbUser   Firebase userInfo
   * @param userInfo User class type temporarily stores userInfo info (name, email, usertype)
   */
  public void addUser(FirebaseUser fbUser, UserInfo userInfo) {

    dbRef.child(fbUser.getUid()).setValue(userInfo);
  }


// TODO @kurtis: 10/8/21  I will re-implement this the rest once we found a way to properly handle the asynchronous
//               inner class with the variables outside. no matter how many times i tried, arrays, lists or custom
//               types, they all seem to be updated AFTER the whole method is executed ...

//  /**
//   * @param fbUser Firebase userInfo
//   *               //   * @param userInfo User class type temporarily stores userInfo info (name, email, usertype)
//   */
//  public void loadUserInfo(FirebaseUser fbUser, ArrayList<String> container) {
//
//    String userEntryKey = dbRef.child(fbUser.getUid()).getKey();
//    Query queryResult = dbRef.orderByKey().equalTo(userEntryKey);
//
////    final ArrayList<UserInfo> list = new ArrayList<>();
////    final ArrayList<String> list = new ArrayList<>();
//
//    queryResult.get().addOnSuccessListener(dataSnapshot -> {
//
//      for (DataSnapshot sh : dataSnapshot.getChildren()) {
////          Log.d("wtf", sh.getKey() + " " + sh.getValue());
//
//        UserInfo userInfo = sh.getValue(UserInfo.class);
//        assert userInfo != null;
//
//        // TODO @kurtis: 10/8/21 here, all the userInfo is loaded at this point
////        activityPage.updateUserInfo(userInfo);
//
//        container.add(userInfo.email);
//
//        Log.d("wtf", userInfo.toString());
//      }
//    });
//  }
//
//  /**
//   * @param fbUser Firebase userInfo
//   *               //   * @param userInfo User class type temporarily stores userInfo info (name, email, usertype)
//   */
//  public void loadUserType(FirebaseUser fbUser) {
//
//    String userEntryKey = dbRef.child(fbUser.getUid()).getKey();
//    Query queryResult = dbRef.orderByKey().equalTo(userEntryKey);
//
////    final ArrayList<UserInfo> list = new ArrayList<>();
//    final ArrayList<String> list = new ArrayList<>();
//
//    queryResult.get().addOnSuccessListener(dataSnapshot -> {
//
//      for (DataSnapshot sh : dataSnapshot.getChildren()) {
////          Log.d("wtf", sh.getKey() + " " + sh.getValue());
//
//        UserInfo userInfo = sh.getValue(UserInfo.class);
//        assert userInfo != null;
//
//        // TODO @kurtis: 10/8/21 here, all the userInfo is loaded at this point
////        activityPage.updateUserInfo(userInfo);
//
//        list.add(userInfo.email);
//
//        Log.d("wtf", userInfo.toString());
//      }
//    });
//
//    Log.d("wtf", list.toString());
//  }
//
//  /**
//   * @param fbUser   Firebase userInfo
//   * @param userInfo User class type temporarily stores userInfo info (name, email, usertype)
//   */
//  public void loadUserFullName(FirebaseUser fbUser, UserInfo userInfo) {
//
//    dbRef.child(fbUser.getUid());
//  }
//
//  /**
//   * @param fbUser   Firebase userInfo
//   * @param userInfo User class type temporarily stores userInfo info (name, email, usertype)
//   */
//  public void loadUserEmail(FirebaseUser fbUser, UserInfo userInfo) {
//
//    dbRef.child(fbUser.getUid());
//  }

}
