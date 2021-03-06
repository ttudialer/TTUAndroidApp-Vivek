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
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.kabaladigital.tingtingu.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class InviteFriendFragmentBinding extends ViewDataBinding {
  @NonNull
  public final Button btnShare;

  @NonNull
  public final ImageView ivArrow;

  @NonNull
  public final ImageView ivFriend;

  @NonNull
  public final ImageView ivShare;

  @NonNull
  public final ImageView ivYou;

  @NonNull
  public final RelativeLayout layoutInvite;

  @NonNull
  public final RelativeLayout layoutShare;

  @NonNull
  public final LinearLayout llInviteFriendReferralCopy;

  @NonNull
  public final TextView tvAndText;

  @NonNull
  public final TextView tvEarnText;

  @NonNull
  public final TextView tvFriend;

  @NonNull
  public final TextView tvReferDayText;

  @NonNull
  public final TextView tvReferText;

  @NonNull
  public final TextView tvReferalCode;

  @NonNull
  public final TextView tvU;

  @NonNull
  public final TextView tvUrFriend;

  @NonNull
  public final TextView tvYou;

  @NonNull
  public final TextView txtAppinfo;

  protected InviteFriendFragmentBinding(Object _bindingComponent, View _root, int _localFieldCount,
      Button btnShare, ImageView ivArrow, ImageView ivFriend, ImageView ivShare, ImageView ivYou,
      RelativeLayout layoutInvite, RelativeLayout layoutShare,
      LinearLayout llInviteFriendReferralCopy, TextView tvAndText, TextView tvEarnText,
      TextView tvFriend, TextView tvReferDayText, TextView tvReferText, TextView tvReferalCode,
      TextView tvU, TextView tvUrFriend, TextView tvYou, TextView txtAppinfo) {
    super(_bindingComponent, _root, _localFieldCount);
    this.btnShare = btnShare;
    this.ivArrow = ivArrow;
    this.ivFriend = ivFriend;
    this.ivShare = ivShare;
    this.ivYou = ivYou;
    this.layoutInvite = layoutInvite;
    this.layoutShare = layoutShare;
    this.llInviteFriendReferralCopy = llInviteFriendReferralCopy;
    this.tvAndText = tvAndText;
    this.tvEarnText = tvEarnText;
    this.tvFriend = tvFriend;
    this.tvReferDayText = tvReferDayText;
    this.tvReferText = tvReferText;
    this.tvReferalCode = tvReferalCode;
    this.tvU = tvU;
    this.tvUrFriend = tvUrFriend;
    this.tvYou = tvYou;
    this.txtAppinfo = txtAppinfo;
  }

  @NonNull
  public static InviteFriendFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.invite_friend_fragment, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static InviteFriendFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<InviteFriendFragmentBinding>inflateInternal(inflater, R.layout.invite_friend_fragment, root, attachToRoot, component);
  }

  @NonNull
  public static InviteFriendFragmentBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.invite_friend_fragment, null, false, component)
   */
  @NonNull
  @Deprecated
  public static InviteFriendFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<InviteFriendFragmentBinding>inflateInternal(inflater, R.layout.invite_friend_fragment, null, false, component);
  }

  public static InviteFriendFragmentBinding bind(@NonNull View view) {
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
  public static InviteFriendFragmentBinding bind(@NonNull View view, @Nullable Object component) {
    return (InviteFriendFragmentBinding)bind(component, view, R.layout.invite_friend_fragment);
  }
}
