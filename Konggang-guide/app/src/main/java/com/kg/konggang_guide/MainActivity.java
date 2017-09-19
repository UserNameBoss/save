package com.kg.konggang_guide;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.kg.konggang_guide.other.activity.AwaitServiceFragment;
import com.kg.konggang_guide.other.activity.MessageActivity;
import com.kg.konggang_guide.other.activity.OrderActivity;
import com.kg.konggang_guide.other.adapter.AwaitPagerAdapter;
import com.kg.konggang_guide.other.adapter.CarAdapter;
import com.kg.konggang_guide.other.base.CpBaseActivty;
import com.kg.konggang_guide.other.bean.DriverListBean;
import com.kg.konggang_guide.other.bean.OrderBean;
import com.kg.konggang_guide.other.event.MyEvent;
import com.kg.konggang_guide.other.event.OrderEvent;
import com.kg.konggang_guide.other.presenter.MainPresenter;
import com.kg.konggang_guide.other.utils.DebugUtils;
import com.kg.konggang_guide.other.utils.DialogUtils;
import com.kg.konggang_guide.other.utils.ShareUtils;
import com.kg.konggang_guide.other.utils.StringUtil;
import com.kg.konggang_guide.other.utils.TimeUtils;
import com.kg.konggang_guide.other.utils.ToastUtil;
import com.kg.konggang_guide.other.view.IMainView;
import com.kg.konggang_guide.other.widget.CircleImageView;
import com.kg.konggang_guide.personal.AppState;
import com.kg.konggang_guide.personal.activity.MyActivity;
import com.squareup.picasso.Picasso;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import io.reactivex.functions.Consumer;

public class MainActivity extends CpBaseActivty implements TextWatcher, IMainView {


