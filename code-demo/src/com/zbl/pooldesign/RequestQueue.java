package com.zbl.pooldesign;

import java.util.LinkedList;

public class RequestQueue {
    private final LinkedList<Request> queue;

    public int getSize(){
        return queue.size();
    }

    public Request getRequest(){
        return this.queue.removeLast();
    }




    private RequestQueue() {
        this.queue = new LinkedList<>();
//        Stream.of("1","2","3","4","5").forEach(name->this.queue.addFirst(new com.zbl.pooldesign.Request(name)));
    }

    public void submit(Request request) {
        this.queue.addFirst(request);
    }

    private enum Instance{
        INSTANCE;

        private RequestQueue instance;

        Instance() {
            instance = new RequestQueue();
        }

        public RequestQueue getInstance() {
            return instance;
        }

    }


    public static RequestQueue getInstance(){
        return Instance.INSTANCE.getInstance();
    }


}
