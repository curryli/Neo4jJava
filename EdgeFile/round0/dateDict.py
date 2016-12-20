import sys
import re
import os
import datetime



def createCardDict(filein, fileout, CardDict):
        i = 0
        with open(filein,'r') as FILEIN:
                with open(fileout,'w') as FILEOUT:
                        for card in FILEIN.readlines():
                                print>>FILEOUT, card.strip(),"\t",i
                                CardDict[card.strip()] = i
                                i=i+1
                FILEOUT.close()                               
        FILEIN.close()


def createDateDict(start, end, fileout, DateDict):
    start_date = datetime.date(*start)
    end_date = datetime.date(*end)


    result = []
    curr_date = start_date
    while curr_date != end_date:
        result.append("%04d%02d%02d" % (curr_date.year, curr_date.month, curr_date.day))
        curr_date += datetime.timedelta(1)
    result.append("%04d%02d%02d" % (curr_date.year, curr_date.month, curr_date.day))

    i = 0
    with open(fileout,'w') as FILEOUT:
        for date in result:
            print>>FILEOUT, "d" + str(i), "\t",date
            DateDict[date] = i
            i=i+1

    FILEOUT.close()


 

if __name__ == '__main__':
 
        CardDict = {}
        DateDict = {}
        
       # createCardDict("AllCards.txt","AllCardDicts.txt",CardDict)
        createDateDict((2016, 7, 1), (2016, 11, 30),"DateDicts.txt",DateDict)
       
 
        
