# -*- coding: utf-8 -*-
"""
Created on Mon Dec 19 15:43:32 2016

@author: Administrator
"""
locset = set()

with open("location.txt",'r') as FILEIN:
    for line in FILEIN:
        locset.add(line.strip())
            
with open("disLoc.txt",'w') as FILEOUT:
    for loc in locset:
        print>>FILEOUT,loc