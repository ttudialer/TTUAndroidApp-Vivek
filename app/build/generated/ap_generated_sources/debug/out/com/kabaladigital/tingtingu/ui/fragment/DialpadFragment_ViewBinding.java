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

<<<<<<< HEAD
  private View view7f0a012b;

  private View view7f0a00cc;

  private View view7f0a00cf;

  private View view7f0a01df;

  private View view7f0a01e0;

  private View view7f0a01e1;
=======
  private View view7f0a00de;

  private View view7f0a009e;

  private View view7f0a00a1;
>>>>>>> 690f5fef70b7b9b3265bdda514b7c8ba275791dd

  private View view7f0a01e2;

  private View view7f0a01e3;

  private View view7f0a01e4;

  private View view7f0a01e5;

  private View view7f0a01e6;

  private View view7f0a01e7;

  private View view7f0a01e8;

<<<<<<< HEAD
  private View view7f0a01ea;

  private View view7f0a01e9;
=======
  private View view7f0a016a;

  private View view7f0a016b;

  private View view7f0a016c;

  private View view7f0a016e;

  private View view7f0a016d;
>>>>>>> 690f5fef70b7b9b3265bdda514b7c8ba275791dd

  @UiThread
  public DialpadFragment_ViewBinding(final DialpadFragment target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.digits_edit_text, "field 'mDigits' and method 'onDigitsClick'");
    target.mDigits = Utils.castView(view, R.id.digits_edit_text, "field 'mDigits'", DigitsEditText.class);
<<<<<<< HEAD
    view7f0a012b = view;
=======
    view7f0a00de = view;
>>>>>>> 690f5fef70b7b9b3265bdda514b7c8ba275791dd
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onDigitsClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.button_call, "field 'mCallButton' and method 'call'");
    target.mCallButton = Utils.castView(view, R.id.button_call, "field 'mCallButton'", ImageView.class);
<<<<<<< HEAD
    view7f0a00cc = view;
=======
    view7f0a009e = view;
>>>>>>> 690f5fef70b7b9b3265bdda514b7c8ba275791dd
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.call(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.button_delete, "field 'mDelButton', method 'delNum', and method 'delAllNum'");
    target.mDelButton = Utils.castView(view, R.id.button_delete, "field 'mDelButton'", ImageView.class);
<<<<<<< HEAD
    view7f0a00cf = view;
=======
    view7f0a00a1 = view;
>>>>>>> 690f5fef70b7b9b3265bdda514b7c8ba275791dd
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
<<<<<<< HEAD
    view7f0a01df = view;
=======
    view7f0a0163 = view;
>>>>>>> 690f5fef70b7b9b3265bdda514b7c8ba275791dd
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
<<<<<<< HEAD
    view7f0a01e0 = view;
=======
    view7f0a0164 = view;
>>>>>>> 690f5fef70b7b9b3265bdda514b7c8ba275791dd
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
<<<<<<< HEAD
    view7f0a01e1 = view;
=======
    view7f0a0165 = view;
>>>>>>> 690f5fef70b7b9b3265bdda514b7c8ba275791dd
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.addChar(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.key_3, "method 'addChar'");
<<<<<<< HEAD
    view7f0a01e2 = view;
=======
    view7f0a0166 = view;
>>>>>>> 690f5fef70b7b9b3265bdda514b7c8ba275791dd
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.addChar(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.key_4, "method 'addChar'");
<<<<<<< HEAD
    view7f0a01e3 = view;
=======
    view7f0a0167 = view;
>>>>>>> 690f5fef70b7b9b3265bdda514b7c8ba275791dd
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.addChar(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.key_5, "method 'addChar'");
<<<<<<< HEAD
    view7f0a01e4 = view;
=======
    view7f0a0168 = view;
>>>>>>> 690f5fef70b7b9b3265bdda514b7c8ba275791dd
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.addChar(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.key_6, "method 'addChar'");
<<<<<<< HEAD
    view7f0a01e5 = view;
