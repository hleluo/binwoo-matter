package com.binwoo.matter;

/**
 * <p>有n盏灯，编号1～n（0<n<10000）</p>
 * <p>第1个人把所有灯打开，第2个人按下所有编号为2的倍数的开关（这些灯将被关掉），第3个人按下所有编号为3的倍数的开关（其中关掉的灯将被打开，开着的灯将被关闭），依次类推。</p>
 * <p>一共有k个人，输入灯数和人数，输出开着的灯的编号。</p>
 * <p>比如输入：10 2输出最后亮灯的编号：1,3,5,7,9</p>
 *
 * @author hleluo
 * @date 2020/7/20 16:17
 */
public class LampSwitch {

  public static void main(String[] args) {
    print(10, 2);
  }

  private static void print(int n, int k) {
    // 初始化灯数组
    int[] lamps = new int[n];
    // 打开所有灯（数组元素大于零代表开灯，等于0代表关灯）
    for (int i = 0; i < n; i++) {
      lamps[i] = i;
    }
    // 遍历后面的人操作
    for (int i = 2; i <= k; i++) {
      for (int j = i; j < n; j++) {
        if (j % i == 0) {
          // 如果开，则置位关，如果关，则置位开
          lamps[j] = lamps[j] == 0 ? i : 0;
        }
      }
    }
    //输出
    for (int i = 0; i < lamps.length; i++) {
      if (lamps[i] > 0) {
        System.out.println(String.valueOf(i));
      }
    }
  }

}
