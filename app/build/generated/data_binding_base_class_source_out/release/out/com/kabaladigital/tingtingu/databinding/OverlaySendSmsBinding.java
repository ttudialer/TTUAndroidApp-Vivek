// Generated by data binding compiler. Do not edit!
package com.kabaladigital.tingtingu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.kabaladigital.tingtingu.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class OverlaySendSmsBinding extends ViewDataBinding {
  @NonNull
  public final View bottomSmsDivider;

  @NonNull
  public final Button buttonSendSms;

  @NonNull
  public final EditText editSms;

  @NonNull
  public final ConstraintLayout overlaySendSms;

  @NonNull
  public final RecyclerView rvMessagesList;

  @NonNull
  public final TextView tvRejectWithSms;

  protected OverlaySendSmsBinding(Object _bindingComponent, View _root, int _localFieldCount,
      View bottomSmsDivider, Button buttonSendSms, EditText editSms,
      ConstraintLayout overlaySendSms, RecyclerView rvMessagesList, TextView tvRejectWithSms) {
    super(_bindingComponent, _root, _localFieldCount);
    this.bottomSmsDivider = bottomSmsDivider;
    this.buttonSendSms = buttonSendSms;
    this.editSms = editSms;
    this.overlaySendSms = overlaySendSms;
    this.rvMessagesList = rvMessagesList;
    this.tvRejectWithSms = tvRejectWithSms;
  }

  @NonNull
  public static OverlaySendSmsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.overlay_send_sms, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static OverlaySendSmsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<OverlaySendSmsBinding>inflateInternal(inflater, R.layout.overlay_send_sms, root, attachToRoot, component);
  }

  @NonNull
  public static OverlaySendSmsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.overlay_send_sms, null, false, component)
   */
  @NonNull
  @Deprecated
  public static OverlaySendSmsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<OverlaySendSmsBinding>inflateInternal(inflater, R.layout.overlay_send_sms, null, false, component);
  }

  public static OverlaySendSmsBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.bind(view, component)
   */
  @Deprecated
  public static OverlaySendSmsBinding bind(@NonNull View view, @Nullable Object component) {
    return (OverlaySendSmsBinding)bind(component, view, R.layout.overlay_send_sms);
  }
}