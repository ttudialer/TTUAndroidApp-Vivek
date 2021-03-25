package com.kabaladigital.tingtingu.ui.fragment.login;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.kabaladigital.tingtingu.R;

public class OperatorDetailFragmentDirections {
  private OperatorDetailFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionOperatorDetailFragmentToOperatorSelectFragment() {
    return new ActionOnlyNavDirections(R.id.action_operatorDetailFragment_to_operatorSelectFragment);
  }

  @NonNull
  public static NavDirections actionOperatorDetailFragmentToRegistrationFragment() {
    return new ActionOnlyNavDirections(R.id.action_operatorDetailFragment_to_registrationFragment);
  }
}
