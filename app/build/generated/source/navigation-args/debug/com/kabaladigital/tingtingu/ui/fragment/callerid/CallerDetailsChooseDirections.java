package com.kabaladigital.tingtingu.ui.fragment.callerid;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.kabaladigital.tingtingu.R;

public class CallerDetailsChooseDirections {
  private CallerDetailsChooseDirections() {
  }

  @NonNull
  public static NavDirections actionViewcalleridchooseToVideoview() {
    return new ActionOnlyNavDirections(R.id.action_viewcalleridchoose_to_videoview);
  }

  @NonNull
  public static NavDirections actionViewcalleridchooseToViewcalleridchooseimage() {
    return new ActionOnlyNavDirections(R.id.action_viewcalleridchoose_to_viewcalleridchooseimage);
  }

  @NonNull
  public static NavDirections actionViewcalleridchooseToViewcalleridchoosevideo() {
    return new ActionOnlyNavDirections(R.id.action_viewcalleridchoose_to_viewcalleridchoosevideo);
  }

  @NonNull
  public static NavDirections actionViewcalleridchooseToImageview() {
    return new ActionOnlyNavDirections(R.id.action_viewcalleridchoose_to_imageview);
  }
}
