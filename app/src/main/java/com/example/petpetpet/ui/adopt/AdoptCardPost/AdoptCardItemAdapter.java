package com.example.petpetpet.ui.adopt.AdoptCardPost;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.petpetpet.R;
import com.example.petpetpet.mysql.DBPetHelper;
import com.example.petpetpet.ui.StringAndBitmap;
import com.example.petpetpet.ui.personal.db.User;

import java.util.List;

public class AdoptCardItemAdapter extends RecyclerView.Adapter<AdoptCardItemAdapter.AdoptCardItemView> {

    private List<AdoptCardItem> products;

    public AdoptCardItemAdapter(List<AdoptCardItem> list) {
        products = list;
    }

    @Override
    public AdoptCardItemView onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_pet_card, viewGroup, false);
        return new AdoptCardItemView(view);
    }

    @Override
    public void onBindViewHolder(AdoptCardItemView cardItemView, int position) {
        cardItemView.PetCardPostImageId.setImageBitmap(products.get(position).getPetImageId());
        cardItemView.PetCardName.setText(products.get(position).getPetName());
        cardItemView.PetCardCommunity.setText(products.get(position).getPetCommunity());
        cardItemView.PetCardLocate.setText(products.get(position).getPetLocate());

        if (products.get(position).getPetSex()==1)
            cardItemView.PetCardSex.setImageResource(R.drawable.male);
        else
            cardItemView.PetCardSex.setImageResource(R.drawable.female);

        if (products.get(position).getHeart()==1)
            cardItemView.PetCardHeart.setImageResource(R.drawable.heart_full);
        else
            cardItemView.PetCardHeart.setImageResource(R.drawable.heart);
// TODO: 2024.3.3 领养可以做的更好看点
        if (products.get(position).getPetState()==1)
            cardItemView.PetCardState.setText("待领养");
        else
            cardItemView.PetCardState.setText("已领养");
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class AdoptCardItemView extends RecyclerView.ViewHolder {
        ImageView PetCardPostImageId;//动物图片
        ImageView PetCardSex;//动物性别
        ImageView PetCardHeart;//喜欢or未喜欢
        TextView PetCardName;//动物名称
        TextView PetCardCommunity;//动物所属社区
        TextView PetCardLocate;//动物位置（如江苏南京栖霞区）
        TextView PetCardState;//领养状态

        public AdoptCardItemView(View itemView) {
            super(itemView);

            PetCardPostImageId = (ImageView) itemView.findViewById(R.id.pet_card_pic);
            PetCardSex = (ImageView) itemView.findViewById(R.id.pet_card_sex);
            PetCardHeart = (ImageView) itemView.findViewById(R.id.pet_card_heart);
            PetCardName = (TextView) itemView.findViewById(R.id.pet_card_name);
            PetCardCommunity = (TextView) itemView.findViewById(R.id.pet_card_community);
            PetCardLocate = (TextView) itemView.findViewById(R.id.pet_card_locate);
            PetCardState = (TextView) itemView.findViewById(R.id.pet_card_state);

            //点击事件放在adapter中使用，也可以写个接口在activity中调用
            //在adapter中设置点击事件
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //可以选择直接在本位置直接写业务处理
                    Toast.makeText(v.getContext(), "点击了猫猫"+PetCardName.getText(),Toast.LENGTH_SHORT).show();
                    //此处回传点击监听事件

//                    StringAndBitmap stringAndBitmap = new StringAndBitmap();
//
//                    User user = new User();
//                    Bitmap bitmap=((BitmapDrawable)PetCardPostImageId.getDrawable()).getBitmap();
//                    String string=stringAndBitmap.bitmapToString(bitmap);
//                    DBPetHelper.insert(string,1,1, (String) PetCardName.getText()
//                            , (String) PetCardCommunity.getText(), "中华田园猫", (String) PetCardLocate.getText(),user.getUserId());

                    //                    StringAndBitmap stringAndBitmap = new StringAndBitmap();
//                    Bitmap bitmap=((BitmapDrawable)head_pic.getDrawable()).getBitmap();
//                    String string=stringAndBitmap.bitmapToString(bitmap);
//                    DBHeadHelper.insert(1, string);
//                    string = DBHeadHelper.query(1);
//                    System.out.println("aaa"+DBHeadHelper.query(1));
//                    head_pic.setImageBitmap(stringAndBitmap.stringToBitmap(string));
//                    TextView personal_name = view.findViewById(R.id.name);
//                    personal_name.setText(DBHeadHelper.query(1));
                }
            });

        }

    }

}


