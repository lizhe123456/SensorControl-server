package com.zlcm.qq;

import com.zlcm.qq.callback.MessageCallback;
import com.zlcm.qq.client.SmartQQClient;
import com.zlcm.qq.model.*;

import java.io.IOException;
import java.util.*;

/**
 * @author ScienJus
 * @date 2015/12/18.
 */
public class Application {
    static SmartQQClient client;
    static Timer timer = new Timer();
    static List<Long> userList;
    public static void main(String[] args) {

        //创建一个新对象时需要扫描二维码登录，并且传一个处理接收到消息的回调，如果你不需要接收消息，可以传null
        client = new SmartQQClient(new MessageCallback() {
            @Override
            public void onMessage(Message message) {
                System.out.println(message.getContent());
                if (message.getContent().equals("停")){
                    try {
                        timer.cancel();
                        client.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onGroupMessage(GroupMessage message) {
                System.out.println(message.getContent());
                if (message.getContent().equals("停")){
                    try {
                        timer.cancel();
                        client.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onDiscussMessage(DiscussMessage message) {
                System.out.println(message.getContent());
                if (message.getContent().equals("停")){
                    try {
                        timer.cancel();
                        client.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        long id = 0;
        long userId = 0;
        //登录成功后便可以编写你自己的业务逻辑了
        List<Group> list = client.getGroupList();
        List<Friend> list1 = client.getFriendList();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getId());
            System.out.println(list.get(i).getName());
            if (list.get(i).getName().equals("垃圾")){
                id = list.get(i).getId();
            }
        }
        userList = new ArrayList();
        for (int i = 0; i < list1.size(); i++) {
            System.out.println(list1.get(i).getUserId());
            System.out.println(list1.get(i).getNickname());
            if (list1.get(i).getNickname().equals("遗忘")){
                userId = list1.get(i).getUserId();
                userList.add(userId);
            }
            if (list1.get(i).getNickname().equals("B Waht U Wanna B")){
                userId = list1.get(i).getUserId();
                userList.add(userId);
            }
        }

        client.sendMessageToGroup(id,"开始");
        Integer cacheTime = 1000 * 3;

        // (TimerTask task, long delay, long period)任务，延迟时间，多久执行
        long finalId = userId;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Random random = new Random();
                int s = random.nextInt(Msg.msg.length);

                client.sendMessageToFriend(userList.get(0),Msg.msg[s]);
                client.sendMessageToFriend(userList.get(1),Msg.msg1[s]);
            }
        }, 5000, cacheTime);

    }
}
