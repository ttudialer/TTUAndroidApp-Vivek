// Generated by data binding compiler. Do not edit!
package com.kabaladigital.tingtingu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import com.kabaladigital.tingtingu.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class CallerDetailsFragmentChooseBinding extends ViewDataBinding {
  @NonNull
  public final VideoView VideoView1;

  @NonNull
  public final Button btnTvCrVP;

  @NonNull
  public final Button btnTvImpVP;

  @NonNull
  public final LinearLayout llCaller;

  @NonNull
  public final ImageView simpleImageView;

  @NonNull
  public final TabLayout tabLayout;

  @NonNull
  public final TextView tvTopName;

  @NonNull
  public final ViewPager viewPager;

  @NonNull
  public final RelativeLayout waitSpinner;

  protected CallerDetailsFragmentChooseBinding(Object _bindingComponent, View _root,
      int _localFieldCount, VideoView VideoView1, Button btnTvCrVP, Button btnTvImpVP,
      LinearLayout llCaller, ImageView simpleImageView, TabLayout tabLayout, TextView tvTopName,
      ViewPager viewPager, RelativeLayout waitSpinner) {
    super(_bindingComponent, _root, _localFieldCount);
    this.VideoView1 = VideoView1;
    this.btnTvCrVP = btnTvCrVP;
    this.btnTvImpVP = btnTvImpVP;
    this.llCaller = llCaller;
    this.simpleImageView = simpleImageView;
    this.tabLayout = tabLayout;
    this.tvTopName = tvTopName;
    this.viewPager = viewPager;
    this.waitSpinner = waitSpinner;
  }

  @NonNull
  public static CallerDetailsFragmentChooseBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.caller_details_fragment_choose, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static CallerDetailsFragmentChooseBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<CallerDetailsFragmentChooseBinding>inflateInternal(inflater, R.layout.caller_details_fragment_choose, root, attachToRoot, component);
  }

  @NonNull
  public static CallerDetailsFragmentChooseBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.caller_details_fragment_choose, null, false, component)
   */
  @NonNull
  @Deprecated
  public static CallerDetailsFragmentChooseBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<CallerDetailsFragmentChooseBinding>inflateInternal(inflater, R.layout.caller_details_fragment_choose, null, false, component);
  }

  public static CallerDetailsFragmentChooseBinding bind(@NonNull View view) {
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
  public static CallerDetailsFragmentChooseBinding bind(@NonNull View view,
      @Nullable Object component) {
    return (CallerDetailsFragmentChooseBinding)bind(component, view, R.layout.caller_details_fragment_choose);
  }
}
