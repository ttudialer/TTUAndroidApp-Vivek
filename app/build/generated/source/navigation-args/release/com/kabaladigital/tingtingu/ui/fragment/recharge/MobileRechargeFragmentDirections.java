package com.kabaladigital.tingtingu.ui.fragment.recharge;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.kabaladigital.tingtingu.R;

public class MobileRechargeFragmentDirections {
  private MobileRechargeFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionRechargeFragmentToDthProcessRechargeFragment() {
    return new ActionOnlyNavDirections(R.id.action_rechargeFragment_to_dthProcessRechargeFragment);
  }

  @NonNull
  public static NavDirections actionRechargeFragmentToOperatorSelectFragment2() {
    return new ActionOnlyNavDirections(R.id.action_rechargeFragment_to_operatorSelectFragment2);
  }
}
