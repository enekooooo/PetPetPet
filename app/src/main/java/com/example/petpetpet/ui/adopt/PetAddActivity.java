package com.example.petpetpet.ui.adopt;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.fragment.DialogFragmentNavigatorDestinationBuilder;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.petpetpet.R;
import com.example.petpetpet.mysql.DBPetHelper;
import com.example.petpetpet.ui.StringAndBitmap;
import com.example.petpetpet.ui.adopt.PopupWindow.BaseActivity;
import com.example.petpetpet.ui.personal.db.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PetAddActivity extends BaseActivity {

    private static final String TAG = "PetAddActivity";

    //初始化控件
    private EditText add_Name;
    private CheckBox add_Sex;
    private CheckBox add_Type;
    private CheckBox add_Locate;
    private EditText add_Community;
    private ImageView add_Pic;
    private Button add_button;

    private Bitmap bitmap;

    private String name = null;
    private String sex = null;
    private String type = null;
    private String locate = null;
    private String community = null;
    private String pic = null;

    List<String> sexList = new ArrayList<>();
    List<String> typeList = new ArrayList<>();
    List<String> locateList = new ArrayList<>();

    public static final int TAKE_PHOTO = 1;
    public static final int CHOOSE_PHOTO = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adopt_pet_add);


        initDate();

        initView();

        add_Pic.setOnClickListener(new View.OnClickListener() {//打开相册选择宠物图片
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(PetAddActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(PetAddActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
//                    Toast.makeText(getApplicationContext(),"未获取权限",Toast.LENGTH_SHORT).show();
                    openAlbum();
                // TODO: 2024.3.7 权限问题
                } else {
                    openAlbum();
                }
            }
        });

        add_button = findViewById(R.id.add_button);//宠物信息存储
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = add_Name.getText().toString();
                community = add_Community.getText().toString();

                if(name == null)
                    Toast.makeText(getApplicationContext(),"宠物昵称不能为空",Toast.LENGTH_SHORT).show();
                else if (sex == null)
                    Toast.makeText(getApplicationContext(),"宠物性别不能为空",Toast.LENGTH_SHORT).show();
                else if (type == null)
                    Toast.makeText(getApplicationContext(),"宠物品种不能为空",Toast.LENGTH_SHORT).show();
                else if (locate == null)
                    Toast.makeText(getApplicationContext(),"宠物所在地不能为空",Toast.LENGTH_SHORT).show();
                else {
                    User user = new User();

                    //将图片转化为bitmap格式用于存储
                    StringAndBitmap stringAndBitmap = new StringAndBitmap();
                    String string=stringAndBitmap.bitmapToString(bitmap);

                    int sex2int = 0;

                    if(sex == "公未绝育")
                        sex2int = 1;
                    else if (sex=="公已绝育")
                        sex2int = 2;
                    else if (sex=="母未绝育")
                        sex2int = 3;
                    else if (sex=="母已绝育")
                        sex2int = 4;

                    //petState为1时为待领养,0为已领养
                    DBPetHelper.insert(string,sex2int,1, name, community, type, locate,user.getUserId());
                    Toast.makeText(getApplicationContext(),"添加成功！",Toast.LENGTH_SHORT).show();

                    finish();
                }
            }
        });

    }

    private void initDate() {
        // 初始化选择栏数据
        /*
        数据库标识
        公未绝育：1
        公已绝育：2
        母未绝育：3
        母已绝育：4
         */
        Collections.addAll(sexList, "公未绝育", "公已绝育", "母未绝育", "母已绝育");
        Collections.addAll(typeList, "猫", "狗");
        Collections.addAll(locateList, "天津", "北京", "上海", "深圳", "江苏", "山东", "广西");
    }


    private void initView() {
        add_Name = findViewById(R.id.add_name);
        add_Sex = findViewById(R.id.add_sex);
        add_Type = findViewById(R.id.add_type);
        add_Locate = findViewById(R.id.add_locate);
        add_Community = findViewById(R.id.add_community);
        add_Pic = findViewById(R.id.add_pic);

        add_Pic.setImageResource(R.drawable.add);

        //性别筛选栏选择
        add_Sex.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                filterTabToggle(isChecked, add_Sex, sexList, new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        hidePopListView();
                        sex = sexList.get(position);
                        add_Sex.setText(sex);

                    }
                },add_Type,add_Locate);
            }
        });

        //品种筛选栏选择
        add_Type.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                filterTabToggle(isChecked, add_Type, typeList, new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        hidePopListView();
                        type = typeList.get(position);
                        add_Type.setText(type);      //猫或狗
                        if (typeList.get(position)=="猫") {
                            /*
                            一次筛选
                             */
                            List<String> catList = new ArrayList<>();
                            Collections.addAll(catList,"中华田园猫","布偶猫","英国短毛猫","美国短毛猫","缅因猫",
                                    "暹罗猫","无毛猫","蓝猫","加菲猫","矮脚猫","卷毛猫","其他/认不出来");
                            filterTabToggle(isChecked, add_Type, catList, new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                                    hidePopListView();
                                    if (catList.get(position)!="其他/认不出来")//如果认不出来，那分类只设为猫
                                        type = type + "-" + catList.get(position);
                                    add_Type.setText(type);

                                    /*
                                    二次筛选
                                     */
                                    if (catList.get(position)=="中华田园猫") {
                                        List<String> catChinaList = new ArrayList<>();
                                        Collections.addAll(catChinaList,"橘猫","三花","狸花","白猫","黑猫",
                                                "奶牛", "其他/认不出来");
                                        filterTabToggle(isChecked, add_Type, catChinaList, new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                                                hidePopListView();
                                                if (catList.get(position)!="其他/认不出来")//如果认不出来，那分类只设为猫-中华田园猫
                                                    type = type + "-" + catChinaList.get(position);
                                                add_Type.setText(type);
                                            }
                                        },add_Sex,add_Locate);
                                    }
                                }
                            },add_Sex,add_Locate);


                        }
                    }
                },add_Sex,add_Locate);
            }
        });

        //位置筛选栏选择
        add_Locate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                filterTabToggle(isChecked, add_Locate, locateList, new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        hidePopListView();
                        locate = locateList.get(position);
                        add_Locate.setText(locate);
                        if(locateList.get(position)=="天津"){

                        }else if (locateList.get(position)=="北京"){

                        }else if (locateList.get(position)=="上海"){

                        }else if (locateList.get(position)=="深圳"){

                        }else if(locateList.get(position)=="山东"){

                            List<String> ShandongList = new ArrayList<>();
                            Collections.addAll(ShandongList,"烟台","济南","青岛","日照","潍坊");

                            filterTabToggle(isChecked, add_Locate, ShandongList, new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                                    hidePopListView();
                                    locate = locate + ShandongList.get(position);
                                    add_Locate.setText(locate);
                                }
                            },add_Sex,add_Type);
                        }else if(locateList.get(position)=="江苏"){

                            List<String> JiangsuList = new ArrayList<>();
                            Collections.addAll(JiangsuList,"南京","苏州","扬州","连云港","镇江");

                            filterTabToggle(isChecked, add_Locate, JiangsuList, new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                                    hidePopListView();
                                    locate = locate + JiangsuList.get(position);
                                    add_Locate.setText(locate);
                                }
                            },add_Sex,add_Type);
                        }else if(locateList.get(position)=="广西"){

                            List<String> GuangxiList = new ArrayList<>();
                            Collections.addAll(GuangxiList,"桂林","南宁","百色","柳州","玉林");

                            filterTabToggle(isChecked, add_Locate, GuangxiList, new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                                    hidePopListView();
                                    locate = locate + GuangxiList.get(position);
                                    add_Locate.setText(locate);
                                }
                            },add_Sex,add_Type);
                        }
                    }
                },add_Sex,add_Type);
            }
        });
    }

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO); // 打开相册
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CHOOSE_PHOTO://从相册选择图片
                if (resultCode == RESULT_OK) {
                    // 判断手机系统版本号
                    if (Build.VERSION.SDK_INT >= 19) {
                        // 4.4及以上系统使用这个方法处理图片
                        handleImageOnKitKat(data);
                    } else {
                        // 4.4以下系统使用这个方法处理图片
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
            default:
                break;
        }
    }

    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        Log.d("TAG", "handleImageOnKitKat: uri is " + uri);
        if (DocumentsContract.isDocumentUri(this, uri)) {
            // 如果是document类型的Uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1]; // 解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // 如果是content类型的Uri，则使用普通方式处理
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            // 如果是file类型的Uri，直接获取图片路径即可
            imagePath = uri.getPath();
        }
        displayImage(imagePath); // 根据图片路径显示图片
    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }

    @SuppressLint("Range")
    private String getImagePath(Uri uri, String selection) {
        String path = null;
        // 通过Uri和selection来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            bitmap = BitmapFactory.decodeFile(imagePath);
            add_Pic.setScaleType(ImageView.ScaleType.FIT_XY);
            add_Pic.setImageBitmap(bitmap);
        } else {
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}


















