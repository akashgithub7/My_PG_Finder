package com.mymindakash.my_pg_finder;

/**
 * Created by Akash117759 on 02-12-2016.
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;


public class KenBurnsView extends ImageView {

    private static final long FRAME_DELAY = 1000 / 60;

    private final Matrix mMatrix = new Matrix();

    private TransitionGenerator mTransGen = (TransitionGenerator) new RandomTransitionGenerator();

    private TransitionListener mTransitionListener;

    private Transition mCurrentTrans;

    private final RectF mViewportRect = new RectF();
    private RectF mDrawableRect;

    private long mElapsedTime;

    private long mLastFrameTime;

    private boolean mPaused;

    private boolean mInitialized;


    public KenBurnsView(Context context) {
        this(context, null);
    }


    public KenBurnsView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public KenBurnsView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mInitialized = true;
        super.setScaleType(ScaleType.MATRIX);
    }


    @Override
    public void setScaleType(ScaleType scaleType) {
    }


    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);

        switch (visibility) {
            case VISIBLE:
                resume();
                break;
            default:
                pause();
                break;
        }
    }


    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        handleImageChange();
    }


    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        handleImageChange();
    }


    @Override
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        handleImageChange();
    }


    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        handleImageChange();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        restart();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        Drawable d = getDrawable();
        if (!mPaused && d != null) {
            if (mDrawableRect.isEmpty()) {
                updateDrawableBounds();
            } else if (hasBounds()) {
                if (mCurrentTrans == null) { // Starting the first transition.
                    startNewTransition();
                }

                if (mCurrentTrans.getDestinyRect() != null) { // If null, it's supposed to stop.
                    mElapsedTime += System.currentTimeMillis() - mLastFrameTime;
                    RectF currentRect = mCurrentTrans.getInterpolatedRect(mElapsedTime);

                    float widthScale = mDrawableRect.width() / currentRect.width();
                    float heightScale = mDrawableRect.height() / currentRect.height();
                    float currRectToDrwScale = Math.min(widthScale, heightScale);
                    float currRectToVpScale = mViewportRect.width() / currentRect.width();
                    float totalScale = currRectToDrwScale * currRectToVpScale;

                    float translX = totalScale * (mDrawableRect.centerX() - currentRect.left);
                    float translY = totalScale * (mDrawableRect.centerY() - currentRect.top);

                    mMatrix.reset();
                    mMatrix.postTranslate(-mDrawableRect.width() / 2, -mDrawableRect.height() / 2);
                    mMatrix.postScale(totalScale, totalScale);
                    mMatrix.postTranslate(translX, translY);

                    setImageMatrix(mMatrix);

                    if (mElapsedTime >= mCurrentTrans.getDuration()) {
                        fireTransitionEnd(mCurrentTrans);
                        startNewTransition();
                    }
                } else {
                    fireTransitionEnd(mCurrentTrans);
                }
            }
            mLastFrameTime = System.currentTimeMillis();
            postInvalidateDelayed(FRAME_DELAY);
        }
        super.onDraw(canvas);
    }



    private void startNewTransition() {
        if (!hasBounds()) {
            throw new UnsupportedOperationException("Can't start transition if the " +
                    "drawable has no bounds!");
        }
        mCurrentTrans = mTransGen.generateNextTransition(mDrawableRect, mViewportRect);
        mElapsedTime = 0;
        mLastFrameTime = System.currentTimeMillis();
        fireTransitionStart(mCurrentTrans);
    }


    public void restart() {
        int width = getWidth();
        int height = getHeight();

        if (width == 0 || height == 0) {
            throw new UnsupportedOperationException("Can't call restart() when view area is zero!");
        }

        updateViewport(width, height);
        updateDrawableBounds();

        if (hasBounds()) {
            startNewTransition();
        }
    }

    private boolean hasBounds() {
        return !mViewportRect.isEmpty();
    }

    private void fireTransitionStart(Transition transition) {
        if (mTransitionListener != null && transition != null) {
            mTransitionListener.onTransitionStart(transition);
        }
    }

    private void fireTransitionEnd(Transition transition) {
        if (mTransitionListener != null && transition != null) {
            mTransitionListener.onTransitionEnd(transition);
        }
    }


    public void setTransitionGenerator(TransitionGenerator transgen) {
        mTransGen = transgen;
        if (hasBounds()) {
            startNewTransition();
        }
    }

    private void updateViewport(float width, float height) {
        mViewportRect.set(0, 0, width, height);
    }

    private void updateDrawableBounds() {
        if (mDrawableRect == null) {
            mDrawableRect = new RectF();
        }
        Drawable d = getDrawable();
        if (d != null) {
            mDrawableRect.set(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
        }
    }


    private void handleImageChange() {
        updateDrawableBounds();
        if (mInitialized && hasBounds()) {
            startNewTransition();
        }
    }


    public void setTransitionListener(TransitionListener transitionListener) {
        mTransitionListener = transitionListener;
    }

    public void pause() {
        mPaused = true;
    }

    public void resume() {
        mPaused = false;
        mLastFrameTime = System.currentTimeMillis();
        invalidate();
    }


    public interface TransitionListener {
        public void onTransitionStart(Transition transition);
        public void onTransitionEnd(Transition transition);
    }
}
