package com.kabaladigital.tingtingu.ui.fragment.callerid;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.kabaladigital.tingtingu.R;

public class CallerDetailsChooseVideoDirections {
  private CallerDetailsChooseVideoDirections() {
  }

  @NonNull
  public static NavDirections actionViewcalleridchoosevideoToVideoview() {
    return new ActionOnlyNavDirections(R.id.action_viewcalleridchoosevideo_to_videoview);
  }
}
