package com.kabaladigital.tingtingu;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.kabaladigital.tingtingu.databinding.ActivityChooseProfileLanguageFragmentBindingImpl;
import com.kabaladigital.tingtingu.databinding.ActivityEndCallBindingImpl;
import com.kabaladigital.tingtingu.databinding.ActivityLoginBindingImpl;
import com.kabaladigital.tingtingu.databinding.ActivityMainBindingImpl;
import com.kabaladigital.tingtingu.databinding.ActivityMenuBindingImpl;
import com.kabaladigital.tingtingu.databinding.ActivityOngoingCallBindingImpl;
import com.kabaladigital.tingtingu.databinding.CallerDetailsFragmentBindingImpl;
import com.kabaladigital.tingtingu.databinding.CallerDetailsFragmentChooseBindingImpl;
import com.kabaladigital.tingtingu.databinding.CallerDetailsFragmentChooseImageBindingImpl;
import com.kabaladigital.tingtingu.databinding.CallerDetailsFragmentChooseVideoBindingImpl;
import com.kabaladigital.tingtingu.databinding.ChooseLanguageFragmentBindingImpl;
import com.kabaladigital.tingtingu.databinding.ChooseMultiLanguageFragmentBindingImpl;
import com.kabaladigital.tingtingu.databinding.ChooseMultiLanguageProfileFragmentBindingImpl;
import com.kabaladigital.tingtingu.databinding.CongoRegistrartionFragmentBindingImpl;
import com.kabaladigital.tingtingu.databinding.ContactListFragmentBindingImpl;
import com.kabaladigital.tingtingu.databinding.DthProcessRechargeFragmentBindingImpl;
import com.kabaladigital.tingtingu.databinding.DthRechargeFragmentBindingImpl;
import com.kabaladigital.tingtingu.databinding.EarnedPointCreditHistoryFragmentBindingImpl;
import com.kabaladigital.tingtingu.databinding.EarnedPointDebitHistoryFragmentBindingImpl;
import com.kabaladigital.tingtingu.databinding.EarnedPointDebitItemsBindingImpl;
import com.kabaladigital.tingtingu.databinding.EarnedPointHistoryFragmentBindingImpl;
import com.kabaladigital.tingtingu.databinding.EndCallAdLayoutBindingImpl;
import com.kabaladigital.tingtingu.databinding.EnterOtFragmentBindingImpl;
import com.kabaladigital.tingtingu.databinding.FragmentMembershipMessageBindingImpl;
import com.kabaladigital.tingtingu.databinding.FragmentRechargeHistoryBindingImpl;
import com.kabaladigital.tingtingu.databinding.ImageViewBindingImpl;
import com.kabaladigital.tingtingu.databinding.InviteFriendFragmentBindingImpl;
import com.kabaladigital.tingtingu.databinding.ItemContactBindingImpl;
import com.kabaladigital.tingtingu.databinding.LayoutIncomingCallBindingImpl;
import com.kabaladigital.tingtingu.databinding.LayoutOperatorHomeBindingImpl;
import com.kabaladigital.tingtingu.databinding.LayoutRecentRechargesBindingImpl;
import com.kabaladigital.tingtingu.databinding.LayoutSurveyListBindingImpl;
import com.kabaladigital.tingtingu.databinding.LayoutWalletBalanceBindingImpl;
import com.kabaladigital.tingtingu.databinding.LoginFragmentBindingImpl;
import com.kabaladigital.tingtingu.databinding.MemberShipFragmentBindingImpl;
import com.kabaladigital.tingtingu.databinding.MobileRechargeFragmentBindingImpl;
import com.kabaladigital.tingtingu.databinding.OnGoingCallBindingImpl;
import com.kabaladigital.tingtingu.databinding.OperatorDetailFragmentBindingImpl;
import com.kabaladigital.tingtingu.databinding.OperatorHomeFragmentBindingImpl;
import com.kabaladigital.tingtingu.databinding.OperatorSelectFragmentBindingImpl;
import com.kabaladigital.tingtingu.databinding.OverlaySendSmsBindingImpl;
import com.kabaladigital.tingtingu.databinding.ProfileChooseMultiLanguageFragmentBindingImpl;
import com.kabaladigital.tingtingu.databinding.ProfileFragmentBindingImpl;
import com.kabaladigital.tingtingu.databinding.ProfileStep1FragmentBindingImpl;
import com.kabaladigital.tingtingu.databinding.ProfileStep2FragmentBindingImpl;
import com.kabaladigital.tingtingu.databinding.RechargeAmountFragmentBindingImpl;
import com.kabaladigital.tingtingu.databinding.RedeemPointFragmentBindingImpl;
import com.kabaladigital.tingtingu.databinding.RegistrationFragmentBindingImpl;
import com.kabaladigital.tingtingu.databinding.RegistrationStep2FragmentBindingImpl;
import com.kabaladigital.tingtingu.databinding.SettingFragmentBindingImpl;
import com.kabaladigital.tingtingu.databinding.SplashScreenFragmentBindingImpl;
import com.kabaladigital.tingtingu.databinding.VideoViewBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYCHOOSEPROFILELANGUAGEFRAGMENT = 1;

  private static final int LAYOUT_ACTIVITYENDCALL = 2;

  private static final int LAYOUT_ACTIVITYLOGIN = 3;

  private static final int LAYOUT_ACTIVITYMAIN = 4;

  private static final int LAYOUT_ACTIVITYMENU = 5;

  private static final int LAYOUT_ACTIVITYONGOINGCALL = 6;

  private static final int LAYOUT_CALLERDETAILSFRAGMENT = 7;

  private static final int LAYOUT_CALLERDETAILSFRAGMENTCHOOSE = 8;

  private static final int LAYOUT_CALLERDETAILSFRAGMENTCHOOSEIMAGE = 9;

  private static final int LAYOUT_CALLERDETAILSFRAGMENTCHOOSEVIDEO = 10;

  private static final int LAYOUT_CHOOSELANGUAGEFRAGMENT = 11;

  private static final int LAYOUT_CHOOSEMULTILANGUAGEFRAGMENT = 12;

  private static final int LAYOUT_CHOOSEMULTILANGUAGEPROFILEFRAGMENT = 13;

  private static final int LAYOUT_CONGOREGISTRARTIONFRAGMENT = 14;

  private static final int LAYOUT_CONTACTLISTFRAGMENT = 15;

  private static final int LAYOUT_DTHPROCESSRECHARGEFRAGMENT = 16;

  private static final int LAYOUT_DTHRECHARGEFRAGMENT = 17;

  private static final int LAYOUT_EARNEDPOINTCREDITHISTORYFRAGMENT = 18;

  private static final int LAYOUT_EARNEDPOINTDEBITHISTORYFRAGMENT = 19;

  private static final int LAYOUT_EARNEDPOINTDEBITITEMS = 20;

  private static final int LAYOUT_EARNEDPOINTHISTORYFRAGMENT = 21;

  private static final int LAYOUT_ENDCALLADLAYOUT = 22;

  private static final int LAYOUT_ENTEROTFRAGMENT = 23;

  private static final int LAYOUT_FRAGMENTMEMBERSHIPMESSAGE = 24;

  private static final int LAYOUT_FRAGMENTRECHARGEHISTORY = 25;

  private static final int LAYOUT_IMAGEVIEW = 26;

  private static final int LAYOUT_INVITEFRIENDFRAGMENT = 27;

  private static final int LAYOUT_ITEMCONTACT = 28;

  private static final int LAYOUT_LAYOUTINCOMINGCALL = 29;

  private static final int LAYOUT_LAYOUTOPERATORHOME = 30;

  private static final int LAYOUT_LAYOUTRECENTRECHARGES = 31;

  private static final int LAYOUT_LAYOUTSURVEYLIST = 32;

  private static final int LAYOUT_LAYOUTWALLETBALANCE = 33;

  private static final int LAYOUT_LOGINFRAGMENT = 34;

  private static final int LAYOUT_MEMBERSHIPFRAGMENT = 35;

  private static final int LAYOUT_MOBILERECHARGEFRAGMENT = 36;

  private static final int LAYOUT_ONGOINGCALL = 37;

  private static final int LAYOUT_OPERATORDETAILFRAGMENT = 38;

  private static final int LAYOUT_OPERATORHOMEFRAGMENT = 39;

  private static final int LAYOUT_OPERATORSELECTFRAGMENT = 40;

  private static final int LAYOUT_OVERLAYSENDSMS = 41;

  private static final int LAYOUT_PROFILECHOOSEMULTILANGUAGEFRAGMENT = 42;

  private static final int LAYOUT_PROFILEFRAGMENT = 43;

  private static final int LAYOUT_PROFILESTEP1FRAGMENT = 44;

  private static final int LAYOUT_PROFILESTEP2FRAGMENT = 45;

  private static final int LAYOUT_RECHARGEAMOUNTFRAGMENT = 46;

  private static final int LAYOUT_REDEEMPOINTFRAGMENT = 47;

  private static final int LAYOUT_REGISTRATIONFRAGMENT = 48;

  private static final int LAYOUT_REGISTRATIONSTEP2FRAGMENT = 49;

  private static final int LAYOUT_SETTINGFRAGMENT = 50;

  private static final int LAYOUT_SPLASHSCREENFRAGMENT = 51;

  private static final int LAYOUT_VIDEOVIEW = 52;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(52);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.kabaladigital.tingtingu.R.layout.activity_choose_profile_language_fragment, LAYOUT_ACTIVITYCHOOSEPROFILELANGUAGEFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.kabaladigital.tingtingu.R.layout.activity_end_call, LAYOUT_ACTIVITYENDCALL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.kabaladigital.tingtingu.R.layout.activity_login, LAYOUT_ACTIVITYLOGIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.kabaladigital.tingtingu.R.layout.activity_main, LAYOUT_ACTIVITYMAIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.kabaladigital.tingtingu.R.layout.activity_menu, LAYOUT_ACTIVITYMENU);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.kabaladigital.tingtingu.R.layout.activity_ongoing_call, LAYOUT_ACTIVITYONGOINGCALL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.kabaladigital.tingtingu.R.layout.caller_details_fragment, LAYOUT_CALLERDETAILSFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.kabaladigital.tingtingu.R.layout.caller_details_fragment_choose, LAYOUT_CALLERDETAILSFRAGMENTCHOOSE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.kabaladigital.tingtingu.R.layout.caller_details_fragment_choose_image, LAYOUT_CALLERDETAILSFRAGMENTCHOOSEIMAGE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.kabaladigital.tingtingu.R.layout.caller_details_fragment_choose_video, LAYOUT_CALLERDETAILSFRAGMENTCHOOSEVIDEO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.kabaladigital.tingtingu.R.layout.choose_language_fragment, LAYOUT_CHOOSELANGUAGEFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.kabaladigital.tingtingu.R.layout.choose_multi_language_fragment, LAYOUT_CHOOSEMULTILANGUAGEFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.kabaladigital.tingtingu.R.layout.choose_multi_language_profile_fragment, LAYOUT_CHOOSEMULTILANGUAGEPROFILEFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.kabaladigital.tingtingu.R.layout.congo_registrartion_fragment, LAYOUT_CONGOREGISTRARTIONFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.kabaladigital.tingtingu.R.layout.contact_list_fragment, LAYOUT_CONTACTLISTFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.kabaladigital.tingtingu.R.layout.dth_process_recharge_fragment, LAYOUT_DTHPROCESSRECHARGEFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.kabaladigital.tingtingu.R.layout.dth_recharge_fragment, LAYOUT_DTHRECHARGEFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.kabaladigital.tingtingu.R.layout.earned_point_credit_history_fragment, LAYOUT_EARNEDPOINTCREDITHISTORYFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.kabaladigital.tingtingu.R.layout.earned_point_debit_history_fragment, LAYOUT_EARNEDPOINTDEBITHISTORYFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.kabaladigital.tingtingu.R.layout.earned_point_debit_items, LAYOUT_EARNEDPOINTDEBITITEMS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.kabaladigital.tingtingu.R.layout.earned_point_history_fragment, LAYOUT_EARNEDPOINTHISTORYFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.kabaladigital.tingtingu.R.layout.end_call_ad_layout, LAYOUT_ENDCALLADLAYOUT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.kabaladigital.tingtingu.R.layout.enter_ot_fragment, LAYOUT_ENTEROTFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.kabaladigital.tingtingu.R.layout.fragment_membership_message, LAYOUT_FRAGMENTMEMBERSHIPMESSAGE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.kabaladigital.tingtingu.R.layout.fragment_recharge_history, LAYOUT_FRAGMENTRECHARGEHISTORY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.kabaladigital.tingtingu.R.layout.image_view, LAYOUT_IMAGEVIEW);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.kabaladigital.tingtingu.R.layout.invite_friend_fragment, LAYOUT_INVITEFRIENDFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.kabaladigital.tingtingu.R.layout.item_contact, LAYOUT_ITEMCONTACT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.kabaladigital.tingtingu.R.layout.layout_incoming_call, LAYOUT_LAYOUTINCOMINGCALL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.kabaladigital.tingtingu.R.layout.layout_operator_home, LAYOUT_LAYOUTOPERATORHOME);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.kabaladigital.tingtingu.R.layout.layout_recent_recharges, LAYOUT_LAYOUTRECENTRECHARGES);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.kabaladigital.tingtingu.R.layout.layout_survey_list, LAYOUT_LAYOUTSURVEYLIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.kabaladigital.tingtingu.R.layout.layout_wallet_balance, LAYOUT_LAYOUTWALLETBALANCE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.kabaladigital.tingtingu.R.layout.login_fragment, LAYOUT_LOGINFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.kabaladigital.tingtingu.R.layout.member_ship_fragment, LAYOUT_MEMBERSHIPFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.kabaladigital.tingtingu.R.layout.mobile_recharge_fragment, LAYOUT_MOBILERECHARGEFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.kabaladigital.tingtingu.R.layout.on_going_call, LAYOUT_ONGOINGCALL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.kabaladigital.tingtingu.R.layout.operator_detail_fragment, LAYOUT_OPERATORDETAILFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.kabaladigital.tingtingu.R.layout.operator_home_fragment, LAYOUT_OPERATORHOMEFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.kabaladigital.tingtingu.R.layout.operator_select_fragment, LAYOUT_OPERATORSELECTFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.kabaladigital.tingtingu.R.layout.overlay_send_sms, LAYOUT_OVERLAYSENDSMS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.kabaladigital.tingtingu.R.layout.profile_choose_multi_language_fragment, LAYOUT_PROFILECHOOSEMULTILANGUAGEFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.kabaladigital.tingtingu.R.layout.profile_fragment, LAYOUT_PROFILEFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.kabaladigital.tingtingu.R.layout.profile_step1_fragment, LAYOUT_PROFILESTEP1FRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.kabaladigital.tingtingu.R.layout.profile_step2_fragment, LAYOUT_PROFILESTEP2FRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.kabaladigital.tingtingu.R.layout.recharge_amount_fragment, LAYOUT_RECHARGEAMOUNTFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.kabaladigital.tingtingu.R.layout.redeem_point_fragment, LAYOUT_REDEEMPOINTFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.kabaladigital.tingtingu.R.layout.registration_fragment, LAYOUT_REGISTRATIONFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.kabaladigital.tingtingu.R.layout.registration_step2_fragment, LAYOUT_REGISTRATIONSTEP2FRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.kabaladigital.tingtingu.R.layout.setting_fragment, LAYOUT_SETTINGFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.kabaladigital.tingtingu.R.layout.splash_screen_fragment, LAYOUT_SPLASHSCREENFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.kabaladigital.tingtingu.R.layout.video_view, LAYOUT_VIDEOVIEW);
  }

  private final ViewDataBinding internalGetViewDataBinding0(DataBindingComponent component,
      View view, int internalId, Object tag) {
    switch(internalId) {
      case  LAYOUT_ACTIVITYCHOOSEPROFILELANGUAGEFRAGMENT: {
        if ("layout/activity_choose_profile_language_fragment_0".equals(tag)) {
          return new ActivityChooseProfileLanguageFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_choose_profile_language_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYENDCALL: {
        if ("layout/activity_end_call_0".equals(tag)) {
          return new ActivityEndCallBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_end_call is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYLOGIN: {
        if ("layout/activity_login_0".equals(tag)) {
          return new ActivityLoginBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_login is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYMAIN: {
        if ("layout/activity_main_0".equals(tag)) {
          return new ActivityMainBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_main is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYMENU: {
        if ("layout/activity_menu_0".equals(tag)) {
          return new ActivityMenuBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_menu is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYONGOINGCALL: {
        if ("layout/activity_ongoing_call_0".equals(tag)) {
          return new ActivityOngoingCallBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_ongoing_call is invalid. Received: " + tag);
      }
      case  LAYOUT_CALLERDETAILSFRAGMENT: {
        if ("layout/caller_details_fragment_0".equals(tag)) {
          return new CallerDetailsFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for caller_details_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_CALLERDETAILSFRAGMENTCHOOSE: {
        if ("layout/caller_details_fragment_choose_0".equals(tag)) {
          return new CallerDetailsFragmentChooseBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for caller_details_fragment_choose is invalid. Received: " + tag);
      }
      case  LAYOUT_CALLERDETAILSFRAGMENTCHOOSEIMAGE: {
        if ("layout/caller_details_fragment_choose_image_0".equals(tag)) {
          return new CallerDetailsFragmentChooseImageBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for caller_details_fragment_choose_image is invalid. Received: " + tag);
      }
      case  LAYOUT_CALLERDETAILSFRAGMENTCHOOSEVIDEO: {
        if ("layout/caller_details_fragment_choose_video_0".equals(tag)) {
          return new CallerDetailsFragmentChooseVideoBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for caller_details_fragment_choose_video is invalid. Received: " + tag);
      }
      case  LAYOUT_CHOOSELANGUAGEFRAGMENT: {
        if ("layout/choose_language_fragment_0".equals(tag)) {
          return new ChooseLanguageFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for choose_language_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_CHOOSEMULTILANGUAGEFRAGMENT: {
        if ("layout/choose_multi_language_fragment_0".equals(tag)) {
          return new ChooseMultiLanguageFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for choose_multi_language_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_CHOOSEMULTILANGUAGEPROFILEFRAGMENT: {
        if ("layout/choose_multi_language_profile_fragment_0".equals(tag)) {
          return new ChooseMultiLanguageProfileFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for choose_multi_language_profile_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_CONGOREGISTRARTIONFRAGMENT: {
        if ("layout/congo_registrartion_fragment_0".equals(tag)) {
          return new CongoRegistrartionFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for congo_registrartion_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_CONTACTLISTFRAGMENT: {
        if ("layout/contact_list_fragment_0".equals(tag)) {
          return new ContactListFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for contact_list_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_DTHPROCESSRECHARGEFRAGMENT: {
        if ("layout/dth_process_recharge_fragment_0".equals(tag)) {
          return new DthProcessRechargeFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for dth_process_recharge_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_DTHRECHARGEFRAGMENT: {
        if ("layout/dth_recharge_fragment_0".equals(tag)) {
          return new DthRechargeFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for dth_recharge_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_EARNEDPOINTCREDITHISTORYFRAGMENT: {
        if ("layout/earned_point_credit_history_fragment_0".equals(tag)) {
          return new EarnedPointCreditHistoryFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for earned_point_credit_history_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_EARNEDPOINTDEBITHISTORYFRAGMENT: {
        if ("layout/earned_point_debit_history_fragment_0".equals(tag)) {
          return new EarnedPointDebitHistoryFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for earned_point_debit_history_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_EARNEDPOINTDEBITITEMS: {
        if ("layout/earned_point_debit_items_0".equals(tag)) {
          return new EarnedPointDebitItemsBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for earned_point_debit_items is invalid. Received: " + tag);
      }
      case  LAYOUT_EARNEDPOINTHISTORYFRAGMENT: {
        if ("layout/earned_point_history_fragment_0".equals(tag)) {
          return new EarnedPointHistoryFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for earned_point_history_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_ENDCALLADLAYOUT: {
        if ("layout/end_call_ad_layout_0".equals(tag)) {
          return new EndCallAdLayoutBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for end_call_ad_layout is invalid. Received: " + tag);
      }
      case  LAYOUT_ENTEROTFRAGMENT: {
        if ("layout/enter_ot_fragment_0".equals(tag)) {
          return new EnterOtFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for enter_ot_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_FRAGMENTMEMBERSHIPMESSAGE: {
        if ("layout/fragment_membership_message_0".equals(tag)) {
          return new FragmentMembershipMessageBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_membership_message is invalid. Received: " + tag);
      }
      case  LAYOUT_FRAGMENTRECHARGEHISTORY: {
        if ("layout/fragment_recharge_history_0".equals(tag)) {
          return new FragmentRechargeHistoryBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_recharge_history is invalid. Received: " + tag);
      }
      case  LAYOUT_IMAGEVIEW: {
        if ("layout/image_view_0".equals(tag)) {
          return new ImageViewBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for image_view is invalid. Received: " + tag);
      }
      case  LAYOUT_INVITEFRIENDFRAGMENT: {
        if ("layout/invite_friend_fragment_0".equals(tag)) {
          return new InviteFriendFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for invite_friend_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMCONTACT: {
        if ("layout/item_contact_0".equals(tag)) {
          return new ItemContactBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_contact is invalid. Received: " + tag);
      }
      case  LAYOUT_LAYOUTINCOMINGCALL: {
        if ("layout/layout_incoming_call_0".equals(tag)) {
          return new LayoutIncomingCallBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for layout_incoming_call is invalid. Received: " + tag);
      }
      case  LAYOUT_LAYOUTOPERATORHOME: {
        if ("layout/layout_operator_home_0".equals(tag)) {
          return new LayoutOperatorHomeBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for layout_operator_home is invalid. Received: " + tag);
      }
      case  LAYOUT_LAYOUTRECENTRECHARGES: {
        if ("layout/layout_recent_recharges_0".equals(tag)) {
          return new LayoutRecentRechargesBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for layout_recent_recharges is invalid. Received: " + tag);
      }
      case  LAYOUT_LAYOUTSURVEYLIST: {
        if ("layout/layout_survey_list_0".equals(tag)) {
          return new LayoutSurveyListBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for layout_survey_list is invalid. Received: " + tag);
      }
      case  LAYOUT_LAYOUTWALLETBALANCE: {
        if ("layout/layout_wallet_balance_0".equals(tag)) {
          return new LayoutWalletBalanceBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for layout_wallet_balance is invalid. Received: " + tag);
      }
      case  LAYOUT_LOGINFRAGMENT: {
        if ("layout/login_fragment_0".equals(tag)) {
          return new LoginFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for login_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_MEMBERSHIPFRAGMENT: {
        if ("layout/member_ship_fragment_0".equals(tag)) {
          return new MemberShipFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for member_ship_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_MOBILERECHARGEFRAGMENT: {
        if ("layout/mobile_recharge_fragment_0".equals(tag)) {
          return new MobileRechargeFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for mobile_recharge_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_ONGOINGCALL: {
        if ("layout/on_going_call_0".equals(tag)) {
          return new OnGoingCallBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for on_going_call is invalid. Received: " + tag);
      }
      case  LAYOUT_OPERATORDETAILFRAGMENT: {
        if ("layout/operator_detail_fragment_0".equals(tag)) {
          return new OperatorDetailFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for operator_detail_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_OPERATORHOMEFRAGMENT: {
        if ("layout/operator_home_fragment_0".equals(tag)) {
          return new OperatorHomeFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for operator_home_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_OPERATORSELECTFRAGMENT: {
        if ("layout/operator_select_fragment_0".equals(tag)) {
          return new OperatorSelectFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for operator_select_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_OVERLAYSENDSMS: {
        if ("layout/overlay_send_sms_0".equals(tag)) {
          return new OverlaySendSmsBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for overlay_send_sms is invalid. Received: " + tag);
      }
      case  LAYOUT_PROFILECHOOSEMULTILANGUAGEFRAGMENT: {
        if ("layout/profile_choose_multi_language_fragment_0".equals(tag)) {
          return new ProfileChooseMultiLanguageFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for profile_choose_multi_language_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_PROFILEFRAGMENT: {
        if ("layout/profile_fragment_0".equals(tag)) {
          return new ProfileFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for profile_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_PROFILESTEP1FRAGMENT: {
        if ("layout/profile_step1_fragment_0".equals(tag)) {
          return new ProfileStep1FragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for profile_step1_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_PROFILESTEP2FRAGMENT: {
        if ("layout/profile_step2_fragment_0".equals(tag)) {
          return new ProfileStep2FragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for profile_step2_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_RECHARGEAMOUNTFRAGMENT: {
        if ("layout/recharge_amount_fragment_0".equals(tag)) {
          return new RechargeAmountFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for recharge_amount_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_REDEEMPOINTFRAGMENT: {
        if ("layout/redeem_point_fragment_0".equals(tag)) {
          return new RedeemPointFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for redeem_point_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_REGISTRATIONFRAGMENT: {
        if ("layout/registration_fragment_0".equals(tag)) {
          return new RegistrationFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for registration_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_REGISTRATIONSTEP2FRAGMENT: {
        if ("layout/registration_step2_fragment_0".equals(tag)) {
          return new RegistrationStep2FragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for registration_step2_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_SETTINGFRAGMENT: {
        if ("layout/setting_fragment_0".equals(tag)) {
          return new SettingFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for setting_fragment is invalid. Received: " + tag);
      }
    }
    return null;
  }

  private final ViewDataBinding internalGetViewDataBinding1(DataBindingComponent component,
      View view, int internalId, Object tag) {
    switch(internalId) {
      case  LAYOUT_SPLASHSCREENFRAGMENT: {
        if ("layout/splash_screen_fragment_0".equals(tag)) {
          return new SplashScreenFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for splash_screen_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_VIDEOVIEW: {
        if ("layout/video_view_0".equals(tag)) {
          return new VideoViewBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for video_view is invalid. Received: " + tag);
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      // find which method will have it. -1 is necessary becausefirst id starts with 1;
      int methodIndex = (localizedLayoutId - 1) / 50;
      switch(methodIndex) {
        case 0: {
          return internalGetViewDataBinding0(component, view, localizedLayoutId, tag);
        }
        case 1: {
          return internalGetViewDataBinding1(component, view, localizedLayoutId, tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(2);

    static {
      sKeys.put(1, "Splash");
      sKeys.put(0, "_all");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(52);

    static {
      sKeys.put("layout/activity_choose_profile_language_fragment_0", com.kabaladigital.tingtingu.R.layout.activity_choose_profile_language_fragment);
      sKeys.put("layout/activity_end_call_0", com.kabaladigital.tingtingu.R.layout.activity_end_call);
      sKeys.put("layout/activity_login_0", com.kabaladigital.tingtingu.R.layout.activity_login);
      sKeys.put("layout/activity_main_0", com.kabaladigital.tingtingu.R.layout.activity_main);
      sKeys.put("layout/activity_menu_0", com.kabaladigital.tingtingu.R.layout.activity_menu);
      sKeys.put("layout/activity_ongoing_call_0", com.kabaladigital.tingtingu.R.layout.activity_ongoing_call);
      sKeys.put("layout/caller_details_fragment_0", com.kabaladigital.tingtingu.R.layout.caller_details_fragment);
      sKeys.put("layout/caller_details_fragment_choose_0", com.kabaladigital.tingtingu.R.layout.caller_details_fragment_choose);
      sKeys.put("layout/caller_details_fragment_choose_image_0", com.kabaladigital.tingtingu.R.layout.caller_details_fragment_choose_image);
      sKeys.put("layout/caller_details_fragment_choose_video_0", com.kabaladigital.tingtingu.R.layout.caller_details_fragment_choose_video);
      sKeys.put("layout/choose_language_fragment_0", com.kabaladigital.tingtingu.R.layout.choose_language_fragment);
      sKeys.put("layout/choose_multi_language_fragment_0", com.kabaladigital.tingtingu.R.layout.choose_multi_language_fragment);
      sKeys.put("layout/choose_multi_language_profile_fragment_0", com.kabaladigital.tingtingu.R.layout.choose_multi_language_profile_fragment);
      sKeys.put("layout/congo_registrartion_fragment_0", com.kabaladigital.tingtingu.R.layout.congo_registrartion_fragment);
      sKeys.put("layout/contact_list_fragment_0", com.kabaladigital.tingtingu.R.layout.contact_list_fragment);
      sKeys.put("layout/dth_process_recharge_fragment_0", com.kabaladigital.tingtingu.R.layout.dth_process_recharge_fragment);
      sKeys.put("layout/dth_recharge_fragment_0", com.kabaladigital.tingtingu.R.layout.dth_recharge_fragment);
      sKeys.put("layout/earned_point_credit_history_fragment_0", com.kabaladigital.tingtingu.R.layout.earned_point_credit_history_fragment);
      sKeys.put("layout/earned_point_debit_history_fragment_0", com.kabaladigital.tingtingu.R.layout.earned_point_debit_history_fragment);
      sKeys.put("layout/earned_point_debit_items_0", com.kabaladigital.tingtingu.R.layout.earned_point_debit_items);
      sKeys.put("layout/earned_point_history_fragment_0", com.kabaladigital.tingtingu.R.layout.earned_point_history_fragment);
      sKeys.put("layout/end_call_ad_layout_0", com.kabaladigital.tingtingu.R.layout.end_call_ad_layout);
      sKeys.put("layout/enter_ot_fragment_0", com.kabaladigital.tingtingu.R.layout.enter_ot_fragment);
      sKeys.put("layout/fragment_membership_message_0", com.kabaladigital.tingtingu.R.layout.fragment_membership_message);
      sKeys.put("layout/fragment_recharge_history_0", com.kabaladigital.tingtingu.R.layout.fragment_recharge_history);
      sKeys.put("layout/image_view_0", com.kabaladigital.tingtingu.R.layout.image_view);
      sKeys.put("layout/invite_friend_fragment_0", com.kabaladigital.tingtingu.R.layout.invite_friend_fragment);
      sKeys.put("layout/item_contact_0", com.kabaladigital.tingtingu.R.layout.item_contact);
      sKeys.put("layout/layout_incoming_call_0", com.kabaladigital.tingtingu.R.layout.layout_incoming_call);
      sKeys.put("layout/layout_operator_home_0", com.kabaladigital.tingtingu.R.layout.layout_operator_home);
      sKeys.put("layout/layout_recent_recharges_0", com.kabaladigital.tingtingu.R.layout.layout_recent_recharges);
      sKeys.put("layout/layout_survey_list_0", com.kabaladigital.tingtingu.R.layout.layout_survey_list);
      sKeys.put("layout/layout_wallet_balance_0", com.kabaladigital.tingtingu.R.layout.layout_wallet_balance);
      sKeys.put("layout/login_fragment_0", com.kabaladigital.tingtingu.R.layout.login_fragment);
      sKeys.put("layout/member_ship_fragment_0", com.kabaladigital.tingtingu.R.layout.member_ship_fragment);
      sKeys.put("layout/mobile_recharge_fragment_0", com.kabaladigital.tingtingu.R.layout.mobile_recharge_fragment);
      sKeys.put("layout/on_going_call_0", com.kabaladigital.tingtingu.R.layout.on_going_call);
      sKeys.put("layout/operator_detail_fragment_0", com.kabaladigital.tingtingu.R.layout.operator_detail_fragment);
      sKeys.put("layout/operator_home_fragment_0", com.kabaladigital.tingtingu.R.layout.operator_home_fragment);
      sKeys.put("layout/operator_select_fragment_0", com.kabaladigital.tingtingu.R.layout.operator_select_fragment);
      sKeys.put("layout/overlay_send_sms_0", com.kabaladigital.tingtingu.R.layout.overlay_send_sms);
      sKeys.put("layout/profile_choose_multi_language_fragment_0", com.kabaladigital.tingtingu.R.layout.profile_choose_multi_language_fragment);
      sKeys.put("layout/profile_fragment_0", com.kabaladigital.tingtingu.R.layout.profile_fragment);
      sKeys.put("layout/profile_step1_fragment_0", com.kabaladigital.tingtingu.R.layout.profile_step1_fragment);
      sKeys.put("layout/profile_step2_fragment_0", com.kabaladigital.tingtingu.R.layout.profile_step2_fragment);
      sKeys.put("layout/recharge_amount_fragment_0", com.kabaladigital.tingtingu.R.layout.recharge_amount_fragment);
      sKeys.put("layout/redeem_point_fragment_0", com.kabaladigital.tingtingu.R.layout.redeem_point_fragment);
      sKeys.put("layout/registration_fragment_0", com.kabaladigital.tingtingu.R.layout.registration_fragment);
      sKeys.put("layout/registration_step2_fragment_0", com.kabaladigital.tingtingu.R.layout.registration_step2_fragment);
      sKeys.put("layout/setting_fragment_0", com.kabaladigital.tingtingu.R.layout.setting_fragment);
      sKeys.put("layout/splash_screen_fragment_0", com.kabaladigital.tingtingu.R.layout.splash_screen_fragment);
      sKeys.put("layout/video_view_0", com.kabaladigital.tingtingu.R.layout.video_view);
    }
  }
}
