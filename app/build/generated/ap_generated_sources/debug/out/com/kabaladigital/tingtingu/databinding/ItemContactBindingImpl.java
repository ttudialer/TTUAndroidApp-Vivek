package com.kabaladigital.tingtingu.databinding;
import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemContactBindingImpl extends ItemContactBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.frameLayout, 1);
        sViewsWithIds.put(R.id.item_photo_placeholder, 2);
        sViewsWithIds.put(R.id.item_photo, 3);
        sViewsWithIds.put(R.id.tv_date_time, 4);
        sViewsWithIds.put(R.id.tv_call_status, 5);
        sViewsWithIds.put(R.id.layout_name, 6);
        sViewsWithIds.put(R.id.tv_caller_name, 7);
        sViewsWithIds.put(R.id.iv_ttu_logo, 8);
        sViewsWithIds.put(R.id.tv_caller_number, 9);
        sViewsWithIds.put(R.id.tv_ttu, 10);
        sViewsWithIds.put(R.id.btn_call, 11);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemContactBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds));
    }
    private ItemContactBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (com.google.android.material.floatingactionbutton.FloatingActionButton) bindings[11]
            , (android.widget.FrameLayout) bindings[1]
            , (de.hdodenhof.circleimageview.CircleImageView) bindings[3]
            , (android.widget.ImageView) bindings[2]
            , (android.widget.ImageView) bindings[8]
            , (android.widget.LinearLayout) bindings[6]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[10]
            );
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
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