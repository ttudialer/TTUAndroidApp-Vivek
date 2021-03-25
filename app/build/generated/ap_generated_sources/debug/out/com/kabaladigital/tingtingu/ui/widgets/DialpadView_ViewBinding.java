// Generated code from Butter Knife. Do not modify!
package com.kabaladigital.tingtingu.ui.widgets;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.kabaladigital.tingtingu.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DialpadView_ViewBinding implements Unbinder {
  private DialpadView target;

  @UiThread
  public DialpadView_ViewBinding(DialpadView target) {
    this(target, target);
  }

  @UiThread
  public DialpadView_ViewBinding(DialpadView target, View source) {
    this.target = target;

    target.mDigits = Utils.findRequiredViewAsType(source, R.id.digits_edit_text, "field 'mDigits'", EditText.class);
    target.mDelete = Utils.findRequiredViewAsType(source, R.id.button_delete, "field 'mDelete'", ImageButton.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DialpadView target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mDigits = null;
    target.mDelete = null;
  }
}
