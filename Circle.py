# This code shows how the eBot can be asked to turn in a circle with a specific radius

# Copyright (c) 2014, Erik Wilhelm
# All rights reserved.

# Redistribution and use in source and binary forms, with or without
# modification, are permitted provided that the following conditions are met:
# 1. Redistributions of source code must retain the above copyright
   # notice, this list of conditions and the following disclaimer.
# 2. Redistributions in binary form must reproduce the above copyright
   # notice, this list of conditions and the following disclaimer in the
   # documentation and/or other materials provided with the distribution.
# 3. All advertising materials mentioning features or use of this software
   # must display the following acknowledgement:
   # This product includes software developed by the <organization>.
# 4. Neither the name of the SUTD nor Edgebotix nor the
   # names of its contributors may be used to endorse or promote products
   # derived from this software without specific prior written permission.

# THIS SOFTWARE IS PROVIDED BY ERIK WILHELM ''AS IS'' AND ANY
# EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
# WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
# DISCLAIMED. IN NO EVENT SHALL ERIK WILHELM BE LIABLE FOR ANY
# DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
# (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
# LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
# ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
# (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
# SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

from __future__ import division # to handle floating poitn division
import sys
sys.path.insert(0, 'C:\Users\Erik Wilhelm\Documents\GitHub\eBot-API') #Insert your directory for the eBot-API here


from eBot import *

#Create new instance of eBot - connects to first eBot the computer is connected to
myEBot = eBot()
myEBot.connect()
myEBot.halt()

# wait before entering loop
sleep(1)
ArcString = input("Enter an integer number of iterations to move in a cicle (200 works well): ")
t_run=int(ArcString) #number of loop iterations (not seconds) to run for
RadString = input("Enter an integer number to describe the radius of curvature (ratio between wheel speeds)(2 works well): ")
rad=int(RadString)
DirStr= input("Enter 'Clk' to inscribe a clockwise circle, 'CClk' for counterclockwise: ")

if DirStr=='CClk':
    v1=-1*rad/10
    v2=1*(1/(rad/10))
    if v1<-1:
        v1=-1
elif DirStr=='Clk':
    v1=1*(1/(rad/10))
    v2=-1*rad/10
    if v2<-1:
        v2=-1
else: #Default to clockwise
    v1=-1*rad/10
    v2=1*(1/(rad/10))
    if v1<-1:
        v1=-1
for i in range(1, t_run, 1):
    myEBot.wheels(v1*1, v2*1) #set the robot in motion, full speed ahead!

myEBot.halt()
sleep(4)

myEBot.close()
