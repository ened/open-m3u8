package com.iheartradio.m3u8;

import com.iheartradio.m3u8.data.PlaylistData;

class PlaylistHandler implements LineHandler {
    @Override
    public void handle(String line, ParseState state) {
        final PlaylistData.Builder builder = new PlaylistData.Builder();

        if (Constants.URL_PATTERN.matcher(line).matches()) {
            builder.withUrl(line);
        } else {
            builder.withPath(line);
        }

        final MasterParseState masterState = state.getMaster();

        masterState.playlists.add(builder
                .withStreamInfo(masterState.streamInfo)
                .build());

        masterState.streamInfo = null;
    }
}
