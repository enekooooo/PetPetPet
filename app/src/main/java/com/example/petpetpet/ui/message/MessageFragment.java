package com.example.petpetpet.ui.message;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petpetpet.R;
import com.example.petpetpet.databinding.FragmentMessageBinding;
import com.example.petpetpet.ui.personal.PersonalAboveActivity.PersonalCollection;
import com.example.petpetpet.ui.personal.PersonalAboveActivity.PersonalFollow;
import com.example.petpetpet.ui.personal.PersonalAboveActivity.PersonalHistory;
import com.example.petpetpet.ui.personal.PersonalItem;
import com.example.petpetpet.ui.personal.PersonalItemAdapter;
import com.example.petpetpet.ui.personal.PersonalUnderActivity.PersonalAbout;
import com.example.petpetpet.ui.personal.PersonalUnderActivity.PersonalHelp;
import com.example.petpetpet.ui.personal.PersonalUnderActivity.PersonalMessage;
import com.example.petpetpet.ui.personal.PersonalUnderActivity.PersonalSetting;

import java.util.ArrayList;

public class MessageFragment extends Fragment {

    private FragmentMessageBinding binding;

    private View view;//定义view用来设置fragment的layout
    public RecyclerView recyclerView;//定义RecyclerView
    private ArrayList<MessageItem> messageItemArrayList = new ArrayList<MessageItem>();
    //自定义recyclerveiw的适配器
    private MessageItemAdapter messageItemAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_message, container, false);

        //对recycleview进行配置
        initRecyclerView();
        //模拟数据
        initData();

        return view;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void initData() {//个人界面列表内容
        String[] names=new String[]{"用户1","用户2","用户3","用户4"};
        int[] ImageId=new int[]{R.drawable.cat_pic,R.drawable.cat_pic,R.drawable.cat_pic,R.drawable.cat_pic};
        for (int i = 0; i < 4; i++) {
            MessageItem messageItem = new MessageItem();
            messageItem.setTitle(names[i]);
            messageItem.setImageId(ImageId[i]);
            messageItemArrayList.add(messageItem);

        }
    }

    /**
     * TODO 对recycleview进行配置
     */

    private void initRecyclerView() {
        //获取RecyclerView
        recyclerView = (RecyclerView) view.findViewById(R.id.message_recycler_setting);
        //创建adapter
        messageItemAdapter = new MessageItemAdapter(getActivity(), messageItemArrayList);
        //给RecyclerView设置adapter
        recyclerView.setAdapter(messageItemAdapter);
        //设置layoutManager,可以设置显示效果，是线性布局、grid布局，还是瀑布流布局
        //参数是：上下文、列表方向（横向还是纵向）、是否倒叙
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        //设置item的分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

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

}