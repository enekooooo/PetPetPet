package com.example.petpetpet.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.example.petpetpet.R;
import com.example.petpetpet.databinding.FragmentHomeBinding;
import com.example.petpetpet.ui.CardPost.CardItem;
import com.example.petpetpet.ui.CardPost.CardItemAdapter;
import com.example.petpetpet.ui.home.TopFragment.HomeViewPageAdapter;
import com.example.petpetpet.ui.message.MessageItem;
import com.example.petpetpet.ui.message.MessageItemAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private View view;
    private FragmentHomeBinding binding;
    private ViewPager viewPagerPic;  //轮播图模块
    private LinearLayout ll_dots_container;
    private TextView loop_dec;
    private int[] mImg;
    private int[] mImg_id;
    private String[] mDec;
    private ArrayList<ImageView> mImgList;
    private int previousSelectedPosition = 0;
    boolean isRunning = false;

    //小模块
    ViewPager viewPager;
    TabLayout tabLayout;
    List<View> views;
    List<String> titles;

    //小模块适配器
    private View viewOne;//定义view用来设置fragment的layout
    private View viewTwo;
    public RecyclerView recyclerView1;//定义RecyclerView
    public RecyclerView recyclerView2;//定义RecyclerView
    private ArrayList<CardItem> cardItemArrayList = new ArrayList<CardItem>();
    //自定义recyclerveiw的适配器
    private CardItemAdapter cardItemAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        if (view!=null) {  // mRootView 不为null时候，返回之间创建的mRootView，不会再进行初始化操作了
            return view;
        }

        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        view = binding.getRoot();
        initLoopView(view);  //实现轮播图



        viewPager = view.findViewById(R.id.home_viewpager);
        tabLayout = view.findViewById(R.id.home_tab_layout);

        viewOne = LayoutInflater.from(view.getContext()).inflate(R.layout.fragment_home_viewpager1, null);
        viewTwo = LayoutInflater.from(view.getContext()).inflate(R.layout.fragment_home_viewpager2, null);

        views = new ArrayList<>();
        views.add(viewOne);
        views.add(viewTwo);
        titles = new ArrayList<>();
        titles.add("推荐");
        titles.add("社区动态");

        HomeViewPageAdapter adapter = new HomeViewPageAdapter(views, titles);

        for (String title : titles) {
            tabLayout.addTab(tabLayout.newTab().setText(title));
        }

        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(adapter);

//        TextView viewpager1_textView = viewOne.findViewById(R.id.viewpager1_textView);
//        viewpager1_textView.setText("我是1");
//
//
//        TextView viewpager2_textView = viewTwo.findViewById(R.id.viewpager2_textView);
//        viewpager2_textView.setText("我是2");

        //对recycleview进行配置
        initRecyclerView();
        //模拟数据
        initData();


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    private void initRecyclerView() {

        //获取RecyclerView
        recyclerView1 = (RecyclerView) viewOne.findViewById(R.id.home_viewpager1);
        recyclerView2 = (RecyclerView) viewTwo.findViewById(R.id.home_viewpager2);

        //创建adapter
        cardItemAdapter = new CardItemAdapter(getActivity(), cardItemArrayList);
        //给RecyclerView设置adapter
        recyclerView1.setAdapter(cardItemAdapter);
        recyclerView2.setAdapter(cardItemAdapter);

        //设置layoutManager,可以设置显示效果，是线性布局、grid布局，还是瀑布流布局
        //参数是：上下文、列表方向（横向还是纵向）、是否倒叙
        recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        //设置item的分割线
        recyclerView1.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView2.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        //瀑布流
        StaggeredGridLayoutManager manager1 = new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL);
        StaggeredGridLayoutManager manager2 = new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL);

        recyclerView1.setLayoutManager(manager1);
        recyclerView2.setLayoutManager(manager2);

        //RecyclerView中没有item的监听事件，需要自己在适配器中写一个监听事件的接口。参数根据自定义

//        messageItemAdapter.setOnItemClickListener(new MessageItemAdapter.OnItemClickListener() {
//            @Override
//            public void OnItemClick(View view, int position) {
//                //此处进行监听事件的业务处理
////                Toast.makeText(getActivity(), "我是item"+position, Toast.LENGTH_SHORT).show();
//                Intent intent;
//                switch (position) {
//                    case 0: //个人信息
//                        Toast.makeText(getActivity(), "个人信息", Toast.LENGTH_SHORT).show();
//                        intent = new Intent(getActivity(), PersonalMessage.class);
//                        startActivity(intent);
//                        break;
//                    case 1: //帮助
//                        Toast.makeText(getActivity(), "帮助", Toast.LENGTH_SHORT).show();
//                        intent = new Intent(getActivity(), PersonalHelp.class);
//                        startActivity(intent);
//                        break;
//                    case 2: //关于
//                        Toast.makeText(getActivity(), "关于", Toast.LENGTH_SHORT).show();
//                        intent = new Intent(getActivity(), PersonalAbout.class);
//                        startActivity(intent);
//                        break;
//                    case 3: //设置
//                        Toast.makeText(getActivity(), "设置", Toast.LENGTH_SHORT).show();
//                        intent = new Intent(getActivity(), PersonalSetting.class);
//                        startActivity(intent);
//                        break;
//                    default:
//                        break;
//                }