//public class AdoptCardItemAdapter extends RecyclerView.Adapter<AdoptCardItemAdapter.CardViewHolder>{
//
//    private Context context;
//    private ArrayList<AdoptCardItem> cardItemList;
//
//
//    //创建构造函数
//    public AdoptCardItemAdapter(Context context, ArrayList<AdoptCardItem> cardItemList) {
//        //将传递过来的数据，赋值给本地变量
//        this.context = context;//上下文
//        this.cardItemList = cardItemList;//实体类数据ArrayList
//    }
//
//    /**
//     * 创建viewhodler，相当于listview中getview中的创建view和viewhodler
//     *
//     * @param parent
//     * @param viewType
//     * @return
//     */
//    @Override
//    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        //创建自定义布局
//        View itemView = View.inflate(context, R.layout.item_card, null);
//        itemView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp2px(context,65)));
//        return new CardViewHolder(itemView);
//    }
//
//    public static int dp2px(Context context, final float dpValue) {//dp转换px
//        final float scale = context.getResources().getDisplayMetrics().density;
//        return (int) (dpValue * scale + 0.5f);
//    }
//
//
//    /**
//     * 得到总条数
//     *
//     * @return
//     */
//    @Override
//    public int getItemCount() {
//        return cardItemList.size();
//    }
//
//    //自定义viewhodler
//    class CardViewHolder extends RecyclerView.ViewHolder {
//
//        private ImageView CardPostImageId;
//        private ImageView CardHeadImageId;
//        private ImageView CardHeart;
//        private TextView CardTitle;
//        private TextView CardUserName;
//
//
//        public CardViewHolder(View itemView) {
//            super(itemView);
//
//            CardPostImageId = (ImageView) itemView.findViewById(R.id.card_post_pic);
//            CardHeadImageId = (ImageView) itemView.findViewById(R.id.card_head_pic);
//            CardHeart = (ImageView) itemView.findViewById(R.id.card_heart);
//            CardTitle = (TextView) itemView.findViewById(R.id.card_title);
//            CardUserName = (TextView) itemView.findViewById(R.id.card_name);
//
//            //点击事件放在adapter中使用，也可以写个接口在activity中调用
//            //在adapter中设置点击事件
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //可以选择直接在本位置直接写业务处理
//                    //Toast.makeText(context,"点击了xxx",Toast.LENGTH_SHORT).show();
//                    //此处回传点击监听事件
//                    if(onItemClickListener!=null){
//                        onItemClickListener.OnItemClick(v, getLayoutPosition());
//                    }
//                }
//            });
//
//        }
//    }
//
//    /**
//     * 绑定数据，数据与view绑定
//     *
//     * @param holder
//     * @param position
//     */
//    @Override
//    public void onBindViewHolder(CardViewHolder holder, int position) {
//        //根据点击位置绑定数据
//        AdoptCardItem data = cardItemList.get(position);
//
//        holder.CardPostImageId.setImageResource(data.getPostImageId());//获取实体类中的字段并设置
//        holder.CardHeadImageId.setImageResource(data.getHeadImageId());
//        holder.CardHeart.setImageResource(data.getHeart());
//        holder.CardTitle.setText(data.getTitle());
//        holder.CardUserName.setText(data.getUserName());
//    }
//
//    /**
//     * 设置item的监听事件的接口
//     */
//    public interface OnItemClickListener {
//        /**
//         * 接口中的点击每一项的实现方法，参数自己定义
//         *
//         * @param view 点击的item的视图
//         * @param position 点击的item的数据
//         */
//        public void OnItemClick(View view, int position);
//    }
//
//
//    //需要外部访问，所以需要设置set方法，方便调用
//    private OnItemClickListener onItemClickListener;
//
//    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
//        this.onItemClickListener = onItemClickListener;
//    }
//
//
//
//}
