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

  private View view7f0a012d;

  private View view7f0a00ce;

  private View view7f0a00d1;

  private View view7f0a01e3;

  private View view7f0a01e4;

  private View view7f0a01e5;

  private View view7f0a01e6;

  private View view7f0a01e7;

  private View view7f0a01e8;

  private View view7f0a01e9;

  private View view7f0a01ea;

  private View view7f0a01eb;

  private View view7f0a01ec;

  private View view7f0a01ee;

  private View view7f0a01ed;

  @UiThread
  public DialpadFragment_ViewBinding(final DialpadFragment target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.digits_edit_text, "field 'mDigits' and method 'onDigitsClick'");
    target.mDigits = Utils.castView(view, R.id.digits_edit_text, "field 'mDigits'", DigitsEditText.class);
    view7f0a012d = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onDigitsClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.button_call, "field 'mCallButton' and method 'call'");
    target.mCallButton = Utils.castView(view, R.id.button_call, "field 'mCallButton'", ImageView.class);
    view7f0a00ce = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.call(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.button_delete, "field 'mDelButton', method 'delNum', and method 'delAllNum'");
    target.mDelButton = Utils.castView(view, R.id.button_delete, "field 'mDelButton'", ImageView.class);
    view7f0a00d1 = view;
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
    view7f0a01e3 = view;
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
    view7f0a01e4 = view;
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
    view7f0a01e5 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.addChar(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.key_3, "method 'addChar'");
    view7f0a01e6 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.addChar(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.key_4, "method 'addChar'");
    view7f0a01e7 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.addChar(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.key_5, "method 'addChar'");
    view7f0a01e8 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.addChar(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.key_6, "method 'addChar'");
    view7f0a01e9 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.addChar(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.key_7, "method 'addChar'");
    view7f0a01ea = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.addChar(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.key_8, "method 'addChar'");
    view7f0a01eb = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.addChar(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.key_9, "method 'addChar'");
    view7f0a01ec = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.addChar(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.key_star, "method 'addChar'");
    view7f0a01ee = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.addChar(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.key_hex, "method 'addChar'");
    view7f0a01ed = view;
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

    view7f0a012d.setOnClickListener(null);
    view7f0a012d = null;
    view7f0a00ce.setOnClickListener(null);
    view7f0a00ce = null;
    view7f0a00d1.setOnClickListener(null);
    view7f0a00d1.setOnLongClickListener(null);
    view7f0a00d1 = null;
    view7f0a01e3.setOnClickListener(null);
    view7f0a01e3.setOnLongClickListener(null);
    view7f0a01e3 = null;
    view7f0a01e4.setOnClickListener(null);
    view7f0a01e4.setOnLongClickListener(null);
    view7f0a01e4 = null;
    view7f0a01e5.setOnClickListener(null);
    view7f0a01e5 = null;
    view7f0a01e6.setOnClickListener(null);
    view7f0a01e6 = null;
    view7f0a01e7.setOnClickListener(null);
    view7f0a01e7 = null;
    view7f0a01e8.setOnClickListener(null);
    view7f0a01e8 = null;
    view7f0a01e9.setOnClickListener(null);
    view7f0a01e9 = null;
    view7f0a01ea.setOnClickListener(null);
    view7f0a01ea = null;
    view7f0a01eb.setOnClickListener(null);
    view7f0a01eb = null;
    view7f0a01ec.setOnClickListener(null);
    view7f0a01ec = null;
    view7f0a01ee.setOnClickListener(null);
    view7f0a01ee = null;
    view7f0a01ed.setOnClickListener(null);
    view7f0a01ed = null;
  }
}
