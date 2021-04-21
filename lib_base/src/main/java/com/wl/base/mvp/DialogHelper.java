
package com.wl.base.mvp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import com.wl.base.R;
import com.wl.lib_log.Logger;

import java.util.HashSet;
import java.util.Set;
import io.reactivex.disposables.Disposable;

class DialogHelper implements  DialogInterface.OnDismissListener {

    public interface OnDismissListener {
        void onDismissed();
    }

    private Context context;
    private Dialog dialog;

    private Set<Disposable> disposables = new HashSet<>();
    /**
     * 是否显示正在加载的View,默认显示
     */
    private boolean isShowLoadView = true;

    private OnDismissListener mOnDismissListener;


    public DialogHelper(Context context) {
        this.context = context;
    }

    public static  DialogHelper initAndBind(final BaseView view, boolean isShowLoadView) {
        if (view == null || !(view instanceof Activity)) {
            throw new IllegalArgumentException("参数只能是Activity");
        }
         DialogHelper helper = new  DialogHelper((Context) view);
        helper.setShowLoadView(isShowLoadView);
        helper.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismissed() {
                view.onLoadViewDismissed();
            }
        });
        return helper;
    }

    /**
     * 是否显示正在加载的View
     *
     * @param showLoadView
     */
    public   DialogHelper setShowLoadView(boolean showLoadView) {
        this.isShowLoadView = showLoadView;
        return this;
    }

    /**
     * 设置加载完成关闭监听
     *
     * @param onDismissListener
     */
    public DialogHelper setOnDismissListener(OnDismissListener onDismissListener) {
        this.mOnDismissListener = onDismissListener;
        return this;
    }

    /**
     * 初始化正在获取数据Dialog
     */
    private Dialog createDialog(Context context) {
        Dialog dialog = new Dialog(context, R.style.CommonDialogTranslucent);
        dialog.setContentView(R.layout.common_dialog_progress);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnDismissListener(this);
        return dialog;
    }

    /**
     * 释放资源
     */
    public synchronized void recycle() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        if (disposables != null) {
            disposables.clear();
        }
        dialog = null;
        context = null;
    }

    /**
     * 显示进度条，并将请求加入集合
     *
     * @param cancel
     */
    public synchronized void showDialog(Object cancel) {
        if (cancel == null) {
            return;
        }
        if (dialog == null) {
            dialog = createDialog(context);
        }

        if (cancel instanceof Disposable) {
            addDisposable(((Disposable) cancel));
        }

        if (isShowLoadView) {
            if (dialog != null && !dialog.isShowing()) {
                dialog.show();
            }
        }
    }


    /**
     * 关闭Dialog
     */
    public synchronized void dismissDialog(Object cancel) {
        if (cancel == null) {
            return;
        }
        if (cancel instanceof Disposable) {
            removeDisposable(((Disposable) cancel));
        }
        int count = disposables.size();
        Logger.d("关闭 " + count);
        //还有未完成的请求，不关闭
        if (count > 0) {
            return;
        }
        //关闭
        if (isShowLoadView) {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        } else {
            cancelAllAndNotifyDismissed();
        }
    }

    /**
     * 添加请求
     *
     * @param d
     */
    private void addDisposable(Disposable d) {
        if (d != null && !d.isDisposed()) {
            disposables.add(d);
            //增加一个任务
            Logger.d("添加一个取消请求- - -" + disposables.size());
        }
    }

    private void removeDisposable(Disposable d) {
        if (d != null && disposables.contains(d)) {
            if (!d.isDisposed()) {
                d.dispose();
            }
            disposables.remove(d);
            //删除一个任务
            Logger.d("删除一个取消请求- - -" + disposables.size());
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        //遍历取消
        cancelAllAndNotifyDismissed();
    }

    /**
     * 取消所有请求
     */
    private void cancelAllAndNotifyDismissed() {
        if (disposables != null && !disposables.isEmpty()) {
            for (Disposable d : disposables) {
                if (d != null && !d.isDisposed()) {
                    d.dispose();
                }
            }
            //清空集合
            disposables.clear();
        }
        Logger.d("取消所有请求");

        if (mOnDismissListener != null) {
            mOnDismissListener.onDismissed();
        }
    }

}