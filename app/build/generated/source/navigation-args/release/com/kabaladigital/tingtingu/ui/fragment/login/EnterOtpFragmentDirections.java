package com.kabaladigital.tingtingu.ui.fragment.login;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.kabaladigital.tingtingu.R;

public class EnterOtpFragmentDirections {
  private EnterOtpFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionEnterOTPFragmentToOperatorDetailFragment() {
    return new ActionOnlyNavDirections(R.id.action_enterOTPFragment_to_operatorDetailFragment);
  }

  @NonNull
  public static NavDirections actionEnterOTPFragmentToOperatorHomeFragment2() {
    return new ActionOnlyNavDirections(R.id.action_enterOTPFragment_to_operatorHomeFragment2);
  }
}
