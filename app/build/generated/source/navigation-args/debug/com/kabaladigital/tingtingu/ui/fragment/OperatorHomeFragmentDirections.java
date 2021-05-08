package com.kabaladigital.tingtingu.ui.fragment;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.kabaladigital.tingtingu.R;

public class OperatorHomeFragmentDirections {
  private OperatorHomeFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionOperatorHomeFragmentToInviteFriendFragment() {
    return new ActionOnlyNavDirections(R.id.action_operatorHomeFragment_to_inviteFriendFragment);
  }

  @NonNull
  public static NavDirections actionOperatorHomeFragmentToEarnedPointHistoryFragment() {
    return new ActionOnlyNavDirections(R.id.action_operatorHomeFragment_to_earnedPointHistoryFragment);
  }

  @NonNull
  public static NavDirections actionOperatorHomeFragmentToReedemPointFragment() {
    return new ActionOnlyNavDirections(R.id.action_operatorHomeFragment_to_reedemPointFragment);
  }

  @NonNull
  public static NavDirections actionOperatorHomeFragmentToProfileFragment() {
    return new ActionOnlyNavDirections(R.id.action_operatorHomeFragment_to_profileFragment);
  }

  @NonNull
  public static NavDirections actionOperatorHomeFragmentToSettingFragment() {
    return new ActionOnlyNavDirections(R.id.action_operatorHomeFragment_to_settingFragment);
  }

  @NonNull
  public static NavDirections actionOperatorHomeFragmentToMemberShipFragment() {
    return new ActionOnlyNavDirections(R.id.action_operatorHomeFragment_to_memberShipFragment);
  }

  @NonNull
  public static NavDirections actionOperatorHomeFragmentToSurveyFragment() {
    return new ActionOnlyNavDirections(R.id.action_operatorHomeFragment_to_surveyFragment);
  }

  @NonNull
  public static NavDirections actionOperatorHomeFragmentToViewcallerphotovideo() {
    return new ActionOnlyNavDirections(R.id.action_operatorHomeFragment_to_viewcallerphotovideo);
  }
}
