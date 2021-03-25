package com.kabaladigital.tingtingu.ui.fragment.login;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.kabaladigital.tingtingu.R;

public class MembershipMessageFragmentDirections {
  private MembershipMessageFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionMembershipMessageFragmentToLoginFragment() {
    return new ActionOnlyNavDirections(R.id.action_membershipMessageFragment_to_loginFragment);
  }
}
