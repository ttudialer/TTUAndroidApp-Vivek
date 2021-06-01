// Generated code from Butter Knife. Do not modify!
package com.kabaladigital.tingtingu.ui.widgets;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.kabaladigital.tingtingu.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ListItem_ViewBinding implements Unbinder {
  private ListItem target;

  @UiThread
  public ListItem_ViewBinding(ListItem target) {
    this(target, target);
  }

  @UiThread
  public ListItem_ViewBinding(ListItem target, View source) {
    this.target = target;

    target.mTitleText = Utils.findRequiredViewAsType(source, R.id.item_title, "field 'mTitleText'", TextView.class);
    target.mDescText = Utils.findRequiredViewAsType(source, R.id.item_desc, "field 'mDescText'", TextView.class);
    target.mIcon = Utils.findRequiredViewAsType(source, R.id.item_image, "field 'mIcon'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ListItem target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mTitleText = null;
    target.mDescText = null;
    target.mIcon = null;
  }
}
