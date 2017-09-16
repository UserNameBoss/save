package com.kg.konggang_guide.other.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.Tip;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;
import com.kg.konggang_guide.R;
import com.kg.konggang_guide.other.base.CpBaseActivty;
import com.kg.konggang_guide.other.bean.CityFromBean;
import com.kg.konggang_guide.other.bean.SearchAddressBean;
import com.kg.konggang_guide.other.event.MyEvent;
import com.kg.konggang_guide.other.presenter.OrderPresenter;
import com.kg.konggang_guide.other.utils.MapUtils;
import com.kg.konggang_guide.other.utils.OkHttpUtils;
import com.kg.konggang_guide.other.utils.StatusBarUtil;
import com.kg.konggang_guide.other.utils.TimeUtils;
import com.kg.konggang_guide.other.view.IOrderView;
import com.kg.konggang_guide.personal.AppState;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

public class OrderActivity extends CpBaseActivty implements IOrderView, RouteSearch.OnRouteSearchListener {

    @BindView(R.id.tv_from)
    TextView tvFrom;
    @BindView(R.id.tv_to)
    TextView tvTo;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.img_official)
    ImageView imgOfficial;
    @BindView(R.id.tv_official)
    TextView tvOfficial;
    @BindView(R.id.img_line_official)
    ImageView imgLineOfficial;
    @BindView(R.id.ll_officical)
    LinearLayout llOfficical;
    @BindView(R.id.img_business)
    ImageView imgBusiness;
    @BindView(R.id.tv_business)
    TextView tvBusiness;
    @BindView(R.id.img_line_business)
    ImageView imgLineBusiness;
    @BindView(R.id.ll_business)
    LinearLayout llBusiness;
    @BindView(R.id.img_limousine)
    ImageView imgLimousine;
    @BindView(R.id.tv_limousine)
    TextView tvLimousine;
    @BindView(R.id.img_line_limousine)
    ImageView imgLineLimousine;
    @BindView(R.id.ll_limousine)
    LinearLayout llLimousine;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_priceDital)
    TextView tvPriceDital;
    @BindView(R.id.tv_order)
    TextView tvOrder;

    private String nowFrom, nowTo, phone;
    private MapUtils mapUtils;
    private LatLonPoint latLonPointFrom, latLonPointTo;

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    private float iTollDistance;
    private String iTime;
    private int iIsOut;
    private float iDistance;
    private OrderPresenter orderPresenter;
    private String locationCity;
    private String airId;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    city = aMapLocation.getCity();
                    locationCity= aMapLocation.getCity();
                    AppState.getInstance().setLocationCity(city);
                    goToPrice();
                } else {
                    mLocationClient.startLocation();
                }
            }
        }
    };
    private String orderId;
    private int cityCode;
    private String city;
    private ArrayList<String> addressList=new ArrayList<>();
    private ArrayList<String> addressIdList=new ArrayList<>();
    private boolean isFrom;
    private RouteSearch routeSearch;
    private String toCity;
    private GeocodeSearch geocoderSearch;
    private String price;

    //第一次进来就获取航站楼
    private boolean isOneGetFrom;

    private void goToPrice() {
        if(!isOneGetFrom) {
            orderPresenter.getCity();
            isOneGetFrom=true;
        }
        if (latLonPointTo == null) {
            mapUtils.searchAddress(nowTo, locationCity, new Inputtips.InputtipsListener() {
                @Override
                public void onGetInputtips(List<Tip> list, int i) {
                    if (i == 1000) {
                        if (list != null && list.size() > 0) {
                            tvTo.setText(list.get(0).getName());
                            latLonPointTo = list.get(0).getPoint();
                            if (latLonPointFrom != null && latLonPointTo != null) {
                                isGotoAddressInfo();
                            }
                        }else{
                            okHttpUtils.dismissDialog();
                            price="0";
                            orderId=null;

                            tvPrice.setText(price + "元");
                            showToask("未找到该地址！");
                        }
                    }else{
                        price="0";
                        orderId=null;

                        tvPrice.setText(price + "元");
                        okHttpUtils.dismissDialog();
                    }
                }
            });
        }else{
            if (latLonPointFrom != null) {
                isGotoAddressInfo();
            }
        }

    }

    private AMapLocationClientOption mLocationOption;
    private OkHttpUtils okHttpUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);

        initViews();
        initEvents();
        initDatas();
    }

    @Override
    protected void initViews() {
        StatusBarUtil.setOiStatusBarColor(this);
        StatusBarUtil.StatusBarLightMode(this);
        supportTitle(true);
        title.setTitleText("立即用车");
        title.setLeftImage(R.mipmap.app_back);
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initDatas() {
        okHttpUtils = OkHttpUtils.getInstance();
        okHttpUtils.showDialog(this);
        orderPresenter = new OrderPresenter(this);
        mapUtils = new MapUtils(this);
        nowFrom = getIntent().getExtras().getString("from");
        nowTo = getIntent().getExtras().getString("to");
        phone = getIntent().getExtras().getString("phone");

        tvFrom.setText(nowFrom);
        tvTo.setText(nowTo);
        tvPhone.setText(phone);
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);


        mLocationOption.setOnceLocation(true);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        if (TextUtils.isEmpty(city)) {
            //启动定位
            mLocationClient.startLocation();
        }
//        city = AppState.getInstance().getOlCity();
//        if (TextUtils.isEmpty(city)) {
//            city = AppState.getInstance().getLocaitonCity();
//            AppState.getInstance().setOlCity(city);
//        }
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void setRequestedOrientation(int requestedOrientation) {
        return;
    }

    @OnClick({R.id.tv_from, R.id.tv_to, R.id.tv_priceDital, R.id.ll_officical, R.id.ll_business, R.id.ll_limousine, R.id.tv_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_officical:
                break;
            case R.id.ll_business:
            case R.id.ll_limousine:
                //ToastUtil.getInstance(this).shortToast();
                showToask("该车型暂未开通！");
                break;
            case R.id.tv_order:

                if (!TextUtils.isEmpty(orderId)) {
                    orderPresenter.confirmOrder();
                } else {
                    showToask("预估价计算失败！");
                }
                break;
            case R.id.tv_from:
                Bundle bundle = new Bundle();
                bundle.putInt("isFrom", 1);
                openActivityForResult(InputAddressActivity.class, 1000, bundle);
                break;
            case R.id.tv_to:
                Bundle bundle1 = new Bundle();
                bundle1.putInt("isFrom", 0);
                openActivityForResult(InputAddressActivity.class, 1001, bundle1);
                break;

            case R.id.tv_priceDital:
                if (!TextUtils.isEmpty(orderId)) {
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("orderId", orderId);
                    bundle2.putString("cityId",cityCode+"");
                    //openActivity(PriceDitailsActivity.class, bundle2);
                    openActivity(FixedPriceActivity.class,bundle2);
                } else {
                    showToask("计算预估价失败,请重新选择地址");
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1000) {
            if (data != null) {
                price="0";
                orderId=null;
                tvPrice.setText(price + "元");
                String fromAddress = data.getStringExtra("address");
                airId=data.getStringExtra("airId");
                city=data.getStringExtra("city");
                cityCode=data.getIntExtra("cityId",1);
                tvFrom.setText(fromAddress);
                okHttpUtils.showDialog(this);
                getFrom(fromAddress);
                System.out.println("==========from=======");
            }

        } else if (requestCode == 1001) {
            if (data != null) {
                price="0";
                orderId=null;
                tvPrice.setText(price + "元");
                String toAddress = data.getStringExtra("name");
                double d1;
                double d2;
                if (latLonPointTo == null) {
                    d1 = data.getDoubleExtra("latitude", 0);
                    d2 = data.getDoubleExtra("longitude", 0);
                } else {
                    d1 = data.getDoubleExtra("latitude", latLonPointTo.getLatitude());
                    d2 = data.getDoubleExtra("longitude", latLonPointTo.getLongitude());
                }
                if (latLonPointTo != null) {
                    latLonPointTo.setLatitude(d1);
                    latLonPointTo.setLongitude(d2);
                } else {
                    latLonPointTo = new LatLonPoint(d1, d2);
                }
                tvTo.setText(toAddress);
                okHttpUtils.showDialog(this);
                goToPrice();
                System.out.println("==========to==========");
            }
        }
    }

    @Override
    public String getGuideId() {
        return AppState.getInstance().getUserId();
    }

    @Override
    public String getMileage() {
        return iTollDistance + "";
    }

    @Override
    public String getTime() {
        return iTime;
    }

    @Override
    public String getIsOut() {
        return iIsOut + "";
    }

    @Override
    public String getOutMile() {
        return iDistance + "";
    }

    @Override
    public String getCityCode() {
        return cityCode + "";
    }

    @Override
    public void setAddress(SearchAddressBean searchAddressBean) {
        if (searchAddressBean != null && searchAddressBean.data != null) {
            for (int i = 0; i < searchAddressBean.data.size(); i++) {
                addressList.clear();
                SearchAddressBean.DataEntity dataEntity = searchAddressBean.data.get(i);
                String[] building = dataEntity.terminalBuilding.split(",");
                for (int j = 0; building != null && j < building.length; j++) {
                    addressList.add(city + dataEntity.airportName + building[j]);
                    addressIdList.add(dataEntity.id+"");
                    if (addressList.size() >= 5) {
                        break;
                    }
                }

                if (addressList.size() >= 5) {
                    break;
                }
            }
        }
        if (addressList.size() > 0) {
            latLonPointFrom = null;
            tvFrom.setText(addressList.get(0));
            airId=addressIdList.get(0);
            getFrom(addressList.get(0));
        }
    }

    @Override
    public void setOrderId(String orderId) {
        okHttpUtils.dismissDialog();
        if (TextUtils.isEmpty(orderId)) {
            showToask("计算预估价失败,请重新选择地址");
        } else {
            this.orderId = orderId;
        }
    }

    @Override
    public void setPrice(String price) {
        BigDecimal bigDecimal = new BigDecimal(price);
        this.price=price = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() + "";

        tvPrice.setText(price + "元");
    }

    @Override
    public void setCityFrom(CityFromBean cityFrom) {
        if (cityFrom != null && cityFrom.data != null) {
            for (int i = 0; i < cityFrom.data.size(); i++) {
                if (cityFrom.data.get(i).cityName.equals(city)) {
                    cityCode = cityFrom.data.get(i).id;
                    AppState.getInstance().setOlCityCode(cityCode);
                    orderPresenter.getAddress();
                    return;
                }
            }
            if (cityFrom.data.size() > 0) {
                city = cityFrom.data.get(0).cityName;
                cityCode = cityFrom.data.get(0).id;
                AppState.getInstance().setOlCity(city);
                AppState.getInstance().setOlCityCode(cityCode);
                orderPresenter.getAddress();
            }
        }
    }

    @Override
    public String getOrderId() {
        return orderId;
    }

    @Override
    public String getUserId() {
        return AppState.getInstance().getUserId();
    }

    @Override
    public String getDepartureLocation() {
        return tvFrom.getText().toString();
    }

    @Override
    public String getArrivedLocation() {
        return tvTo.getText().toString();
    }

    @Override
    public String getName() {
        return "自己乘车";
    }

    @Override
    public String getTelephone() {
        return tvPhone.getText().toString();
    }

    @Override
    public String getIsSend() {
        return "1";
    }

    @Override
    public String getCarType() {
        return "1";
    }

    @Override
    public String getEstimatePrice() {
        return price;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public String getAirport() {
        return city;
    }

    @Override
    public String getTerminalBuilding() {
        return tvFrom.getText().toString();
    }

    @Override
    public String getAirId() {
        return airId;
    }

    @Override
    public String getDeparturePosition() {
        return latLonPointFrom.getLongitude() + "," + latLonPointFrom.getLatitude();
    }

    @Override
    public String getArrivedPosition() {
        return latLonPointTo.getLongitude() + "," + latLonPointTo.getLatitude();
    }

    @Override
    public String getArriredTime() {
        return iTime;
    }

    @Override
    public String getOTime() {
        return TimeUtils.getAllDate(System.currentTimeMillis() + (10 * 60 * 1000)).toString();
    }

    @Override
    public String getArriedTime() {
        return TimeUtils.getCurrentTime();
    }

    @Override
    public void isOrderSuccess() {
        showToask("下单成功！");
        MyEvent myEvent=new MyEvent();
        myEvent.setType(1);
        myEvent.setRefresh(true);
        EventBus.getDefault().post(myEvent);
        finish();
    }


    public void getFrom(String address) {
        GeocodeSearch geocoderSearch = new GeocodeSearch(OrderActivity.this);
        geocoderSearch.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() {
            @Override
            public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {

            }

            @Override
            public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
                if (OrderActivity.this.isDestroyed()) {
                    return;
                }
                if (i == 1000) {
                    if (geocodeResult.getGeocodeAddressList().size() > 0) {
                        isFrom = true;
                        latLonPointFrom = geocodeResult.getGeocodeAddressList().get(0).getLatLonPoint();
                        city = geocodeResult.getGeocodeAddressList().get(0).getCity();
                        if (latLonPointTo != null && latLonPointFrom != null) {
                            isGotoAddressInfo();
                        }
                    }else{
                        okHttpUtils.dismissDialog();
                        showToask("未找到该地址！");

                    }
                }else{
                    okHttpUtils.dismissDialog();
                }
            }
        });
        GeocodeQuery query = new GeocodeQuery(address, city);
        geocoderSearch.getFromLocationNameAsyn(query);
    }


    //检测是否能够去计算预估价
    public void isGotoAddressInfo() {

        getAddressInfo();

    }

    private void getAddressInfo() {
        OkHttpUtils.getInstance().showDialog(this);
        routeSearch = new RouteSearch(this);
        routeSearch.setRouteSearchListener(this);
        //latLonPointFrom = new LatLonPoint(23.124538, 114.421673);

        //规划线路
        RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(latLonPointFrom, latLonPointTo);
        RouteSearch.DriveRouteQuery query = new RouteSearch.DriveRouteQuery(fromAndTo, RouteSearch.DRIVING_MULTI_STRATEGY_FASTEST_SHORTEST, null, null, "");
        routeSearch.calculateDriveRouteAsyn(query);
    }

    @Override
    public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {

    }

    @Override
    public void onDriveRouteSearched(DriveRouteResult driveRouteResult, int i) {
        if (OrderActivity.this.isDestroyed()) {
            return;
        }
        if (i == 1000) {
            List<DrivePath> listPath = driveRouteResult.getPaths();
            if (listPath != null && listPath.size() > 0) {
                DrivePath drivePath = listPath.get(0);
                iTollDistance = drivePath.getDistance() / 1000;
                iTime = drivePath.getDuration() / 60 + "";
                if(iTollDistance<=200) {
                    getToCityInfo();
                }else{
                    showToask("路程超过200公里！");
                    okHttpUtils.dismissDialog();
                }
            }else{
                okHttpUtils.dismissDialog();
                showToask("未找到该地址！");
            }
        } else {
            tvPrice.setText("0");
            okHttpUtils.dismissDialog();

            Toast.makeText(OrderActivity.this, "预估费用计算失败", Toast.LENGTH_SHORT);

        }
    }

    @Override
    public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {

    }

    @Override
    public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {

    }


    public void getToCityInfo() {
        geocoderSearch = new GeocodeSearch(this);
        RegeocodeQuery query = new RegeocodeQuery(latLonPointTo, 200, GeocodeSearch.AMAP);
        final MapUtils mapUtils = new MapUtils(this);
        mapUtils.GetgeocoderSearch(geocoderSearch, query, new GeocodeSearch.OnGeocodeSearchListener() {
            @Override
            public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {

                if (i == 1000) {
                    RegeocodeAddress regeocodeAddress = regeocodeResult.getRegeocodeAddress();
                    toCity = regeocodeAddress.getCity();
                    if (toCity.equals(city)) {
                        //没有出城
                        iIsOut = 0;
                        orderPresenter.getPrice();
                    } else {
                        //出城
                        //拿到城市的坐标
                        iIsOut = 1;
                        orderPresenter.getPrice();
                    }
                } else {
                    tvPrice.setText("0");
                    okHttpUtils.dismissDialog();

                    //  ToastUtil.getInstance(NowFragment.this.getContext()).shortToast("预估费用计算失败");
                    Toast.makeText(OrderActivity.this, "预估费用计算失败", Toast.LENGTH_SHORT);
                }
            }

            @Override
            public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
            }
        });

    }
}
