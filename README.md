## 根据网上一些资料以及自己琢磨的，写了一个MVP的框架，这是用知乎日报的api测试这个框架的Demo

### 先写一个基础接口

```java
public interface IBaseView {
}
```

### 抽象的基础Presenter

```java
public abstract class BasePresenter <V extends IBaseView> {

    protected Reference<V> mVReference;

    //建立一个view的弱引用，防止内存泄漏
    //继承这个presenter时重写该构造方法，传this进来
    public BasePresenter(V view){
        mVReference=new WeakReference<V>(view);
    }


    protected V getView(){
        return mVReference.get();
    }

    //每次getView()的时候应该都调用这个方法检查引用是否还未被回收 
    public boolean isViewAttached(){
        return mVReference!=null&&mVReference.get()!=null;
    }

    public void detachView(){
        if (mVReference != null) {
            mVReference.clear();
            mVReference=null;
        }
    }
}
```

### 抽象的基础Activity

```java
public abstract class BaseActivtiy<V extends IBaseView, P extends BasePresenter<V>>  extends AppCompatActivity {
    
    //继承这个类的activity直接用mPresnter即可
    protected P mPresenter;

    /**
     * @return 布局文件
     */
    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * 实现该方法时，new一个自己写的presenter实例
     * @return presenter对象
     */
    protected abstract P getPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        //与上面对应
        mPresenter=getPresenter();
    }

    @Override
    protected void onDestroy() {
        //解除presenter对视图的引用
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
    }
}
```

### 抽象的基础Fragment(与上面的activity雷同)

```java
public abstract class BaseFragment<V extends IBaseView, P extends BasePresenter<V>> extends Fragment {

    protected P mPresenter;

    /**
     * @return frgment的布局文件
     */
    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * @return presenter对象
     */
    protected abstract P getPresenter();

    /**
     * 留出这个抽象方法
     * 把一些额外的view任务以及presenter的任务都交给子类
     */
    protected abstract void initView();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        mPresenter = getPresenter();
        initView();
        return view;
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroyView();

    }
}
```


### 代码很简单，可以根据自己的业务需求对基础类做一些扩展，比如加上ButterKnife，等等，具体使用方法看Demo的代码。

### 该Demo运行效果：
![](https://github.com/howshea/BaseMVPDemo/raw/master/screenshots/Screenshot_20161215-170209.png)
