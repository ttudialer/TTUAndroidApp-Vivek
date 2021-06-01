package com.kabaladigital.tingtingu.ui.fragment.profile;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.kabaladigital.tingtingu.R;

public class ProfileFragmentDirections {
  private ProfileFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionProfileFragmentToProfileStep2Fragment2() {
    return new ActionOnlyNavDirections(R.id.action_profileFragment_to_profileStep2Fragment2);
  }
}