//            }
//        });
    }

    // TODO: 2024.2.16 心心没搞
    private void initData() {//个人界面列表内容
        String[] names=new String[]{"用户1","用户2","用户3","用户4","用户5"};
        String[] titles=new String[]{"标题1","标题2","标题3","标题4","标题5"};

        int[] PostImageId=new int[]{R.drawable.mm1,R.drawable.mm2,R.drawable.mm3,R.drawable.mm4,R.drawable.mm5};
        int[] HeadImageId=new int[]{R.drawable.cat_pic,R.drawable.cat_pic,R.drawable.cat_pic,R.drawable.cat_pic,R.drawable.cat_pic};


        for (int i = 0; i < 5; i++) {
            CardItem cardItem = new CardItem();
            cardItem.setPostImageId(PostImageId[i]);
            cardItem.setHeadImageId(HeadImageId[i]);
            cardItem.setUserName(names[i]);
            cardItem.setTitle(titles[i]);

            cardItemArrayList.add(cardItem);

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void initLoopView(View view) {//轮播图
        viewPagerPic = (ViewPager)view.findViewById(R.id.loopviewpager);
        ll_dots_container = (LinearLayout)view.findViewById(R.id.ll_dots_loop);
        loop_dec = (TextView)view.findViewById(R.id.loop_dec);

        // 图片资源id数组
        mImg = new int[]{
                R.drawable.mm1,
                R.drawable.mm2,
                R.drawable.mm3,
                R.drawable.mm4,
                R.drawable.mm5
        };

        // 文本描述
        mDec = new String[]{
                "轮播图1",
                "轮播图2",
                "轮播图3",
                "轮播图4",
                "轮播图5"
        };

        mImg_id = new int[]{
                R.id.pager_img1,
                R.id.pager_img2,
                R.id.pager_img3,
                R.id.pager_img4,
                R.id.pager_img5
        };

        // 初始化要展示的5个ImageView
        mImgList = new ArrayList<ImageView>();
        ImageView imageView;
        View dotView;
        LinearLayout.LayoutParams layoutParams;
        for(int i=0;i<mImg.length;i++){
            //初始化要显示的图片对象
            imageView = new ImageView(getActivity());

            imageView.setBackgroundResource(mImg[i]);
            imageView.setId(mImg_id[i]);
            imageView.setOnClickListener(new pagerOnClickListener(getActivity().getApplicationContext()));

            mImgList.add(imageView);
//            加引导点
            dotView = new View(getActivity());
            dotView.setBackgroundResource(R.drawable.dot);
            layoutParams = new LinearLayout.LayoutParams(10,10);
            if(i!=0){
                layoutParams.leftMargin=10;
            }
            //设置默认所有都不可用
            dotView.setEnabled(false);
            ll_dots_container.addView(dotView,layoutParams);
        }

        ll_dots_container.getChildAt(0).setEnabled(true);
        loop_dec.setText(mDec[0]);
        previousSelectedPosition=0;
        //设置适配器
        viewPagerPic.setAdapter(new LoopViewAdapter(mImgList));
        // 把ViewPager设置为默认选中Integer.MAX_VALUE / t2，从十几亿次开始轮播图片，达到无限循环目的;
        int m = (Integer.MAX_VALUE / 2) %mImgList.size();
        int currentPosition = Integer.MAX_VALUE / 2 - m;
        viewPagerPic.setCurrentItem(currentPosition);

        viewPagerPic.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                int newPosition = i % mImgList.size();
                loop_dec.setText(mDec[newPosition]);
                ll_dots_container.getChildAt(previousSelectedPosition).setEnabled(false);
                ll_dots_container.getChildAt(newPosition).setEnabled(true);
                previousSelectedPosition = newPosition;
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        // 开启轮询
        new Thread(){
            public void run(Runnable runnable){
                isRunning = true;
                while(isRunning){
                    try{
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //下一条
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
//                        }
//                    });
                    run(new Runnable(){
                        @Override
                        public void run() {
                            viewPagerPic.setCurrentItem(viewPagerPic.getCurrentItem()+1);
                        }
                    });
                }
            }
        }.start();

    }



}