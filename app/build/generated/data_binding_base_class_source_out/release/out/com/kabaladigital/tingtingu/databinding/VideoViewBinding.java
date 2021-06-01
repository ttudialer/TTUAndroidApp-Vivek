// Generated by data binding compiler. Do not edit!
package com.kabaladigital.tingtingu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.kabaladigital.tingtingu.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class VideoViewBinding extends ViewDataBinding {
  @NonNull
  public final VideoView VideoView;

  @NonNull
  public final Button btnUpdateTtuSetDefault;

  protected VideoViewBinding(Object _bindingComponent, View _root, int _localFieldCount,
      VideoView VideoView, Button btnUpdateTtuSetDefault) {
    super(_bindingComponent, _root, _localFieldCount);
    this.VideoView = VideoView;
    this.btnUpdateTtuSetDefault = btnUpdateTtuSetDefault;
  }

  @NonNull
  public static VideoViewBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root,
      boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.video_view, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static VideoViewBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root,
      boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<VideoViewBinding>inflateInternal(inflater, R.layout.video_view, root, attachToRoot, component);
  }

  @NonNull
  public static VideoViewBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.video_view, null, false, component)
   */
  @NonNull
  @Deprecated
  public static VideoViewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<VideoViewBinding>inflateInternal(inflater, R.layout.video_view, null, false, component);
  }

  public static VideoViewBinding bind(@NonNull View view) {
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
  public static VideoViewBinding bind(@NonNull View view, @Nullable Object component) {
    return (VideoViewBinding)bind(component, view, R.layout.video_view);
  }
}