package me.lu7idan;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class StatesViewGroup extends FrameLayout {
    SparseArray<View> stateViews;

    public StatesViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        stateViews = new SparseArray<>();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        findViewsByTag(stateViews, this, "_state");
    }


    public void showState(boolean visible, int id, boolean reverseOtherStates) {
        if (stateViews.get(id) != null) {
            for (int i = 0; i < stateViews.size(); i++) {
                View v = stateViews.valueAt(i);
                if (v.getId() == id)
                    v.setVisibility(visible ? View.VISIBLE : View.GONE);
                else if (reverseOtherStates)
                    v.setVisibility(visible ? View.GONE : View.VISIBLE);
            }
        }
    }

    public void showState(int id, boolean ignoreHidingOtherStates) {
        showState(true, id, !ignoreHidingOtherStates);
    }

    public void showState(int id) {
        showState(true, id, true);
    }

    public void hideState(int id) {
        showState(false, id, false);
    }

    public boolean isStateVisible(int id) {
        if (stateViews.get(id) != null) {
            return stateViews.get(id).getVisibility() == View.VISIBLE;
        }
        return false;
    }

    public View getState(int id) {
        return stateViews.get(id);
    }

    private static void findViewsByTag(SparseArray<View> views, ViewGroup root, String tag) {
        final int childCount = root.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = root.getChildAt(i);
            if (child instanceof ViewGroup) {
                findViewsByTag(views, (ViewGroup) child, tag);
            }

            final Object tagObj = child.getTag();
            if (tagObj != null && tagObj.equals(tag)) {
                views.put(child.getId(), child);
            }
        }
    }
}
