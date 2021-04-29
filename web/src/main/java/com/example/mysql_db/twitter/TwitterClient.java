package com.example.mysql_db.twitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;

@Service
public class TwitterClient {

    private Twitter twitter;

    public TwitterClient(){
    }

    public String relpy(long replyId, String text){

        StatusUpdate stat= new StatusUpdate(text);
        stat.inReplyToStatusId(replyId);
        try {
            twitter.updateStatus(stat);


            return "Replied";

        }catch (Exception e){
            e.printStackTrace();
        }

        return "Not replied";

    }

    public String connect() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("J9BxQEzgnFusrdaAk15dAVcqA")
                .setOAuthConsumerSecret("s7GLXMuEpyYhgwGFmaPns3XSQJrbsIlkhHDtLtXSA3OG0LIYmM")
                .setOAuthAccessToken("803776376310362112-9Ejq8p6FWO3GbstsrPMvEU0QXTD5z8R")
                .setOAuthAccessTokenSecret("nQcAxoWozfSgTFzHEdoSgUzFBfHtuUFVJnKEnqzIFlend");
        TwitterFactory tf = new TwitterFactory(cb.build());
        this.twitter = tf.getInstance();
        return "Connected!!";
    }

    public List<Status> getMentionPost(Paging page){

        List<Status> statuses = null;

        try {
            //ホームのタイムラインを表示
            //List<Status> statuses=twitter.getHomeTimeline(page);

            //自分にメンションされているタイムラインを表示するはず
            statuses = twitter.getMentionsTimeline(page);

            //自分がリツイートされたもの表示
            //List<Status> statuses = twitter.getRetweetsOfMe(page);

            for(Status status:statuses){
                System.out.println(status.getUser().getName()+"(@"+status.getUser().getScreenName()+")\n"+ status.getText()+"\n");
            }
        } catch (TwitterException e) {
            e.printStackTrace();
        }

        return statuses;

    }



}

