package com.kabaladigital.tingtingu.ui.fragment.points;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.kabaladigital.tingtingu.R;

public class EarnedPointHistoryFragmentDirections {
  private EarnedPointHistoryFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionEarnedPointHistoryFragmentToEarnedPointCreditHistoryFragment() {
    return new ActionOnlyNavDirections(R.id.action_earnedPointHistoryFragment_to_earnedPointCreditHistoryFragment);
  }
}
