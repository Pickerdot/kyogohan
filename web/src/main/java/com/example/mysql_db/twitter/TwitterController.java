package com.example.mysql_db.twitter;

import com.example.mysql_db.entuty.Dish;
import com.example.mysql_db.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import twitter4j.Paging;
import twitter4j.Status;

import java.util.List;
import java.util.Random;


@Controller
public class TwitterController {

    @Autowired
    TwitterClient tc;

    @Autowired
    DishService dishService;

    @Scheduled(fixedDelay = 400000)
    public void replyMessage(){



        //一度に取得するツイートの個数
        int tweet_num = 20;

        Paging page = new Paging();
        page.setCount(tweet_num);
        long old_id = 0;

        System.out.println(tc.connect());
        List<Status> oldStatuses = tc.getMentionPost(page);//過去のリスト
        String message = "すみません！料理名を取得できませんでした";
        //現在のリスト
        List<Status> statuses = tc.getMentionPost(page);
        old_id = statuses.get(0).getId();

        try{

        }catch(Exception e){
            e.printStackTrace();
        }

        for(int m = 0; m < 8; m++){
            System.out.println(tc.connect());
            statuses = tc.getMentionPost(page);
            for(int i = 0;i<10;i++) {

                try {
                    statuses = tc.getMentionPost(page);
                    for(int o = 0; o < tweet_num ; o++ ){
                        String text = null;
                        System.out.println(statuses.get(o).getId());
                        System.out.println(old_id);
                        long replyId = statuses.get(o).getId();
                        text =  "@" + statuses.get(o).getUser().getScreenName() + "\n";
                        //System.out.println(tc.relpy(replyId,text));
                        if(statuses.get(o).getId() == old_id){
                            break;
                        }else{
                            String contents = statuses.get(o).getText();
                            if(contents.contains("きょうごはん")){
                                message = this.getDish();
                                String replyMessage = text + message + "!!!";
                                System.out.println(tc.relpy(replyId,replyMessage));
                            }
                        }
                    }
                    System.out.println(i);
                    Thread.sleep(20000);
                    old_id = statuses.get(0).getId();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getDish() {
        List<Dish> list = dishService.getAll();
        int listLength = list.size();
        Random random = new Random();
        int randomDishId = random.nextInt(listLength) + 1;
        System.out.println("listLength:" + listLength);
        System.out.println(randomDishId);
        Dish dish = dishService.getOne(randomDishId);
        return dish.getDishName();

    }
}
