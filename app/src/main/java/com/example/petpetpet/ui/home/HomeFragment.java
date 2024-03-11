package com.example.petpetpet.ui.home;

import android.graphics.Rect;
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
    List<View> Listviews;
    List<String> titles;

    //小模块适配器
    private View viewOne;//定义view用来设置fragment的layout
    private View viewTwo;
    public RecyclerView recyclerView1;//定义RecyclerView
    public RecyclerView recyclerView2;//定义RecyclerView
    private List<CardItem> cardItemArrayList=new ArrayList<CardItem>();


    private String[] texts = new String[] {
            "已知花意，未见其花。已见其花，未闻花名。", "此生无悔入四月，来世愿做友人A。",
            "此生无悔入夏目，来世愿做帐中妖。", "安兹王屠帝，号天下于此。",
            "如果幸福有颜色的话，那一定是被末日所染红的蓝色", "吾王剑之所指，吾等心之所向。",
            "无论在什么地方，什么时候，在我们的头顶都是同样悠远的天穹，就好像是永远都无法分开的羁绊","镜子里显示出来的永远只是真实的影像，而不是真实的自己。",
            "人类的心里住着一只野兽，纯粹，凶猛，无法驯养，那是一只叫做“嫉妒”的野兽" ,"如果我闭上了双眼，看到的是黑暗的话，那么当我睁开眼睛去看这个世界的时候，是否会是一片光明？",
            "我想成为一个温柔的人，因为曾被温柔的人那样对待，深深了解那种被温柔相待的感觉。","如果时光可以倒流 我还是会选择认识你 虽然会伤痕累累 但是心中的温暖记忆是谁都无法给与的 谢谢你来过我的世界",
            "无论在哪里遇到你，我都会喜欢上你","只要有你在，我就无所不能。",
    };
    private int[] photo = new int[] { R.drawable.mm1,R.drawable.mm2,R.drawable.mm3,
            R.drawable.mm4,R.drawable.mm5,R.drawable.cat_pic,R.drawable.cat,R.drawable.mm3,R.drawable.mm5,
            R.drawable.cat_pic,R.drawable.mm1,R.drawable.cat_pic,R.drawable.mm1,R.drawable.mm3};


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

        Listviews = new ArrayList<>();
        Listviews.add(viewOne);
        Listviews.add(viewTwo);
        titles = new ArrayList<>();
        titles.add("推荐");
        titles.add("社区动态");



        HomeViewPageAdapter adapter = new HomeViewPageAdapter(Listviews, titles);

        for (String title : titles) {
            tabLayout.addTab(tabLayout.newTab().setText(title));
        }

        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(adapter);


        //对recycleview进行配置
        initRecyclerView();


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
        //设置layoutManager
        recyclerView1.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recyclerView2.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        //设置adapter
        initData();
        CardItemAdapter adapter=new CardItemAdapter(cardItemArrayList);
        recyclerView1.setAdapter(adapter);
        recyclerView2.setAdapter(adapter);

        //设置item之间的间隔
        SpacesItemDecoration decoration=new SpacesItemDecoration(16);
        recyclerView1.addItemDecoration(decoration);
        recyclerView2.addItemDecoration(decoration);


        /**
//        //获取RecyclerView
//        recyclerView1 = (RecyclerView) viewOne.findViewById(R.id.home_viewpager1);
//        recyclerView2 = (RecyclerView) viewTwo.findViewById(R.id.home_viewpager2);
//
//        //创建adapter
//        cardItemAdapter = new AdoptCardItemAdapter(getActivity(), cardItemArrayList);
//        //给RecyclerView设置adapter
//        recyclerView1.setAdapter(cardItemAdapter);
//        recyclerView2.setAdapter(cardItemAdapter);
//
//        //设置layoutManager,可以设置显示效果，是线性布局、grid布局，还是瀑布流布局
//        //参数是：上下文、列表方向（横向还是纵向）、是否倒叙
//        recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
//        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
//        //设置item的分割线
//        recyclerView1.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
//        recyclerView2.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
//
//        //瀑布流
//        StaggeredGridLayoutManager manager1 = new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL);
//        StaggeredGridLayoutManager manager2 = new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL);
//
//        recyclerView1.setLayoutManager(manager1);
//        recyclerView2.setLayoutManager(manager2);
**/


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


        for (int i = 0; i < 11; i++) {
            CardItem cardItem = new CardItem();
            cardItem.setPostImageId(photo[i]);
            cardItem.setHeadImageId(HeadImageId[i%5]);
            cardItem.setUserName(names[i%5]);
            cardItem.setTitle(texts[i]);

            cardItemArrayList.add(cardItem);

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