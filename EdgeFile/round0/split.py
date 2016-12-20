import sys
import re
import os
r1 = re.compile('\\s+')
r = re.compile('\\001')
carddict = {}
datedict = {}

with open("DateDicts.txt",'r') as FILEIN:
    for line in FILEIN:
        datedict[r1.split(line)[1]] =  r1.split(line)[0].strip()
        
 
i=0
    
with open("trans_0.txt",'r') as FILEIN:
    for line in FILEIN:
        if not carddict.has_key(r.split(line)[0]):
            carddict[r.split(line)[0]] = i
            i =i+1 
        if not carddict.has_key(r.split(line)[1]):
            carddict[r.split(line)[2]] = i
            i =i+1 
            

with open("quxian_0.txt",'r') as FILEIN:
    for line in FILEIN:
        if not carddict.has_key(r.split(line)[0]):
            carddict[r.split(line)[0]] = i
            i =i+1 

with open("query_0.txt",'r') as FILEIN:
    for line in FILEIN:
        if not carddict.has_key(r.split(line)[0]):
            carddict[r.split(line)[0]] = i
            i =i+1 
    
with open("CardDict.txt",'w') as FILEOUT: 
    for card in carddict.keys():
        print>>FILEOUT, carddict[card], '\t', card
        

with open("trans_0.txt",'r') as FILEIN:
    with open("MappedTrans0.txt",'w') as FILEOUT:
        for line in FILEIN:
            print>>FILEOUT, carddict[r.split(line)[0]], '\t',datedict[r.split(line)[1]], '\t',carddict[r.split(line)[2]], '\t',  r.split(line)[3], '\t',  r.split(line)[4].strip()
            print>>FILEOUT, carddict[r.split(line)[2]], '\t',datedict[r.split(line)[1]], '\t',carddict[r.split(line)[0]], '\t',  r.split(line)[3], '\t',  r.split(line)[4].strip()
        

with open("quxian_0.txt",'r') as FILEIN:
    with open("MappedQuxian0.txt",'w') as FILEOUT:
        for line in FILEIN:
            print>>FILEOUT, carddict[r.split(line)[0]], '\t', datedict[r.split(line)[1]], '\t', r.split(line)[2], '\t', r.split(line)[3].strip() 

with open("query_0.txt",'r') as FILEIN:
    with open("MappedQuery_0.txt",'w') as FILEOUT:
        for line in FILEIN:
            print>>FILEOUT, carddict[r.split(line)[0]], '\t', datedict[r.split(line)[1]], '\t', r.split(line)[2], '\t', r.split(line)[3].strip() 
        
