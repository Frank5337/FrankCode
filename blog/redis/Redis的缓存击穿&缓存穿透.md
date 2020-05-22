---
title: Redis的缓存击穿&缓存穿透
date: 2020-05-18 18:00:00
tags: 
- Redis
categories: 
- Redis
---

&emsp;&emsp;在客户端(浏览器，手机，第三方的一些设备) 去请求到我们的服务器，如果涉及到服务器的交互的话，很多热点数据会直接打到数据库，可能会导致数据库崩溃的情况，于是就有了使用Redis来避免这种情况的发生

<!--more-->

#### 什么是缓存击穿&缓存穿透？

&emsp;&emsp;比如现在有一个请求的key是-1，而我redis和数据库中只有1~10的数据，那么key=-1在redis中肯定是没有的，就会直接打到数据库上，就会出现如下连穿两层的情况<br/>

<div style="text-align: center">
<img  title = "缓存穿透&缓存击穿" src="https://gitee.com/zbl5337/config_center/raw/master/image/blog/redis/Redis%E7%BC%93%E5%AD%98%E5%87%BB%E7%A9%BF&%E7%BC%93%E5%AD%98%E7%A9%BF%E9%80%8F01.png"/>
</div>

&emsp;&emsp;然后这个请求就直接返回回去了，每次都能请求到我们的数据库上，那如果 我是一个黑客，上面的Client都是我的肉机，不断的去模拟请求-1然后缓存里面没有 数据库里面也没有，就可能把我们的数据库打崩，造成服务器不可用