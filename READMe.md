### Fragment使用注意事项回顾：[Fragment相关博客] (https://blog.csdn.net/u012440207/article/category/2220511)

#### 1、commit()  commitNow()  commitAllowingStateLoss()  commitNowAllowingStateLoss() executePendingTransactions()使用场景：

##### 1）commit(): 如果执行的提交是不需要同步的，或者需要将提交都添加到回退栈中，那么久使用commit()。
##### 2) commitNow(): 如果执行的提交是同步的，且无需添加到回退栈中，则使用该方法。
##### 3) executePendingTransactions(): 如果需要把多次的操作在同一时间点一起进行，则使用该方法。
##### 4) commitAllowingStateLoss()/commitAllowingStateLoss(): 如果你需要在Activity执行完onSaveInstanceState()之后，
#####    还需要提交，而且不关心恢复时是否会丢失此次提交，那么可以使用commitAllowingStateLoss()或者commitNowAllowingStateLoss()方法。