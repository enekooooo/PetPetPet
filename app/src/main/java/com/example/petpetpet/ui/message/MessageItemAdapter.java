package com.example.petpetpet.ui.message;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.petpetpet.R;

import java.util.ArrayList;


public class MessageItemAdapter extends RecyclerView.Adapter<MessageItemAdapter.MessageViewHolder> {
    private Context context;
    private ArrayList<MessageItem> messageItemList;


    //创建构造函数
    public MessageItemAdapter(Context context, ArrayList<MessageItem> messageItemList) {
        //将传递过来的数据，赋值给本地变量
        this.context = context;//上下文
        this.messageItemList = messageItemList;//实体类数据ArrayList
    }

    /**
     * 创建viewhodler，相当于listview中getview中的创建view和viewhodler
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //创建自定义布局
        View itemView = View.inflate(context, R.layout.item_message, null);
        itemView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp2px(context,65)));
        return new MessageViewHolder(itemView);
    }

    public static int dp2px(Context context, final float dpValue) {//dp转换px
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 绑定数据，数据与view绑定
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        //根据点击位置绑定数据
        MessageItem data = messageItemList.get(position);
//        holder.mItemGoodsImg;
        holder.messageItemTextView.setText(data.getTitle());//获取实体类中的name字段并设置
        holder.messageItemPic.setImageResource(data.getImageId());
    }

    /**
     * 得到总条数
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return messageItemList.size();
    }

    //自定义viewhodler
    class MessageViewHolder extends RecyclerView.ViewHolder {
        private ImageView messageItemPic;
        private TextView messageItemTextView;

        public MessageViewHolder(View itemView) {
            super(itemView);
            messageItemPic = (ImageView) itemView.findViewById(R.id.card_head_pic);
            messageItemTextView = (TextView) itemView.findViewById(R.id.message_item_textView);
            //点击事件放在adapter中使用，也可以写个接口在activity中调用
            //在adapter中设置点击事件
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //可以选择直接在本位置直接写业务处理
                    //Toast.makeText(context,"点击了xxx",Toast.LENGTH_SHORT).show();
                    //此处回传点击监听事件
                    if(onItemClickListener!=null){
                        onItemClickListener.OnItemClick(v, getLayoutPosition());
                    }
                }
            });

        }
    }

    /**
     * 设置item的监听事件的接口
     */
    public interface OnItemClickListener {
        /**
         * 接口中的点击每一项的实现方法，参数自己定义
         *
         * @param view 点击的item的视图
         * @param position 点击的item的数据
         */
        public void OnItemClick(View view, int position);
    }


    //需要外部访问，所以需要设置set方法，方便调用
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}



