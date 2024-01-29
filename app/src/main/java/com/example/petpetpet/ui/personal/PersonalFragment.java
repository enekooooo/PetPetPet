package com.example.petpetpet.ui.personal;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.petpetpet.R;
import com.example.petpetpet.ui.personal.PersonalAboveActivity.PersonalCollection;
import com.example.petpetpet.ui.personal.PersonalAboveActivity.PersonalFollow;
import com.example.petpetpet.ui.personal.PersonalAboveActivity.PersonalHistory;
import com.example.petpetpet.ui.personal.PersonalUnderActivity.PersonalAbout;
import com.example.petpetpet.ui.personal.PersonalUnderActivity.PersonalHelp;
import com.example.petpetpet.ui.personal.PersonalUnderActivity.PersonalMessage;
import com.example.petpetpet.ui.personal.PersonalUnderActivity.PersonalSetting;

import java.util.ArrayList;

public class PersonalFragment extends Fragment {


    private View view;//定义view用来设置fragment的layout
    public RecyclerView recyclerView;//定义RecyclerView
    //定义以goodsentity实体类为对象的数据集合
    private ArrayList<PersonalItem> personalItemArrayList = new ArrayList<PersonalItem>();
    //自定义recyclerveiw的适配器
    private PersonalItemAdapter personalItemAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //获取fragment的layout
        view = inflater.inflate(R.layout.fragment_personal, container, false);

        //对recycleview进行配置
        initRecyclerView();
        //模拟数据
        initData();

//        MyDecoration myDecoration = new MyDecoration();//设置item间隔
//
//        myDecoration.setColor(WHITE).setMargin(dp2px(getContext(), 15)).setDividerHeight(dp2px(getContext(),20));
//
//        recyclerView.addItemDecoration(myDecoration);

        ImageButton button_follow = view.findViewById(R.id.button_follow);
        ImageButton button_collection = view.findViewById(R.id.button_collection);
        ImageButton button_history = view.findViewById(R.id.button_history);

        button_follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PersonalFollow.class);
                startActivity(intent);
            }
        });

        button_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PersonalCollection.class);
                startActivity(intent);
            }
        });

        button_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PersonalHistory.class);
                startActivity(intent);
            }
        });


        return view;
    }



    /**
     * TODO 模拟数据
     */
    private void initData() {//个人界面列表内容
        String[] names=new String[]{"个人信息","帮助","关于","设置"};
        int[] ImageId=new int[]{R.drawable.edit,R.drawable.search,R.drawable.interrogation,R.drawable.settings};
        for (int i = 0; i < 4; i++) {
            PersonalItem personalItem = new PersonalItem();
            personalItem.setTitle(names[i]);
            personalItem.setImageId(ImageId[i]);
            personalItemArrayList.add(personalItem);

        }
    }

    /**
     * TODO 对recycleview进行配置
     */

    private void initRecyclerView() {
        //获取RecyclerView
        recyclerView = (RecyclerView) view.findViewById(R.id.personal_recycler_setting);
        //创建adapter
        personalItemAdapter = new PersonalItemAdapter(getActivity(), personalItemArrayList);
        //给RecyclerView设置adapter
        recyclerView.setAdapter(personalItemAdapter);
        //设置layoutManager,可以设置显示效果，是线性布局、grid布局，还是瀑布流布局
        //参数是：上下文、列表方向（横向还是纵向）、是否倒叙
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        //设置item的分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        //RecyclerView中没有item的监听事件，需要自己在适配器中写一个监听事件的接口。参数根据自定义
        personalItemAdapter.setOnItemClickListener(new PersonalItemAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                //此处进行监听事件的业务处理
//                Toast.makeText(getActivity(), "我是item"+position, Toast.LENGTH_SHORT).show();
                Intent intent;
                switch (position) {
                    case 0: //个人信息
                        Toast.makeText(getActivity(), "个人信息", Toast.LENGTH_SHORT).show();
                        intent = new Intent(getActivity(), PersonalMessage.class);
                        startActivity(intent);
                        break;
                    case 1: //帮助
                        Toast.makeText(getActivity(), "帮助", Toast.LENGTH_SHORT).show();
                        intent = new Intent(getActivity(), PersonalHelp.class);
                        startActivity(intent);
                        break;
                    case 2: //关于
                        Toast.makeText(getActivity(), "关于", Toast.LENGTH_SHORT).show();
                        intent = new Intent(getActivity(), PersonalAbout.class);
                        startActivity(intent);
                        break;
                    case 3: //设置
                        Toast.makeText(getActivity(), "设置", Toast.LENGTH_SHORT).show();
                        intent = new Intent(getActivity(), PersonalSetting.class);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }


            }
        });
    }
}