package com.binwoo.matter;

import java.util.Random;

/**
 * 位排序.
 *
 * @author hleluo
 * @date 2020/7/20 16:40
 */
public class BitSort {

  private static byte[] values;

  public static void main(String[] args) {
    /*问题输入:一个最多包含n个正整数的文件，这个文件中的正整数最大不能超过n，n=10000000，这个文件中的数值不能重复。
    问题输出：对上述文件中的正整数按从小到大顺序进行排序。
    限制条件：最多1M的内存空间是可用的（磁盘空间不限），运行时间要几分钟。*/

    /*解决分析：用普通的排序方法很难实现（内存太小），所以采用用bit位代表整数的方法，
    即一个bit位代表一个整数，每个bit所代表的整数由它在bit串中的位置决定
  （第一个bit代表0000001，第二个bit代表0000002，第三个bit代表0000003，......）。
    时间负责度：O(n),空间负责读：O(n).
    1M=1024kB=1024×1024B=1024×1024×8bit=8388608bit，即1M最多可以代表8388608个整数，
    而n=10000000，即最少需要10000000个bit位，
    所以可以分两次来完成排序工作（先完成00000001-5000000范围内的排序，然后再完成5000001-9999999范围内的排序）。*/
    int n = 50;
    init(n);
    //生成随机数
    int count = 0;
    Random random = new Random();
    while (count < n) {
      int num = random.nextInt(n);
      System.out.print(num + ",");
      setBit(num);
      count++;
    }
    System.out.println();
    // 输出
    for (int i = 0; i < values.length; i++) {
      int value = i << 3;
      for (int j = 0; j < 8; j++) {
        if (((values[i] >> (7 - j)) & 1) == 1) {
          System.out.print((value + j) + ",");
        }
      }
    }
  }

  private static void init(int n) {
    int length = n % 8 == 0 ? n >> 3 : (n >> 3) + 1;
    values = new byte[length];
  }

  private static void setBit(int value) {
    int byteIndex = value >> 3;
    int bitIndex = value % 8;
    values[byteIndex] |= 1 << (7 - bitIndex);
  }

}
