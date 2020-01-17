package com.zbl.servlet.v1;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zbl
 * @Date: Created in 9:37 2020/1/16
 * @Description: 责任链模式 3.0
 * @Version: $
 */
public class ServletNo1 {

    public static void main(String[] args) {
        Request request = new Request();
        request.setStr("request");
        Response response = new Response();
        response.setStr("response");
        FilterChain filterChain = new FilterChain();
        filterChain.setIsRequest(true);
        filterChain.add(new HTMLFilter()).add(new HeaderFilter());
        filterChain.doFilter(request, response, filterChain);

    }

}


class Request {
    private String str;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}

class Response {
    private String str;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}

interface Filter {
    void doFilter(Request request, Response response, FilterChain filterChain);
}

class FilterChain implements Filter {
    private List<Filter> filters = new ArrayList<>();
    String request;
    String response;
    Boolean isNext;
    Boolean isRequest;
    Integer nextIndex;

    public void setIsRequest(Boolean IsRequest) {
        isRequest = IsRequest;
    }

    public Boolean getIsRequest() {
        return isRequest;
    }

    public Boolean getNext() {
        return isNext;
    }

    public void setNext(Boolean next) {
        isNext = next;
    }

    public FilterChain add(Filter f) {
        filters.add(f);
        return this;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }



    public void doFilter (Request request, Response response, FilterChain filterChain){
        for (Filter filter : filters) {
            filter.doFilter(request, response, filterChain);
        }
        setIsRequest(false);
        for (int i = filters.size() - 1; i >= 0; i--) {
            filters.get(i).doFilter(request, response, filterChain);
        }
    }
}

class HTMLFilter implements Filter {

    @Override
    public void doFilter(Request request, Response response, FilterChain filterChain) {
        if (filterChain.getIsRequest()){
            System.out.println("Html request");
        } else {
            System.out.println("Html response");
        }
    }
}

class HeaderFilter implements Filter {

    @Override
    public void doFilter(Request request, Response response, FilterChain filterChain) {
        if (filterChain.getIsRequest()){
            System.out.println("Header request");
        } else {
            System.out.println("Header response");
        }

    }
}

