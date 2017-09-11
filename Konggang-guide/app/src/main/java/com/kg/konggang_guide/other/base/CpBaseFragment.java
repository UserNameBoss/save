package com.kg.konggang_guide.other.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.kg.konggang_guide.R;
import com.kg.konggang_guide.other.utils.ToastUtil;


/**
 * Created by Administrator on 2016/12/2
 */
public abstract class CpBaseFragment extends BaseFragment implements IView{
    protected Handler handler = new Handler();
    protected ProgressDialog waitDialog;

    @Override
    public void toResult(int code) {

    }

    @Override
    public void noNet() {

    }

    @Override
    public void showToask(String str) {
        ToastUtil.getInstance(this.getActivity().getApplication()).shortToast(str);
    }

    @Override
    public void showToask(int i) {
        ToastUtil.getInstance(this.getActivity().getApplication()).shortToast(i);
    }

    @Override
    public void showDialog(String msg) {

    }

    @Override
    public void toOtherActivity(Class<?> cls) {
        openActivity(cls);
    }

    @Override
    public void toFinishActivity() {
        this.getActivity().finish();
    }

    @Override
    public Context getMyContext() {
        return this.getActivity();
    }

    public void setLoading(boolean isLoading) {
        try {
            if (isLoading) {
                if (waitDialog == null || !waitDialog.isShowing()) {
                    waitDialog = new ProgressDialog(CpBaseFragment.this.getActivity(), R.style.MyDialogStyleBottom);
                    waitDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    waitDialog.setCanceledOnTouchOutside(false);
                    ImageView view = new ImageView(CpBaseFragment.this.getActivity());
                    view.setLayoutParams(new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
                    Animation loadAnimation = AnimationUtils.loadAnimation(
                            CpBaseFragment.this.getActivity(), R.anim.rotate);
                    view.startAnimation(loadAnimation);
                    loadAnimation.start();
                    view.setImageResource(R.mipmap.loading);
                    waitDialog.show();
                    waitDialog.setContentView(view);
                }

            } else {
                if (waitDialog != null && waitDialog.isShowing()) {
                    waitDialog.dismiss();
                    waitDialog = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
