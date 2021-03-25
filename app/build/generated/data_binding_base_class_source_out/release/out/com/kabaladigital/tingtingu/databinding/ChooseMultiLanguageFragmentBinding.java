// Generated by data binding compiler. Do not edit!
package com.kabaladigital.tingtingu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.kabaladigital.tingtingu.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ChooseMultiLanguageFragmentBinding extends ViewDataBinding {
  @NonNull
  public final Button btnMultiLanguage;

  @NonNull
  public final CheckBox checkEng;

  @NonNull
  public final CheckBox checkHindi;

  @NonNull
  public final LinearLayout linearRadioButtons;

  @NonNull
  public final RecyclerView rvMultiLanguage;

  @NonNull
  public final View viewLine;

  protected ChooseMultiLanguageFragmentBinding(Object _bindingComponent, View _root,
      int _localFieldCount, Button btnMultiLanguage, CheckBox checkEng, CheckBox checkHindi,
      LinearLayout linearRadioButtons, RecyclerView rvMultiLanguage, View viewLine) {
    super(_bindingComponent, _root, _localFieldCount);
    this.btnMultiLanguage = btnMultiLanguage;
    this.checkEng = checkEng;
    this.checkHindi = checkHindi;
    this.linearRadioButtons = linearRadioButtons;
    this.rvMultiLanguage = rvMultiLanguage;
    this.viewLine = viewLine;
  }

  @NonNull
  public static ChooseMultiLanguageFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.choose_multi_language_fragment, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ChooseMultiLanguageFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ChooseMultiLanguageFragmentBinding>inflateInternal(inflater, R.layout.choose_multi_language_fragment, root, attachToRoot, component);
  }

  @NonNull
  public static ChooseMultiLanguageFragmentBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.choose_multi_language_fragment, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ChooseMultiLanguageFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ChooseMultiLanguageFragmentBinding>inflateInternal(inflater, R.layout.choose_multi_language_fragment, null, false, component);
  }

  public static ChooseMultiLanguageFragmentBinding bind(@NonNull View view) {
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
  public static ChooseMultiLanguageFragmentBinding bind(@NonNull View view,
      @Nullable Object component) {
    return (ChooseMultiLanguageFragmentBinding)bind(component, view, R.layout.choose_multi_language_fragment);
  }
}
