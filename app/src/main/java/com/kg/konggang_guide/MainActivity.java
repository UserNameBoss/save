package com.kg.konggang_guide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.kg.konggang_guide.other.activity.AwaitServiceFragment;
import com.kg.konggang_guide.other.activity.MessageActivity;
import com.kg.konggang_guide.other.activity.OrderActivity;
import com.kg.konggang_guide.other.adapter.AwaitPagerAdapter;
import com.kg.konggang_guide.other.adapter.CarAdapter;
import com.kg.konggang_guide.other.base.CpBaseActivty;
import com.kg.konggang_guide.other.utils.DialogUtils;
import com.kg.konggang_guide.other.utils.StringUtil;
import com.kg.konggang_guide.other.utils.ToastUtil;
import com.kg.konggang_guide.other.widget.CircleImageView;
import com.kg.konggang_guide.personal.activity.MyActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.kg.konggang_guide.R.id.tv_login;

public class MainActivity extends CpBaseActivty implements TextWatcher {


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
    @BindView(tv_login)
    TextView tvLogin;
    @BindView(R.id.ll_nowOrder)
    LinearLayout llNowOrder;
    @BindView(R.id.fl_message)
    FrameLayout fl_message;

    private ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    private AwaitServiceFragment awaitServiceFragment, awaitOrderFragment;
    private AwaitPagerAdapter awaitPagerAdapter;
    private CarAdapter carAdapter;
    private String phone;
    private String nowTo;
    private String nowFrom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initViews();
        initEvents();
        initDatas();
    }

    @Override
    protected void initViews() {

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xrvCar.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void initEvents() {
        etNowFrom.addTextChangedListener(this);
        etNowTo.addTextChangedListener(this);
        etPhone.addTextChangedListener(this);
    }

    @Override
    protected void initDatas() {
        awaitServiceFragment = new AwaitServiceFragment();
        awaitServiceFragment.setOnFragmentData(new AwaitServiceFragment.OnFragmentData() {
            @Override
            public void itemClick(int position, int type) {
                llChangeGuide.setVisibility(View.VISIBLE);
                llOrder.setVisibility(View.GONE);
                llNowOrder.setVisibility(View.GONE);

            }
        });
        awaitOrderFragment = new AwaitServiceFragment();
        awaitOrderFragment.setOnFragmentData(new AwaitServiceFragment.OnFragmentData() {
            @Override
            public void itemClick(int position, int type) {
                llChangeGuide.setVisibility(View.GONE);
                llOrder.setVisibility(View.VISIBLE);
                llNowOrder.setVisibility(View.GONE);
            }
        });
        awaitOrderFragment.setType(1);
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
            public void toCar(int position) {
                DialogUtils.toCarDialog(MainActivity.this, "确定派车？", "确定", new DialogUtils.OnClickListener() {
                    @Override
                    public void onClickRight() {
                        DialogUtils.hintMessage(MainActivity.this, "kskkkkkkkk", "sdfsdf");
                    }
                });
            }
        });
        xrvCar.setAdapter(carAdapter);
    }

    @Override
    public void setRequestedOrientation(int requestedOrientation) {
        return;
    }

    @OnClick({R.id.img_guideIcon,R.id.fl_message,R.id.tv_login,R.id.tv_nowOrder, R.id.ll_await, R.id.ll_await2, R.id.tv_chanageGuide})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_await:

                vpager.setCurrentItem(0);
                break;
            case R.id.ll_await2:

                vpager.setCurrentItem(1);
                break;
            case R.id.tv_chanageGuide:
                DialogUtils.changeGuideDialog(MainActivity.this, new DialogUtils.OnCommitMessage() {
                    @Override
                    public void setMessage(String guideCode, String message) {

                    }
                });
                break;
            case R.id.tv_nowOrder:
                llChangeGuide.setVisibility(View.GONE);
                llOrder.setVisibility(View.GONE);
                llNowOrder.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_login:
                if(StringUtil.isMobile(phone)){
                    Bundle bundle=new Bundle();
                    bundle.putString("from",nowFrom);
                    bundle.putString("to",nowTo);
                    bundle.putString("phone",phone);
                    openActivity(OrderActivity.class,bundle);
                }else{
                    ToastUtil.getInstance(MainActivity.this).shortToast("请输入正确的手机号！");
                }
                break;
            case  R.id.fl_message:
                openActivity(MessageActivity.class);
                break;

            case R.id.img_guideIcon:
                openActivity(MyActivity.class);
                break;

        }
    }

    private void setAwaitBg(TextView t1, TextView t2, ImageView img1, ImageView img2) {
        t1.setTextColor(getResources().getColor(R.color.color_38ADFF));
        t2.setTextColor(getResources().getColor(R.color.color_333333));
        img1.setVisibility(View.VISIBLE);
        img2.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.tv_order)
    public void onViewClicked() {
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }


    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }


    @Override
    public void afterTextChanged(Editable s) {
        nowFrom=etNowFrom.getText().toString();
        nowTo=etNowTo.getText().toString();
        phone=etPhone.getText().toString();
        if(!TextUtils.isEmpty(nowFrom)&&!TextUtils.isEmpty(nowTo)&&!TextUtils.isEmpty(phone)){
            tvLogin.setEnabled(true);
        }else{
            tvLogin.setEnabled(false);
        }
    }
}
