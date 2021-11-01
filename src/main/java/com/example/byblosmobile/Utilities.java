package com.example.byblosmobile;

import android.util.Log;
import android.widget.EditText;

public class Utilities {

  /**
   * An simple black-list checker for human names.
   *
   * @param first (EditText obj) user input string which shouldn't contain characters like _*()/> etc.
   * @param last  (EditText obj) user input string
   * @return true if is a valid person name
   */
  protected static boolean nameChecker(EditText first, EditText last) {

    String textStr = first.getText().toString().trim() + last.getText().toString().trim();
    Character[] invalidChars = {' ', '!', '"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', ':',
            ';', '<', '=', '>', '?', '@', '[', '\\', ']', '^', '_', '`', '{', '|', '}', '~'};


    for (Character c : textStr.toCharArray()) {
      for (Character invalidC : invalidChars)
        if (c.equals(invalidC)) {

          Log.d("wtf", "-------- invalid chars -------- " + textStr);
          return false;
        }
    }

    return true;
  }
}
