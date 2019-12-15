package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/web/chat/{username}")
@Component
public class MyWebSocket {
    //用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<MyWebSocket>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //存放用户名
    private String username;

    //用户名和session绑定的哈希表
    private static Map<String,Session> map=new HashMap<String, Session>();


    @OnOpen
    public void onOpen(Session session,@PathParam("username") String username) throws JSONException, IOException {
        this.username=username;
        this.session = session;
        /*for (MyWebSocket item : webSocketSet) {
            if(item.username.equals(username)) {
            }
        }*/
        map.put(username,session);
        webSocketSet.add(this);     //加入set中
        JSONObject json1 =new JSONObject();
        JSONArray json2=new JSONArray();
        json1.put("type",2);
        System.out.println("on");
        for (MyWebSocket item : webSocketSet) {
            json2.put(item.username);
        }
        json1.put("msg",json2);
        System.out.println(json1.toString());
        broadcCast(json1.toString());
    }

    @OnClose
    public void onClose() throws JSONException, IOException {
        webSocketSet.remove(this);  //从set中删除
        System.out.println("off");
        JSONObject json1 =new JSONObject();
        JSONArray json2=new JSONArray();
        json1.put("type",2);
        System.out.println("on");
        for (MyWebSocket item : webSocketSet) {
            json2.put(item.username);
        }
        json1.put("msg",json2);
        System.out.println(json1.toString());
        broadcCast(json1.toString());
    }


    @OnMessage
    public void onMessage(String message, Session session, @PathParam("username") String username) throws IOException, JSONException {
        System.out.println("来自客户端的消息-->"+username+": " + message);
        ObjectMapper objectMapper=new ObjectMapper();
        SocketMsg socketMsg;
        socketMsg=objectMapper.readValue(message,SocketMsg.class);
        if(socketMsg.getType()==1){
            Session fromSession=map.get(socketMsg.getFromUser());
            Session toSession=map.get(socketMsg.getToUser());
            if(toSession!=null){
                fromSession.getAsyncRemote().sendText(message);
                if(!socketMsg.getFromUser().equals(socketMsg.getToUser()))
                toSession.getAsyncRemote().sendText(message);
            }
        }
        else broadcCast(message);
    }

    @OnError
    public void onError(Session session, Throwable error) throws JSONException, IOException {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    public void broadcCast(String message) throws IOException {
        for (MyWebSocket item : webSocketSet) {
            item.session.getAsyncRemote().sendText(message);//异步发送消息.
        }
    }

}
