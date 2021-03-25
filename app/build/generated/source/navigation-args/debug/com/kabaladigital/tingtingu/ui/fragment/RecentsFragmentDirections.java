package com.kabaladigital.tingtingu.ui.fragment;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.kabaladigital.tingtingu.R;

public class RecentsFragmentDirections {
  private RecentsFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionRecentFragmentToCGroupsFragment() {
    return new ActionOnlyNavDirections(R.id.action_recentFragment_to_cGroupsFragment);
  }

  @NonNull
  public static NavDirections actionRecentFragmentToContactsFragment() {
    return new ActionOnlyNavDirections(R.id.action_recentFragment_to_contactsFragment);
  }
}
