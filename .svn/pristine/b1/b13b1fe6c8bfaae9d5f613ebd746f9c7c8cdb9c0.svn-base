# $Header: /export/repo/cvsroot/WebServices/BlobServer/src/jython/testserver.py,v 1.1 2006/05/09 19:31:10 adean Exp $
# jython script to repeatedly request a URL using metadata.  For now I have been hand editing to tweak.
# Usage:
#   jython testserver.py

import urllib
import time
import sys

# Production URL
url="http://blob.manheim.com/blobserver/findByMetadata?vin=1J4FF68S6XL659785&dealer=1317294&appname=DEALEREXCOMMERCE&type=PDFCR"

# Staging URL
url="http://blob.imanheim.com/blobserver/findByMetadata?vin=1J4FF68S6XL659785&dealer=1317294&appname=DEALEREXCOMMERCE&type=PDFCR"

print url

num = 25
print "Trying to access " , num , " times "
print time.asctime()

for i in range(num):
    try:
        connection = urllib.urlopen(url)
        html = connection.read()
        # print connection.info()
        if ( html.find( 'HTTP Status 500' ) >= 0 ) :
            print "Status error 500 for ", url
        # else :
        #    print html
  
        # print "sleep 5"
        # time.sleep(1)

    except:       
        print "caught an Exception"

print time.asctime()


