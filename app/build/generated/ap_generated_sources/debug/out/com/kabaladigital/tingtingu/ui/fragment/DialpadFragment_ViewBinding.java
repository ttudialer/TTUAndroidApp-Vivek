// Generated code from Butter Knife. Do not modify!
package com.kabaladigital.tingtingu.ui.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.ui.widgets.DialpadView;
import com.kabaladigital.tingtingu.ui.widgets.DigitsEditText;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DialpadFragment_ViewBinding implements Unbinder {
  private DialpadFragment target;

  private View view7f0a00dc;

  private View view7f0a009c;

  private View view7f0a009f;

  private View view7f0a0160;

  private View view7f0a0161;

  private View view7f0a0162;

  private View view7f0a0163;

  private View view7f0a0164;

  private View view7f0a0165;

  private View view7f0a0166;

  private View view7f0a0167;

  private View view7f0a0168;

  private View view7f0a0169;

  private View view7f0a016b;

  private View view7f0a016a;

  @UiThread
  public DialpadFragment_ViewBinding(final DialpadFragment target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.digits_edit_text, "field 'mDigits' and method 'onDigitsClick'");
    target.mDigits = Utils.castView(view, R.id.digits_edit_text, "field 'mDigits'", DigitsEditText.class);
    view7f0a00dc = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onDigitsClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.button_call, "field 'mCallButton' and method 'call'");
    target.mCallButton = Utils.castView(view, R.id.button_call, "field 'mCallButton'", ImageView.class);
    view7f0a009c = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.call(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.button_delete, "field 'mDelButton', method 'delNum', and method 'delAllNum'");
    target.mDelButton = Utils.castView(view, R.id.button_delete, "field 'mDelButton'", ImageView.class);
    view7f0a009f = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.delNum(p0);
      }
    });
    view.setOnLongClickListener(new View.OnLongClickListener() {
      @Override
      public boolean onLongClick(View p0) {
        return target.delAllNum(p0);
      }
    });
    target.mNumbersTable = Utils.findRequiredViewAsType(source, R.id.dialpad, "field 'mNumbersTable'", TableLayout.class);
    target.mDialpadView = Utils.findRequiredViewAsType(source, R.id.dialpad_view, "field 'mDialpadView'", DialpadView.class);
    view = Utils.findRequiredView(source, R.id.key_0, "method 'addChar' and method 'addPlus'");
    view7f0a0160 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.addChar(p0);
      }
    });
    view.setOnLongClickListener(new View.OnLongClickListener() {
      @Override
      public boolean onLongClick(View p0) {
        return target.addPlus(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.key_1, "method 'addChar' and method 'startVoiceMail'");
    view7f0a0161 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.addChar(p0);
      }
    });
    view.setOnLongClickListener(new View.OnLongClickListener() {
      @Override
      public boolean onLongClick(View p0) {
        return target.startVoiceMail(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.key_2, "method 'addChar'");
    view7f0a0162 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.addChar(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.key_3, "method 'addChar'");
    view7f0a0163 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.addChar(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.key_4, "method 'addChar'");
    view7f0a0164 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.addChar(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.key_5, "method 'addChar'");
    view7f0a0165 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.addChar(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.key_6, "method 'addChar'");
    view7f0a0166 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.addChar(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.key_7, "method 'addChar'");
    view7f0a0167 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.addChar(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.key_8, "method 'addChar'");
    view7f0a0168 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.addChar(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.key_9, "method 'addChar'");
    view7f0a0169 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.addChar(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.key_star, "method 'addChar'");
    view7f0a016b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.addChar(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.key_hex, "method 'addChar'");
    view7f0a016a = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.addChar(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    DialpadFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mDigits = null;
    target.mCallButton = null;
    target.mDelButton = null;
    target.mNumbersTable = null;
    target.mDialpadView = null;

    view7f0a00dc.setOnClickListener(null);
    view7f0a00dc = null;
    view7f0a009c.setOnClickListener(null);
    view7f0a009c = null;
    view7f0a009f.setOnClickListener(null);
    view7f0a009f.setOnLongClickListener(null);
    view7f0a009f = null;
    view7f0a0160.setOnClickListener(null);
    view7f0a0160.setOnLongClickListener(null);
    view7f0a0160 = null;
    view7f0a0161.setOnClickListener(null);
    view7f0a0161.setOnLongClickListener(null);
    view7f0a0161 = null;
    view7f0a0162.setOnClickListener(null);
    view7f0a0162 = null;
    view7f0a0163.setOnClickListener(null);
    view7f0a0163 = null;
    view7f0a0164.setOnClickListener(null);
    view7f0a0164 = null;
    view7f0a0165.setOnClickListener(null);
    view7f0a0165 = null;
    view7f0a0166.setOnClickListener(null);
    view7f0a0166 = null;
    view7f0a0167.setOnClickListener(null);
    view7f0a0167 = null;
    view7f0a0168.setOnClickListener(null);
    view7f0a0168 = null;
    view7f0a0169.setOnClickListener(null);
    view7f0a0169 = null;
    view7f0a016b.setOnClickListener(null);
    view7f0a016b = null;
    view7f0a016a.setOnClickListener(null);
    view7f0a016a = null;
  }
}