=======
    view7f0a0169 = view;
>>>>>>> 690f5fef70b7b9b3265bdda514b7c8ba275791dd
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.addChar(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.key_7, "method 'addChar'");
<<<<<<< HEAD
    view7f0a01e6 = view;
=======
    view7f0a016a = view;
>>>>>>> 690f5fef70b7b9b3265bdda514b7c8ba275791dd
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.addChar(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.key_8, "method 'addChar'");
<<<<<<< HEAD
    view7f0a01e7 = view;
=======
    view7f0a016b = view;
>>>>>>> 690f5fef70b7b9b3265bdda514b7c8ba275791dd
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.addChar(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.key_9, "method 'addChar'");
<<<<<<< HEAD
    view7f0a01e8 = view;
=======
    view7f0a016c = view;
>>>>>>> 690f5fef70b7b9b3265bdda514b7c8ba275791dd
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.addChar(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.key_star, "method 'addChar'");
<<<<<<< HEAD
    view7f0a01ea = view;
=======
    view7f0a016e = view;
>>>>>>> 690f5fef70b7b9b3265bdda514b7c8ba275791dd
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.addChar(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.key_hex, "method 'addChar'");
<<<<<<< HEAD
    view7f0a01e9 = view;
=======
    view7f0a016d = view;
>>>>>>> 690f5fef70b7b9b3265bdda514b7c8ba275791dd
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

<<<<<<< HEAD
    view7f0a012b.setOnClickListener(null);
    view7f0a012b = null;
    view7f0a00cc.setOnClickListener(null);
    view7f0a00cc = null;
    view7f0a00cf.setOnClickListener(null);
    view7f0a00cf.setOnLongClickListener(null);
    view7f0a00cf = null;
    view7f0a01df.setOnClickListener(null);
    view7f0a01df.setOnLongClickListener(null);
    view7f0a01df = null;
    view7f0a01e0.setOnClickListener(null);
    view7f0a01e0.setOnLongClickListener(null);
    view7f0a01e0 = null;
    view7f0a01e1.setOnClickListener(null);
    view7f0a01e1 = null;
    view7f0a01e2.setOnClickListener(null);
    view7f0a01e2 = null;
    view7f0a01e3.setOnClickListener(null);
    view7f0a01e3 = null;
    view7f0a01e4.setOnClickListener(null);
    view7f0a01e4 = null;
    view7f0a01e5.setOnClickListener(null);
    view7f0a01e5 = null;
    view7f0a01e6.setOnClickListener(null);
    view7f0a01e6 = null;
    view7f0a01e7.setOnClickListener(null);
    view7f0a01e7 = null;
    view7f0a01e8.setOnClickListener(null);
    view7f0a01e8 = null;
    view7f0a01ea.setOnClickListener(null);
    view7f0a01ea = null;
    view7f0a01e9.setOnClickListener(null);
    view7f0a01e9 = null;
=======
    view7f0a00de.setOnClickListener(null);
    view7f0a00de = null;
    view7f0a009e.setOnClickListener(null);
    view7f0a009e = null;
    view7f0a00a1.setOnClickListener(null);
    view7f0a00a1.setOnLongClickListener(null);
    view7f0a00a1 = null;
    view7f0a0163.setOnClickListener(null);
    view7f0a0163.setOnLongClickListener(null);
    view7f0a0163 = null;
    view7f0a0164.setOnClickListener(null);
    view7f0a0164.setOnLongClickListener(null);
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
    view7f0a016a.setOnClickListener(null);
    view7f0a016a = null;
    view7f0a016b.setOnClickListener(null);
    view7f0a016b = null;
    view7f0a016c.setOnClickListener(null);
    view7f0a016c = null;
    view7f0a016e.setOnClickListener(null);
    view7f0a016e = null;
    view7f0a016d.setOnClickListener(null);
    view7f0a016d = null;
>>>>>>> 690f5fef70b7b9b3265bdda514b7c8ba275791dd
  }
}
