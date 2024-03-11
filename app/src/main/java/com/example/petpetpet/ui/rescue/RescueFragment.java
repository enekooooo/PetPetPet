package com.example.petpetpet.ui.rescue;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.example.petpetpet.R;
import com.example.petpetpet.databinding.FragmentRescueBinding;
import com.example.petpetpet.ui.CardPost.CardItem;
import com.example.petpetpet.ui.CardPost.CardItemAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class RescueFragment extends Fragment {

    private FragmentRescueBinding binding;
    private View view;

    //小模块
    ViewPager viewPager;
    TabLayout tabLayout;
    List<View> Listviews;
    List<String> titles;

    //小模块适配器
    private View viewOne;//定义view用来设置fragment的layout
    private View viewTwo;
    private View viewThree;
    private View viewFour;
    public RecyclerView recyclerView1;//定义RecyclerView
    public RecyclerView recyclerView2;//定义RecyclerView
    public RecyclerView recyclerView3;//定义RecyclerView
    public RecyclerView recyclerView4;//定义RecyclerView
    private List<CardItem> cardItemArrayList=new ArrayList<CardItem>();



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        if (view!=null) {  // mRootView 不为null时候，返回之间创建的mRootView，不会再进行初始化操作了
            return view;
        }

        binding = FragmentRescueBinding.inflate(inflater, container, false);
        view = binding.getRoot();


//        RescueViewModel rescueViewModel =
//                new ViewModelProvider(this).get(RescueViewModel.class);

        binding = FragmentRescueBinding.inflate(inflater, container, false);

        viewPager = view.findViewById(R.id.rescue_viewpager);
        tabLayout = view.findViewById(R.id.rescue_tab_layout);

        viewOne = LayoutInflater.from(view.getContext()).inflate(R.layout.fragment_rescue_viewpager1, null);
        viewTwo = LayoutInflater.from(view.getContext()).inflate(R.layout.fragment_rescue_viewpager2, null);
        viewThree = LayoutInflater.from(view.getContext()).inflate(R.layout.fragment_rescue_viewpager3, null);
        viewFour = LayoutInflater.from(view.getContext()).inflate(R.layout.fragment_rescue_viewpager4, null);

        Listviews = new ArrayList<>();
        Listviews.add(viewOne);
        Listviews.add(viewTwo);
        Listviews.add(viewThree);
        Listviews.add(viewFour);
        titles = new ArrayList<>();
        titles.add("所有");
        titles.add("喂食");
        titles.add("TNR");
        titles.add("其他");

        RescueViewPageAdapter adapter = new RescueViewPageAdapter(Listviews, titles);

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
        recyclerView1 = (RecyclerView) viewOne.findViewById(R.id.rescue_viewpager1);
        recyclerView2 = (RecyclerView) viewTwo.findViewById(R.id.rescue_viewpager2);
        recyclerView3 = (RecyclerView) viewThree.findViewById(R.id.rescue_viewpager3);
        recyclerView4 = (RecyclerView) viewFour.findViewById(R.id.rescue_viewpager4);

        //设置layoutManager
        recyclerView1.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView2.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView3.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView4.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        //设置adapter
        initData();
        CardItemAdapter adapter = new CardItemAdapter(cardItemArrayList);
        recyclerView1.setAdapter(adapter);
        recyclerView2.setAdapter(adapter);
        recyclerView3.setAdapter(adapter);
        recyclerView4.setAdapter(adapter);

        //设置item之间的间隔
        SpacesItemDecoration decoration=new SpacesItemDecoration(16);
        recyclerView1.addItemDecoration(decoration);
        recyclerView2.addItemDecoration(decoration);
        recyclerView3.addItemDecoration(decoration);
        recyclerView4.addItemDecoration(decoration);
    }

    private void initData() {//个人界面列表内容

        String[] names=new String[]{"用户1","用户2","用户3","用户4","用户5"};
        String[] titles=new String[]{"标题1","标题2","标题3","标题4","标题5"};

        int[] PostImageId=new int[]{R.drawable.mm1,R.drawable.mm2,R.drawable.mm3,R.drawable.mm4,R.drawable.mm5};
        int[] HeadImageId=new int[]{R.drawable.cat_pic,R.drawable.cat_pic,R.drawable.cat_pic,R.drawable.cat_pic,R.drawable.cat_pic};

        String[] texts = new String[] {
                "已知花意，未见其花。已见其花，未闻花名。", "此生无悔入四月，来世愿做友人A。",
                "此生无悔入夏目，来世愿做帐中妖。", "安兹王屠帝，号天下于此。",
                "如果幸福有颜色的话，那一定是被末日所染红的蓝色", "吾王剑之所指，吾等心之所向。",
                "无论在什么地方，什么时候，在我们的头顶都是同样悠远的天穹，就好像是永远都无法分开的羁绊","镜子里显示出来的永远只是真实的影像，而不是真实的自己。",
                "人类的心里住着一只野兽，纯粹，凶猛，无法驯养，那是一只叫做“嫉妒”的野兽" ,"如果我闭上了双眼，看到的是黑暗的话，那么当我睁开眼睛去看这个世界的时候，是否会是一片光明？",
                "我想成为一个温柔的人，因为曾被温柔的人那样对待，深深了解那种被温柔相待的感觉。","如果时光可以倒流 我还是会选择认识你 虽然会伤痕累累 但是心中的温暖记忆是谁都无法给与的 谢谢你来过我的世界",
                "无论在哪里遇到你，我都会喜欢上你","只要有你在，我就无所不能。",
        };
        int[] photo = new int[] { R.drawable.mm1,R.drawable.mm2,R.drawable.mm3,
                R.drawable.mm4,R.drawable.mm5,R.drawable.cat_pic,R.drawable.cat,R.drawable.mm3,R.drawable.mm5,
                R.drawable.cat_pic,R.drawable.mm1,R.drawable.cat_pic,R.drawable.mm1,R.drawable.mm3};

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
}