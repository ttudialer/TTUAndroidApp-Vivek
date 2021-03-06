package com.kabaladigital.tingtingu.databinding;
import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class OnGoingCallBindingImpl extends OnGoingCallBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.text_status, 1);
        sViewsWithIds.put(R.id.rv_calls, 2);
        sViewsWithIds.put(R.id.text_stopwatch, 3);
        sViewsWithIds.put(R.id.text_caller, 4);
        sViewsWithIds.put(R.id.text_caller_number, 5);
        sViewsWithIds.put(R.id.frameLayout, 6);
        sViewsWithIds.put(R.id.image_placeholder, 7);
        sViewsWithIds.put(R.id.image_photo, 8);
        sViewsWithIds.put(R.id.dialer_layout, 9);
        sViewsWithIds.put(R.id.dialer_fragment, 10);
        sViewsWithIds.put(R.id.video_placeholder, 11);
        sViewsWithIds.put(R.id.ad_image_placeholder, 12);
        sViewsWithIds.put(R.id.button_hold, 13);
        sViewsWithIds.put(R.id.button_swap, 14);
        sViewsWithIds.put(R.id.button_keypad, 15);
        sViewsWithIds.put(R.id.button_mute, 16);
        sViewsWithIds.put(R.id.button_add_call, 17);
        sViewsWithIds.put(R.id.button_speaker, 18);
        sViewsWithIds.put(R.id.button_merge, 19);
        sViewsWithIds.put(R.id.reject_btn, 20);
        sViewsWithIds.put(R.id.ad_image_banner_placeholder, 21);
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public OnGoingCallBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 22, sIncludes, sViewsWithIds));
    }
    private OnGoingCallBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.ImageView) bindings[21]
            , (android.widget.ImageView) bindings[12]
            , (android.widget.ImageView) bindings[17]
            , (android.widget.ImageView) bindings[13]
            , (android.widget.ImageView) bindings[15]
            , (android.widget.ImageView) bindings[19]
            , (android.widget.ImageView) bindings[16]
            , (android.widget.ImageView) bindings[18]
            , (android.widget.ImageView) bindings[14]
            , (android.widget.FrameLayout) bindings[10]
            , (android.widget.LinearLayout) bindings[9]
            , (android.widget.FrameLayout) bindings[6]
            , (de.hdodenhof.circleimageview.CircleImageView) bindings[8]
            , (android.widget.ImageView) bindings[7]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[0]
            , (com.google.android.material.floatingactionbutton.FloatingActionButton) bindings[20]
            , (androidx.recyclerview.widget.RecyclerView) bindings[2]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[1]
            , (android.widget.TextView) bindings[3]
            , (android.widget.VideoView) bindings[11]
            );
        this.ongoingCallLayout.setTag(null);
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