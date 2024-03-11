package com.example.petpetpet.ui.adopt;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.petpetpet.MainActivity;
import com.example.petpetpet.R;
import com.example.petpetpet.databinding.FragmentAdoptBinding;
import com.example.petpetpet.mysql.DBPetHelper;
import com.example.petpetpet.ui.adopt.AdoptCardPost.AdoptCardItem;
import com.example.petpetpet.ui.adopt.AdoptCardPost.AdoptCardItemAdapter;
import com.example.petpetpet.ui.adopt.PopupWindow.BaseFragment;
import com.example.petpetpet.ui.personal.db.User;
import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdoptFragment extends BaseFragment {

    private static final String TAG = "AdoptFragment";

    private FragmentAdoptBinding binding;
    private View view;
    public RecyclerView recyclerView;//定义RecyclerView
    private List<AdoptCardItem> cardItemArrayList=new ArrayList<AdoptCardItem>();

    //用户筛选数据
    private String select_place;
    private String select_type;
    private String select_sex;

    private Handler handler = new mHandler();

    LinearLayout mPlaceAll;
    CheckBox mPlaceCb;
    LinearLayout mTypeAll;
    CheckBox mTypeCb;
    LinearLayout mSexAll;
    CheckBox mSexCb;
    SpinKitView spinKitView;//loading界面

    List<String> placeList = new ArrayList<>();
    List<String> typeList = new ArrayList<>();
    List<String> sexList = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AdoptViewModel adoptViewModel =
                new ViewModelProvider(this).get(AdoptViewModel.class);

        binding = FragmentAdoptBinding.inflate(inflater, container, false);
        view = binding.getRoot();

        spinKitView= view.findViewById(R.id.spin_kit);
        spinKitView.setVisibility(View.VISIBLE);
//        long start = SystemClock.elapsedRealtime();
        initDate();
//        Log.d(TAG, "initDate()" + (SystemClock.elapsedRealtime() - start) + "ms]");//打印执行时间


        initDataThread initDataThread1 = new initDataThread();
        Thread thread = new Thread(initDataThread1);
        thread.start();

//        start = SystemClock.elapsedRealtime();
        initView();
//        Log.d(TAG, "initView()" + (SystemClock.elapsedRealtime() - start) + "ms]");打印执行时间

        FloatingActionButton addPet = view.findViewById(R.id.adopt_floatingActionButton);
        addPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User();
                String username = user.getUserName();
                if(username != null)
                {
                    Intent intent = new Intent(getActivity(), PetAddActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getActivity(),"请先登录再进行添加领养动物操作",Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }

    private void initRecyclerView() {

        //获取RecyclerView
        recyclerView = (RecyclerView) view.findViewById(R.id.adopt_recycler);

        //设置layoutManager
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        //设置adapter
        AdoptCardItemAdapter adapter = new AdoptCardItemAdapter(cardItemArrayList);
        recyclerView.setAdapter(adapter);

        //设置item之间的间隔
        SpacesItemDecoration decoration=new SpacesItemDecoration(16);
        recyclerView.addItemDecoration(decoration);
    }

    private void initData() {//个人界面列表内容

        cardItemArrayList = DBPetHelper.queryAll();

    }


    private class mHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what==1)
                initRecyclerView();
            spinKitView.setVisibility(View.INVISIBLE);//loading设为不可见
        }
    }

    private class initDataThread implements Runnable{

        @Override
        public void run() {
            cardItemArrayList = DBPetHelper.queryAll();
            Message message = Message.obtain();
            message.what = 1;
            handler.sendMessage(message);

        }
    }

    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;
        public SpacesItemDecoration(int space) {
            this.space=space;
        }
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.left=space;
            outRect.right=space;
            outRect.bottom=space;
            if(parent.getChildAdapterPosition(view)==0){
                outRect.top=space;
            }
        }
    }


    private void initDate() {
        // 初始化选择栏数据
        Collections.addAll(placeList, "天津", "北京", "上海", "深圳", "江苏", "山东", "广西");
        Collections.addAll(typeList, "猫", "狗");
        Collections.addAll(sexList, "所有", "公未绝育", "公已绝育", "母未绝育", "母已绝育");
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mPlaceAll = (LinearLayout) view.findViewById(R.id.ll_place_tab);
        mPlaceCb = (CheckBox) view.findViewById(R.id.cb_place);
        mTypeAll = (LinearLayout) view.findViewById(R.id.ll_type);
        mTypeCb = (CheckBox) view.findViewById(R.id.cb_type);
        mSexAll = (LinearLayout) view.findViewById(R.id.ll_time);
        mSexCb = (CheckBox) view.findViewById(R.id.cb_time);

        // 点击选择城市整体(LinearLayout)
        mPlaceAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPlaceCb.isChecked())
                    mPlaceCb.setChecked(false);
                else
                    mPlaceCb.setChecked(true);
            }
        });

        // 点击选择类型整体
        mTypeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTypeCb.isChecked())
                    mTypeCb.setChecked(false);
                else
                    mTypeCb.setChecked(true);
            }
        });
        // 点击选择性别整体
        mSexAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mSexCb.isChecked())
                    mSexCb.setChecked(false);
                else
                    mSexCb.setChecked(true);
            }
        });

        // 选择城市cb
        mPlaceCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                filterTabToggle(isChecked, mPlaceAll, placeList, new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        hidePopListView();
                        select_place = placeList.get(position);
                        mPlaceCb.setText(select_place);
// TODO: 2024.3.1 二次筛选城市，写的很丑陋
                        if(placeList.get(position)=="天津"){

                        }else if (placeList.get(position)=="北京"){

                        }else if (placeList.get(position)=="上海"){

                        }else if (placeList.get(position)=="深圳"){

                        }else if(placeList.get(position)=="山东"){

                            List<String> ShandongList = new ArrayList<>();
                            Collections.addAll(ShandongList,"烟台","济南","青岛","日照","潍坊");

                            filterTabToggle(isChecked, mPlaceAll, ShandongList, new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                                    hidePopListView();
                                    select_place = select_place + ShandongList.get(position);
                                    mPlaceCb.setText(select_place);
                                }
                            }, mPlaceCb, mTypeCb, mSexCb);
                        }else if(placeList.get(position)=="江苏"){

                            List<String> JiangsuList = new ArrayList<>();
                            Collections.addAll(JiangsuList,"南京","苏州","扬州","连云港","镇江");

                            filterTabToggle(isChecked, mPlaceAll, JiangsuList, new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                                    hidePopListView();
                                    select_place = select_place + JiangsuList.get(position);
                                    mPlaceCb.setText(select_place);
                                }
                            }, mPlaceCb, mTypeCb, mSexCb);
                        }else if(placeList.get(position)=="广西"){

                            List<String> GuangxiList = new ArrayList<>();
                            Collections.addAll(GuangxiList,"桂林","南宁","百色","柳州","玉林");

                            filterTabToggle(isChecked, mPlaceAll, GuangxiList, new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                                    hidePopListView();
                                    select_place = select_place + GuangxiList.get(position);
                                    mPlaceCb.setText(select_place);
                                }
                            }, mPlaceCb, mTypeCb, mSexCb);
                        }

                    }
                }, mPlaceCb, mTypeCb, mSexCb);
            }
        });

        // 选择品种cb
        mTypeCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                filterTabToggle(isChecked, mTypeAll, typeList, new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        hidePopListView();
                        select_type = typeList.get(position);
                        mTypeCb.setText(typeList.get(position));
                        if (typeList.get(position)=="猫") {
                            /*
                            一次筛选
                             */
                            List<String> catList = new ArrayList<>();
                            Collections.addAll(catList,"所有","中华田园猫","布偶猫","英国短毛猫","美国短毛猫","缅因猫",
                                    "暹罗猫","无毛猫","蓝猫","加菲猫","矮脚猫","卷毛猫");
                            filterTabToggle(isChecked, mTypeAll, catList, new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                                    hidePopListView();
                                    if (catList.get(position)!="所有")//分类设为猫
                                        select_type = select_type + "-" + catList.get(position);
                                    mTypeCb.setText(catList.get(position));

                                    /*
                                    二次筛选
                                     */
                                    if (catList.get(position)=="中华田园猫") {
                                        List<String> catChinaList = new ArrayList<>();
                                        Collections.addAll(catChinaList,"所有", "橘猫","三花","狸花","白猫","黑猫",
                                                "奶牛");
                                        filterTabToggle(isChecked, mTypeAll, catChinaList, new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                                                hidePopListView();
                                                if (catList.get(position)!="所有")//分类设为猫-中华田园猫
                                                    select_type = select_type + "-" + catChinaList.get(position);
                                                mTypeCb.setText(catChinaList.get(position));
                                            }
                                        }, mTypeCb, mPlaceCb, mSexCb);
                                    }
                                }
                            }, mTypeCb, mPlaceCb, mSexCb);


                        }
                    }
                }, mTypeCb, mPlaceCb, mSexCb);
            }
        });

        // 选择性别cb
        mSexCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                filterTabToggle(isChecked, mSexAll, sexList, new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        hidePopListView();
                        select_sex = sexList.get(position);
                        mSexCb.setText(select_sex);
// TODO: 2024.3.8 筛选
                        List<AdoptCardItem> a = new ArrayList<AdoptCardItem>();
                        cardItemArrayList = a;
                        initRecyclerView();
                    }
                }, mSexCb, mPlaceCb, mTypeCb);

            }


        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}