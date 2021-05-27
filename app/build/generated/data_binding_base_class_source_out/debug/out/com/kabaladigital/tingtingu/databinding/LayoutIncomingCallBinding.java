// Generated by data binding compiler. Do not edit!
package com.kabaladigital.tingtingu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kabaladigital.tingtingu.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class LayoutIncomingCallBinding extends ViewDataBinding {
  @NonNull
  public final ImageView adImagePlaceholder;

  @NonNull
  public final FloatingActionButton btnAnswer;

  @NonNull
  public final FloatingActionButton btnReject;

  @NonNull
  public final FrameLayout frameLayout;

  @NonNull
  public final ImageView imageFullscreenPlaceholder;

  @NonNull
  public final CircleImageView imagePhoto;

  @NonNull
  public final ImageView imagePlaceholder;

  @NonNull
  public final ConstraintLayout incomingCallLayout;

  @NonNull
  public final TextView tvCallerName;

  @NonNull
  public final TextView tvCallerNumber;

  @NonNull
  public final TextView tvStatus;

  @NonNull
  public final VideoView videoFullscreenPlaceholder;

  @NonNull
  public final VideoView videoPlaceholder;

  protected LayoutIncomingCallBinding(Object _bindingComponent, View _root, int _localFieldCount,
      ImageView adImagePlaceholder, FloatingActionButton btnAnswer, FloatingActionButton btnReject,
      FrameLayout frameLayout, ImageView imageFullscreenPlaceholder, CircleImageView imagePhoto,
      ImageView imagePlaceholder, ConstraintLayout incomingCallLayout, TextView tvCallerName,
      TextView tvCallerNumber, TextView tvStatus, VideoView videoFullscreenPlaceholder,
      VideoView videoPlaceholder) {
    super(_bindingComponent, _root, _localFieldCount);
    this.adImagePlaceholder = adImagePlaceholder;
    this.btnAnswer = btnAnswer;
    this.btnReject = btnReject;
    this.frameLayout = frameLayout;
    this.imageFullscreenPlaceholder = imageFullscreenPlaceholder;
    this.imagePhoto = imagePhoto;
    this.imagePlaceholder = imagePlaceholder;
    this.incomingCallLayout = incomingCallLayout;
    this.tvCallerName = tvCallerName;
    this.tvCallerNumber = tvCallerNumber;
    this.tvStatus = tvStatus;
    this.videoFullscreenPlaceholder = videoFullscreenPlaceholder;
    this.videoPlaceholder = videoPlaceholder;
  }

  @NonNull
  public static LayoutIncomingCallBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.layout_incoming_call, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static LayoutIncomingCallBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<LayoutIncomingCallBinding>inflateInternal(inflater, R.layout.layout_incoming_call, root, attachToRoot, component);
  }

  @NonNull
  public static LayoutIncomingCallBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.layout_incoming_call, null, false, component)
   */
  @NonNull
  @Deprecated
  public static LayoutIncomingCallBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<LayoutIncomingCallBinding>inflateInternal(inflater, R.layout.layout_incoming_call, null, false, component);
  }

  public static LayoutIncomingCallBinding bind(@NonNull View view) {
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
  public static LayoutIncomingCallBinding bind(@NonNull View view, @Nullable Object component) {
    return (LayoutIncomingCallBinding)bind(component, view, R.layout.layout_incoming_call);
  }
}
