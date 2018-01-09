package advert.sdk.com.advertlibrary.wiget;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


public class AdInsertLoad extends DialogFragment {

    private ImageView ivClose, ivData;
    private LinearLayout llAdinsert;
    private RelativeLayout rlAdinsert;

    private int location = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     /*  //View view = inflater.inflate(R.layout.fragment_ad_banner, container, false);
        ivClose = (ImageView) view.findViewById(R.id.iv_close);
        rlAdinsert = (RelativeLayout) view.findViewById(R.id.rl_adinsert);
        llAdinsert = (LinearLayout) view.findViewById(R.id.ll_adinsert);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);//*/
        // 设置宽度为屏宽、靠近屏幕底部。
        final Window window = getDialog().getWindow();
        // window.setBackgroundDrawableResource(R.color.gray_light);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams wlp = window.getAttributes();


        if (location == 0) {
            wlp.gravity = Gravity.TOP;
        } else if (location == 1) {
            wlp.gravity = Gravity.CENTER;
        } else if (location == 2) {
            wlp.gravity = Gravity.BOTTOM;
        }

        wlp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        wlp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(wlp);

//        rlAdinsert.setGravity(Gravity.BOTTOM | Gravity.CENTER);
//        llAdinsert.setGravity(Gravity.CENTER|Gravity.CENTER_VERTICAL);
        //Glide.with(getActivity()).load(R.drawable.close).centerCrop().into(ivClose);
        SupportMultipleScreensUtil.init(getActivity());
        SupportMultipleScreensUtil.scale(llAdinsert);
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        llAdinsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //new DownloadApk(getActivity()).initDownloadManager();
            }
        });
        //return view;

    /*@Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            dialog.getWindow().setLayout((int) (dm.widthPixels * 0.75), ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }*/

   /* private void downloadApk() {
        Toast.makeText(getActivity(), "正在下载:", Toast.LENGTH_SHORT).show();
        DownloadUtils.getsInstance().setListener(new DownloadUtils.OnDownloadListener() {
            @Override
            public void onDowload(String mp3Url) { //下载成功
                Toast.makeText(getActivity(), "下载成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailed(String error) { //下载失败
                Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
            }
        }).download();
    }*/


    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }*/
        return null;
    }
}