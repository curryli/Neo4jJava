import sys
import re
import os
r = re.compile('\\t')
carddict = {}

with open("dictRound4.txt",'r') as FILEIN:
    for line in FILEIN:
        carddict[ r.split(line)[0]] =  r.split(line)[1].strip()
    
    
    
    
    

with open("staticInOut0.txt",'r') as FILEIN:
    with open("trans.txt",'w') as FILEOUT:
        for line in FILEIN:
            print>>FILEOUT, carddict[r.split(line)[0]], '\t', carddict[r.split(line)[1]], '\t', r.split(line)[2].strip()
        

#with open("BlackQuxian.txt",'r') as FILEIN:
#    with open("quxian.txt",'w') as FILEOUT:
#        for line in FILEIN:
#            print>>FILEOUT, carddict[r.split(line)[0]], '\t', r.split(line)[1], '\t', r.split(line)[2].strip()

#with open("quxian_loc",'r') as FILEIN:
#    with open("quxian_loc.txt",'w') as FILEOUT:
#        for line in FILEIN:
#            print>>FILEOUT, carddict[r.split(line)[0]], '\t', r.split(line)[3].strip(), '\t', r.split(line)[1], '\t', r.split(line)[2].strip() 