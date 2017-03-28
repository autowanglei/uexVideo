/*
 *  Copyright (C) 2014 The AppCan Open Source Project.
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.

 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.

 *  You should have received a copy of the GNU Lesser General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.zywx.wbpalmstar.plugin.uexvideo;

import org.zywx.wbpalmstar.plugin.uexvideo.utils.ConstantUtil;
import org.zywx.wbpalmstar.plugin.uexvideo.vo.PlayerControlVO;

/**
 * Created by wanglei on 2017/3/24.
 */

public class VideoAgent {

    private VideoAgent() {
    }

    public static VideoAgent getInstance() {
        return VideoAgentHandler.sInstance;
    }

    private static class VideoAgentHandler {
        private static final VideoAgent sInstance = new VideoAgent();
    }

    public void playerControl(VideoPlayerActivityForViewToWeb mVideoPlayerActivityForViewToWeb,
                              PlayerControlVO playerControlVO) {
        MediaPlayerControl mediaPlayerControl = MediaPlayerControl.values()[playerControlVO.cmd];
        switch (mediaPlayerControl) {
            case START:
                mVideoPlayerActivityForViewToWeb.startMediaPlayer();
                mVideoPlayerActivityForViewToWeb.showMediaPlayerControler();
                mVideoPlayerActivityForViewToWeb.notifyHideControllers();
                break;
            case PAUSE:
                mVideoPlayerActivityForViewToWeb.pauseMediaPlayer();
                mVideoPlayerActivityForViewToWeb.showMediaPlayerControler();
                mVideoPlayerActivityForViewToWeb.notifyHideControllers();
                break;
            case FULLSCREEN:
                mVideoPlayerActivityForViewToWeb.setVideoDisplayMode(ConstantUtil.MODE_FULL_SCEEN);
                break;
            case EXITFULLSCREEN:
                mVideoPlayerActivityForViewToWeb.setVideoDisplayMode(ConstantUtil.MODE_SCALE);
                break;
            case FASTFORWARD:
            case FASTREVERSE:
                mVideoPlayerActivityForViewToWeb.fastSeekAction(mediaPlayerControl,
                        playerControlVO.fastSeekStep);
                break;
        }
    }
}
