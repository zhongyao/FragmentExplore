package com.hongri.fragment;

import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 *
 * 参考：
 * https://blog.csdn.net/freelander_j/article/details/52925745
 * https://www.jianshu.com/p/83e673c453f9
 *
 * commit() 与 commitAllowingStateLoss() 的区别：
 * commit---会在Activity保存状态之前提交，否则会报异常
 * 【所以一般情况下需要尽量提前调用，比如在onCreate中；异步回调时避免使用commit】。
 * commitAllowingStateLoss---会忽略Activity的状态保持。
 * 【不得已的情况下可以使用commitAllowingStateLoss，毕竟崩溃比页面状态信息丢失更严重】。
 *
 * commitNow():立刻执行
 * commitNowAllowingStateLoss:立刻执行(忽略状态保存)
 *
 * 如果你需要同步提交Fragment并且无需添加到回退栈中，则使用commitNow()。Support库中在
 * FragmentPagerAdapter中使用这个函数，来确保更新Adapter的时候页面被正确的添加和删除。一般来说，只要不添加到回退栈中，都可以使用这个函数来提交。
 * 如果执行的提交不需要是同步的，或者需要将提交都添加到回退栈中，那么就使用commit()。
 * 如果你需要把多次提交的操作在同一个时间点一起执行，则使用 executePendingTransactions()
 * 如果你需要在Activity执行完onSaveInstanceState()之后还要进行提交，而且不关心恢复时是否会丢失此次提交，那么可以使用commitAllowingStateLoss()
 * 或commitNowAllowingStateLoss()。
 *
 *
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        MainFragment mainFragment = new MainFragment();
        transaction.add(R.id.root_layout, mainFragment);
        transaction.addToBackStack("main");
        //transaction.commit();
        transaction.commitAllowingStateLoss();
        transaction.commitNow();
        transaction.commitNowAllowingStateLoss();

    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }
}
