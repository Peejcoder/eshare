package com.kzmen.sczxjf.ui.fragment.kzmessage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kzmen.sczxjf.AppContext;
import com.kzmen.sczxjf.R;
import com.kzmen.sczxjf.adapter.KzActivGridAdapter;
import com.kzmen.sczxjf.adapter.KzMainColumnAdapter;
import com.kzmen.sczxjf.bean.kzbean.MainColumnItemBean;
import com.kzmen.sczxjf.test.AnserQuesActivity;
import com.kzmen.sczxjf.test.bean.Music;
import com.kzmen.sczxjf.test.server.PlayService;
import com.kzmen.sczxjf.ui.activity.kzmessage.ActivListActivity;
import com.kzmen.sczxjf.ui.activity.kzmessage.AskListActivity;
import com.kzmen.sczxjf.ui.activity.kzmessage.CaseListActivity;
import com.kzmen.sczxjf.ui.activity.kzmessage.CourseListActivity;
import com.kzmen.sczxjf.util.EToastUtil;
import com.kzmen.sczxjf.util.glide.GlideCircleTransform;
import com.kzmen.sczxjf.util.glide.GlideRoundTransform;
import com.kzmen.sczxjf.view.ExPandGridView;
import com.kzmen.sczxjf.view.banner.BannerLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 卡掌门--掌信端
 */
public class KzMessageFragment extends Fragment {

