package com.kabaladigital.tingtingu.ui.fragment.recharge;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.kabaladigital.tingtingu.R;

public class DthRechargeFragmentDirections {
  private DthRechargeFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionDthRechargeFragmentToDthProcessRechargeFragment() {
    return new ActionOnlyNavDirections(R.id.action_dthRechargeFragment_to_dthProcessRechargeFragment);
  }

  @NonNull
  public static NavDirections actionDthRechargeFragmentToOperatorSelectFragment2() {
    return new ActionOnlyNavDirections(R.id.action_dthRechargeFragment_to_operatorSelectFragment2);
  }
}
