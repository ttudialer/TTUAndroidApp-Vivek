package com.kabaladigital.tingtingu.ui.fragment.login;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.kabaladigital.tingtingu.R;

public class SplashScreenFragmentDirections {
  private SplashScreenFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionSplashFragmentToChooseLanguageFragment() {
    return new ActionOnlyNavDirections(R.id.action_splashFragment_to_chooseLanguageFragment);
  }

  @NonNull
  public static NavDirections actionSplashFragmentToLoginFragment() {
    return new ActionOnlyNavDirections(R.id.action_splashFragment_to_loginFragment);
  }

  @NonNull
  public static NavDirections actionSplashFragmentToOperatorDetailFragment() {
    return new ActionOnlyNavDirections(R.id.action_splashFragment_to_operatorDetailFragment);
  }

  @NonNull
  public static NavDirections actionSplashFragmentToRegistrationFragment() {
    return new ActionOnlyNavDirections(R.id.action_splashFragment_to_registrationFragment);
  }

  @NonNull
  public static NavDirections actionSplashFragmentToRegistrationStep2Fragment2() {
    return new ActionOnlyNavDirections(R.id.action_splashFragment_to_registrationStep2Fragment2);
  }
}
