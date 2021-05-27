package com.kabaladigital.tingtingu.databinding;
import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class LayoutIncomingCallBindingImpl extends LayoutIncomingCallBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tv_status, 1);
        sViewsWithIds.put(R.id.tv_caller_name, 2);
        sViewsWithIds.put(R.id.tv_caller_number, 3);
        sViewsWithIds.put(R.id.frameLayout, 4);
        sViewsWithIds.put(R.id.image_placeholder, 5);
        sViewsWithIds.put(R.id.image_photo, 6);
        sViewsWithIds.put(R.id.video_fullscreen_placeholder, 7);
        sViewsWithIds.put(R.id.video_placeholder, 8);
        sViewsWithIds.put(R.id.ad_image_placeholder, 9);
        sViewsWithIds.put(R.id.image_fullscreen_placeholder, 10);
        sViewsWithIds.put(R.id.btn_answer, 11);
        sViewsWithIds.put(R.id.btn_reject, 12);
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public LayoutIncomingCallBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 13, sIncludes, sViewsWithIds));
    }
    private LayoutIncomingCallBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.ImageView) bindings[9]
            , (com.google.android.material.floatingactionbutton.FloatingActionButton) bindings[11]
            , (com.google.android.material.floatingactionbutton.FloatingActionButton) bindings[12]
            , (android.widget.FrameLayout) bindings[4]
            , (android.widget.ImageView) bindings[10]
            , (de.hdodenhof.circleimageview.CircleImageView) bindings[6]
            , (android.widget.ImageView) bindings[5]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[0]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[1]
            , (android.widget.VideoView) bindings[7]
            , (android.widget.VideoView) bindings[8]
            );
        this.incomingCallLayout.setTag(null);
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