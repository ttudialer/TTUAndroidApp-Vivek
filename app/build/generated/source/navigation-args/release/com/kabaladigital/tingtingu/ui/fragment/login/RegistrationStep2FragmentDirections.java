package com.kabaladigital.tingtingu.ui.fragment.login;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.kabaladigital.tingtingu.R;

public class RegistrationStep2FragmentDirections {
  private RegistrationStep2FragmentDirections() {
  }

  @NonNull
  public static NavDirections actionRegistrationStep2Fragment2ToChooseMultiLanguageFragment() {
    return new ActionOnlyNavDirections(R.id.action_registrationStep2Fragment2_to_chooseMultiLanguageFragment);
  }

  @NonNull
  public static NavDirections actionRegistrationStep2Fragment2ToCongoRegistrartionFragment() {
    return new ActionOnlyNavDirections(R.id.action_registrationStep2Fragment2_to_congoRegistrartionFragment);
  }
}
