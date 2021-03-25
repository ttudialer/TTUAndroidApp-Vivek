package com.kabaladigital.tingtingu.databinding;
import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class InviteFriendFragmentBindingImpl extends InviteFriendFragmentBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tv_refer_text, 1);
        sViewsWithIds.put(R.id.tv_and_text, 2);
        sViewsWithIds.put(R.id.tv_earn_text, 3);
        sViewsWithIds.put(R.id.layout_invite, 4);
        sViewsWithIds.put(R.id.iv_you, 5);
        sViewsWithIds.put(R.id.tv_u, 6);
        sViewsWithIds.put(R.id.tv_you, 7);
        sViewsWithIds.put(R.id.iv_arrow, 8);
        sViewsWithIds.put(R.id.iv_friend, 9);
        sViewsWithIds.put(R.id.tv_ur_friend, 10);
        sViewsWithIds.put(R.id.tv_friend, 11);
        sViewsWithIds.put(R.id.tv_refer_day_text, 12);
        sViewsWithIds.put(R.id.ll_invite_friend_referral_copy, 13);
        sViewsWithIds.put(R.id.tv_referal_code, 14);
        sViewsWithIds.put(R.id.layout_share, 15);
        sViewsWithIds.put(R.id.btn_share, 16);
        sViewsWithIds.put(R.id.iv_share, 17);
        sViewsWithIds.put(R.id.txt_appinfo, 18);
    }
    // views
    @NonNull
    private final android.widget.RelativeLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public InviteFriendFragmentBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 19, sIncludes, sViewsWithIds));
    }
    private InviteFriendFragmentBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.Button) bindings[16]
            , (android.widget.ImageView) bindings[8]
            , (android.widget.ImageView) bindings[9]
            , (android.widget.ImageView) bindings[17]
            , (android.widget.ImageView) bindings[5]
            , (android.widget.RelativeLayout) bindings[4]
            , (android.widget.RelativeLayout) bindings[15]
            , (android.widget.LinearLayout) bindings[13]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[11]
            , (android.widget.TextView) bindings[12]
            , (android.widget.TextView) bindings[1]
            , (android.widget.TextView) bindings[14]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[18]
            );
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}