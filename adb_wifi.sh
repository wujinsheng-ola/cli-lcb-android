#!/bin/bash
_port=":6666"
port=${_port:1-start}
# 将adb先断开
adb disconnect
#取到ip地址. 这里包括了3钟获取IP的方法，ip address应该是通用的方法
ip=`adb shell "ip address | grep inet | grep -v inet6 | grep -v 127"`
len=${#ip}
if [[ $len != 0 ]]; _ip=`echo $ip |cut -f 2 -d ' ' |cut -f 1 -d '/'`${_port}
then ip=`adb shell "ifconfig | grep inet | grep -v inet6 | grep -v 127"`len=${#ip}
# 在魅族的手机上，可能获取不到数据，通过另外的方法来取if [[ $len == 0 ]]; thenip=`adb shell "getprop dhcp.wlan0.ipaddress" | head -n 1`len=${#ip}val=`expr $len - 1`_ip=${ip:0:val}${_port}elseip=`echo ${ip} | cut -f 2 -d ' '`_ip=`echo ${ip} | cut -f 2 -d ':'`${_port}fi
fi
result=`adb tcpip ${port}`
result=`adb connect ${_ip}`
echo $result