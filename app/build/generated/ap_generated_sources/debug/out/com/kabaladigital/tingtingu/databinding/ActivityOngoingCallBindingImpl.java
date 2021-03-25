package com.kabaladigital.tingtingu.databinding;
import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityOngoingCallBindingImpl extends ActivityOngoingCallBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new androidx.databinding.ViewDataBinding.IncludedLayouts(4);
        sIncludes.setIncludes(0, 
            new String[] {"on_going_call", "layout_incoming_call", "overlay_send_sms"},
            new int[] {1, 2, 3},
            new int[] {com.kabaladigital.tingtingu.R.layout.on_going_call,
                com.kabaladigital.tingtingu.R.layout.layout_incoming_call,
                com.kabaladigital.tingtingu.R.layout.overlay_send_sms});
        sViewsWithIds = null;
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityOngoingCallBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }
    private ActivityOngoingCallBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3
            , (androidx.coordinatorlayout.widget.CoordinatorLayout) bindings[0]
            , (com.kabaladigital.tingtingu.databinding.LayoutIncomingCallBinding) bindings[2]
            , (com.kabaladigital.tingtingu.databinding.OnGoingCallBinding) bindings[1]
            , (com.kabaladigital.tingtingu.databinding.OverlaySendSmsBinding) bindings[3]
            );
        this.frame.setTag(null);
        setContainedBinding(this.incomingCallLayout);
        setContainedBinding(this.ongoingCallLayout);
        setContainedBinding(this.overlaySendSms);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x8L;
        }
        ongoingCallLayout.invalidateAll();
        incomingCallLayout.invalidateAll();
        overlaySendSms.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (ongoingCallLayout.hasPendingBindings()) {
            return true;
        }
        if (incomingCallLayout.hasPendingBindings()) {
            return true;
        }
        if (overlaySendSms.hasPendingBindings()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    public void setLifecycleOwner(@Nullable androidx.lifecycle.LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        ongoingCallLayout.setLifecycleOwner(lifecycleOwner);
        incomingCallLayout.setLifecycleOwner(lifecycleOwner);
        overlaySendSms.setLifecycleOwner(lifecycleOwner);
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeOngoingCallLayout((com.kabaladigital.tingtingu.databinding.OnGoingCallBinding) object, fieldId);
            case 1 :
                return onChangeOverlaySendSms((com.kabaladigital.tingtingu.databinding.OverlaySendSmsBinding) object, fieldId);
            case 2 :
                return onChangeIncomingCallLayout((com.kabaladigital.tingtingu.databinding.LayoutIncomingCallBinding) object, fieldId);
        }
        return false;
    }
    private boolean onChangeOngoingCallLayout(com.kabaladigital.tingtingu.databinding.OnGoingCallBinding OngoingCallLayout, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeOverlaySendSms(com.kabaladigital.tingtingu.databinding.OverlaySendSmsBinding OverlaySendSms, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeIncomingCallLayout(com.kabaladigital.tingtingu.databinding.LayoutIncomingCallBinding IncomingCallLayout, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
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
        executeBindingsOn(ongoingCallLayout);
        executeBindingsOn(incomingCallLayout);
        executeBindingsOn(overlaySendSms);
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): ongoingCallLayout
        flag 1 (0x2L): overlaySendSms
        flag 2 (0x3L): incomingCallLayout
        flag 3 (0x4L): null
    flag mapping end*/
    //end
}