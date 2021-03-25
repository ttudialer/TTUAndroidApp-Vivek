package com.kabaladigital.tingtingu.ui.fragment;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.kabaladigital.tingtingu.R;

public class CGroupsFragmentDirections {
  private CGroupsFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionCGroupsFragmentToContactsFragment() {
    return new ActionOnlyNavDirections(R.id.action_cGroupsFragment_to_contactsFragment);
  }

  @NonNull
  public static NavDirections actionCGroupsFragmentToRecentsFragment() {
    return new ActionOnlyNavDirections(R.id.action_cGroupsFragment_to_recentsFragment);
  }
}
