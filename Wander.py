#this code puts the eBot in wander mode, where it tries to follow a wall at a specific distance using the 'left hand rule' for maze solving and a simple P controller

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


import sys
sys.path.insert(0, 'C:\Users\Erik Wilhelm\Documents\GitHub\eBot-API') #Insert your directory for the eBot-API here

from eBot import *
import math

myEBot = eBot()

myEBot.connect()

myEBot.wheels(1, 1) #set the robot in motion, full speed ahead!
myvalue = [0, 0, 0, 0, 0, 0]

t_run=1000 #number of loop iterations (not seconds) to run for

DistStr = input("Enter a distance which the robot should maintain from the wall (0.3m is nice): ")
dist=float(DistStr) #distance to maintain from the wall

kp=0.1 #proportional gain

for i in range(1, t_run, 1):
    sonars = myEBot.robot_uS()
	
    error=dist-sonars[0] #distance to left most sonar
    
    if math.fabs(error)>0.05 # to avoid too much control
	if error<0: #robot is too far from wall, shift left slightly
	    control1=1+kp*error #slow down left track (error is negative)
	    control2=1
        else: #robot is too close to wall, shift right slightly
	    control1=1
	    control2=1-kp*error #slow down right track
	    
    myEBot.wheels(control1, control2)
    else #do nothing below an error threshhold
	myEBot.wheels(1,1)

    print sonars

myEBot.halt()
sleep(4)

myEBot.close()


