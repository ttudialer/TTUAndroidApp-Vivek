package com.kabaladigital.tingtingu.databinding;
import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class SettingFragmentBindingImpl extends SettingFragmentBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tv_temporary_disable, 1);
        sViewsWithIds.put(R.id.switch_temporary_disable, 2);
        sViewsWithIds.put(R.id.tv_temporary_min, 3);
        sViewsWithIds.put(R.id.tv_temporary_max, 4);
        sViewsWithIds.put(R.id.seek_bar_temporary_disable, 5);
        sViewsWithIds.put(R.id.tv_days, 6);
        sViewsWithIds.put(R.id.line_one, 7);
        sViewsWithIds.put(R.id.tv_change_operator_heading, 8);
        sViewsWithIds.put(R.id.tv_change_operator_sub_heading, 9);
        sViewsWithIds.put(R.id.iv_operator, 10);
        sViewsWithIds.put(R.id.line_two, 11);
        sViewsWithIds.put(R.id.tv_change_plan_type, 12);
        sViewsWithIds.put(R.id.tv_change_plan_type_sub_heading, 13);
        sViewsWithIds.put(R.id.line_three, 14);
        sViewsWithIds.put(R.id.tv_change_language, 15);
        sViewsWithIds.put(R.id.tv_change_language_sub_heading, 16);
        sViewsWithIds.put(R.id.line_four, 17);
    }
    // views
    @NonNull
    private final androidx.core.widget.NestedScrollView mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public SettingFragmentBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 18, sIncludes, sViewsWithIds));
    }
    private SettingFragmentBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.ImageView) bindings[10]
            , (android.view.View) bindings[17]
            , (android.view.View) bindings[7]
            , (android.view.View) bindings[14]
            , (android.view.View) bindings[11]
            , (androidx.appcompat.widget.AppCompatSeekBar) bindings[5]
            , (android.widget.Switch) bindings[2]
            , (android.widget.TextView) bindings[15]
            , (android.widget.TextView) bindings[16]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[12]
            , (android.widget.TextView) bindings[13]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[1]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[3]
            );
        this.mboundView0 = (androidx.core.widget.NestedScrollView) bindings[0];
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