    @BindView(R.id.img_guideIcon)
    CircleImageView imgGuideIcon;
    @BindView(R.id.tv_msgCount)
    TextView tvMsgCount;
    @BindView(R.id.tv_await)
    TextView tvAwait;
    @BindView(R.id.img_await)
    ImageView imgAwait;
    @BindView(R.id.tv_await2)
    TextView tvAwait2;
    @BindView(R.id.img_await2)
    ImageView imgAwait2;
    @BindView(R.id.vpager)
    ViewPager vpager;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_from)
    TextView tvFrom;
    @BindView(R.id.tv_to)
    TextView tvTo;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_chanageGuide)
    TextView tvChanageGuide;
    @BindView(R.id.ll_await)
    LinearLayout llAwait;
    @BindView(R.id.ll_await2)
    LinearLayout llAwait2;
    @BindView(R.id.xrv_car)
    XRecyclerView xrvCar;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_changeGuide)
    LinearLayout llChangeGuide;
    @BindView(R.id.tv_flight)
    TextView tvFlight;
    @BindView(R.id.tv_order_time)
    TextView tvOrderTime;
    @BindView(R.id.tv_order_from)
    TextView tvOrderFrom;
    @BindView(R.id.tv_order_to)
    TextView tvOrderTo;
    @BindView(R.id.tv_order)
    TextView tvOrder;
    @BindView(R.id.tv_order_phone)
    TextView tvOrderPhone;
    @BindView(R.id.ll_order)
    LinearLayout llOrder;
    @BindView(R.id.tv_nowOrder)
    TextView tvNowOrder;
    @BindView(R.id.et_now_from)
    EditText etNowFrom;
    @BindView(R.id.et_now_to)
    EditText etNowTo;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.ll_nowOrder)
    LinearLayout llNowOrder;
    @BindView(R.id.fl_message)
    FrameLayout fl_message;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_flight01)
    TextView tvFlight01;
    @BindView(R.id.ll_flight01)
    LinearLayout llFlight01;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.ll_flight02)
    LinearLayout llFlight02;
    @BindView(R.id.tv_changeHint)
    TextView tvChangeHint;
    @BindView(R.id.ll_changeGuideChild)
    LinearLayout llChangeGuideChild;
    @BindView(R.id.tv_orderHint)
    TextView tvOrderHint;
    @BindView(R.id.ll_orderChild)
    LinearLayout llOrderChild;

    private ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    private AwaitServiceFragment awaitServiceFragment, awaitOrderFragment;
    private AwaitPagerAdapter awaitPagerAdapter;
    private CarAdapter carAdapter;
    private String phone;
    private String nowTo;
    private String nowFrom;
    private MainPresenter mainPresenter;
    private DriverListBean driverList;
    private String guidePhone;
    private String remarkMessage;
    private String dirId;
    //接机类型 1立即 2预约
    //待服务
    private String order01Type;
    //待接单
    private String order02Type;
    private String typeOrder;

    //待服务订单ID
    private String orderId01;
    //待接单订单ID
    private String orderId02;
    private String cityId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initViews();
        initEvents();
        initDatas();
    }

    @Override
    protected void initViews() {

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // supportTitleStatus(true);
        setMiuiStatusBarDarkMode(this, true);
        requestRxPermissions(Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xrvCar.setLayoutManager(linearLayoutManager);
        xrvCar.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mainPresenter.getDriverList();
            }

            @Override
            public void onLoadMore() {

            }
        });
    }

    //请求权限
    private void requestRxPermissions(String... permissions) {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(permissions).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(@NonNull Boolean granted) throws Exception {
                if (granted) {
                    DebugUtils.prinlnLog("已获取权限");
                } else {
                    DebugUtils.prinlnLog("已拒绝一个或以上权限");
                }
            }
        });
    }

    @Override
    protected void initEvents() {
        etNowFrom.addTextChangedListener(this);
        etNowTo.addTextChangedListener(this);
        etPhone.addTextChangedListener(this);
    }

    @Override
    protected void initDatas() {


        //设置别名
        JPushInterface.setAlias(this, ShareUtils.getInstance().getCache(AppSet.FLAR_USERID), new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> set) {

            }
        });

        awaitServiceFragment = new AwaitServiceFragment();
        awaitServiceFragment.setOnFragmentData(new AwaitServiceFragment.OnFragmentData() {
            @Override
            public void itemClick(int position, int type, OrderBean.DataEntity.ListEntity dataEntity) {
                llChangeGuide.setVisibility(View.VISIBLE);
                llOrder.setVisibility(View.GONE);
                llNowOrder.setVisibility(View.GONE);
                orderId01 = dataEntity.orderId;
                typeOrder=order01Type = dataEntity.type + "";
                if ("2".equals(order01Type)) {
                    llFlight01.setVisibility(View.VISIBLE);
                    tvFlight01.setText(dataEntity.flightNumber);
                } else {
                    llFlight01.setVisibility(View.GONE);
                }
                tvTime.setText(TimeUtils.getDateWithTime(dataEntity.upTime));
                tvPhone.setText(dataEntity.telephone);
                tvFrom.setText(dataEntity.departureLocation);
                tvTo.setText(dataEntity.arrivedLocation);
                if (vpager.getCurrentItem() != 0) {
                    vpager.setCurrentItem(0);
                }
            }
        });
        awaitOrderFragment = new AwaitServiceFragment();
        awaitOrderFragment.setOnFragmentData(new AwaitServiceFragment.OnFragmentData() {
            @Override
            public void itemClick(int position, int type, OrderBean.DataEntity.ListEntity dataEntity) {
                llChangeGuide.setVisibility(View.GONE);
                llOrder.setVisibility(View.VISIBLE);
                llNowOrder.setVisibility(View.GONE);
                orderId02 = dataEntity.orderId;
                typeOrder=order02Type = dataEntity.type + "";
                if ("2".equals(dataEntity.type)) {
                    llFlight02.setVisibility(View.VISIBLE);
                    tvFlight.setText(dataEntity.flightNumber);
                } else {
                    llFlight02.setVisibility(View.GONE);
                }
                tvOrderTime.setText(TimeUtils.getDateWithTime(dataEntity.upTime));
                tvOrderPhone.setText(dataEntity.telephone);
                tvOrderFrom.setText(dataEntity.departureLocation);
                tvOrderTo.setText(dataEntity.arrivedLocation);
                if (vpager.getCurrentItem() != 1) {
                    vpager.setCurrentItem(1);
                }
            }
        });
        awaitOrderFragment.setType(1);
        awaitServiceFragment.setType(0);
        fragmentArrayList.add(awaitServiceFragment);
        fragmentArrayList.add(awaitOrderFragment);
        awaitPagerAdapter = new AwaitPagerAdapter(getSupportFragmentManager());
        awaitPagerAdapter.setFragments(fragmentArrayList);
        vpager.setAdapter(awaitPagerAdapter);
        vpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    setAwaitBg(tvAwait, tvAwait2, imgAwait, imgAwait2);
                    llChangeGuide.setVisibility(View.VISIBLE);
                    llOrder.setVisibility(View.GONE);
                    tvTitle.setText("派车");


                } else {
                    llOrder.setVisibility(View.VISIBLE);
                    llChangeGuide.setVisibility(View.GONE);
                    tvTitle.setText("接单");
                    setAwaitBg(tvAwait2, tvAwait, imgAwait2, imgAwait);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        carAdapter = new CarAdapter();
        carAdapter.setOnClickCar(new CarAdapter.OnClickCar() {
            @Override
            public void toCar(final int position) {
                if (!TextUtils.isEmpty(orderId01)) {
                    DialogUtils.toCarDialog(MainActivity.this, "确认派车？", "确定", new DialogUtils.OnClickListener() {
                        @Override
                        public void onClickRight() {
                            dirId = driverList.data.get(position).uId + "";
                            mainPresenter.toCar();
                        }
                    });
                } else {
                    showToask("您还未选择订单！");
                }
            }
        });

        String picture = AppState.getInstance().getPicture();
        if (!TextUtils.isEmpty(picture)) {
            Picasso.with(this).load(picture).into(imgGuideIcon);
        }
        String name = AppState.getInstance().getName();
        if (!TextUtils.isEmpty(name)) {
            tv_name.setText(name);
        } else {
            tv_name.setText("出行管家");
        }

        xrvCar.setAdapter(carAdapter);
        mainPresenter = new MainPresenter(this);
        mainPresenter.getDriverList();
        mainPresenter.getWrite();
    }

    @Override
    public void setRequestedOrientation(int requestedOrientation) {
        return;
    }

    @OnClick({R.id.tv_order, R.id.img_guideIcon, R.id.fl_message, R.id.tv_login, R.id.tv_nowOrder, R.id.ll_await, R.id.ll_await2, R.id.tv_chanageGuide})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_await:
                vpager.setCurrentItem(0);
                break;
            case R.id.ll_await2:
                vpager.setCurrentItem(1);
                break;
            case R.id.tv_chanageGuide:
                if (!TextUtils.isEmpty(orderId01)) {
                    DialogUtils.changeGuideDialog(MainActivity.this, new DialogUtils.OnCommitMessage() {


                        @Override
                        public void setMessage(String guideCode, String message) {
                            guidePhone = guideCode;
                            remarkMessage = message;
                            mainPresenter.setChangeGuide();
                        }
                    });
                } else {
                    showToask("您还未选择订单！");
                }
                break;
            case R.id.tv_nowOrder:
                llChangeGuide.setVisibility(View.GONE);
                llOrder.setVisibility(View.GONE);
                llNowOrder.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_login:
                if (StringUtil.isMobile(phone)) {
                    Bundle bundle = new Bundle();
                    bundle.putString("from", nowFrom);
                    bundle.putString("to", nowTo);
                    bundle.putString("phone", phone);
                    openActivity(OrderActivity.class, bundle);
                } else {
                    ToastUtil.getInstance(MainActivity.this).shortToast("请输入正确的手机号！");
                }
                break;
            case R.id.fl_message:
                openActivity(MessageActivity.class);
                break;

            case R.id.img_guideIcon:
                openActivity(MyActivity.class);
                break;
            case R.id.tv_order:
                if (!TextUtils.isEmpty(orderId02)) {
                    mainPresenter.getOrder();
                } else {
                    showToask("您还未选择订单！");
                }
                break;

        }
    }

    private void setAwaitBg(TextView t1, TextView t2, ImageView img1, ImageView img2) {
        t1.setTextColor(getResources().getColor(R.color.color_38ADFF));
        t2.setTextColor(getResources().getColor(R.color.color_333333));
        img1.setVisibility(View.VISIBLE);
        img2.setVisibility(View.INVISIBLE);
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }


    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }


    @Override
    public void afterTextChanged(Editable s) {
        nowFrom = etNowFrom.getText().toString();
        nowTo = etNowTo.getText().toString();
        phone = etPhone.getText().toString();
        if (!TextUtils.isEmpty(nowFrom) && !TextUtils.isEmpty(nowTo) && !TextUtils.isEmpty(phone)) {
            tvLogin.setEnabled(true);
        } else {
            tvLogin.setEnabled(false);
        }
    }

    @Override
    public String getTerminalBuilding() {
        return "宝安机场F1航站楼";
    }

    @Override
    public String getOrderId() {
        return orderId01;
    }

    @Override
    public String getPhone() {
        return guidePhone;
    }

    @Override
    public String getRemark() {
        return remarkMessage;
    }

    @Override
    public void isChangeGuide() {
        showToask("改派成功！");

        orderId01 = "";
        order01Type = "";
        tvFlight01.setText("");
        llFlight01.setVisibility(View.GONE);
        tvTime.setText("");
        tvPhone.setText("");
        tvFrom.setText("上车地点");
        tvTo.setText("下车地点");
        awaitServiceFragment.refreshData();
    }

    @Override
    public String getOrderId02() {
        return orderId02;
    }

    @Override
    public String getGuideId() {
        return AppState.getInstance().getUserId();
    }

    @Override
    public void getOrderSuccess(boolean isSuccess) {
        if(isSuccess) {
            showToask("接单成功！");
            orderId02 = "";
            order02Type = "";
            tvFlight.setText("");
            llFlight02.setVisibility(View.GONE);
            tvOrderTime.setText("");
            tvOrderPhone.setText("");
            tvOrderFrom.setText("上车地点");
            tvOrderTo.setText("下车地点");
        }
        awaitOrderFragment.refreshData();
        awaitServiceFragment.refreshData();
    }

    @Override
    public String getType() {
        return order01Type;
    }

    @Override
    public String getDirId() {
        return dirId;
    }

    @Override
    public String getCityId() {
        return AppState.getInstance().getCityId();
    }

    @Override
    public void isSendCarSuccess(boolean isSuccess) {
        if(isSuccess) {
            showToask("派车成功！");
            orderId01 = "";
            order01Type = "";
            tvFlight01.setText("");
            llFlight01.setVisibility(View.GONE);
            tvTime.setText("");
            tvPhone.setText("");
            tvFrom.setText("上车地点");
            tvTo.setText("下车地点");
        }

        awaitServiceFragment.refreshData();
        mainPresenter.getDriverList();

    }

    @Override
    public void setMessageNoRead(int count) {
        if (count > 0) {
            tvMsgCount.setText(count + "");
            tvMsgCount.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setDriverList(DriverListBean driverList) {
        this.driverList = driverList;
        carAdapter.setDriverListBean(driverList);
        xrvCar.refreshComplete();
    }


    @Subscribe
    public void onEventMainThread(MyEvent myEvent) {
        if (myEvent.getType() == 1) {
            if (myEvent.isRefresh()) {
                awaitServiceFragment.refreshData();
            }
        } else if (myEvent.getType() == 2) {
            tvMsgCount.setText("0");
            tvMsgCount.setVisibility(View.GONE);
        }
    }

    private double lastTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - lastTime < 2000) {
                finish();
            } else {
                //如果大于两秒，继续弹出提示
                Toast.makeText(this, "再按一次退出!", Toast.LENGTH_SHORT).show();
                lastTime = System.currentTimeMillis();
            }
        }

        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEventMainThread(OrderEvent orderEvent) {
        if (orderEvent.getIsRefresh()) {
            if (orderEvent.getType() == 3) {
                String message = orderEvent.getMessage();
                if (!TextUtils.isEmpty(message)) {
                    try {
                        JSONObject jsonObject = new JSONObject(message);
                        int type=jsonObject.getInt("type");
                        String message1=null;
                        if(type==1){
                            message1="您有一笔立即接机的订单，请及时接单。";
                            DialogUtils.hintMessage(this,matcherSearchText(getResources().getColor(R.color.color_f44b4b),message1,"立即接机"),orderEvent.getCurrTime());
                        }else{
                            String timeString=jsonObject.getString("time");
                            if(!TextUtils.isEmpty(timeString)) {
                                CharSequence charTime=TimeUtils.getDateWithTime(TimeUtils.getTimeLong(timeString));
                                message1 = "您有一笔"+charTime+ "的接机订单，请及时接单。";
                                DialogUtils.hintMessage(this, matcherSearchText(getResources().getColor(R.color.color_38ADFF),message1,charTime.toString()), orderEvent.getCurrTime());
                            }
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                mainPresenter.getWrite();
            }
        }
    }

    public SpannableString matcherSearchText(int color, String text, String keyword) {
        SpannableString ss = new SpannableString(text);

        Pattern pattern = Pattern.compile(keyword);
        Matcher matcher = pattern.matcher(ss);

        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            ss.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        return ss;
    }

    public void setLayoutChange(boolean isVisible, int type) {
        if (type == 0) {
            if (isVisible) {
                tvChangeHint.setVisibility(View.GONE);
                llChangeGuideChild.setVisibility(View.VISIBLE);
            } else {
                tvChangeHint.setVisibility(View.VISIBLE);
                llChangeGuideChild.setVisibility(View.GONE);
            }
        } else {
            if (isVisible) {
                tvOrderHint.setVisibility(View.GONE);
                llOrderChild.setVisibility(View.VISIBLE);
            } else {
                tvOrderHint.setVisibility(View.VISIBLE);
                llOrderChild.setVisibility(View.GONE);
            }
        }
    }
}
