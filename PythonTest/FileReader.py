# -*- coding: utf-8 -*-
import codecs
import re
from filecmp import cmp

print("hello")
file = open("E:/phb-app.2017-09-11.log", "r", encoding='UTF-8')
dict = {}
output = open('E:/data.txt', 'w')
while 1:
    line = file.readline()
    matchObj = re.match(".*WebServiceInterceptor#handleMessage,get request from method-(.+)", line, re.M)
    if matchObj:
        # print ("matchObj.group(1) : ", matchObj.group(1))
        if matchObj.group(1) in dict:
            dict[matchObj.group(1)] = dict[matchObj.group(1)] + 1
        else:
            dict[matchObj.group(1)] = 1
    # [ v for v in sorted(dict.values())]
    # b=sorted(dict.items(), lambda asd:asd[1])

    if not line:
        break
pass  # do something
for item,value in dict.items():
    print ('%s\t%s' %(item, value))
    output.writelines('%s\t%s' %(item, value));

print(len(dict))
