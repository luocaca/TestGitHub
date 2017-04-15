package com.hldj.hmyg.saler;

import android.content.Context;

import com.hldj.hmyg.R;
import com.zzy.flowers.widget.popwin.BottomPopwin;

public class FlowerInfoPhotoChoosePopwin2 extends BottomPopwin {


    public FlowerInfoPhotoChoosePopwin2(Context context,  onPhotoStateChangeListener onPhotoState) {
        super(new int[]{R.string.photo_take_pic, R.string.photo_album_title, R.string.cancel}, context);
    }

    @Override
    protected void handleClickListener(int pos) {
        switch (pos) {
            case 0:

                break;
            case 1:

                break;
            default:
                break;
        }
    }

    public interface onPhotoStateChangeListener {
        void onTakePic();

        void onChoosePic();

        void onCancle();

    }

}
