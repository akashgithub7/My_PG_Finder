package com.mymindakash.my_pg_finder;

/**
 * Created by Akash117759 on 02-12-2016.
 */
import android.graphics.RectF;

public interface TransitionGenerator {

    public Transition generateNextTransition(RectF drawableBounds, RectF viewport);

}
