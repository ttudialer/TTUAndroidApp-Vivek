package com.kabaladigital.tingtingu.ui.fragment;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.kabaladigital.tingtingu.R;

public class ContactsFragmentDirections {
  private ContactsFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionContactsFragmentToCGroupsFragment() {
    return new ActionOnlyNavDirections(R.id.action_contactsFragment_to_cGroupsFragment);
  }

  @NonNull
  public static NavDirections actionContactsFragmentToRecentsFragment() {
    return new ActionOnlyNavDirections(R.id.action_contactsFragment_to_recentsFragment);
  }
}
