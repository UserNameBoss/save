package com.kg.konggang_guide.other.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.kg.konggang_guide.R;
import com.kg.konggang_guide.other.adapter.AddressAdapter;
import com.kg.konggang_guide.other.adapter.SelectCityAdapter;
import com.kg.konggang_guide.other.base.CpBaseActivty;
import com.kg.konggang_guide.other.bean.AddressBean;
import com.kg.konggang_guide.other.bean.AddressFromBean;
import com.kg.konggang_guide.other.bean.CityBean;
import com.kg.konggang_guide.other.presenter.InputAddressPresenter;
import com.kg.konggang_guide.other.utils.AddressFromDao;
import com.kg.konggang_guide.other.utils.AddressToDao;
import com.kg.konggang_guide.other.utils.MapUtils;
import com.kg.konggang_guide.other.utils.OkHttpUtils;
import com.kg.konggang_guide.other.utils.StatusBarUtil;
import com.kg.konggang_guide.other.view.IInputAddressView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InputAddressActivity extends CpBaseActivty implements IInputAddressView{

    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.xrl_search)
    XRecyclerView xrlSearch;
    @BindView(R.id.sp_city)
    Spinner spinner;

    private AddressAdapter addressAdapter;
    private ArrayAdapter<String> arrayAdapter;
    private SelectCityAdapter selectCityAdapter;
    private ArrayList<String> addressList=new ArrayList<>();

    //0：不是上车地点，1：是上车地点
    private int isFrom;
    private CityBean cityBean;
    private InputAddressPresenter inputAddressPresenter;
    private int cityId;
    private AddressBean addressBean;
    private List<Tip> listAddress;
    private MapUtils mapUtils;
    private OkHttpUtils okHttpUtils;
    private InputtipsQuery inputquery;
    private Inputtips inputTips;
    private String cityName;
    private AddressToDao addressToDao;
    private AddressFromDao addressFromDao;
    private List<AddressFromBean> addressFromList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_address);
        ButterKnife.bind(this);
        initViews();
        initEvents();
        initDatas();
    }

    @Override
    protected void initViews() {
        StatusBarUtil.setOiStatusBarColor(this);
        StatusBarUtil.StatusBarLightMode(this);
        //supportTitle(true);
//        etAddress.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
//        etAddress.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//
//                if(actionId==EditorInfo.IME_ACTION_SEARCH){
//                    if(isFrom==1) {
//                        inputAddressPresenter.getAddress();
//                    }else{
//                        okHttpUtils.showDialog(InputAddressActivity.this);
//                        mapUtils.searchAddress(etAddress.getText().toString(), tvCity.getText().toString(), new Inputtips.InputtipsListener() {
//                            @Override
//                            public void onGetInputtips(List<Tip> list, int i) {
//                                listAddress=list;
//                                addressAdapter.setList(list);
//                                okHttpUtils.dismissDialog();
//                            }
//                        });
//                    }
//                }
//                return false;
//            }
//        });
        etAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                gotoSearch();
            }
        });
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xrlSearch.setLayoutManager(linearLayoutManager);
    }

    private void gotoSearch() {
        if(isFrom==1) {
            inputAddressPresenter.getAddress();
        }else{

            if(inputTips!=null){
                inputTips.setInputtipsListener(null);
                inputTips=null;
            }
            inputquery = new InputtipsQuery(etAddress.getText().toString(),tvCity.getText().toString());
            inputquery.setCityLimit(true);
            inputTips = new Inputtips(InputAddressActivity.this,inputquery);
            inputTips.setInputtipsListener(new Inputtips.InputtipsListener() {
                @Override
                public void onGetInputtips(List<Tip> list, int i) {
                    listAddress=list;
                    addressAdapter.setList(list);
                }
            });
            inputTips.requestInputtipsAsyn();
        }
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initDatas() {
        isFrom=getIntent().getExtras().getInt("isFrom",0);
//        if(isFrom==1){
//            addressFromDao=new AddressFromDao(this);
//        }else{
//
//        }
        mapUtils=new MapUtils(this);
        okHttpUtils=OkHttpUtils.getInstance();
        inputAddressPresenter=new InputAddressPresenter(this);
        inputAddressPresenter.getCity();

        addressAdapter=new AddressAdapter();
        addressAdapter.setOnItemClick(new AddressAdapter.OnItemClick() {
            @Override
            public void clickItem(int position) {
                if(isFrom==0){
                    Tip tip=listAddress.get(position);
                    Intent intent=new Intent();
                    intent.putExtra("name",tip.getName());
                    intent.putExtra("city",cityName);
                    intent.putExtra("cityId",cityId);
                    intent.putExtra("latitude",tip.getPoint().getLatitude());
                    intent.putExtra("longitude",tip.getPoint().getLongitude());
                    setResult(1001,intent);
                    finish();
                }
            }

            @Override
            public void deleteItem(int position) {

            }

            @Override
            public void clickItemFrom(String address,String airId) {
                Intent intent=new Intent();
                intent.putExtra("address",address);
                intent.putExtra("airId",airId);
                intent.putExtra("city",cityName);
                intent.putExtra("cityId",cityId);
//                AddressFromBean addressFromBean=new AddressFromBean(AppState.getInstance().getUserId(),address,airId,cityName,cityId);
//                addressFromDao.add(addressFromBean);
                setResult(1002,intent);
                finish();
            }
        });
        addressAdapter.setIsFrom(isFrom);
        xrlSearch.setAdapter(addressAdapter);

        selectCityAdapter=new SelectCityAdapter();
        spinner.setAdapter(selectCityAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(cityBean!=null&&cityBean.data!=null) {
                    cityName=cityBean.data.get(position).cityName;
                    tvCity.setText(cityName);
                    cityId=cityBean.data.get(position).id;
                    gotoSearch();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void setRequestedOrientation(int requestedOrientation) {
        return;
    }

    @OnClick(R.id.tv_cancel)
    public void onViewClicked() {
        finish();
    }

    @Override
    public String getCityId() {
        return cityId+"";
    }

    @Override
    public String getSearchName() {
        return etAddress.getText().toString();
    }

    @Override
    public void setCityBean(CityBean cityBean) {
        this.cityBean=cityBean;
        if(cityBean.data!=null&&cityBean.data.size()>0){
            cityId=cityBean.data.get(0).id;
            tvCity.setText(cityBean.data.get(0).cityName);
        }
        selectCityAdapter.setCityBean(cityBean);
    }

    @Override
    public void setSearchAddress(AddressBean addressBean) {
        this.addressBean=addressBean;
        addressAdapter.setAddressBean(addressBean);
    }

//    public void getLiteAddressFrom(){
//       addressFromList=addressFromDao.listByUserId(Integer.parseInt(AppState.getInstance().getUserId()));
//
//    }

}
