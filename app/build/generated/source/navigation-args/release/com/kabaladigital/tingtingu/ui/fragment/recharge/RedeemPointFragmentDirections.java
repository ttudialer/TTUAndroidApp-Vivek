package com.kabaladigital.tingtingu.ui.fragment.recharge;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.kabaladigital.tingtingu.R;

public class RedeemPointFragmentDirections {
  private RedeemPointFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionReedemPointFragmentToRechargeHistoryFragment() {
    return new ActionOnlyNavDirections(R.id.action_reedemPointFragment_to_rechargeHistoryFragment);
  }
}
