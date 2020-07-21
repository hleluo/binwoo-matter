package com.binwoo.matter;

/**
 * 问题描述：瓶酒2元/瓶，每2个空酒瓶可换一瓶啤酒，每4个啤酒盖可换1瓶啤酒，问：10元钱能买多少瓶啤酒？
 *
 * @author hleluo
 * @date 2020/7/20 16:13
 */
public class BeerExchange {

  public static void main(String[] args) {
    System.out.println(buy(10));
  }

  private static int buy(int money) {
    // 总啤酒数、瓶盖数、酒瓶数
    int beer, cap, bottle;
    // 初始计算
    beer = cap = bottle = money / 2;
    while (cap >= 4 || bottle >= 2) {
      // 可兑换的啤酒数
      int exchange = cap / 4 + bottle / 2;
      beer += exchange;
      // 剩余瓶盖数
      cap = exchange + cap % 4;
      // 剩余酒瓶数
      bottle = exchange + bottle % 2;
    }
    return beer;
  }

}
