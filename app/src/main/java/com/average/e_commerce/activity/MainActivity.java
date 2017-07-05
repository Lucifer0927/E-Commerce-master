package com.average.e_commerce.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.average.e_commerce.R;
import com.average.e_commerce.base.IActivity;
import com.average.e_commerce.fragment.ClassifyFragment;
import com.average.e_commerce.fragment.HomeFragment;
import com.average.e_commerce.fragment.MeFragment;
import com.average.e_commerce.fragment.ShopFragment;
import com.average.e_commerce.fragment.WebFragment;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MainActivity extends IActivity {
    private FragmentManager fragmentManager;
    private List<Fragment> fragments = new ArrayList<Fragment>();
    private RadioGroup radioGroup;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getSupportFragmentManager();
        createFragment(savedInstanceState);
        switchFragment(0);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(4);
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                switch (checkedId) {

                    case R.id.tabber_rb_home:

                        switchFragment(0);
                        break;

                    case R.id.tabber_rb_classify:
                        switchFragment(1);

                        break;

                    case R.id.tabber_rb_shop:
                        switchFragment(2);

                        break;

                    case R.id.tabber_rb_me:
                        switchFragment(3);

                        break;

                }
            }
        });
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        radioGroup = (RadioGroup) findViewById(R.id.tabber_rg_main);
        iv = (ImageView) findViewById(R.id.leGou_iv_main);
    }

    @Override
    public int getActivityLayoutId() {
        return R.layout.activity_main;
    }

    public void createFragment(Bundle savedInstanceState) {

        HomeFragment firstFragment = (HomeFragment) fragmentManager.findFragmentByTag("HomeFragment");
        ClassifyFragment secondFragment = (ClassifyFragment) fragmentManager.findFragmentByTag("ClassifyFragment");
        ShopFragment thirdFragment = (ShopFragment) fragmentManager.findFragmentByTag("ShopFragment");
        MeFragment fourthFragment = (MeFragment) fragmentManager.findFragmentByTag("MeFragment");
        WebFragment webFragment= (WebFragment) fragmentManager.findFragmentByTag("WebFragment");
        if (firstFragment == null) {
            firstFragment = new HomeFragment();
        }

        if (secondFragment == null) {
            secondFragment = new ClassifyFragment();
        }
        if (thirdFragment == null) {
            thirdFragment = new ShopFragment();
        }
        if (fourthFragment == null) {
            fourthFragment = new MeFragment();
        }
        if (webFragment==null){
            webFragment=new WebFragment();
        }

        fragments.add(firstFragment);
        fragments.add(secondFragment);
        fragments.add(thirdFragment);
        fragments.add(fourthFragment);
        fragments.add(webFragment);


    }


    public void switchFragment(int pos) {

        FragmentTransaction transaction = fragmentManager.beginTransaction();


        if (!fragments.get(pos).isAdded()) {

            transaction.add(R.id.container_fram_main, fragments.get(pos), fragments.get(pos).getClass().getSimpleName());
        }

        for (int i = 0; i < fragments.size(); i++) {

            if (i == pos) {
                transaction.show(fragments.get(pos));
            } else {
                transaction.hide(fragments.get(i));
            }

        }
        transaction.commit();


    }



}
