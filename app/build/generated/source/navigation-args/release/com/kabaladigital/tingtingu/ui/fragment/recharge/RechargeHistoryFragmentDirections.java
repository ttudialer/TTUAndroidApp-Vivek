package com.kabaladigital.tingtingu.ui.fragment.recharge;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.kabaladigital.tingtingu.R;

public class RechargeHistoryFragmentDirections {
  private RechargeHistoryFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionRechargeHistoryFragmentToDthRechargeFragment() {
    return new ActionOnlyNavDirections(R.id.action_rechargeHistoryFragment_to_dthRechargeFragment);
  }

  @NonNull
  public static NavDirections actionRechargeHistoryFragmentToRechargeFragment() {
    return new ActionOnlyNavDirections(R.id.action_rechargeHistoryFragment_to_rechargeFragment);
  }
}
