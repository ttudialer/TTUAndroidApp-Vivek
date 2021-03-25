package com.kabaladigital.tingtingu.ui.fragment.login;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.kabaladigital.tingtingu.R;

public class ChooseLanguageFragmentDirections {
  private ChooseLanguageFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionChooseLanguageFragmentToLoginFragment() {
    return new ActionOnlyNavDirections(R.id.action_chooseLanguageFragment_to_loginFragment);
  }

  @NonNull
  public static NavDirections actionChooseLanguageFragmentToMembershipMessageFragment() {
    return new ActionOnlyNavDirections(R.id.action_chooseLanguageFragment_to_membershipMessageFragment);
  }
}