    @InjectView(R.id.bl_main_banner)
    BannerLayout blMainBanner;
    @InjectView(R.id.gv_column)
    ExPandGridView gvColumn;
    @InjectView(R.id.iv_user_head)
    ImageView ivUserHead;
    @InjectView(R.id.tv_user_identity)
    TextView tvUserIdentity;
    @InjectView(R.id.tv_user_name)
    TextView tvUserName;
    @InjectView(R.id.ll_user_head)
    LinearLayout llUserHead;
    @InjectView(R.id.tv_course_ex)
    TextView tvCourseEx;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.tv_media_start_time)
    TextView tvMediaStartTime;
    @InjectView(R.id.tv_media_end_time)
    TextView tvMediaEndTime;
    @InjectView(R.id.iv_course_play)
    ImageView ivCoursePlay;
    @InjectView(R.id.tv_xiaojiang_title1)
    TextView tvXiaojiangTitle1;
    @InjectView(R.id.iv_xiaojiang_play1)
    ImageView ivXiaojiangPlay1;
    @InjectView(R.id.tv_xiaojiang_title2)
    TextView tvXiaojiangTitle2;
    @InjectView(R.id.iv_xiaojiang_play2)
    ImageView ivXiaojiangPlay2;
    @InjectView(R.id.ll_more_course)
    LinearLayout llMoreCourse;
    @InjectView(R.id.tv_ask_title1)
    TextView tvAskTitle1;
    @InjectView(R.id.iv_ask_head2)
    ImageView ivAskHead2;
    @InjectView(R.id.tv_ask_listen_state2)
    TextView tvAskListenState2;
    @InjectView(R.id.tv_ask_listen_type1)
    TextView tvAskListenType1;
    @InjectView(R.id.tv_ask_listen_name1)
    TextView tvAskListenName1;
    @InjectView(R.id.tv_ask_listen_count1)
    TextView tvAskListenCount1;
    @InjectView(R.id.tv_ask_title2)
    TextView tvAskTitle2;
    @InjectView(R.id.iv_ask_head1)
    ImageView ivAskHead1;
    @InjectView(R.id.tv_ask_listen_state1)
    TextView tvAskListenState1;
    @InjectView(R.id.tv_ask_listen_type2)
    TextView tvAskListenType2;
    @InjectView(R.id.tv_ask_listen_name2)
    TextView tvAskListenName2;
    @InjectView(R.id.tv_ask_listen_count2)
    TextView tvAskListenCount2;
    @InjectView(R.id.ll_more_ask)
    LinearLayout llMoreAsk;
    @InjectView(R.id.gv_more_activ)
    ExPandGridView gvMoreActiv;
    @InjectView(R.id.ll_more_activ)
    LinearLayout llMoreActiv;
    @InjectView(R.id.sb_play)
    SeekBar sb_play;
    private View view = null;
    private BannerLayout bl_main_banner;
    private List<String> urlList;
    private GridView gv_column;
    private List<String> listTst;
    private List<MainColumnItemBean> columnItemBeanList;
    private List<String> listTstActiv;
    private KzMainColumnAdapter kzMainColumnAdapter;
    private KzActivGridAdapter kzActivGridAdapter;
    private List<Music> mMusicList;

    private String url = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_kz_message, container, false);
        }
        ButterKnife.inject(this, view);
        initView();
        return view;
    }

    private void initView() {
        blMainBanner.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                EToastUtil.show(getActivity(), "" + position);
            }
        });
        initData();
        sb_play.setOnSeekBarChangeListener(new SeekBarChangeEvent());
    }

    private void initData() {
        mMusicList = new ArrayList<>();
        urlList = new ArrayList<>();
        listTst = new ArrayList<>();
        columnItemBeanList = new ArrayList<>();
        listTstActiv = new ArrayList<>();
        urlList.add(url);
        urlList.add("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png");
        urlList.add("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png");
        urlList.add("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png");
        urlList.add("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png");
        blMainBanner.setViewUrls(urlList);

        columnItemBeanList.add(new MainColumnItemBean("课程", R.drawable.menu_lesson));
        columnItemBeanList.add(new MainColumnItemBean("问答", R.drawable.menu_interlocution));
        columnItemBeanList.add(new MainColumnItemBean("测评", R.drawable.menu_evaluation));
        columnItemBeanList.add(new MainColumnItemBean("活动", R.drawable.menu_activity));
        columnItemBeanList.add(new MainColumnItemBean("案例", R.drawable.menu_case));

        kzMainColumnAdapter = new KzMainColumnAdapter(getActivity(), columnItemBeanList);
        gvColumn.setAdapter(kzMainColumnAdapter);
        listTstActiv.add("测试1");
        listTstActiv.add("测试1");
        listTstActiv.add("测试1");
        listTstActiv.add("测试1");
        kzActivGridAdapter = new KzActivGridAdapter(getActivity(), listTstActiv);
        gvMoreActiv.setAdapter(kzActivGridAdapter);
        gvColumn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                switch (position) {
                    case 0:
                        intent = new Intent(getActivity(), CourseListActivity.class);
                        break;
                    case 1:
                        intent = new Intent(getActivity(), AskListActivity.class);
                        break;
                    case 2:
                        intent = new Intent(getActivity(), AnserQuesActivity.class);
                        break;
                    case 3:
                        intent = new Intent(getActivity(), ActivListActivity.class);
                        break;
                    case 4:
                        intent = new Intent(getActivity(), CaseListActivity.class);
                        break;
                }
                if (intent != null) {
                    startActivity(intent);
                }
            }
        });
        Glide.with(getActivity()).load(R.drawable.icon_user1).transform(new GlideRoundTransform(getActivity(), 10)).into(ivUserHead);
        Glide.with(getActivity()).load(R.drawable.icon_user).transform(new GlideCircleTransform(getActivity())).into(ivAskHead1);
        Glide.with(getActivity()).load(R.drawable.icon_user).transform(new GlideCircleTransform(getActivity())).into(ivAskHead2);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.iv_course_play, R.id.iv_xiaojiang_play1, R.id.iv_xiaojiang_play2, R.id.ll_more_course, R.id.ll_more_ask, R.id.ll_more_activ})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.iv_course_play:
                mMusicList.clear();
                Music music = new Music();
                music.setType(Music.Type.ONLINE);
                music.setPath("http://192.168.0.102:8000/static/mp3/Dawn.mp3");
                mMusicList.add(music);
                Music music1 = new Music();
                music1.setType(Music.Type.ONLINE);
                music1.setPath("http://192.168.0.102:8000/static/mp3/Fade.mp3");
                mMusicList.add(music1);
                Music music2 = new Music();
                music2.setType(Music.Type.ONLINE);
                music2.setPath("http://192.168.0.102:8000/static/mp3/鬼迷心窍.mp3");
                mMusicList.add(music2);
                AppContext.getPlayService().setMusicList(mMusicList);
                play();
                AppContext.getPlayService().setOnPreInter(new PlayService.onPreInter() {
                    @Override
                    public void prePercent(int percent) {
                        sb_play.setSecondaryProgress(percent);
                    }
                });
                AppContext.getPlayService().setGetTime(new PlayService.getTime() {
                    @Override
                    public void time(String start, String end, int pos) {
                        tvMediaStartTime.setText(start);
                        tvMediaEndTime.setText(end);
                        sb_play.setProgress(pos);
                    }
                });
                break;
            case R.id.iv_xiaojiang_play1:
                mMusicList.clear();
                Music musicp = new Music();
                musicp.setType(Music.Type.ONLINE);
                musicp.setPath("http://192.168.0.102:8000/static/mp3/Dawn.mp3");
                mMusicList.add(musicp);
                AppContext.getPlayService().setMusicList(mMusicList);
                playStart();
                break;
            case R.id.iv_xiaojiang_play2:
                mMusicList.clear();
                Music musicp1 = new Music();
                musicp1.setType(Music.Type.ONLINE);
                musicp1.setPath("http://192.168.0.102:8000/static/mp3/鬼迷心窍.mp3");
                mMusicList.add(musicp1);
                AppContext.getPlayService().setMusicList(mMusicList);
                playStart();
                break;
            case R.id.ll_more_course:
                intent = new Intent(getActivity(), CourseListActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_more_ask:
                intent = new Intent(getActivity(), AskListActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_more_activ:
                intent = new Intent(getActivity(), ActivListActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void play() {
        AppContext.getPlayService().playPause();
    }

    private void playStart() {
        AppContext.getPlayService().playStart();
    }

    class SeekBarChangeEvent implements SeekBar.OnSeekBarChangeListener {
        int progress;

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
            this.progress = progress * (AppContext.getPlayService().mPlayer.getDuration())
                    / seekBar.getMax();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // seekTo()的参数是相对与影片时间的数字，而不是与seekBar.getMax()相对的数字
            AppContext.getPlayService().mPlayer.seekTo(progress);
        }
    }
}