import sys
import re
import os
r = re.compile('\\t')
carddict = {}

with open("dictRound4.txt",'r') as FILEIN:
    for line in FILEIN:
        carddict[ r.split(line)[0]] =  r.split(line)[1].strip()
    
    
    
    
    
 

with open("tele_quxian_location4.txt",'r') as FILEIN:
    with open("Quxian_loc4.txt",'w') as FILEOUT:
        for line in FILEIN:
            print>>FILEOUT, carddict[r.split(line)[0]], '\t', r.split(line)[1], '\t', r.split(line)[2], '\t', r.split(line)[3].strip() 

with open("Query_loc4.txt",'w') as FILEOUT:
    with open("tele_query_round4.txt",'r') as FILEIN:
        for line in FILEIN:
            if line != "aaa\n":
                print>>FILEOUT, carddict[r.split(line)[0]], '\t', r.split(line)[1], '\t', r.split(line)[2].strip()
                
