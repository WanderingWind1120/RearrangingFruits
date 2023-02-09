package org.example;

import java.util.TreeMap;

public class Solution {
    public long minCost(int[] b1, int[] b2) {
        long res = 0, min = Long.MAX_VALUE, replace = 0;
        TreeMap<Integer, Integer> dif = new TreeMap<Integer, Integer>();
        // Sử dụng cái tree map này  để nó lưu các cặp key value theo thứ tự key có giá trị tăng dần
        // để tối ưu cost trao đổi khi loop bên trong cái treemap này
        // nó là một dạng mở rộng cùa HashMap
        for(int b : b1) {
            dif.put(b, dif.getOrDefault(b, 0) + 1);
            min = Math.min(min, b);
        }
        for(int b : b2) {
            dif.put(b, dif.getOrDefault(b, 0) - 1);
            min = Math.min(min, b);
        }

        for(Integer key : dif.keySet()) {
            int v = dif.get(key);
            if(v % 2 > 0) return -1;
            replace += Math.max(0, v / 2); // Với cách việt này thì chỉ tình được số fruit bên basket 1 có giá bằng nhau
            // và số lượng chẵn dương
            // Muốn cái phép tráo đổi 2 túi hoa quả này cho nhau  thành 2 túi giống hệt nhau tức là nó phải có số lượng mỗi
            // túi bằng nhau vì mỗi lần chỉ được tráo đổi sô lượng bằng nhau và tổng số lượng cặp mỗi quả bằng giá nhau
            // ở mỗi basket phải bằng nhau
        }


        for(Integer key : dif.keySet()) { // Loop key lần lượt trong treeset để lấy key có giá trị tăng dần
            // tối ưu cost khi trao đổi hoa quả giữa 2 túi
            long canTake = Math.min(replace, Math.abs(dif.get(key)) / 2); // Tại sao không trừ trực tiếp vào replace
            // với mỗi số lượng bộ key tăng dần nhận được
            // bởi vì như sau có thể khi loop tới một bộ kha mà số lượng lần trao đổi để ngang bằng giá trị cho phép
            // cả directed hay indirected ( chính là giá trị của replace ) nó còn 1 thì lại gặp 1 key mà ở đó nó
            // có 4 value có key giống nhau thì nó sẽ làm âm replace và trao đổi dư 1 lần
            // bởi vì thực tế như sau khi Dùng 1 cặp key ở trong một túi có số lượng chẵn ( đã trừ các key triệt tiêu do
            // trùng vs key bên túi còn lại và cùng số lượng ) thì mỗi lần quy đổi 1 cặp để phân chia mỗi túi một nửa
            // số quả cố giá trị key bằng key đang loop thì nó sẽ ăn mất luôn 1 hay nhiều bộ key có số lượng chẵn tương ứng
            // bên túi còn lại
            // tức số lượng phép hoán đổi không được phép vượt quá replace nếu vượt quá chắc chắn nó sẽ sai
            res += canTake * Math.min(key, 2 * min);
            // đây chính là dòng code tìm ra cost nhỏ hơn giữa việc trao đổi trực tiếp và trao đổi gián tiếp
            replace -= canTake;
        }

        return res;
    }
}
