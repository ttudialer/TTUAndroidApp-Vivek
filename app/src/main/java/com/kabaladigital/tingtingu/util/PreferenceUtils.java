package com.kabaladigital.tingtingu.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.SparseArray;

import androidx.annotation.StringRes;

import com.kabaladigital.tingtingu.R;

public class PreferenceUtils {

    private static PreferenceUtils sSharedPrefs;
    private static Context mContext_1;
    private Context mContext;
    private SharedPreferences mPref;
    private SharedPreferences.Editor mEditor;
    private boolean mBulkUpdate = false;

    private static final SparseArray<Object> sDefaultValues = new SparseArray<>();

    /**
     * Constructor
     * @param context
     */
    private PreferenceUtils(Context context) {
        mPref = PreferenceManager.getDefaultSharedPreferences(context);
        mContext = context;
        mContext_1 = context;

        sDefaultValues.put(R.string.pref_app_theme_key, context.getString(R.string.pref_app_theme_default_value));
        sDefaultValues.put(R.string.pref_reject_call_timer_key, context.getString(R.string.pref_reject_call_timer_default_value));
        sDefaultValues.put(R.string.pref_answer_call_timer_key, context.getString(R.string.pref_answer_call_timer_default_value));
        sDefaultValues.put(R.string.pref_default_page_key, context.getString(R.string.pref_default_page_default_value));
        sDefaultValues.put(R.string.pref_is_first_instance_key, context.getResources().getBoolean(R.bool.pref_is_first_instance_default_value));
        sDefaultValues.put(R.string.pref_last_version_key, -1);
        sDefaultValues.put(R.string.pref_user_selected_language_key, "en");
        sDefaultValues.put(R.string.pref_user_mobile_number_type_key, -1);
        sDefaultValues.put(R.string.pref_user_token_value, "");
        sDefaultValues.put(R.string.pref_user_mobile_number_operator_name, "MTNL");
        sDefaultValues.put(R.string.pref_refer_code_key, "");
        sDefaultValues.put(R.string.pref_is_registered_age_value, -1);
        sDefaultValues.put(R.string.pref_is_registered_gender_value, -1);
        sDefaultValues.put(R.string.pref_is_registered_step_two_education_value,"Others");
        sDefaultValues.put(R.string.pref_is_registered_step_two_profession_value,"Others");

        sDefaultValues.put(R.string.pref_is_language_selected_key, false);
        sDefaultValues.put(R.string.pref_is_otp_verify_key, false);
        sDefaultValues.put(R.string.pref_is_operator_detail_filled_key, false);
        sDefaultValues.put(R.string.pref_is_registered_step_one_key, false);
        sDefaultValues.put(R.string.pref_is_registered_step_two_key, false);
        sDefaultValues.put(R.string.pref_is_login_key, false);

        sDefaultValues.put(R.string.pref_daily_threshold_value, 16);

        sDefaultValues.put(R.string.pref_referrer_reward_days, "30");
        sDefaultValues.put(R.string.pref_referrer_points, "10");
        sDefaultValues.put(R.string.pref_referee_points, "50");

        sDefaultValues.put(R.string.pref_recharge_select_operator_name, "RelianceJIO");
        sDefaultValues.put(R.string.pref_recharge_select_dth_operator_name, "TataSky");
        sDefaultValues.put(R.string.pref_c_upload_date, "");

        sDefaultValues.put(R.string.pref_profile_path, "");
        sDefaultValues.put(R.string.u_id, "");

        sDefaultValues.put(R.string.pref_image_path_Draft, "");

    }

    public static PreferenceUtils getInstance(Context context, boolean isNewActivity)
    {
        if (sSharedPrefs == null && isNewActivity)
        {
            sSharedPrefs = new PreferenceUtils(context.getApplicationContext());
        }
        return sSharedPrefs;
    }

    public static PreferenceUtils getInstance()
    {
        try{
                if (sSharedPrefs != null) {
                }
                else{
                    sSharedPrefs = new PreferenceUtils(mContext_1);
                }
                return sSharedPrefs;
           }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }


        throw new IllegalArgumentException("Should use getInstance(Context) at least once before using this method.");
    }

    public Object getDefaultValue(@StringRes int key) {
        return sDefaultValues.get(key);
    }



    public void putInt(@StringRes int key, int value) {
        doEdit();
        mEditor.putInt(mContext.getString(key), value);
        doCommit();
    }

    public void putString(@StringRes int key, String value) {
        doEdit();
        mEditor.putString(mContext.getString(key), value);
        doCommit();
    }

    public void putBoolean(@StringRes int key, boolean value) {
        doEdit();
        mEditor.putBoolean(mContext.getString(key), value);
        doCommit();
    }

    public void putFloat(@StringRes int key, float value) {
        doEdit();
        mEditor.putFloat(mContext.getString(key), value);
        doCommit();
    }

    public void putLong(@StringRes int key, long value) {
        doEdit();
        mEditor.putLong(mContext.getString(key), value);
        doCommit();
    }


    public int getInt(@StringRes int key) {
        return mPref.getInt(mContext.getString(key), (int) getDefaultValue(key));
    }

    public String getString(@StringRes int key) {
        return mPref.getString(mContext.getString(key), (String) getDefaultValue(key));
    }

    public boolean getBoolean(@StringRes int key) {
        return mPref.getBoolean(mContext.getString(key), (boolean) getDefaultValue(key));
    }

    public float getFloat(@StringRes int key) {
        return mPref.getFloat(mContext.getString(key), (float) getDefaultValue(key));
    }

    public long getLong(@StringRes int key) {
        return mPref.getLong(mContext.getString(key), (long) getDefaultValue(key));
    }

    public void edit() {
        mBulkUpdate = true;
        mEditor = mPref.edit();
    }

    public void commit() {
        mBulkUpdate = false;
        mEditor.commit();
        mEditor = null;
    }

    private void doEdit() {
        if (!mBulkUpdate && mEditor == null) {
            mEditor = mPref.edit();
        }
    }

    private void doCommit() {
        if (!mBulkUpdate && mEditor != null) {
            mEditor.commit();
            mEditor = null;
        }
    }

    public void clear(){
        doEdit();
        mEditor.clear().apply();
    }




}
