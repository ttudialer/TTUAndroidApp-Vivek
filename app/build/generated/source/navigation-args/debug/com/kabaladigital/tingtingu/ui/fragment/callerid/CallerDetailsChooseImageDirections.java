package com.kabaladigital.tingtingu.ui.fragment.callerid;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.kabaladigital.tingtingu.R;

public class CallerDetailsChooseImageDirections {
  private CallerDetailsChooseImageDirections() {
  }

  @NonNull
  public static NavDirections actionViewcalleridchooseimageToImageview() {
    return new ActionOnlyNavDirections(R.id.action_viewcalleridchooseimage_to_imageview);
  }
}
