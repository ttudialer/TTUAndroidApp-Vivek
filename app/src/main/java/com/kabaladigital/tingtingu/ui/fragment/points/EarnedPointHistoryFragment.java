package com.kabaladigital.tingtingu.ui.fragment.points;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProviders;

import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.databinding.EarnedPointHistoryFragmentBinding;
import com.kabaladigital.tingtingu.ui.activity.MainActivity;
import com.kabaladigital.tingtingu.util.PreferenceUtils;
import com.kabaladigital.tingtingu.viewmodels.EarnedPointHistoryViewModel;

public class EarnedPointHistoryFragment extends Fragment {

    private EarnedPointHistoryFragmentBinding binding;
    private EarnedPointHistoryViewModel mViewModel;
    private String langType;

    public static EarnedPointHistoryFragment newInstance() {
        return new EarnedPointHistoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                                          @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.earned_point_history_fragment, container, false);

        langType = PreferenceUtils.getInstance().getString(R.string.pref_user_selected_language_key);

        String[] title;
        if (langType.equals("hi")){
            title = new String[]{"क्रेडिट", "डेबिट"};
        } else {
            title = new String[]{"Credit", "Debit"};
        }
        binding.viewpager.setAdapter(new ViewPagerAdapter(getChildFragmentManager(), title));
        binding.tablayout.setupWithViewPager(binding.viewpager);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EarnedPointHistoryViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onResume() {
        getActivity().setTitle("Earn Points");
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        super.onResume();
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {
        private final Fragment[] childFragments;
        private final String[] title;

        public ViewPagerAdapter(@NonNull FragmentManager fm, String[] title) {
            super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
            this.title = title;
            childFragments = new Fragment[] {
                    new EarnedPointCreditHistoryFragment(), //0
                    new EarnedPointDebitHistoryFragment(), //1
                  };
        }


        @NonNull
        @Override
        public Fragment getItem(int position) {
            return childFragments[position];
        }

        @Override
        public int getCount() {
            return childFragments.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }
    }
}
