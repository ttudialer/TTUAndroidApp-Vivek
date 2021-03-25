// Generated by data binding compiler. Do not edit!
package com.kabaladigital.tingtingu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.kabaladigital.tingtingu.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class RegistrationStep2FragmentBinding extends ViewDataBinding {
  @NonNull
  public final Button btnProvideLater;

  @NonNull
  public final Button btnRegistration;

  @NonNull
  public final EditText etAddressOne;

  @NonNull
  public final EditText etAddressThree;

  @NonNull
  public final EditText etAddressTwo;

  @NonNull
  public final EditText etEmail;

  @NonNull
  public final TextView etLanguageSelection;

  @NonNull
  public final LinearLayout layoutBottomButton;

  @NonNull
  public final AppCompatSpinner spinnerEducation;

  @NonNull
  public final AppCompatSpinner spinnerProfession;

  protected RegistrationStep2FragmentBinding(Object _bindingComponent, View _root,
      int _localFieldCount, Button btnProvideLater, Button btnRegistration, EditText etAddressOne,
      EditText etAddressThree, EditText etAddressTwo, EditText etEmail,
      TextView etLanguageSelection, LinearLayout layoutBottomButton,
      AppCompatSpinner spinnerEducation, AppCompatSpinner spinnerProfession) {
    super(_bindingComponent, _root, _localFieldCount);
    this.btnProvideLater = btnProvideLater;
    this.btnRegistration = btnRegistration;
    this.etAddressOne = etAddressOne;
    this.etAddressThree = etAddressThree;
    this.etAddressTwo = etAddressTwo;
    this.etEmail = etEmail;
    this.etLanguageSelection = etLanguageSelection;
    this.layoutBottomButton = layoutBottomButton;
    this.spinnerEducation = spinnerEducation;
    this.spinnerProfession = spinnerProfession;
  }

  @NonNull
  public static RegistrationStep2FragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.registration_step2_fragment, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static RegistrationStep2FragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<RegistrationStep2FragmentBinding>inflateInternal(inflater, R.layout.registration_step2_fragment, root, attachToRoot, component);
  }

  @NonNull
  public static RegistrationStep2FragmentBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.registration_step2_fragment, null, false, component)
   */
  @NonNull
  @Deprecated
  public static RegistrationStep2FragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<RegistrationStep2FragmentBinding>inflateInternal(inflater, R.layout.registration_step2_fragment, null, false, component);
  }

  public static RegistrationStep2FragmentBinding bind(@NonNull View view) {
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
  public static RegistrationStep2FragmentBinding bind(@NonNull View view,
      @Nullable Object component) {
    return (RegistrationStep2FragmentBinding)bind(component, view, R.layout.registration_step2_fragment);
  }
}
