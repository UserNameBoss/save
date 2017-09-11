package com.kg.konggang_guide.other.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.kg.konggang_guide.R;
import com.kg.konggang_guide.other.adapter.AddressAdapter;
import com.kg.konggang_guide.other.adapter.SelectCityAdapter;
import com.kg.konggang_guide.other.base.CpBaseActivty;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InputAddressActivity extends CpBaseActivty {

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
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        etAddress.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xrlSearch.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initDatas() {
        addressAdapter=new AddressAdapter();
        xrlSearch.setAdapter(addressAdapter);
        addressList.add("北京");
        addressList.add("上海");
        addressList.add("深圳");
        //addressList.add("我看看你有多超");
        selectCityAdapter=new SelectCityAdapter();
        selectCityAdapter.setCityList(addressList);
        spinner.setAdapter(selectCityAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tvCity.setText(addressList.get(position));

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
}
