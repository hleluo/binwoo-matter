## 算法与数据结构实战
算法列表 

| 序号 | 问题类型 | 类名 | 问题描述 |
| :--: | :--:    | :---- | :---- |
| 1    | 啤酒兑换 | BeerExchange.java | 瓶酒2元/瓶，每2个空酒瓶可换一瓶啤酒，每4个啤酒盖可换1瓶啤酒，问：10元钱能买多少瓶啤酒？|
| 2    | 位排序   | BitSort.java |问题输入:一个最多包含n个正整数的文件，这个文件中的正整数最大不能超过n，n=10000000，这个文件中的数值不能重复。<br/>问题输出：对上述文件中的正整数按从小到大顺序进行排序。<br/>限制条件：最多1M的内存空间是可用的（磁盘空间不限），运行时间要几分钟。|
| 3    | 开关问题 | LampSwitch.java | 有n盏灯，编号1～n（0<n<10000）,第1个人把所有灯打开，第2个人按下所有编号为2的倍数的开关（这些灯将被关掉），第3个人按下所有编号为3的倍数的开关（其中关掉的灯将被打开，开着的灯将被关闭），依次类推。<br/>一共有k个人，输入灯数和人数，输出开着的灯的编号。<br/>比如输入：10 2输出最后亮灯的编号：1,3,5,7,9。|
| 4    | TopK问题 | TopK.java | 现有一个文本文件，文件中存在大量数据，数据条数达到亿级别的量，需要按照某一列进行排序。样例数据如下：<br/>buyer1,item1,1381234****,123,2020-01-01 01:00:00,1...<br/>buyer2,item2,1381234****,123,2020-01-01 01:00:00,2...<br/>buyer2,item2,1381234****,123,2020-01-01 01:00:00,3...<br/>要求：1：可以按照其中某几列进行排序，按照手机号码或者用户ID进行排序。2：内存使用限制不超过64M，高效快速完成排序，并输出前100万数据。<br/>