// Generated by data binding compiler. Do not edit!
package com.kabaladigital.tingtingu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.kabaladigital.tingtingu.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class CallerDetailsFragmentChooseVideoBinding extends ViewDataBinding {
  @NonNull
  public final RecyclerView recyclerView;

  protected CallerDetailsFragmentChooseVideoBinding(Object _bindingComponent, View _root,
      int _localFieldCount, RecyclerView recyclerView) {
    super(_bindingComponent, _root, _localFieldCount);
    this.recyclerView = recyclerView;
  }

  @NonNull
  public static CallerDetailsFragmentChooseVideoBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.caller_details_fragment_choose_video, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static CallerDetailsFragmentChooseVideoBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<CallerDetailsFragmentChooseVideoBinding>inflateInternal(inflater, R.layout.caller_details_fragment_choose_video, root, attachToRoot, component);
  }

  @NonNull
  public static CallerDetailsFragmentChooseVideoBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.caller_details_fragment_choose_video, null, false, component)
   */
  @NonNull
  @Deprecated
  public static CallerDetailsFragmentChooseVideoBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<CallerDetailsFragmentChooseVideoBinding>inflateInternal(inflater, R.layout.caller_details_fragment_choose_video, null, false, component);
  }

  public static CallerDetailsFragmentChooseVideoBinding bind(@NonNull View view) {
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
  public static CallerDetailsFragmentChooseVideoBinding bind(@NonNull View view,
      @Nullable Object component) {
    return (CallerDetailsFragmentChooseVideoBinding)bind(component, view, R.layout.caller_details_fragment_choose_video);
  }
}
