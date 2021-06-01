// Generated by data binding compiler. Do not edit!
package com.kabaladigital.tingtingu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kabaladigital.tingtingu.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class OnGoingCallBinding extends ViewDataBinding {
  @NonNull
  public final ImageView adImageBannerPlaceholder;

  @NonNull
  public final ImageView adImagePlaceholder;

  @NonNull
  public final ImageView buttonAddCall;

  @NonNull
  public final ImageView buttonHold;

  @NonNull
  public final ImageView buttonKeypad;

  @NonNull
  public final ImageView buttonMerge;

  @NonNull
  public final ImageView buttonMute;

  @NonNull
  public final ImageView buttonSpeaker;

  @NonNull
  public final ImageView buttonSwap;

  @NonNull
  public final FrameLayout dialerFragment;

  @NonNull
  public final LinearLayout dialerLayout;

  @NonNull
  public final FrameLayout frameLayout;

  @NonNull
  public final CircleImageView imagePhoto;

  @NonNull
  public final ImageView imagePlaceholder;

  @NonNull
  public final ConstraintLayout ongoingCallLayout;

  @NonNull
  public final FloatingActionButton rejectBtn;

  @NonNull
  public final RecyclerView rvCalls;

  @NonNull
  public final TextView textCaller;

  @NonNull
  public final TextView textCallerNumber;

  @NonNull
  public final TextView textStatus;

  @NonNull
  public final TextView textStopwatch;

  @NonNull
  public final VideoView videoPlaceholder;

  protected OnGoingCallBinding(Object _bindingComponent, View _root, int _localFieldCount,
      ImageView adImageBannerPlaceholder, ImageView adImagePlaceholder, ImageView buttonAddCall,
      ImageView buttonHold, ImageView buttonKeypad, ImageView buttonMerge, ImageView buttonMute,
      ImageView buttonSpeaker, ImageView buttonSwap, FrameLayout dialerFragment,
      LinearLayout dialerLayout, FrameLayout frameLayout, CircleImageView imagePhoto,
      ImageView imagePlaceholder, ConstraintLayout ongoingCallLayout,
      FloatingActionButton rejectBtn, RecyclerView rvCalls, TextView textCaller,
      TextView textCallerNumber, TextView textStatus, TextView textStopwatch,
      VideoView videoPlaceholder) {
    super(_bindingComponent, _root, _localFieldCount);
    this.adImageBannerPlaceholder = adImageBannerPlaceholder;
    this.adImagePlaceholder = adImagePlaceholder;
    this.buttonAddCall = buttonAddCall;
    this.buttonHold = buttonHold;
    this.buttonKeypad = buttonKeypad;
    this.buttonMerge = buttonMerge;
    this.buttonMute = buttonMute;
    this.buttonSpeaker = buttonSpeaker;
    this.buttonSwap = buttonSwap;
    this.dialerFragment = dialerFragment;
    this.dialerLayout = dialerLayout;
    this.frameLayout = frameLayout;
    this.imagePhoto = imagePhoto;
    this.imagePlaceholder = imagePlaceholder;
    this.ongoingCallLayout = ongoingCallLayout;
    this.rejectBtn = rejectBtn;
    this.rvCalls = rvCalls;
    this.textCaller = textCaller;
    this.textCallerNumber = textCallerNumber;
    this.textStatus = textStatus;
    this.textStopwatch = textStopwatch;
    this.videoPlaceholder = videoPlaceholder;
  }

  @NonNull
  public static OnGoingCallBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.on_going_call, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static OnGoingCallBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<OnGoingCallBinding>inflateInternal(inflater, R.layout.on_going_call, root, attachToRoot, component);
  }

  @NonNull
  public static OnGoingCallBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.on_going_call, null, false, component)
   */
  @NonNull
  @Deprecated
  public static OnGoingCallBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<OnGoingCallBinding>inflateInternal(inflater, R.layout.on_going_call, null, false, component);
  }

  public static OnGoingCallBinding bind(@NonNull View view) {
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
  public static OnGoingCallBinding bind(@NonNull View view, @Nullable Object component) {
    return (OnGoingCallBinding)bind(component, view, R.layout.on_going_call);
  }
}