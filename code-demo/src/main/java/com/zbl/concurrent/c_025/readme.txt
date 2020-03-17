总结：
1：对于map/set的选择使用
HashMap
TreeMap
LinkedHashMap

Hashtable
Collections.sychronizedXXX

ConcurrentHashMap
ConcurrentSkipListMap

2：队列
ArrayList 不同步
LinkedList 不同步
Collections.synchronizedXXX 同步
CopyOnWriteList 同步 读快写慢
并发量高的情况
Queue
	CocurrentLinkedQueue //concurrentArrayQueue 高并发队列
	BlockingQueue  阻塞队列
		LinkedBQ   无界队列
		ArrayBQ    有界队列
		TransferQueue 直接交给消费者
		SynchronusQueue 特殊的transferQueue
	DelayQueue执行定时任务
		
	