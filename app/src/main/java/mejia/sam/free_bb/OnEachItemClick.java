package mejia.sam.free_bb;

import android.view.View;

import mejia.sam.free_bb.model.Person;

public interface OnEachItemClick {
    void onEachClick(View view , Person person  , String department);
}
