/**
 * @author wmy
 * @date 2021/6/11 16:46
 */

/*
描述
编写一个函数来验证输入的字符串是否是有效的 IPv4 或 IPv6 地址

IPv4 地址由十进制数和点来表示，每个地址包含4个十进制数，其范围为 0 - 255， 用(".")分割。比如，172.16.254.1；
同时，IPv4 地址内的数不会以 0 开头。比如，地址 172.16.254.01 是不合法的。

IPv6 地址由8组16进制的数字来表示，每组表示 16 比特。这些组数字通过 (":")分割。比如,  2001:0db8:85a3:0000:0000:8a2e:0370:7334 是一个有效的地址。而且，我们可以加入一些以 0 开头的数字，字母可以使用大写，也可以是小写。所以， 2001:db8:85a3:0:0:8A2E:0370:7334 也是一个有效的 IPv6 address地址 (即，忽略 0 开头，忽略大小写)。

然而，我们不能因为某个组的值为 0，而使用一个空的组，以至于出现 (::) 的情况。 比如， 2001:0db8:85a3::8A2E:0370:7334 是无效的 IPv6 地址。
同时，在 IPv6 地址中，多余的 0 也是不被允许的。比如， 02001:0db8:85a3:0000:0000:8a2e:0370:7334 是无效的。

说明: 你可以认为给定的字符串里没有空格或者其他特殊字符。
示例1
输入：
"172.16.254.1"
复制
返回值：
"IPv4"
复制
说明：
这是一个有效的 IPv4 地址, 所以返回 "IPv4"
示例2
输入：
"2001:0db8:85a3:0:0:8A2E:0370:7334"
复制
返回值：
"IPv6"
复制
说明：
这是一个有效的 IPv6 地址, 所以返回 "IPv6"
示例3
输入：
"256.256.256.256"
复制
返回值：
"Neither"
复制
说明：
这个地址既不是 IPv4 也不是 IPv6 地址
备注：
ip地址的类型，可能为
IPv4,   IPv6,   Neither
 */
public class Q_VerifyIPAddress {
    /**
     * 验证IP地址
     *
     * @param IP string字符串 一个IP地址字符串
     * @return string字符串
     */
    public String solve(String IP) {
        // write code here
        if (IP.contains(".")) {
            return judgeIPv4(IP) ? "IPv4" : "Neither";
        } else if (IP.contains(":")) {
            return judgeIPv6(IP) ? "IPv6" : "Neither";
        } else {
            return "Neither";
        }
    }

    private boolean judgeIPv4(String ip) {
        String[] ipArrays = ip.split("\\.");
        if (ipArrays.length != 4) {
            return false;
        }
        for (String ipArray : ipArrays) {
            if (ipArray.length() > 4) {
                return false;
            }
            if (ipArray.length() >= 1 && ipArray.charAt(0) == '0') {
                return false;
            }
            if (Integer.parseInt(ipArray) < 0 || Integer.parseInt(ipArray) > 255) {
                return false;
            }
        }
        return true;
    }

    private boolean judgeIPv6(String ip) {
        String[] ipArrays = ip.split(":");
        if (ipArrays.length != 8) {
            return false;
        }
        for (String ipArray : ipArrays) {
            if (ipArray.length() > 1 && Integer.parseInt(ipArray, 16) == 0) {
                return false;
            }
            for (char c : ipArray.toCharArray()) {
                if (c - '0' > 69) {
                    return false;
                }
            }
        }
        return true;
    }


    public static void main(String[] args) {
        Q_VerifyIPAddress app = new Q_VerifyIPAddress();
        String ip = "2001:0db8:85a3:0:0:8A2E:0370:7334";
        String res = app.solve(ip);
        System.out.println(res);
    }
}
