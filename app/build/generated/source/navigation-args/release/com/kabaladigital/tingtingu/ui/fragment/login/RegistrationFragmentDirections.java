package com.kabaladigital.tingtingu.ui.fragment.login;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.kabaladigital.tingtingu.R;

public class RegistrationFragmentDirections {
  private RegistrationFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionRegistrationFragmentToCongoRegistrartionFragment() {
    return new ActionOnlyNavDirections(R.id.action_registrationFragment_to_congoRegistrartionFragment);
  }

  @NonNull
  public static NavDirections actionRegistrationFragmentToRegistrationStep2Fragment2() {
    return new ActionOnlyNavDirections(R.id.action_registrationFragment_to_registrationStep2Fragment2);
  }
}
