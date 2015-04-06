package com.snakeindustry.snakemultiplayer.generalApp.player.stats;

import java.util.List;

/**
 * Created by Adrien on 06/04/15.
 */
public interface OneGameStats {


    //Played time
    public OneStats getPlayedTime();
    public void addPlayedTime(double hour);
    //nb play
    public OneStats getNbPlay();
    public void addAPlay();

    //friends
    public void addAPlayWithAFriend(String friend);
    public List<OneStats> getStatsFriends();

    public List<OneStats> getFriendsList();
    //This may have returned an in or a double but a string is enough, because comparing the score between different games does not make much sens.
    //and a string allows different format of score
    public String getBestScoreAsAString();



    public List<OneStats> getSpecificStats();

    public List<OneStats> getAllStats();

    public void reset();
}
