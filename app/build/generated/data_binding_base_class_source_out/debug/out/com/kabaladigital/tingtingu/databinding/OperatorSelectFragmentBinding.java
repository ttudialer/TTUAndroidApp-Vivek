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

public abstract class OperatorSelectFragmentBinding extends ViewDataBinding {
  @NonNull
  public final RecyclerView rvSelectOperator;

  protected OperatorSelectFragmentBinding(Object _bindingComponent, View _root,
      int _localFieldCount, RecyclerView rvSelectOperator) {
    super(_bindingComponent, _root, _localFieldCount);
    this.rvSelectOperator = rvSelectOperator;
  }

  @NonNull
  public static OperatorSelectFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.operator_select_fragment, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static OperatorSelectFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<OperatorSelectFragmentBinding>inflateInternal(inflater, R.layout.operator_select_fragment, root, attachToRoot, component);
  }

  @NonNull
  public static OperatorSelectFragmentBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.operator_select_fragment, null, false, component)
   */
  @NonNull
  @Deprecated
  public static OperatorSelectFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<OperatorSelectFragmentBinding>inflateInternal(inflater, R.layout.operator_select_fragment, null, false, component);
  }

  public static OperatorSelectFragmentBinding bind(@NonNull View view) {
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
  public static OperatorSelectFragmentBinding bind(@NonNull View view, @Nullable Object component) {
    return (OperatorSelectFragmentBinding)bind(component, view, R.layout.operator_select_fragment);
  }
}
