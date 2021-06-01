package com.kabaladigital.tingtingu.ui.fragment;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.kabaladigital.tingtingu.R;

public class CallerDetailsFragmentDirections {
  private CallerDetailsFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionViewcallerphotovideoToViewcalleridchoose() {
    return new ActionOnlyNavDirections(R.id.action_viewcallerphotovideo_to_viewcalleridchoose);
  }
}
