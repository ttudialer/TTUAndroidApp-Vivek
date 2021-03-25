package com.kabaladigital.tingtingu.ui.fragment.profile;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.kabaladigital.tingtingu.R;

public class ProfileStep2FragmentDirections {
  private ProfileStep2FragmentDirections() {
  }

  @NonNull
  public static NavDirections actionProfileStep2FragmentToProfileChooseMultiLanguageFragment() {
    return new ActionOnlyNavDirections(R.id.action_profileStep2Fragment_to_profileChooseMultiLanguageFragment);
  }
}
