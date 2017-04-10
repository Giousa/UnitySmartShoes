package com.zmm.unitysmartshoes;

import android.content.ContextWrapper;
import android.util.Log;

import com.unity3d.player.UnityPlayer;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2017/4/10
 * Time:下午2:53
 */

public class PermanentUnityPlayer extends UnityPlayer {

    public PermanentUnityPlayer(ContextWrapper contextWrapper) {
        super(contextWrapper);
    }

    @Override
    protected void kill() {
        Log.d("TAG","this unity has killed.");
    }
}

