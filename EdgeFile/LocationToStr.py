# -*- coding: utf-8 -*-
"""
Created on Mon Dec 19 15:43:32 2016

@author: Administrator
"""
 
with open("disLoc.txt",'r') as FILEIN:
    with open("disLocMap.txt",'w') as FILEOUT:
        for line in FILEIN:
             print>>FILEOUT, "Loc" + line.strip()
             
             
with open("Query_loc4.txt",'r') as FILEIN:
    with open("Query_Mao4.txt",'w') as FILEOUT:
        for line in FILEIN:
            list = line.split("\t")
            print>>FILEOUT, list[0] + "\t" +  "Loc" +  list[1] + "\t" + list[2].strip()
             
with open("Quxian_loc4.txt",'r') as FILEIN:
    with open("Quxian_Map4.txt",'w') as FILEOUT:
        for line in FILEIN:
            list = line.split("\t")
            print>>FILEOUT, list[0] + "\t" +  "Loc" +  list[1] + "\t" + list[2] + "\t" + list[3].strip()