package com.xiaoyou.face.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import com.bin.david.form.data.CellInfo;
import com.bin.david.form.data.format.bg.BaseCellBackgroundFormat;
import com.xiaoyou.face.R;
import com.xiaoyou.face.databinding.ActivitySignDetailBinding;
import com.xiaoyou.face.model.Login;
import com.xiaoyou.face.service.History;
import com.xiaoyou.face.service.SQLiteHelper;
import com.xiaoyou.face.service.Service;
import com.xiaoyou.face.service.StudentInfo;
import com.xiaoyou.face.service.StudentInfoTO;
import com.xiaoyou.face.utils.Tools;
import com.xuexiang.xui.XUI;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 签到详情显示
 * @author 小游
 */
public class SignDetailActivity extends AppCompatActivity {

    private ActivitySignDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 不在AndroidManifest.xml里面设置因为这个会无法继承AppCompatActivity
        XUI.initTheme(this);
        super.onCreate(savedInstanceState);
        binding = ActivitySignDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        // 视图初始化
        initView();
    }

    /**
     * 视图初始化
     */
    private void initView(){
        // 导航栏按钮点击事件
        binding.navigation.setLeftClickListener(v->{finish();});
        // 显示签到详情
        showDetail();
    }

    /**
     * 显示签到的详情情况
     */
    private void showDetail(){
        List<Login> list = new ArrayList<>();
        // 查询签到详情信息
        Service service = new SQLiteHelper(this);
        try {
            List<StudentInfoTO> histories =service.getCountToday();
            @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (StudentInfoTO history : histories) {
                list.add(new Login(history.getStuId(),history.getName(),sdf.format(history.getDateTime())));
            }
        }catch (Exception e){
            return;
        }
        // 表格样式(占满全屏)
        binding.table.getConfig().setMinTableWidth(Tools.getWidth(this));
        // 显示数据
        binding.table.setData(list);
        // 统计行
        binding.table.getConfig().setFixedTitle(true);
        // 格式化内容背景(这里我们设置背景交替)
        binding.table.getConfig().setContentCellBackgroundFormat(new BaseCellBackgroundFormat<CellInfo>() {
            @Override
            public int getBackGroundColor(CellInfo cellInfo) {
                int color = R.color.white;
                if (cellInfo.row%2 ==0){
                   color = R.color.tab_background;
                }
                return ContextCompat.getColor(getApplicationContext(), color);
            }
        });
        // 隐藏顶部的序号列
        binding.table.getConfig().setShowXSequence(false);

    }

}