package com.kabaladigital.tingtingu.ui.fragment;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.databinding.InviteFriendFragmentBinding;
import com.kabaladigital.tingtingu.ui.activity.MainActivity;
import com.kabaladigital.tingtingu.util.PreferenceUtils;
import com.kabaladigital.tingtingu.viewmodels.InviteFriendViewModel;

import static android.content.Context.CLIPBOARD_SERVICE;

public class InviteFriendFragment extends Fragment {

    private InviteFriendFragmentBinding binding;
    private InviteFriendViewModel mViewModel;
    private String code = "";
    private String langType;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.invite_friend_fragment, container, false);

        code = PreferenceUtils.getInstance().getString(R.string.pref_refer_code_key);
        binding.tvReferalCode.setText(code);


        PackageInfo pinfo = null;
        try {
            pinfo = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }



        binding.txtAppinfo.setText("TTU App Version Name "+pinfo.versionName+ " "+ "App Version Code "+pinfo.versionCode);



        String ShareText = "Use this Ting Ting U Application Invitation Referral Code to Earn " + code;

        langType = PreferenceUtils.getInstance().getString(R.string.pref_user_selected_language_key);





        if (langType.equals("hi")){
            binding.tvYou.setText("आप "+ PreferenceUtils.getInstance().getString(R.string.pref_referrer_points) +" अंक अर्जित करते हैं");
            binding.tvFriend.setText("आपका दोस्त "
                    + PreferenceUtils.getInstance().getString(R.string.pref_referee_points) +" अंक अर्जित करता है");
            binding.tvReferDayText.setText("जब आप रेफ़र करते हैं और आपका दोस्त TTU (टी टी यू) के साथ "
                    + PreferenceUtils.getInstance().getString(R.string.pref_referrer_reward_days) +" दिनों तक सक्रिय रहता है");
        }else {
            binding.tvYou.setText("You Earn "+ PreferenceUtils.getInstance().getString(R.string.pref_referrer_points) +" points");
            binding.tvFriend.setText("Your friend Earns "
                    + PreferenceUtils.getInstance().getString(R.string.pref_referee_points) +" points");
            binding.tvReferDayText.setText("When you Refer and friend complete "
                    + PreferenceUtils.getInstance().getString(R.string.pref_referrer_reward_days) +" active days with TTU");
        }


        binding.layoutShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, ShareText);
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
            }
        });

        binding.llInviteFriendReferralCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager)getContext().getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", ShareText);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getContext(), "Copied", Toast.LENGTH_SHORT).show();
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        getActivity().setTitle("Invite");
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        super.onResume();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(InviteFriendViewModel.class);
    }

}
